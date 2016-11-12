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


@WebServlet("/CheckRegistrationID.action")
public class CheckRegistrationIDAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Seller seller = new Seller();
		seller.setRegistrationID(request.getParameter("registrationID"));
		
		SellerService service = new SellerServiceImpl();
		boolean success = service.checkRegistrationID(seller);
		
		response.getWriter().println(success);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
