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

import com.sbs.java.dy.util.DBUtil;
import com.sbs.java.dy.util.SecSql;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String url = "jdbc:mysql://localhost:3306/dy?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
		String user = "root";
		String password = "0910";

		// Ŀ���� ����̹� Ȱ��ȭ
		String driverName = "com.mysql.cj.jdbc.Driver";

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
			con = DriverManager.getConnection(url, user, password);
			
			int page = 1;

			if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			int itemsInAPage = 20;

			int limitFrom = (page - 1) * itemsInAPage;

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM article;");

			int totalCount = DBUtil.selectRowIntValue(con, sql);

			int totalpage = (int) Math.ceil((double) totalCount / itemsInAPage);

			sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

			System.out.println(sql);

			List<Map<String, Object>> articleRows = DBUtil.selectRows(con, sql);

			request.setAttribute("articleRows", articleRows);
			request.setAttribute("page", page);
			request.setAttribute("totalpage", totalpage);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
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