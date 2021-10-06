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
			
			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> loginedMemberRow = null;

			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				isLogined = true;

				SecSql sql = SecSql.from("SELECT * FROM `member`");
				sql.append("WHERE id = ?", loginedMemberId);
				loginedMemberRow = DBUtil.selectRow(con, sql);
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMemberRow", loginedMemberRow);

			
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