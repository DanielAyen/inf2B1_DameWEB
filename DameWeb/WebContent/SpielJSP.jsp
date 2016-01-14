<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Logik.SpielBean"%>
<%@page import="Logik.FarbEnum"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spiel</title>
</head>
<body>

	<%
		HttpSession cheatSession = request.getSession(true);
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		FarbEnum farbe = (FarbEnum) cheatSession.getAttribute("farbe");

		//out.print(farbeS2);
		//out.print(farbeS1);
		if (spiel == null || farbe == null) {
			response.sendRedirect("Cheater.jsp");
		} else {
			//out.print(farbeS1);
			//out.print(farbeS2);
			out.print("<h1>Spiel</h1>");
			//System.out.println("JAVA Console Blabla");
			java.util.Date date = new java.util.Date();
			int cnt = 0;
			String ausgabe = "";
			String X;
			String Y;
			String fFarbe;
			String figurA;
			String figurF;
			String aktiv;

			//HttpSession session=request.getSession();

			String[][] brettAusgabe = new String[12][12];
			brettAusgabe = (String[][]) session.getAttribute("brett");
			String gewonnen = (String) session.getAttribute("gewonnen");
			if (gewonnen != null) {
				System.out.println("GEWONNEN");
				ausgabe = "<h2>DER SPIELER MIT DER FARBE" + gewonnen + " HAT GEWONNEN";
			} else {
				//System.out.println(brettAusgabe[0][0]);
				//System.out.println(brettAusgabe[11][11]);
				//String feld="x,y, Feld f s/w, Figur n/s/d,Figur f s/w/n,aktiv y/n";
				if (brettAusgabe != null) {
					ausgabe = "<table border=\"1\" align=\"center\"> <tr>";//align=\"center\"
					// gewonnen(spiel.getGewonnenerSpieler());
					cnt = 0;

					for (int i = 11; i >= 0; i--) {// zeilefor (int i = 11; i >= 0; i--)(int i = 0; i <12; i++)
						for (int j = 0; j < 12; j++) {// spalte

							String test = brettAusgabe[i][j];
							//System.out.println(test);
							String[] feld = test.split(";");
							//System.out.println(""+feld[0]);
							X = feld[0];
							Y = feld[1];
							fFarbe = feld[2];
							figurA = feld[3];
							figurF = feld[4];
							aktiv = feld[5];

							if (fFarbe.equals("s")) {
								if (figurA.equals("n")) {

									// buttonArray[zeile][spalte].setIcon(felds);
									ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"felds.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
									cnt++;
								} else {

									//Spielfigur fig = spiel.getBrett().getBrettFeldIndex(i, j).getSpielfigur();

									if (figurF.equals("s")) {

										if (aktiv.equals("y")) {

											if (figurA.equals("d")) {
												// buttonArray[zeile][spalte].setIcon(dameSG);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameSG.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
											} else {

												// buttonArray[zeile][spalte].setIcon(figurSG);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"SteinSG.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
											}

											cnt++;
										} else {

											if (figurA.equals("d")) {
												// buttonArray[zeile][spalte].setIcon(dames);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameS.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
											} else {

												// buttonArray[zeile][spalte].setIcon(figurs);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"FeldSSteinS.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
											}

											cnt++;
										}
									} else {

										if (aktiv.equals("y")) {

											if (figurA.equals("d")) {
												// buttonArray[zeile][spalte].setIcon(dameWG);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameWG.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
											} else {

												// buttonArray[zeile][spalte].setIcon(figurWG);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"SteinWG2.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";

											}
											cnt++;
										} else {

											if (figurA.equals("d")) {
												// buttonArray[zeile][spalte].setIcon(damew);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameW.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
											} else {

												// buttonArray[zeile][spalte].setIcon(figurw);
												ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"FeldSSteinW.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";

											}
											cnt++;
										}
									}
								}
							} else {
								ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"feldw.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
								cnt++;
							}

							if (cnt == 12) {
								ausgabe += "</tr><tr>";
								cnt = 0;
							}

						}
					}
				}

				ausgabe += "</tr>";

				ausgabe += "<textarea id=\"log\" readonly>" + session.getAttribute("log") + "</textarea>";
				System.out.println(session.getAttribute("log"));
				ausgabe += "</tr>";

				out.println("date : " + date + "");

				out.println("<table border=\"1\" align=\"center\" > <tr><td> <form action=\"Speichern.jsp\"><input type=\"submit\" value=\"Speichern\"></form></td><td> <form action=\"ladenServlet\"><input type=\"submit\" value=\"Laden\"></form></td></table>");

				out.println("<form action=\"refreshServlet\"><input type=\"submit\" value=\"Refresh\" style=\"position:relative; top:10%; left: 95%;\"></form>");
				out.println("<form action=\"kiServlet\"><input type=\"submit\" value=\"KI ziehen\" style=\"position:relative; top:10%; left: 95%;\"></form>");
				String reset = "reset";
				out.println("<form action=\"resetServlet\"><input type=\"submit\" value=\"Reset\" style=\"position:relative; top:10%; left: 95%;\"></form>");
			}
			out.println(ausgabe);
		}
	%>
</body>
</html>