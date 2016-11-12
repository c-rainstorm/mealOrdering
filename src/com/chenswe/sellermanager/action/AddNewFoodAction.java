package com.chenswe.sellermanager.action;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.sellermanager.service.impl.SellerServiceImpl;

@WebServlet("/addNewFood.action")
@MultipartConfig
public class AddNewFoodAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part image = request.getPart("foodPhoto");

		InputStream imageInputStream = null;
		if (image.getSize() != 0)
			imageInputStream = image.getInputStream();

		SellerService service = new SellerServiceImpl();
		boolean success = service.addFood(request, imageInputStream);

		if (success) {
			response.sendRedirect(request.getContextPath() + "/pages/profiles/seller_behind.jsp");
		} else {
			response.getWriter().print("添加失败！请稍后再试...");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
