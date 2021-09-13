package com.sbs.java.dy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/printDan")
public class HomePrintDanServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String inputedDan = request.getParameter("dan");
		
		if(inputedDan == null) {
			inputedDan = "1";
		}
		
		String inputedlimit = request.getParameter("limit");
		
		if(inputedlimit == null) {
			inputedlimit = "9";
		}
				
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedlimit);
		
		response.getWriter().append(String.format("%d´Ü<br>", dan));
		
		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
		}
	}

}