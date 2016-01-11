package WebGUI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logik.FarbEnum;
import Logik.SpielBean;
import Logik.Spielfigur;

/**
 * Servlet implementation class refreshServlet
 */
@WebServlet("/refreshServlet")
public class refreshServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SpielBean spiel;
	private String ausgabe;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public refreshServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		if (spiel != null) {
			if (spiel.getBrett() != null) {

				ausgabe = "<table border=\"1\"> <tr>";
				// gewonnen(spiel.getGewonnenerSpieler());
				int cnt = 0;
				for (int i = 11; i >= 0; i--) {// zeile
					for (int j = 0; j <= 12 - 1; j++) {// spalte

						if (spiel.getBrett().getBrettFeldIndex(i, j).getIstSchwarz()) {
							if (!spiel.getBrett().getBrettFeldIndex(i, j).getIstBelegt()) {

								// buttonArray[zeile][spalte].setIcon(felds);
								ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"felds.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
								cnt++;
							} else {

								Spielfigur fig = spiel.getBrett().getBrettFeldIndex(i, j).getSpielfigur();

								if (fig.getFarbe() == FarbEnum.SCHWARZ) {

									if (FarbEnum.SCHWARZ == spiel.getAmZug()) {

										// buttonArray[zeile][spalte].setIcon(figurSG);
										ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"SteinSG.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
										cnt++;
										if (spiel.getBrett().getBrettFeldIndex(i, j).getSpielfigur().getDame(fig)) {
											// buttonArray[zeile][spalte].setIcon(dameSG);
											ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameSG.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
											cnt++;
										}

									} else {

										// buttonArray[zeile][spalte].setIcon(figurs);
										ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"FeldSSteinS.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
										cnt++;
										if (fig.getDame(fig)) {
											// buttonArray[zeile][spalte].setIcon(dames);
											ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameS.png\" alt=\"S X:" + i + "Y:" + j + "\"></a></td>";
											cnt++;
										}
									}
								} else {

									if (FarbEnum.WEIß == spiel.getAmZug()) {

										// buttonArray[zeile][spalte].setIcon(figurWG);
										ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"SteinWG2.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
										cnt++;
										if (spiel.getBrett().getBrettFeldIndex(i, j).getSpielfigur().getDame(fig)) {
											// buttonArray[zeile][spalte].setIcon(dameWG);
											ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameWG.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
											cnt++;
										}
									} else {

										// buttonArray[zeile][spalte].setIcon(figurw);
										ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"FeldSSteinW.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
										cnt++;
										if (fig.getDame(fig)) {
											// buttonArray[zeile][spalte].setIcon(damew);
											ausgabe += "<td><a href=zugServlet?X=" + ((char) (65 + j)) + "&Y=" + (i + 1) + "><img src=\"dameW.png\" alt=\"W X:" + i + "Y:" + j + "\"></a></td>";
											cnt++;
										}
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
				ausgabe += "</tr>";

			} else {
				ausgabe += "Fehler!";

			}
		} else {
			ausgabe += "Fehler!";

		}

		ausgabe += "<textarea id=\"log\" readonly>" + spiel.getLog() + "</textarea>";
		session.getServletContext().setAttribute("brett", ausgabe);

		response.sendRedirect("SpielJSP.jsp");

	}

}
