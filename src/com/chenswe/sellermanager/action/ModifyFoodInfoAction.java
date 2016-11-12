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


@WebServlet("/modifyFoodInfo.action")
@MultipartConfig
public class ModifyFoodInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
		Part image = request.getPart("image");
		
		InputStream imageInputStream = null;
		if(image.getSize() != 0)
			imageInputStream = image.getInputStream();
		
		SellerService service = new SellerServiceImpl();
		boolean success = service.modifyFoodInfo(request, imageInputStream);
		
		if(success){
			response.sendRedirect(request.getContextPath() + "/pages/profiles/seller_behind.jsp");	
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("修改失败！请稍后再试...");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
