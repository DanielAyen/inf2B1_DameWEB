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

/**
 * Servlet implementation class NeuServlet
 */
@WebServlet("/NeuServlet")
public class NeuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SpielBean spiel;
	private FarbEnum farbeS1 = null;
	private FarbEnum farbeS2 = null;
	private int groesse=12;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NeuServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		HttpSession s1sess = request.getSession(true);
		spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		String auswahl1 = request.getParameter("auswahl1");
		String auswahl2 = request.getParameter("auswahl2");
		String farbe = request.getParameter("farbe");
		boolean istKi = false;
		boolean pruefFarbe = false;
		boolean pruefArt = false;
		//System.out.println(auswahl1);
		//System.out.println(auswahl2);
		//System.out.println(farbe);
		
		int g=(int) session.getServletContext().getAttribute("groesse");
		if(g==8||g==10||g==12){
			groesse=g;
		}
		
		if (auswahl1 != null && auswahl2 != null && farbe != null) {
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
			if (pruefFarbe == true && pruefArt == true) {
				// Auswahl Spieler 1 (Mensch | KI)
				if (auswahl1.equals("Mensch")) {
					Spieler s1 = spiel.spielerErstellen("Spieler1", farbeS1, istKi);
					spiel.erstelleFiguren(s1, spiel.getBrett());
					s1sess.setAttribute("farbe", farbeS1);
					session.getServletContext().setAttribute("farbe", farbeS1);
				} else {
					Spieler s1 = spiel.spielerErstellen("KI", farbeS1, true);
					spiel.erstelleFiguren(s1, spiel.getBrett());
					s1sess.setAttribute("farbe", farbeS1);
					session.getServletContext().setAttribute("farbe", farbeS1);
				}
				// ________________________________________________________________________________
				// Auswahl Spieler 2 (Mensch | KI)
				// Mensch gegen KI
				if (auswahl1.equals("Mensch") && auswahl2.equals("KI")) {
					Spieler s2 = spiel.spielerErstellen("KI", farbeS2, true);
					spiel.erstelleFiguren(s2, spiel.getBrett());
					session.getServletContext().setAttribute("farbe", farbeS2);
					// Wegen ein Spieler
					s1sess.setAttribute("farbe", farbeS1);
				}
				// KI gegen Mensch
				if (auswahl1.equals("KI") && auswahl2.equals("Mensch")) {
					Spieler s2 = spiel.spielerErstellen("KI", farbeS2, false);
					spiel.erstelleFiguren(s2, spiel.getBrett());
					session.getServletContext().setAttribute("farbe", farbeS2);
					// Wegen ein Spieler
					s1sess.setAttribute("farbe", farbeS2);
				}
				// KI gegen KI
				if (auswahl1.equals("KI") && auswahl2.equals("KI")) {
					Spieler s2 = spiel.spielerErstellen("KI", farbeS2, true);
					spiel.erstelleFiguren(s2, spiel.getBrett());
					session.getServletContext().setAttribute("farbeS2", farbeS2);
					// Wegen ein Spieler
					s1sess.setAttribute("farbe", farbeS1);
				}
				// Wenn Mensch gegen Mensch ausgewählt, muss Spieler 1 auf Spieler 2
				// warten
				if (auswahl1.equals("Mensch") && auswahl2.equals("Mensch")) {
					response.sendRedirect("AufSpielerWarten.jsp");
				}
				// _________________________________________________________________________________
				spiel.spielBauen(groesse);
				
			}
			// Wenn Mensch gegen KI oder andersrum, kann direkt gespielt werden
			if (auswahl1.equals("Mensch") && auswahl2.equals("KI") || auswahl1.equals("KI") && auswahl2.equals("Mensch") || auswahl1.equals("KI") && auswahl2.equals("KI")) {
				spiel.starten();
				response.sendRedirect("refreshServlet");
			}
			// Falls nichts oder teils eingegeben wurde
		} else {
			response.sendRedirect("Neu.jsp");
		}
	}
}