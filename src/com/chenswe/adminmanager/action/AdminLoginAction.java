package com.chenswe.adminmanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chenswe.adminmanager.entity.Admin;
import com.chenswe.adminmanager.service.AdminService;
import com.chenswe.adminmanager.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class AdminLoginAction
 */
@WebServlet("/AdminLogin.action")
public class AdminLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = new Admin();
		admin.setUsername(request.getParameter("form-username"));
		admin.setPassword(request.getParameter("form-password"));
		
		AdminService service = new AdminServiceImpl();
		boolean success = service.checkLoginInfo(admin);

		HttpSession session = request.getSession();
		if (success) {
			// 添加session属性
			session.setAttribute("adminLoginStatus", "true");
			session.setAttribute("adminname", admin.getUsername());

			// 重定向到个人中心
			response.sendRedirect(request.getContextPath() + "/pages/profiles/admin.jsp");
		} else {
			session.setAttribute("adminLoginStatus", "false");

			String url = request.getHeader("referer");
			if (url.lastIndexOf('?') > 0)
				url = url.substring(0, url.lastIndexOf('?'));

			response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
