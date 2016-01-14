<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spiel speichern</title>
</head>
<body>


	<form method="post" action="speichernServlet">

		
		<fieldset id=1>
			<input type="radio" id="CSV" name="auswahl1" value="CSV"> <br />
			<input type="radio" id="SER" name="auswahl1" value="SER"> <br />
			<input type="radio" id="PDF" name="auswahl1" value="PDF"> <br />
		</fieldset>


		<input id="senden" type="submit" value="Senden..."></input>
	</form>


	zu speichern servlet
	
	
</body>
</html>