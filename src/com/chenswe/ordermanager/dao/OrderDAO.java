package com.chenswe.ordermanager.dao;

import java.util.ArrayList;

import com.chenswe.ordermanager.entity.Order;
import com.chenswe.ordermanager.entity.OrderBrief;
import com.chenswe.usermanager.entity.User;

public interface OrderDAO {

	/**
	 * 通过orderID获取整个订单
	 * 
	 * @param orderID
	 * 			订单号
	 * @return
	 * 			一个订单
	 */
	Order getOrderByID(String orderID);

	/**
	 * 完成订单
	 * 
	 * @param order
	 * 			订单
	 * @return
	 * 			true- 操作成功
	 * 			false- 操作失败
	 */
	boolean finishOrder(Order order);

	/**
	 * 获取用户第page页的订单记录
	 * 
	 * @param user
	 * 			用户
	 * @param page
	 * 			页数
	 * @return
	 * 			最多10个订单的简介
	 */
	ArrayList<OrderBrief> getUserOrderBriefs(User user, int page);
	

	/**
	 * 	通过参数信息获取相应的订单简介
	 * 
	 * @param registrationID
	 * 			商家注册号
	 * @param pageContent
	 * 			显示订单类型
	 * @param page
	 * 			显示页码
	 * @return
	 * 			订单简介列表
	 */
	ArrayList<OrderBrief> getSellerOrderBriefs(String registrationID, String pageContent, int page);
	
	/**
	 * 更新订单信息
	 * 
	 * 若curstatus 为-1， 则只更新orderID对应的一条记录
	 * 否则，更新所有状态为curstatus的订单
	 * @param curStatus
	 * 			当前状态
	 * @param orderID
	 * 			订单ID
	 * @return
	 * 			true -- 成功
	 * 			false -- 失败
	 */
	boolean updateOrderStatus(String curStatus, String orderID);
	
	/**
	 * 商家销售量增加1
	 * 
	 * @param registrationID
	 */
	boolean inscreaseSellerSoldNum(String registrationID);
	
	/**
	 * 获取当前商家已售订单数
	 * 
	 * @param registrationID
	 * 			商家注册号
	 * @return
	 * 			订单数
	 */
	String getSellerSoldNum(String registrationID);

	/**
	 * 过用户名获取用户手机号
	 * 
	 * @param username 
	 * 			用户名
	 * @return
	 * 			用户手机号
	 */
	String getUserPhone(String username);
	
	/**
	 * 获取当前商家配送费
	 * 
	 * @param registrationID
	 * 			商家注册号
	 * @return
	 * 			配送费
	 */
	int getSellerDeliveryFee(String registrationID);

	/**
	 * 添加订单中食品
	 * 
	 * @param orderID
	 * 			订单号
	 * @param foodID
	 * 			食品ID
	 * @param foodCount
	 * 			购买数量
	 */
	void addFoodInOrder(String orderID, String foodID, String foodCount);
	
	/**
	 * 通过食品ID获取食品价格
	 * 
	 * @param foodID
	 * 			食品ID
	 * @return
	 * 			食品价格
	 */
	float getFoodPrice(String foodID);

	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	boolean addOrder(Order order);

}
