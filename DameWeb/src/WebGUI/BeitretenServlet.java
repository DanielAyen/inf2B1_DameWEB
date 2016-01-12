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
import Logik.Spieler;

/**
 * Servlet implementation class BeitretenServlet
 */
@WebServlet("/BeitretenServlet")
public class BeitretenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FarbEnum farbeS2=null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BeitretenServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		FarbEnum farbeS1 = (FarbEnum) session.getServletContext().getAttribute("farbeS1");

		// Nur eine Abfrage zur Farbe weil noch kein zweiter Spieler existiert
		if (spiel == null || farbeS1 == null) {
			response.sendRedirect("Cheater.jsp");
		} else {
	
			if (farbeS1 == FarbEnum.SCHWARZ) {
				farbeS2 = FarbEnum.WEIß;
			} else {
				farbeS2 = FarbEnum.SCHWARZ;
			}
			Spieler s2 = spiel.spielerErstellen("Spieler2", farbeS2, false);
			spiel.erstelleFiguren(s2, spiel.getBrett());
			
			HttpSession s2sess = request.getSession(true);
			s2sess.setAttribute("farbeS2", farbeS2);
			session.getServletContext().setAttribute("farbeS2", farbeS2);
			
			response.sendRedirect("refreshServlet");
		
			
//			String act = request.getParameter("beitreten");
//
//			if (act == null) {
//				response.sendRedirect("Index.html");
//
//			} else if (act.equals("Beitreten")) {
//				response.sendRedirect("Beitreten.html");// NACH SPIELEN SUCHEN
//			}

		}
	}
}