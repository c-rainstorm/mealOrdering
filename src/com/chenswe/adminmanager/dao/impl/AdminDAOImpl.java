package com.chenswe.adminmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chenswe.adminmanager.dao.AdminDAO;
import com.chenswe.adminmanager.entity.Admin;
import com.chenswe.util.DBUtil;

public class AdminDAOImpl implements AdminDAO {

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
	public boolean checkLoginInfo(Admin admin) {
		boolean flag = false;
		String sql = "select username from admins where username = ? and password = password(?);";
		
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement.setString(2, admin.getPassword());
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
				flag = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
			
		return flag;
	}

}
