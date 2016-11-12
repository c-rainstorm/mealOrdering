package com.chenswe.util;

import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * 
 * 该类作用为从xml配置文件中读取数据库的配置信息
 * 默认数据库为mysql
 * 
 * 配置文件在/WebContent/config/mysql.xml
 * 
 * 可供配置的参数有
 * 		主机地址
 * 		端口号
 * 		数据库名称
 * 		数据库用户名
 * 		数据库密码
 * 
 * @author chen_swe
 *
 */
public class DBConfig {
	private String driver = "com.mysql.jdbc.Driver";
	private String host;
	private String port;
	private String database;
	private String username;
	private String password;
	
	/**
	 * 在初始化时从文件中获取配置并保存
	 */
	public DBConfig() {
		SAXBuilder jdomBuilder = new SAXBuilder();
		
		try {
			String configPath = DBConfig.class.getResource("/") + "../../WebContent/config/mysql.xml";
			
			
			Document document = jdomBuilder.build(configPath);
			
			Element root = document.getRootElement();
		
			setHost(root.getChildText("host").trim());
			setPort(root.getChildText("port").trim());
			setDatabase(root.getChildText("database").trim());
			setUsername(root.getChildText("username").trim());
			setPassword(root.getChildText("password").trim());
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	

	public String getDriver() {
		return driver;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
