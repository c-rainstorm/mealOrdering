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
import com.chenswe.util.StringUtil;

@WebServlet("/SellerSignup.action")
public class SellerSignupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Seller seller = new Seller();
		seller.setRegistrationID(request.getParameter("registration_id"));
		seller.setPhone(request.getParameter("phone"));
		seller.setPassword(request.getParameter("password"));
		seller.setStoreName(request.getParameter("store_name"));
		seller.setStoreAddress(request.getParameter("store_address"));
		seller.setCategory(request.getParameter("category"));
		seller.setDescription(request.getParameter("store_description"));
		String fee = request.getParameter("delivery_fee");
		if(!StringUtil.isEmpty(fee))
			seller.setDeliveryFee(Integer.parseInt(fee));
		
		System.out.println(seller.toString());
		
		SellerService service = new SellerServiceImpl();
		boolean success = service.addSeller(seller);
		
		if(success){
			HttpSession session = request.getSession();
			session.setAttribute("sellerLoginStatus", "true");
			session.setAttribute("registrationID", seller.getRegistrationID());
		
			response.sendRedirect(request.getContextPath() + "/pages/profiles/seller_behind.jsp");
		}else{
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("注册失败！请稍后再试...");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
