package com.chenswe.usermanager.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.ordermanager.entity.OrderBrief;
import com.chenswe.ordermanager.service.OrderService;
import com.chenswe.ordermanager.service.Impl.OrderServiceImpl;
import com.chenswe.usermanager.entity.User;
import com.google.gson.Gson;

@WebServlet("/GetUserOrderBrief.action")
public class GetUserOrderBriefAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		OrderService service = new OrderServiceImpl();
		ArrayList<OrderBrief> briefs = service.getUserOrderBriefs(user, page);
		
		Gson gson = new Gson();
		String jBriefs = gson.toJson(briefs);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jBriefs);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
