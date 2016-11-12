package com.chenswe.ordermanager.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.ordermanager.service.OrderService;
import com.chenswe.ordermanager.service.Impl.OrderServiceImpl;

@WebServlet("/SubmitOrder.action")
public class SubmitOrderAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		OrderService service = new OrderServiceImpl();
		boolean success = service.submitOrder(request);
		if(success){
			response.getWriter().print(success);
		}else{
			response.getWriter().print(success);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
