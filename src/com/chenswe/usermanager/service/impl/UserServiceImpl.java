/**
 * 
 */
package com.chenswe.usermanager.service.impl;

import java.util.ArrayList;

import com.chenswe.usermanager.dao.UserDAO;
import com.chenswe.usermanager.dao.impl.UserDAOImpl;
import com.chenswe.usermanager.entity.Receiver;
import com.chenswe.usermanager.entity.User;
import com.chenswe.usermanager.service.UserService;

/**
 * @author chen_swe
 *
 */
public class UserServiceImpl implements UserService {
	
	private UserDAO dao = new UserDAOImpl();
	
	@Override
	public boolean addUser(User user) {
		return dao.addUser(user);
	}

	@Override
	public boolean checkLoginInfo(User user) {
		return dao.checkLoginInfo(user);
	}

	@Override
	public String getUsername(User user) {
		return dao.getUsername(user);
	}

	@Override
	public boolean phoneIsExist(User user) {
		return dao.phoneIsExist(user);
	}

	@Override
	public boolean updateUserPassword(User user) {
		return dao.updateUserPassword(user);
	}

	@Override
	public boolean usernameIsExist(User user) {
		return dao.usernameIsExist(user);
	}

	@Override
	public User getUserInfo(User user) {
		return dao.getUesrInfo(user);
	}

	@Override
	public ArrayList<Receiver> getReceiverInfo(User user, int pageNum) {
		return dao.getReceiverInfo(user, pageNum);
	}

	@Override
	public boolean setDefault(Receiver receiver) {
		return dao.setDefault(receiver);
	}

	@Override
	public boolean updateReceiverInfo(Receiver receiver) {
		return dao.updateReceiverInfo(receiver);
	}

	@Override
	public boolean removeReceiver(Receiver receiver) {
		return dao.removeReceiver(receiver);
	}

	@Override
	public boolean checkReceiverPhone(User user, Receiver receiver) {
		return dao.checkReceiverPhone(user, receiver);
	}

	@Override
	public boolean addNewReceiver(User user, Receiver receiver) {
		user = getUserInfo(user);
		receiver.setOwner(user.getPhone());
		return dao.addNewReceiver(receiver);
	}



}
