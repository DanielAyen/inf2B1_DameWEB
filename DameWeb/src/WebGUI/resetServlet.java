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
 * Servlet implementation class resetServlet
 */
@WebServlet("/resetServlet")
public class resetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public resetServlet() {
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
		HttpSession session = request.getSession();

		SpielBean spiel=(SpielBean) session.getServletContext().getAttribute("spiel");
		
		String reset = null;
		session.getServletContext().setAttribute("Xz", reset);
		session.getServletContext().setAttribute("Yz", reset);
		session.getServletContext().setAttribute("Xs", reset);
		session.getServletContext().setAttribute("Ys", reset);
spiel.log("RESET");
		response.sendRedirect("refreshServlet");
	}

}
