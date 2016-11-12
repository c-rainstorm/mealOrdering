package com.chenswe.ordermanager.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.chenswe.ordermanager.dao.OrderDAO;
import com.chenswe.ordermanager.dao.impl.OrderDAOImpl;
import com.chenswe.ordermanager.entity.Order;
import com.chenswe.ordermanager.entity.OrderBrief;
import com.chenswe.ordermanager.service.OrderService;
import com.chenswe.usermanager.entity.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderServiceImpl implements OrderService {

	OrderDAO dao = new OrderDAOImpl();
	
	@Override
	public Order getOrderByID(String orderID) {
		return dao.getOrderByID(orderID);
	}

	@Override
	public boolean finishOrder(Order order) {
		return dao.finishOrder(order);
	}

	@Override
	public ArrayList<OrderBrief> getUserOrderBriefs(User user, int page) {
		return dao.getUserOrderBriefs(user, page);
	}

	@Override
	public ArrayList<OrderBrief> getSellerOrderBriefs(String registrationID, String pageContent, int page) {
		return dao.getSellerOrderBriefs(registrationID, pageContent, page);
	}

	@Override
	public boolean updateOrderStatus(String curStatus, String orderID) {
		return dao.updateOrderStatus(curStatus, orderID);
	}

	@Override
	public boolean submitOrder(HttpServletRequest request) {
		boolean success = true;
		String registrationID = request.getParameter("registrationID");
		String temp = request.getParameter("receiver");
		String receiverPhone = temp.split("\u2002")[2];
		
		Order order = new Order();
		success = success && dao.inscreaseSellerSoldNum(registrationID);

		order.setOrderID(registrationID + dao.getSellerSoldNum(registrationID));
		
		order.setPayer(dao.getUserPhone(request.getParameter("username")));
		order.setReceiverPhone(receiverPhone);
		order.setOrderTime(new Timestamp(new Date().getTime()));	
		order.setPayway(Integer.parseInt(request.getParameter("payway")));
		order.setStatus(0);
		order.setComment(request.getParameter("commit"));
		order.setDeliveryFee(dao.getSellerDeliveryFee(registrationID));
		
		String JsonShoppingCars = (String) request.getSession().getAttribute("shoppingCars");
		
		float total = 0;
		JsonParser parser = new JsonParser();
		if(JsonShoppingCars != null){
			JsonArray shoppingCars = parser.parse(JsonShoppingCars).getAsJsonArray();
			
			for(int i = 0; i < shoppingCars.size(); ++i){
				JsonArray shoppingCar = shoppingCars.get(i).getAsJsonArray();
				String firstFoodID = shoppingCar.get(0).getAsJsonObject().get("foodID").getAsString();
				if(firstFoodID.indexOf(registrationID) == 0){
					for(int j = 0; j < shoppingCar.size(); ++j){
						JsonObject food =  shoppingCar.get(j).getAsJsonObject();
						String foodID = food.get("foodID").getAsString();
						String foodCount = food.get("foodCount").getAsString();
						dao.addFoodInOrder(order.getOrderID(),foodID,foodCount);
						total += Integer.parseInt(foodCount) * dao.getFoodPrice(foodID);
					}
					break;
				}
			}
		}
		order.setTotal(total);
		
		success = success && dao.addOrder(order);

		return success;
	}

}
