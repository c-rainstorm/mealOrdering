package com.chenswe.sellermanager.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.sellermanager.entity.SellerBrief;
import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.sellermanager.service.impl.SellerServiceImpl;
import com.google.gson.Gson;

@WebServlet("/getSellerBrief.action")
public class GetSellerBriefAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageContent = request.getParameter("pageContent");
		int page = Integer.parseInt(request.getParameter("page"));
		
		SellerService service = new SellerServiceImpl();
		ArrayList<SellerBrief> sellerBriefs = service.getSellerBrief(pageContent, page);
		
		Gson gson = new Gson();
		String jSellerBriefs = gson.toJson(sellerBriefs);

		response.getWriter().println(jSellerBriefs);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
