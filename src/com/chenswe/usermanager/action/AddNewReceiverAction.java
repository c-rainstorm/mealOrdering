package com.chenswe.usermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.usermanager.entity.User;
import com.chenswe.usermanager.entity.Receiver;
import com.chenswe.usermanager.service.UserService;
import com.chenswe.usermanager.service.impl.UserServiceImpl;

@WebServlet("/AddNewReceiver.action")
public class AddNewReceiverAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		
		Receiver receiver = new Receiver();
		receiver.setReceiverAddr(request.getParameter("receiverAddr"));
		receiver.setReceiverName(request.getParameter("receiverName"));
		receiver.setReceiverPhone(request.getParameter("receiverPhone"));
		
		UserService service = new UserServiceImpl();
		boolean success = service.addNewReceiver(user, receiver);
		
		response.getWriter().println(success);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
