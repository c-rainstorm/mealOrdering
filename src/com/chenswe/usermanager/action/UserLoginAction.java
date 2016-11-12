package com.chenswe.usermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chenswe.usermanager.entity.User;
import com.chenswe.usermanager.service.UserService;
import com.chenswe.usermanager.service.impl.UserServiceImpl;

@WebServlet("/userLogin.action")
public class UserLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setPhone(request.getParameter("form-username"));
		user.setPassword(request.getParameter("form-password"));
		
		UserService service = new UserServiceImpl();
		boolean success = service.checkLoginInfo(user);
		
		HttpSession session = request.getSession();
		if(success){
			user.setUsername(service.getUsername(user));
			
			//添加session属性
			session.setAttribute("userLoginStatus", "true");
			session.setAttribute("userPhone", user.getPhone());
			session.setAttribute("username", user.getUsername());

			//重定向到个人中心
			response.sendRedirect(request.getContextPath() + "/pages/profiles/user.jsp");
		}else{
			session.setAttribute("userLoginStatus", "false");

			String url = request.getHeader("referer");
			if(url.lastIndexOf('?') > 0)
				url = url.substring(0, url.lastIndexOf('?'));
				
			response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
