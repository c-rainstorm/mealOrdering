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
import com.google.gson.Gson;

@WebServlet("/GetUserInfo.action")
public class GetUserInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		
		UserService service = new UserServiceImpl();
		user = service.getUserInfo(user);
		
		Gson gson = new Gson();
		String jUser = gson.toJson(user);
		
		response.getWriter().println(jUser);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
