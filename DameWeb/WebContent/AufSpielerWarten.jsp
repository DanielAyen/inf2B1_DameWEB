<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Logik.SpielBean"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Warte auf zweiten Spieler</title>
</head>
<body>

	<%
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		
		if (spiel == null) {
			out.print("<h1>HUANCHEATER</h1>");
			out.print(
					"<div id=\"anmeldung\"> <a id=\"in3\" href=\"Index.jsp\">Sich ins Spiel cheaten! Meld dich an AMK</a></div>");
		} else {
			
			out.print("<h1>Warte auf zweiten Spieler</h1>");
			out.println("<form action=\"AufSpielerWartenServlet\"><input type=\"submit\" value=\"Spieler schon da?\"></form>");
		}
	%>

</body>
</html>