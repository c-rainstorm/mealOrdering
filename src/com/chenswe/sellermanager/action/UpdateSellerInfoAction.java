package com.chenswe.sellermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chenswe.sellermanager.entity.Seller;
import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.sellermanager.service.impl.SellerServiceImpl;
import com.chenswe.util.StringUtil;


@WebServlet("/updateSellerInfo.action")
public class UpdateSellerInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Seller seller = new Seller();
		seller.setRegistrationID(request.getParameter("registration_id"));
		seller.setPhone(request.getParameter("phone"));
		seller.setPassword(request.getParameter("password"));
		seller.setStoreName(request.getParameter("store_name"));
		seller.setStoreAddress(request.getParameter("store_address"));
		seller.setCategory(request.getParameter("category"));
		seller.setDescription(request.getParameter("store_description"));
		seller.setAnnouncement(request.getParameter("announcement"));
		
		System.out.println(seller.toString());
		
		String fee = request.getParameter("delivery_fee");
		if(!StringUtil.isEmpty(fee))
			seller.setDeliveryFee(Integer.parseInt(fee));
		
		SellerService service = new SellerServiceImpl();
		boolean success = service.updateSellerInfo(seller);
		
		if(success){
			response.sendRedirect(request.getContextPath() + "/pages/profiles/seller_behind.jsp");
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().println("修改失败！请稍后再试...");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
