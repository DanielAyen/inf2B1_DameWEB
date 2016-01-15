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

		<input name="dateiName" type="text" size="20" value="Dateiname"></input>

		<fieldset id=1>
			<input type="radio" id="CSV" name="auswahl1" value="CSV"> <label
				for="sp"> CSV</label> <br /> <input type="radio" id="SER"
				name="auswahl1" value="SER"> <label for="sp"> SER</label> <br />
			<input type="radio" id="PDF" name="auswahl1" value="PDF"> <label
				for="sp"> PDF</label> <br />
		</fieldset>


		<input id="senden" type="submit" value="Senden..."></input>
	</form>


</body>
</html>