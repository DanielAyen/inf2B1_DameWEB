<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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