package com.sbs.java.dy.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.java.dy.Config;
import com.sbs.java.dy.exception.SQLErrorException;
import com.sbs.java.dy.util.DBUtil;
import com.sbs.java.dy.util.SecSql;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		// Ŀ���� ����̹� Ȱ��ȭ
		String driverName = Config.getDBDriverClassName();

		try {
			Class.forName(driverName);
			
		} catch (ClassNotFoundException e) {
			System.err.printf("[ClassNotFoundException ����, %s]\n", e.getMessage());
			response.getWriter().append("DB ����̹� Ŭ���� �ε� ����");
			return;
		}

		// DB ����
		Connection con = null;

		try {
			con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();	
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}