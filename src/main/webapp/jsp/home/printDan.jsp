<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
int dan = Integer.parseInt(request.getParameter("dan"));
int limit = Integer.parseInt(request.getParameter("limit"));
String color = request.getParameter("color");
%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>구구단 출력</title>
</head>
<body>
	<div style="color:<%=color%>;">== <%=dan%>단 ==</div>
	
	<%for (int i = 1; i <= 9; i++) {%>
	<div style="color:<%=color%>;"><%=dan %> * <%=i %> = <%=dan * i %></div>
	<%} %>
</body>
</html>