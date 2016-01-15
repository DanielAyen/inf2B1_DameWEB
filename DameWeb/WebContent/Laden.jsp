<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spiel laden</title>
</head>
<body>
	<h1>Spiel laden</h1>
	<p>Bitte die zu ladende Datei mit Namen der Datei und der Endung
		angeben. Bsp: Save01.csv (Groß- und Kleinschreibung beachten!)</p>
		<p>P.S. bei einem Fehler landest du wieder hier!</p>
		<br/>
	<form method="post" action="ladenServlet">

		<input name="dateiName" type="text" size="20" value="Dateiname"></input>


		<input id="senden" type="submit" value="Senden..."></input>
	</form>


</body>
</html>