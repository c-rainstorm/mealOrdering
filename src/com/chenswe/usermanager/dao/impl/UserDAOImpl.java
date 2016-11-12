/**
 * 
 */
package com.chenswe.usermanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chenswe.usermanager.dao.UserDAO;
import com.chenswe.usermanager.entity.Receiver;
import com.chenswe.usermanager.entity.User;
import com.chenswe.util.DBUtil;

/**
 * @author chen_swe
 *
 */
public class UserDAOImpl implements UserDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public boolean addUser(User user) {
		boolean success = true;

		String sql = "insert into users values(?,?,password(?));";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPhone());
			preparedStatement.setString(3, user.getPassword());

			int count = preparedStatement.executeUpdate();
			if (count == 0) {
				System.out.println("用户插入失败！");
				success = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return success;
	}

	@Override
	public boolean checkLoginInfo(User user) {
		boolean flag = false;
		String sql = "select * from users where phone = ? and password = password(?);";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getPhone());
			preparedStatement.setString(2, user.getPassword());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return flag;
	}

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
	public ArrayList<Receiver> getReceiverInfo(User user, int pageNum) {
		
		
		
		ArrayList<Receiver> receivers = new ArrayList<>();
		String sql = 	"SELECT * FROM receiver_addr " +
						"INNER JOIN users " + 
						"ON users.phone = receiver_addr.owner " +
						"WHERE users.username = ? " + 
						"ORDER BY receiver_addr.default DESC ";
		if(pageNum != -1){
			sql = sql + "LIMIT ?,2;";
		}else{
			sql = sql + ";";
		}
		
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getUsername());
			
			if(pageNum != -1)
				preparedStatement.setInt(2, (pageNum - 1) * 2);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Receiver receiver = new Receiver();
				receiver.setOwner(resultSet.getString("owner"));
				receiver.setReceiverAddr(resultSet.getString("receiverAddr"));
				receiver.setReceiverName(resultSet.getString("receiverName"));
				receiver.setReceiverPhone(resultSet.getString("receiverPhone"));
				receiver.setIsdefault(resultSet.getInt("default"));
				
				receivers.add(receiver);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return receivers;
	}

	@Override
	public User getUesrInfo(User user) {
		String sql = "select phone from users where username = ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getUsername());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user.setPhone(resultSet.getString("phone"));
				;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return user;
	}

	@Override
	public String getUsername(User user) {
		String username = null;
		String sql = "select username from users where phone = ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getPhone());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				username = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return username;
	}

	@Override
	public boolean phoneIsExist(User user) {
		boolean isExist = false;

		String sql = "select * from users where phone = ?;";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getPhone());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				isExist = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return isExist;
	}

	@Override
	public boolean setDefault(Receiver receiver) {
		boolean flag = false;
		String sql1 = "UPDATE receiver_addr " +
					"set receiver_addr.default = 0 " +
					"WHERE receiver_addr.owner = ? and receiver_addr.default = 1; ";
		String sql2 = "UPDATE receiver_addr " +
					"set receiver_addr.default = 1 " +
					"WHERE receiver_addr.owner = ? and receiver_addr.receiverPhone = ?;";

		try {
			connection = DBUtil.getConnection();
			
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, receiver.getOwner());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, receiver.getOwner());
			preparedStatement.setString(2, receiver.getReceiverPhone());

			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return flag;
	}

	@Override
	public boolean updateReceiverInfo(Receiver receiver) {
		boolean flag = false;
		String sql = "UPDATE receiver_addr set receiverName = ?, receiverAddr = ? " +
					"WHERE owner = ? and receiverPhone = ?; ";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, receiver.getReceiverName());
			preparedStatement.setString(2, receiver.getReceiverAddr());
			preparedStatement.setString(3, receiver.getOwner());
			preparedStatement.setString(4, receiver.getReceiverPhone());
			
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
	public boolean updateUserPassword(User user) {
		boolean flag = false;
		String sql = "update users set password = password(?) where username = ?";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getUsername());

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
	public boolean usernameIsExist(User user) {
		boolean isExist = false;

		String sql = "select * from users where username = ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getUsername());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				isExist = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return isExist;
	}

	@Override
	public boolean removeReceiver(Receiver receiver) {
		boolean flag = false;
		String sql = "DELETE FROM receiver_addr WHERE receiver_addr.owner = ? AND receiver_addr.receiverPhone = ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, receiver.getOwner());
			preparedStatement.setString(2, receiver.getReceiverPhone());
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
	public boolean checkReceiverPhone(User user, Receiver receiver) {
		boolean flag = false;
		String sql = "SELECT receiverName FROM receiver_addr " +
					 "INNER JOIN users " +
					 "ON users.phone = receiver_addr.owner " +
					 "where users.username = ? and receiver_addr.receiverPhone= ?;";

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, receiver.getReceiverPhone());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return flag;
	}

	@Override
	public boolean addNewReceiver(Receiver receiver) {
		boolean success = false;
		String sql = "INSERT INTO receiver_addr "
				+ "(receiver_addr.owner, receiverName, receiverPhone, receiverAddr, receiver_addr.default) "
				+ "VALUES (?, ?, ?, ?, ?);";
		
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, receiver.getOwner());
			preparedStatement.setString(2, receiver.getReceiverName());
			preparedStatement.setString(3, receiver.getReceiverPhone());
			preparedStatement.setString(4, receiver.getReceiverAddr());
			preparedStatement.setInt(5, receiver.getIsdefault());

			int count = preparedStatement.executeUpdate();
			if (count > 0) {
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
