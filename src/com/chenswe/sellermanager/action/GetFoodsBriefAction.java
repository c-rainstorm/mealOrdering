package com.chenswe.sellermanager.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.sellermanager.entity.Food;
import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.sellermanager.service.impl.SellerServiceImpl;
import com.google.gson.Gson;

@WebServlet("/GetFoodsBrief.action")
public class GetFoodsBriefAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String registrationID = request.getParameter("registrationID");
		String pageContent = request.getParameter("pageContent");
		int page = Integer.parseInt(request.getParameter("page"));
		
		SellerService service = new SellerServiceImpl();
		ArrayList<Food> foods = service.getFoodsBrief(registrationID,pageContent,page);
		
		Gson gson = new Gson();
		String jFoods = gson.toJson(foods);

		response.getWriter().println(jFoods);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
