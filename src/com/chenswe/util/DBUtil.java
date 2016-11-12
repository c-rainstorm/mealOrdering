package com.chenswe.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库实用工具类
 * 
 * @author chen_swe
 *
 */
public class DBUtil {
	private static DruidDataSource dataSource = null;
	private static Connection connection = null;
	/**
	 * 获取一个数据库连接
	 * 
	 * 如果未配置数据库连接池，则先配置连接池，再进行连接获取	
	 * 
	 * @return
	 * 		一个数据库连接
	 */
	public static Connection getConnection() {
		if(dataSource == null){
			dataSource = new DruidDataSource();
			DBConfig config = new DBConfig();
		
			dataSource.setDriverClassName(config.getDriver());
			String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort()
					+ "/" + config.getDatabase() + "?&useSSL=false";
			dataSource.setUrl(url);
			dataSource.setUsername(config.getUsername());
			dataSource.setPassword(config.getPassword());
		}
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}	
	public static void main(String[] args) {
		
	}
}
