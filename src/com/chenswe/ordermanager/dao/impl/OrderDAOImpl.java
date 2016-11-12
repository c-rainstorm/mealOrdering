package com.chenswe.ordermanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.chenswe.ordermanager.dao.OrderDAO;
import com.chenswe.ordermanager.entity.FoodInOrder;
import com.chenswe.ordermanager.entity.Order;
import com.chenswe.ordermanager.entity.OrderBrief;
import com.chenswe.sellermanager.entity.Food;
import com.chenswe.usermanager.entity.User;
import com.chenswe.util.DBUtil;

public class OrderDAOImpl implements OrderDAO {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * close resultSet, preparedStatement, connection
	 */
	private void closeAll() {
		try {

			if (resultSet != null)
				resultSet.close();

			if (preparedStatement != null)
				preparedStatement.close();

			if (connection != null)
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order getOrderByID(String orderID) {
		Order order = new Order();
		order.setOrderID(orderID);
		order.setFoods(getFoodsInOrderByID(order));
		
		try {
			String sql = "SELECT e.orderID, e.comment, e.deliveryFee, " + "e.finishTime, e.orderTime,e.payer_phone, "
					+ "e.payway,e.receiver_phone,e.status, e.total, " + "r.receiverAddr,r.receiverName "
					+ "from extra_in_order as e " + "INNER JOIN receiver_addr as r "
					+ "ON r.owner = e.payer_phone AND r.receiverPhone = e.receiver_phone " + "WHERE e.orderID = ?;";

			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, order.getOrderID());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order.setPayer(resultSet.getString("payer_phone"));
				order.setReceiverPhone(resultSet.getString("receiver_phone"));
				order.setReceiverName(resultSet.getString("receiverName"));
				order.setReceiverAddr(resultSet.getString("receiverAddr"));
				order.setOrderTime(resultSet.getTimestamp("orderTime"));
				order.setFinishTime(resultSet.getTimestamp("finishTime"));
				order.setPayway(resultSet.getInt("payway"));
				order.setStatus(resultSet.getInt("status"));
				order.setComment(resultSet.getString("comment"));
				order.setDeliveryFee(resultSet.getInt("deliveryFee"));
				order.setTotal(resultSet.getInt("total"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		
		
		return order;
	}

	private ArrayList<FoodInOrder> getFoodsInOrderByID(Order order) {
		ArrayList<FoodInOrder> foods = new ArrayList<>(20);

		String sql = "SELECT foods.foodID, foods.imageAddr, " + "foods.name, foods.price, " + "foods_in_order.soldNum "
				+ "FROM foods " + "INNER JOIN foods_in_order " + "ON foods.foodID = foods_in_order.foodID "
				+ "WHERE foods_in_order.orderID = ?; ";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getOrderID());

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FoodInOrder foodInOrder = new FoodInOrder();

				Food food = new Food();
				food.setFoodID(resultSet.getString("foodID"));
				food.setImageAddr(resultSet.getString("imageAddr"));
				food.setName(resultSet.getString("name"));
				food.setPrice(resultSet.getFloat("price"));
				foodInOrder.setFood(food);

				foodInOrder.setSoldNum(resultSet.getInt("soldNum"));

				foods.add(foodInOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return foods;
	}

	@Override
	public boolean finishOrder(Order order) {
		boolean flag = false;
		String sql = "UPDATE extra_in_order set status = 4 , finishTime = ? WHERE orderID = ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setTimestamp(1, (new Timestamp((new Date()).getTime())));
			preparedStatement.setString(2, order.getOrderID());

			int count = preparedStatement.executeUpdate();
			if (count > 0)
				flag = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return flag;
	}

	@Override
	public ArrayList<OrderBrief> getUserOrderBriefs(User user, int page) {
		ArrayList<OrderBrief> briefs = new ArrayList<>(50);

		String sql = "SELECT orderID, status, orderTime " + "from extra_in_order " + "INNER JOIN users "
				+ "ON users.phone = extra_in_order.payer_phone " + "WHERE users.username = ?"
				+ "ORDER BY status, orderTime DESC " + "LIMIT ?, 10;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setInt(2, (page - 1) * 10);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderBrief brief = new OrderBrief();
				brief.setOrderID(resultSet.getString("orderID"));
				brief.setStatus(resultSet.getInt("status"));

				briefs.add(brief);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return briefs;
	}

	@Override
	public ArrayList<OrderBrief> getSellerOrderBriefs(String registrationID, String pageContent, int page) {
		ArrayList<OrderBrief> briefs = new ArrayList<>(50);
		
		String status = "%";
		if(pageContent.equals("notAccept")){
			status = "0";
		}else if(pageContent.equals("notSend")){
			status = "1";
		}else if(pageContent.equals("notArrive")){
			status = "2";
		}else if(pageContent.equals("unfinished")){
			status = "3";
		}else if(pageContent.equals("finished")){
			status = "4";
		}
		
		String sql = "SELECT orderID, extra_in_order.status "
				+ "FROM extra_in_order "
				+ "WHERE extra_in_order.status LIKE ? AND orderID LIKE ? "
				+ "ORDER BY extra_in_order.status, orderTime DESC "
				+ "LIMIT ?,10;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, status);
			preparedStatement.setString(2, registrationID + "%");
			preparedStatement.setInt(3, (page - 1) * 10);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderBrief brief = new OrderBrief();
				brief.setOrderID(resultSet.getString("orderID"));
				brief.setStatus(resultSet.getInt("status"));

				briefs.add(brief);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return briefs;

	}

	@Override
	public boolean updateOrderStatus(String curStatus, String orderID) {
		boolean flag = false;
		if(curStatus.equals("-1")){
			curStatus = "%";
		}
		
		String sql = "UPDATE extra_in_order set status = status + 1 WHERE orderID like ? and status like ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, orderID);
			preparedStatement.setString(2, curStatus);

			int count = preparedStatement.executeUpdate();
			if (count > 0)
				flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return flag;
	}

	@Override
	public boolean inscreaseSellerSoldNum(String registrationID) {
		boolean success = false;
		String sql = "UPDATE sellers set sold_num = sold_num + 1 WHERE registration_id = ? ;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, registrationID);

			int count = preparedStatement.executeUpdate();
			if(count > 0)
				success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return success;
	}

	@Override
	public String getSellerSoldNum(String registrationID) {
		int soldNum = 0;
		String sql = "select sold_num from sellers WHERE registration_id = ? ;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, registrationID);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				soldNum = resultSet.getInt("sold_num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return soldNum + "";
	}

	@Override
	public String getUserPhone(String username) {
		String phone = "";
		String sql = "select phone from users where username = ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				phone = resultSet.getString("phone");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return phone;
	}

	@Override
	public int getSellerDeliveryFee(String registrationID) {
		int deliveryFee = 0;
		String sql = "select delivery_fee from sellers WHERE registration_id = ? ;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, registrationID);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				deliveryFee = resultSet.getInt("delivery_fee");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return deliveryFee;
	}

	@Override
	public void addFoodInOrder(String orderID, String foodID, String foodCount) {
		updateFoodSoldNum(foodID,foodCount);
		
		String sql = "insert into foods_in_order values(?,?,?);";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, orderID);
			preparedStatement.setString(2, foodID);
			preparedStatement.setString(3, foodCount);

			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	private void updateFoodSoldNum(String foodID, String foodCount) {
	
		String sql = "UPDATE foods set soldNum = soldNum + ? WHERE foodID = ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, Integer.parseInt(foodCount));
			preparedStatement.setString(2, foodID);

			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	@Override
	public float getFoodPrice(String foodID) {
		float price = 0;
		String sql = "select price from foods where foodID = ?;";
		
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, foodID);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				price = resultSet.getFloat("price");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return price;
	}

	@Override
	public boolean addOrder(Order order) {
		boolean success = false;
		
		String sql = "INSERT INTO extra_in_order VALUES(?,?,?,?,'2099-12-31 23:59:59',?,?,?,?,?);";
		try {
			
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, order.getOrderID());
			preparedStatement.setString(2, order.getPayer());
			preparedStatement.setString(3, order.getReceiverPhone());
			preparedStatement.setTimestamp(4, order.getOrderTime());
			preparedStatement.setInt(5, order.getPayway());
			preparedStatement.setInt(6, order.getStatus());
			preparedStatement.setString(7, order.getComment());
			preparedStatement.setInt(8, order.getDeliveryFee());
			preparedStatement.setFloat(9, order.getTotal());			
			
			int count = preparedStatement.executeUpdate();
			if(count > 0){
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return success;
	}

}
