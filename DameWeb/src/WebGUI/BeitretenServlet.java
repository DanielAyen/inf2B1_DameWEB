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

/**
 * Servlet implementation class BeitretenServlet
 */
@WebServlet("/BeitretenServlet")
public class BeitretenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		FarbEnum farbeS1 = (FarbEnum) session.getAttribute("farbeS1");
		
		//Nur eine Abfrage weil noch kein zweiter Spieler existiert
		if (spiel == null || farbeS1 == null) {
			response.sendRedirect("Cheater.jsp");
		} else {
		
		
		
		String act = request.getParameter("beitreten");

		if (act == null) {
			response.sendRedirect("Index.html");

		} else if (act.equals("Beitreten")) {
			response.sendRedirect("Beitreten.html");//NACH SPIELEN SUCHEN
		}

	}}
}