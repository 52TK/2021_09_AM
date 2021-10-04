package com.sbs.java.dy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		HttpSession session= request.getSession();
		
		boolean isLogined = false;
		int loginedMemberId = -1;
		
		if(session.getAttribute("loginMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginMemberId");
			isLogined = true;
		}
		request.setAttribute("isLogined", isLogined);
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
