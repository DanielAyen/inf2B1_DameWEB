<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PDF</title>
</head>
<body>

<form method="post" action="refreshServlet">
		<input type="Submit" name="index" value="Wieder zum Spiel"></input>
	</form>

<%

String pfad = (String) session.getServletContext().getAttribute("pfad");

if(pfad!=null){
	
	out.println("<form action=\""+pfad+"\"><input type=\"submit\" value=\"PDF\"></form>");
}


%>






</body>
</html>