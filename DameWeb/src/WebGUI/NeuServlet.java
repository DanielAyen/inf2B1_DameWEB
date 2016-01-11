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

	public NeuServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		// String name = request.getParameter("spielername");
		String auswahl1 = request.getParameter("auswahl1");
		String auswahl2 = request.getParameter("auswahl2");
		String farbe = request.getParameter("farbe");

		boolean istKi = false;
		boolean pruef = false;

		if (auswahl1 != null && auswahl2 != null && farbe != null) {
			// Farbe
			if (farbe.equals("Schwarz")) {
				farbeS1 = FarbEnum.SCHWARZ;
				pruef = true;
			} else if (farbe.equals("Weiss")) {
				farbeS1 = FarbEnum.WEIß;
				pruef = true;
			} else {
				pruef = false;
			}

			// Spielerart
			if (auswahl1.equals("Mensch")) {
				pruef = true;
				istKi = false;

			} else if (auswahl1.equals("KI")) {
				pruef = true;
				istKi = true;
			} else {
				pruef = false;
			}

			if (farbeS1 != null && pruef == true) {

				Spieler s1 = spiel.spielerErstellen("Spieler1", farbeS1, istKi);
				spiel.erstelleFiguren(s1, spiel.getBrett());

				HttpSession s1sess = request.getSession();
				s1sess.setAttribute("farbeS1", farbeS1);
				session.getServletContext().setAttribute("farbeS1", farbeS1);

				if (auswahl2.equals("KI")) {

					if (farbeS1 == FarbEnum.SCHWARZ) {
						farbeS1 = FarbEnum.WEIß;
					} else {
						farbeS1 = FarbEnum.SCHWARZ;
					}
					Spieler s2 = spiel.spielerErstellen("KI", farbeS1, true);
					spiel.erstelleFiguren(s2, spiel.getBrett());

				}

				spiel.spielBauen(12);
				spiel.starten();

			} else {
				response.sendRedirect("Neu.jsp");
			}

			// Prüfung bestanden und zweite Spielerartwahl: weitere
			// Vorgehensweise
			if (pruef == true && auswahl2.equals("Mensch")) {
				response.sendRedirect("Spielwarten.html");
			} else if (pruef == true && auswahl2.equals("KI")) {
				response.sendRedirect("refreshServlet");
			}

			// Falls nichts oder teils eingegeben wurde
		} else {
			response.sendRedirect("Neu.jsp");

		}
	}

}
