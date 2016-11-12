package com.chenswe.sellermanager.dao;

import java.util.ArrayList;

import com.chenswe.sellermanager.entity.Food;
import com.chenswe.sellermanager.entity.Seller;
import com.chenswe.sellermanager.entity.SellerBrief;

public interface sellerDAO {

	/**
	 * 检测商家注册号是否已经被使用
	 * 
	 * @param seller
	 * 			商家
	 * @return
	 * 			true - 以被使用
	 * 			false - 未被使用
	 */
	boolean checkRegistrationID(Seller seller);

	/**
	 * 检测手机号是否已被使用
	 * 
	 * @param seller
	 * 			商家
	 * @return
	 * 			true - 已被使用
	 * 			false - 未被使用
	 */
	boolean checkPhone(Seller seller);

	/**
	 * 插入新商家
	 * 
	 * @param seller
	 * 			商家
	 * @return
	 * 			true - 添加成功；
	 * 			false - 添加失败；
	 */
	boolean addSeller(Seller seller);
	/**
	 * 检测用户手机号和密码是否匹配
	 * 
	 * @param user
	 * 			一个商家
	 * @return
	 * 			true - 正确
	 * 			false - 错误
	 */
	boolean checkLoginInfo(Seller seller);

	/**
	 * 使用手机号获取商家注册号
	 * 
	 * @param seller
	 * 			一个商家
	 * @return
	 * 			该商家注册号
	 */
	String getRegistrationID(Seller seller);

	/**
	 * 获取pageContent类型的商家简介
	 * 
	 * @param pageContent
	 * 			商家分类
	 * @param page 
	 * 			页码
	 * @return
	 * 			最多12条匹配所需类型的商家信息
	 */
	ArrayList<SellerBrief> getSellerBrief(String pageContent, int page);

	/**
	 * 通过商家注册号来查询商家的详细信息
	 * 
	 * @param seller
	 * 			商家
	 * @return
	 * 			商家详细信息
	 */
	Seller getSellerDetail(Seller seller);

	/**
	 * 使用注册号获取相应页码和类型的食品简介
	 * 
	 * @param registrationID
	 * 			商家注册号
	 * @param pageContent
	 * 			内容类型
	 * @param page
	 * 			获取页码
	 * @return
	 * 			不超过12个食品的简介
	 */
	ArrayList<Food> getFoodsBrief(String registrationID, String pageContent, int page);

	/**
	 * 添加到购物车时，重新获取食品信息
	 * @param food
	 * 			食品，包含id
	 * @return
	 * 			完整食品信息
	 */
	Food getFoodBrief(Food food);
	
	/**
	 * 获取店铺状态
	 * @param registrationID
	 * 			商家
	 * @return
	 * 			店铺状态码，0--已打烊  1-- 营业中
	 */
	int getStoreStatus(String registrationID);
	
	/**
	 * 变更店铺状态
	 * 
	 * @param registrationID
	 * 				商家注册号
	 */
	void changeStoreStatus(String registrationID);
	
	/**
	 * 更新商家信息
	 * @param seller
	 * 			商家
	 * @return
	 * 			true -- 修改成功
	 * 			false -- 修改失败
	 */
	boolean updateSellerInfo(Seller seller);
	
	/**
	 * 获取对应商家的食品信息
	 * 
	 * @param registrationID
	 * 			注册号
	 * @param page
	 * 			页码
	 * @return
	 * 			不多于10条的食品信息
	 */
	ArrayList<Food> getSellerFoods(String registrationID, int page);
	
	/**
	 *更改食品信息
	 * @param food
	 * @return 
	 */
	boolean modifyFoodInfo(Food food);
	
	
	void changeFoodStatus(String foodID);

	boolean deleteFood(String foodID);

	boolean increaseFoodNum(String registrationID);

	int getFoodNum(String registrationID);

	boolean addFood(Food food);

}
