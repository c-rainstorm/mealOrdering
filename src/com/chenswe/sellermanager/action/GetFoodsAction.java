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

@WebServlet("/getFoods.action")
public class GetFoodsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String registrationID = request.getParameter("registrationID");
		int page = Integer.parseInt(request.getParameter("page"));
		
		SellerService service = new SellerServiceImpl();
		ArrayList<Food> foods = service.getSellerFoods(registrationID,page);
		
		Gson gson = new Gson();
		String jFoods = gson.toJson(foods);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(jFoods);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
