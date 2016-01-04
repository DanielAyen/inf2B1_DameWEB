<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spiel</title>
</head>
<body>

	<%
		System.out.println("JAVA Console Blabla");
		java.util.Date date = new java.util.Date();
		int cnt = 0;
		String ausgabe = "";
		ausgabe = "<table border=\"1\"> <tr>";
		boolean ss = false;

		for (int i = 11; i >= 0; i--) {
			for (int j = 0; j < 12; j++) {
				
				if (ss == false) {
					ausgabe += "<td><a href=zugServlet?datum=a4><img src=\"feldw.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
					ss = !ss;
					cnt++;
				} else {
					ausgabe += "<td><a href=zugServlet><img src=\"felds.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
					//\"https://www.google.de\"
					ss = !ss;
					cnt++;
				}
				
				if (cnt == 12) {
					ss = !ss;
					ausgabe+="</tr><tr>";
					cnt = 0;
				}
			}
		}
		
	
		

		out.println("HTML TEST");
		out.println("<br/>");
		out.println("date : " + date + "Blabla");
		ausgabe+="</tr>";
		out.println(ausgabe);
	%>

	<h1>TEST</h1>
	<p>HUHU</p>

</body>
</html>