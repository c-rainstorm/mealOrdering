package com.chenswe.util;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * 
 * 璇ョ被浣滅敤涓轰粠xml閰嶇疆鏂囦欢涓鍙栨暟鎹簱鐨勯厤缃俊鎭� 榛樿鏁版嵁搴撲负mysql
 * 
 * 閰嶇疆鏂囦欢鍦�/WebContent/config/mysql.xml
 * 
 * 鍙緵閰嶇疆鐨勫弬鏁版湁 涓绘満鍦板潃 绔彛鍙� 鏁版嵁搴撳悕绉� 鏁版嵁搴撶敤鎴峰悕 鏁版嵁搴撳瘑鐮�
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
     * 鍦ㄥ垵濮嬪寲鏃朵粠鏂囦欢涓幏鍙栭厤缃苟淇濆瓨
     */
    public DBConfig() {
        SAXBuilder jdomBuilder = new SAXBuilder();

        try {
            String configPath = DBConfig.class.getResource("/") + "../../config/mysql.xml";

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
