package WebGUI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logik.SpielBean;

/**
 * Servlet implementation class weiterServlet
 */
@WebServlet("/weiterServlet")
public class weiterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public weiterServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		String weiter = request.getParameter("weiter");

		if (weiter != null) {

			if (weiter.equals("ja")) {

				spiel.willWeiterZiehen();
				response.sendRedirect("refreshServlet");

			} else if (weiter.equals("nein")) {

				spiel.willNichtWeiterZiehen();
				response.sendRedirect("refreshServlet");

			}

		}
		response.sendRedirect("refreshServlet");

	}

}