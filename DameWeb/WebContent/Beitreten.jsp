<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Logik.SpielBean"%>
<%@page import="Logik.FarbEnum"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Beitreten</title>
</head>
<body>

	<%
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		FarbEnum farbeS1 = (FarbEnum) session.getAttribute("farbeS1");
		
		//Nur eine Abfrage weil noch kein zweiter Spieler existiert
		if (spiel == null || farbeS1 == null) {
			response.sendRedirect("Cheater.jsp");
		} else {

			out.print("<h1>Beitreten</h1>");
			out.println("<form action=\"refreshServlet\"><input type=\"submit\" value=\"Auf ins Spiel!\"></form>");
		}
	%>

</body>
</html>