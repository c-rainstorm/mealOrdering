package com.chenswe.usermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.usermanager.entity.User;
import com.chenswe.usermanager.service.UserService;
import com.chenswe.usermanager.service.impl.UserServiceImpl;

@WebServlet("/CheckPhone.action")
public class CheckPhoneAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 空用户对象
		User user = new User();

		user.setPhone(request.getParameter("phone"));

		UserService service = new UserServiceImpl();

		boolean isExist = service.phoneIsExist(user);

		response.getWriter().println(isExist);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
