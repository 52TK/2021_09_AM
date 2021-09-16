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

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			List<Map<String, Object>> articleRows = DBUtil.selectRows(con, sql);

			request.setAttribute("articleRows", articleRows);
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

}