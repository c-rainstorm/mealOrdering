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

/**
 * Servlet implementation class FinishOrderAction
 */
@WebServlet("/FinishOrder.action")
public class FinishOrderAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order();
		order.setOrderID(request.getParameter("orderID"));
		
		OrderService service = new OrderServiceImpl();
		boolean success = service.finishOrder(order);
		
		response.getWriter().println(success);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
