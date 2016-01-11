<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Logik.SpielBean"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spiel</title>
</head>
<body>

	<%
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		
		if (spiel == null) {
			out.print("<h1>HUANCHEATER</h1>");
			out.print(
					"<div id=\"anmeldung\"> <a id=\"in3\" href=\"Index.jsp\">Sich ins Spiel cheaten! Meld dich an AMK</a></div>");
		} else {
			out.print("<h1>Spiel</h1>");
			System.out.println("JAVA Console Blabla");
			java.util.Date date = new java.util.Date();
			int cnt = 0;
			String ausgabe = "";
			boolean ss = false;

			//HttpSession session=request.getSession();

			String brettAusgabe = (String) session.getServletContext().getAttribute("brett");

			out.println(brettAusgabe);
			out.println("HTML TEST");
			out.println("<br/>");
			out.println("date : " + date + "");

			out.println("<form action=\"refreshServlet\"><input type=\"submit\" value=\"Refresh\"></form>");
			out.println("<form action=\"kiServlet\"><input type=\"submit\" value=\"KI ziehen\"></form>");
			ausgabe += "</tr>";
			//out.println(ausgabe);
		}
	%>
</body>
</html>