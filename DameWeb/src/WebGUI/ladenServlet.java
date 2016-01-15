package WebGUI;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logik.SpielBean;
import Logik.Spieler;

/**
 * Servlet implementation class ladenServlet
 */
@WebServlet("/ladenServlet")
public class ladenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ladenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		String name = request.getParameter("dateiName");
		File selectedFile = new File("/home/informatik/repository_lokal/DameWeb/Saves/" + name);
		System.out.println(spiel.getBrett());
		if (!selectedFile.exists()) {

			response.sendRedirect("Laden.jsp");

		} else {

			if (selectedFile.getName().endsWith(".pdf")) {

				response.sendRedirect("Laden.jsp");

			} else if (selectedFile.getName().endsWith(".csv")) {

				System.out.println(selectedFile);
				System.out.println(selectedFile.getName());
				System.out.println(selectedFile.getAbsolutePath());
				spiel.laden(selectedFile);
				System.out.println(spiel.getBrett());

				Spieler s1 = spiel.getS1();
				Spieler s2 = spiel.getS2();

				if (s1.getIstKi() && s2.getIstKi()) {// ki ki

					session.setAttribute("farbe", s1.getFarbe());

					response.sendRedirect("refreshServlet");
				}
				if (s1.getIstKi() && !s2.getIstKi()) {// mensch ki
					session.setAttribute("farbe", s1.getFarbe());
					response.sendRedirect("refreshServlet");

				} else if (!s1.getIstKi() && s2.getIstKi()) {// ki mensch
					session.setAttribute("farbe", s2.getFarbe());
					response.sendRedirect("refreshServlet");

				} else if (!s1.getIstKi() && !s2.getIstKi()) {// mensch mensch
					session.setAttribute("farbe", s1.getFarbe());
					response.sendRedirect("AufSpielerWartenServlet");

				}

			} else if (selectedFile.getName().endsWith(".ser")) {

				spiel.laden(selectedFile);

			} else {

			}
		}
	}

}
