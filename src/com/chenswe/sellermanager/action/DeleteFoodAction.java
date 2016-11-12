package com.chenswe.sellermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.sellermanager.service.impl.SellerServiceImpl;


@WebServlet("/deleteFood.action")
public class DeleteFoodAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodID = request.getParameter("foodID");
		
		SellerService service = new SellerServiceImpl();
		
		boolean success = service.deleteFood(foodID);
		
		if(success){
			response.sendRedirect(request.getContextPath() + "pages/profiles/seller_behind.jsp");
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().println("删除失败!请稍后再试...");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
