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
import com.google.gson.Gson;


@WebServlet("/GetSellerDetail.action")
public class GetSellerDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Seller seller = new Seller();
		seller.setRegistrationID(request.getParameter("registrationID"));
		
		SellerService service = new SellerServiceImpl();
		seller = service.getSellerDetail(seller);
		
		Gson gson = new Gson();
		String jSeller = gson.toJson(seller);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(jSeller);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
