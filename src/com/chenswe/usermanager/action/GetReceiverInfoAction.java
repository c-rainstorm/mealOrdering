package com.chenswe.usermanager.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.usermanager.entity.Receiver;
import com.chenswe.usermanager.entity.User;
import com.chenswe.usermanager.service.UserService;
import com.chenswe.usermanager.service.impl.UserServiceImpl;
import com.chenswe.util.StringUtil;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetReceiverInfoAction
 */
@WebServlet("/GetReceiverInfo.action")
public class GetReceiverInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		
		int pageNum = -1;
		if(!StringUtil.isEmpty(request.getParameter("page"))){
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		UserService service = new UserServiceImpl();
		ArrayList<Receiver> receivers = service.getReceiverInfo(user, pageNum);  
		
		
		Gson gson = new Gson();
		String jReceivers = gson.toJson(receivers);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jReceivers);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
