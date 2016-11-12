package com.chenswe.sellermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chenswe.sellermanager.entity.Seller;
import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.sellermanager.service.impl.SellerServiceImpl;

/**
 * Servlet implementation class SellerLoginAction
 */
@WebServlet("/SellerLogin.action")
public class SellerLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Seller seller = new Seller();
		seller.setPhone(request.getParameter("form-username"));
		seller.setPassword(request.getParameter("form-password"));

		SellerService service = new SellerServiceImpl();
		boolean success = service.checkLoginInfo(seller);

		HttpSession session = request.getSession();
		if (success) {
			seller.setRegistrationID(service.getRegistrationID(seller));

			// 添加session属性
			session.setAttribute("sellerLoginStatus", "true");
			session.setAttribute("registrationID", seller.getRegistrationID());

			// 重定向到个人中心
			response.sendRedirect(request.getContextPath() + "/pages/profiles/seller_behind.jsp");
		} else {
			session.setAttribute("sellerLoginStatus", "false");

			String url = request.getHeader("referer");
			if (url.lastIndexOf('?') > 0)
				url = url.substring(0, url.lastIndexOf('?'));

			response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
