package WebGUI;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logik.SpielBean;

/**
 * Servlet implementation class zugServlet
 */
@WebServlet("/zugServlet")
public class zugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public zugServlet() {
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
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		String x = request.getParameter("X");
		String y = request.getParameter("Y");
		System.out.println(request.getParameter("X"));
		System.out.println(request.getParameter("Y"));
		boolean gezogen;
		if (x != null && y != null) {

			gezogen = spiel.ziehen(startp, endp);

			if (gezogen == false) {

				response.sendRedirect("SpielJSP.jsp");
				// LOGGER FEHLER

			} else {
				response.sendRedirect("SpielJSP.jsp");
				// LOGGER GUT

			}
			// LOGGER

		} else if (x == null && y == null) {

			response.sendRedirect("SpielJSP.jsp");
			// LOGGER BEIDES NULL GEHT NICHT

		} else if (x != null && y == null) {

			session.getServletContext().setAttribute("Xcoord", x);
			// X IN LOGGER
			response.sendRedirect("SpielJSP.jsp");

		} else if (x == null && y != null) {

			int yi = Integer.parseInt("y");

		}
		// ////////////////////////////////////////////////////////////
		if (x != null && y != null) {
			
			if (session.getServletContext().getAttribute("Xs") != null && session.getServletContext().getAttribute("Ys") != null) {
				session.getServletContext().setAttribute("Xz", x);
				session.getServletContext().setAttribute("Yz", y);
				//ziehen/gültigkeit
				//alles wieder raus
			} else {
				session.getServletContext().setAttribute("Xs", x);
				session.getServletContext().setAttribute("Ys", y);
			}
			response.sendRedirect("SpielJSP.jsp");// U LOGGER MELDUNG

		} else {
			// LOGGER FEHLER
		}

		// ////////////////////////////////////////////////////////////////
		// if (Pattern.matches("[a-l A-L][0-11]",datum)){
		// System.out.println("mhm");
		// }System.out.println("ne");
		// response.sendRedirect("SpielJSP.jsp");

		response.sendRedirect("refreshServlet");
	}

}
