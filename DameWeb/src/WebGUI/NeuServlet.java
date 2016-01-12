package WebGUI;

import Logik.FarbEnum;
import Logik.SpielBean;
import Logik.Spieler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/NeuServlet")
public class NeuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SpielBean spiel;
	private FarbEnum farbeS1 = null;
	private FarbEnum farbeS2 = null;

	public NeuServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		// String name = request.getParameter("spielername");
		String auswahl1 = request.getParameter("auswahl1");
		String auswahl2 = request.getParameter("auswahl2");
		String farbe = request.getParameter("farbe");

		boolean istKi = false;
		boolean pruefFarbe = false;
		boolean pruefArt = false;

		if (auswahl1 != null || auswahl2 != null || farbe != null) {
			// Farbe
			if (farbe.equals("Schwarz")) {
				farbeS1 = FarbEnum.SCHWARZ;
				pruefFarbe = true;
				farbeS2 = FarbEnum.WEIß;
			} else if (farbe.equals("Weiss")) {
				farbeS1 = FarbEnum.WEIß;
				pruefFarbe = true;
				farbeS2 = FarbEnum.SCHWARZ;
			} else {
				pruefFarbe = false;
			}

			// Spielerart
			if (auswahl1.equals("Mensch")) {
				pruefArt = true;
				istKi = false;

			} else if (auswahl1.equals("KI")) {
				pruefArt = true;
				istKi = true;
			} else {
				pruefArt = false;
			}

			if (pruefFarbe==true && pruefArt == true) {

				Spieler s1 = spiel.spielerErstellen("Spieler1", farbeS1, istKi);
				spiel.erstelleFiguren(s1, spiel.getBrett());

				HttpSession s1sess = request.getSession(true);
				s1sess.setAttribute("farbeS1", farbeS1);
				session.getServletContext().setAttribute("farbeS1", farbeS1);

				if (auswahl2.equals("KI")) {

					// if (farbeS1 == FarbEnum.SCHWARZ) {
					// farbeS1 = FarbEnum.WEIß;
					// } else {
					// farbeS1 = FarbEnum.SCHWARZ;
					// }
					Spieler s2 = spiel.spielerErstellen("KI", farbeS2, true);
					spiel.erstelleFiguren(s2, spiel.getBrett());
					//Wegen KI
					s1sess.setAttribute("farbeS2", farbeS2);
				}
				spiel.spielBauen(12);
				spiel.starten();
			} else {
				response.sendRedirect("Neu.jsp");
			}

			// Prüfung bestanden und zweite Spielerartwahl: weitere
			// Vorgehensweise
			if (auswahl2.equals("Mensch")) {
				response.sendRedirect("AufSpielerWarten.jsp");
			} else if (auswahl2.equals("KI")) {
				response.sendRedirect("refreshServlet");
			}

			// Falls nichts oder teils eingegeben wurde
		} else {
			response.sendRedirect("Neu.jsp");

		}
	}
}
