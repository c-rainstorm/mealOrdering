package com.chenswe.adminmanager.service.impl;

import com.chenswe.adminmanager.dao.AdminDAO;
import com.chenswe.adminmanager.dao.impl.AdminDAOImpl;
import com.chenswe.adminmanager.entity.Admin;
import com.chenswe.adminmanager.service.AdminService;

public class AdminServiceImpl implements AdminService {

	private AdminDAO dao = new AdminDAOImpl();
	
	@Override
	public boolean checkLoginInfo(Admin admin) {
		return dao.checkLoginInfo(admin);
	}

}
