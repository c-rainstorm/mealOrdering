package com.chenswe.adminmanager.dao;

import com.chenswe.adminmanager.entity.Admin;

public interface AdminDAO {

	/**
	 * 检测管理员登录信息
	 * 
	 * @param admin
	 * 			管理员
	 * @return
	 * 			true - 信息核对成功
	 * 			false - 信息不匹配
	 */
	boolean checkLoginInfo(Admin admin);

}
