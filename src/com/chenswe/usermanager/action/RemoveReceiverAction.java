package com.chenswe.usermanager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenswe.usermanager.entity.Receiver;
import com.chenswe.usermanager.service.UserService;
import com.chenswe.usermanager.service.impl.UserServiceImpl;

@WebServlet("/RemoveReceiver.action")
public class RemoveReceiverAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Receiver receiver = new Receiver();
		receiver.setOwner(request.getParameter("owner"));
		receiver.setReceiverPhone(request.getParameter("receiverPhone"));
		
		UserService service = new UserServiceImpl();
		boolean success = service.removeReceiver(receiver);
		
		response.getWriter().println(success);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
