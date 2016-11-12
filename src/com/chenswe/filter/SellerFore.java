package com.chenswe.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chenswe.util.StringUtil;


@WebFilter("/pages/profiles/seller_fore.jsp")
public class SellerFore implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest)request;
		HttpSession session = request2.getSession();
		
		String shoppingCars = (String) session.getAttribute("shoppingCars");
		if(StringUtil.isEmpty(shoppingCars)){
			session.setAttribute("shoppingCars", "{}");
		}
		session.setAttribute("userRegistrationID", request.getParameter("registrationID"));

		chain.doFilter(request2, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
