<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Logik.SpielBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Neues Spiel erstellen</title>
</head>
<body>
	<%
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		if (spiel == null) {
			out.print("<h1>HUANCHEATER</h1>");
			out.print(
					"<div id=\"anmeldung\"> <a id=\"in3\" href=\"Index.jsp\">Sich ins Spiel cheaten! Meld dich an AMK</a></div>");
		} else {
		}
		%>
			<form method="post" action="NeuServlet">

	<!-- 	<input name="spielername" type="text" size="20" value="Spielername"></input> -->
		
<fieldset id=1>
			<input type="radio" id="mensch" name="auswahl1" value="Mensch">
			<label for="ki"> Mensch</label>
			<br/>
			<input type="radio" id="ki" name="auswahl1" value="KI">
			<label for="ki"> KI</label>
			<br/>
			<input type="radio" id="schwarz" name="farbe" value="Schwarz">
			<label for="ki"> Schwarz</label>
			<br/>
			<input type="radio" id="weiss" name="farbe" value="Weiss">
			<label for="ki"> Weiß</label>
			<br/>
		</fieldset>


<fieldset id=2>	
			<input type="radio" id="mensch" name="auswahl2" value="Mensch">
			<label for="ki"> Mensch</label>
			<br/>
			<input type="radio" id="ki" name="auswahl2" value="KI">
			<label for="ki"> KI</label>
			<br/>
		</fieldset>
		
		<input id="senden" type="submit" value="Senden..."></input>
	</form>

</body>
</html>