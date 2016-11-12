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

@WebServlet("/UpdateUserPassword.action")
public class UpdateUserPasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPhone(request.getParameter("phone"));
		user.setPassword(request.getParameter("password"));
		
		UserService service = new UserServiceImpl();
		boolean success = service.updateUserPassword(user);
		
		if(success){
			response.sendRedirect(request.getContextPath() + "/pages/profiles/user.jsp");
		}else{
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("修改密码失败！请稍后再试！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
