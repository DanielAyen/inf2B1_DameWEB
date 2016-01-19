package WebGUI;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logik.FarbEnum;
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
		this.doPost(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		String x = request.getParameter("X");
		String y = request.getParameter("Y");
		// System.out.println(request.getParameter("X"));
		// System.out.println(request.getParameter("Y"));
		boolean gezogen;

		// ////////////////////////////////////////////////////////////

		FarbEnum farbe = (FarbEnum) session.getAttribute("farbe");
		//System.out.println(farbe);
		//System.out.println(spiel.getAmZug());
		if (spiel.getAmZug() == farbe) {//

			if (x != null && y != null) {

				if (session.getServletContext().getAttribute("Xs") != null && session.getServletContext().getAttribute("Ys") != null) {
					session.getServletContext().setAttribute("Xz", x);
					session.getServletContext().setAttribute("Yz", y);
					gezogen = spiel.ziehen(session.getServletContext().getAttribute("Xs") + "+" + session.getServletContext().getAttribute("Ys"), session.getServletContext().getAttribute("Xz") + "+" + session.getServletContext().getAttribute("Yz"));
					System.out.println(session.getServletContext().getAttribute("Xs"));
					System.out.println(session.getServletContext().getAttribute("Ys"));
					System.out.println(session.getServletContext().getAttribute("Xz"));
					System.out.println(session.getServletContext().getAttribute("Yz"));
					
					
					// System.out.println(gezogen);
					session.getServletContext().setAttribute("Xz", null);
					session.getServletContext().setAttribute("Yz", null);
					session.getServletContext().setAttribute("Xs", null);
					session.getServletContext().setAttribute("Ys", null);
				} else {
					session.getServletContext().setAttribute("Xs", x);
					session.getServletContext().setAttribute("Ys", y);
					spiel.log(x + y);

				}

				if (session.getServletContext().getAttribute("Xs") != null && session.getServletContext().getAttribute("Xz") != null && session.getServletContext().getAttribute("Xs").equals(session.getServletContext().getAttribute("Xz")) && session.getServletContext().getAttribute("Ys") != null
						&& session.getServletContext().getAttribute("Yz") != null && session.getServletContext().getAttribute("Ys").equals(session.getServletContext().getAttribute("Yz"))) {
					session.getServletContext().setAttribute("Xz", null);
					session.getServletContext().setAttribute("Yz", null);
					session.getServletContext().setAttribute("Xs", null);
					session.getServletContext().setAttribute("Ys", null);
				}
				// U LOGGER MELDUNG
				// response.sendRedirect("refreshServlet");
			} else {
				// LOGGER FEHLER
			}

			// ////////////////////////////////////////////////////////////////
			// if (Pattern.matches("[a-l A-L][0-11]",datum)){
			// System.out.println("mhm");
			// }System.out.println("ne");
			// response.sendRedirect("SpielJSP.jsp");
			response.sendRedirect("refreshServlet");
		} else {
			response.sendRedirect("refreshServlet");
		}
	}

}
