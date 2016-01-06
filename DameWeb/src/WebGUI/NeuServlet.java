package WebGUI;

import Logik.FarbEnum;
import Logik.SpielBean;
import Logik.Spieler;

import java.io.IOException;
import java.io.PrintWriter;

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

	public NeuServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		// String name = request.getParameter("spielername");
		String auswahl1 = request.getParameter("auswahl1");
		String auswahl2 = request.getParameter("auswahl2");
		String farbe = request.getParameter("farbe");

		FarbEnum farbeEnum = null;
		boolean istKi = false;
		boolean pruef = false;

		if (farbe != null) {

			if (farbe.equals("Schwarz")) {
				farbeEnum = FarbEnum.SCHWARZ;
				pruef = true;
			} else if (farbe.equals("Weiss")) {
				farbeEnum = FarbEnum.WEIß;
				pruef = true;
			} else {
				pruef = false;

			}
		} else {
			pruef = false;
		}
		if (auswahl1 != null) {
			if (auswahl1.equals("Mensch")) {
				pruef = true;
				istKi = false;

			} else if (auswahl1.equals("KI")) {
				pruef = true;
				istKi = true;
			} else {
				pruef = false;

			}
		} else {
			pruef = false;
		}

		if (auswahl2 != null && pruef == true) {

			if (auswahl2.equals("Mensch")) {

				response.sendRedirect("Spielwarten.html");
			}

			else if (auswahl2.equals("KI")) {

				response.sendRedirect("refreshServlet");
			}

		} else {
			pruef = false;

		}

		if (farbeEnum != null && pruef == true) {

			Spieler s1 = spiel.spielerErstellen("Spieler1", farbeEnum, istKi);

			spiel.erstelleFiguren(s1, spiel.getBrett());
			if (auswahl2.equals("KI")) {

				if (farbeEnum == FarbEnum.SCHWARZ) {
					farbeEnum = FarbEnum.WEIß;
				} else {
					farbeEnum = FarbEnum.SCHWARZ;
				}
				Spieler s2 = spiel.spielerErstellen("KI", farbeEnum, true);

				spiel.erstelleFiguren(s2, spiel.getBrett());

			}

			spiel.spielBauen(12);
			spiel.starten();

		} else {
			response.sendRedirect("Neu.html");
		}

	}

}
