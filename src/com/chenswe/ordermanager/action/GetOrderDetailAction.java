package com.chenswe.ordermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.ordermanager.entity.Order;
import com.chenswe.ordermanager.service.OrderService;
import com.chenswe.ordermanager.service.Impl.OrderServiceImpl;
import com.google.gson.Gson;

@WebServlet("/GetOrderDetail.action")
public class GetOrderDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrderService service = new OrderServiceImpl();
		
		Order order = service.getOrderByID(request.getParameter("orderID"));
		
		Gson gson = new Gson();
		String jOrder = gson.toJson(order);

		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jOrder);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
