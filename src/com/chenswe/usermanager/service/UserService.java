package com.chenswe.usermanager.service;

import java.util.ArrayList;

import com.chenswe.usermanager.entity.Receiver;
import com.chenswe.usermanager.entity.User;

public interface UserService {
	
	/**
	 * 添加新用户
	 * 
	 * @param user
	 *            新用户
	 * @return true - 添加成功 false - 添加失败
	 */
	boolean addUser(User user);

	/**
	 * 判断用户手机号和密码是否正确
	 * 
	 * @param user
	 * 			一个用户
	 * @return
	 * 			true - 正确
	 * 			false - 错误
	 */
	boolean checkLoginInfo(User user);

	/**
	 * 使用手机号获取对应用户名
	 * 
	 * @param user
	 * 			一个用户
	 * @return
	 * 			该用户的用户名
	 */
	String getUsername(User user);

	/**
	 * 判断当前手机号是否已注册
	 * 
	 * @param user
	 * 			一个用户
	 * @return
	 * 			true - if the phone is exist
	 * 			otherwise, false;
	 */
	boolean phoneIsExist(User user);

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * 			一个用户
	 * @return
	 * 			true - 更改成功
	 * 			false - 更改失败
	 */
	boolean updateUserPassword(User user);

	/**
	 * 判断用户名是否已存在
	 * 
	 * @param user
	 * 			一个用户
	 * @return
	 * 			true - if the username is exist
	 * 			otherwise, false;
	 */
	boolean usernameIsExist(User user);

	/**
	 * 使用用户名获取完整用户信息
	 * 
	 * @param user
	 * 			一个用户
	 * @return
	 * 			包含完整信息的用户
	 */
	User getUserInfo(User user);
	
	/**
	 * 获取对应用户的收货人
	 * 
	 * @param user
	 * 			用户
	 * @param pageNum
	 * 			显示页码
	 * @return
	 * 			两个收货人信息
	 */
	ArrayList<Receiver> getReceiverInfo(User user, int pageNum);

	/**
	 * 将该收件人设为默认
	 * @param receiver
	 * 			收件人
	 * @return
	 * 			true - 设置成功
	 * 			false - 设置失败
	 */
	boolean setDefault(Receiver receiver);

	/**
	 * 更新相应收件人信息
	 * 
	 * @param receiver
	 * 			收件人
	 * @return
	 * 			true - 更新成功
	 * 			false - 更新失败
	 */			
	boolean updateReceiverInfo(Receiver receiver);
	
	/**
	 * 删除相应收件人
	 * 
	 * @param receiver
	 * 			收件人
	 * @return
	 * 			true - 删除成功
	 * 			false - 删除失败
	 */
	boolean removeReceiver(Receiver receiver);
	
	/**
	 * 使用用户名检测新收件人的手机号已存在
	 * 
	 * @param user
	 * 			用户
	 * @param receiver
	 * 			收件人
	 * @return
	 * 			true - 收件人手机号已存在
	 * 			false - 手机号不存在
	 */
	boolean checkReceiverPhone(User user, Receiver receiver);
	
	/**
	 * 添加收货人
	 * @param user 
	 * 			用户
	 * @param receiver
	 * 			收货人
	 * @return
	 * 			true - 添加成功
	 * 			false - 添加失败	
	 */
	boolean addNewReceiver(User user, Receiver receiver);
}
