package com.chenswe.sellermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.sellermanager.entity.Food;
import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.sellermanager.service.impl.SellerServiceImpl;
import com.google.gson.Gson;

@WebServlet("/GetFoodBrief.action")
public class GetFoodBriefAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Food food = new Food();
		food.setFoodID(request.getParameter("foodID"));

		SellerService service = new SellerServiceImpl();
		food = service.getFoodBrief(food);
		
		Gson gson = new Gson();
		String jFood = gson.toJson(food);

		response.getWriter().println(jFood);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
