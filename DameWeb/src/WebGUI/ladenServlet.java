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
		File selectedFile = new File("/home/informatik/repository_lokal/inf2B1_DameWEBB/DameWeb/Saves/" + name);
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

					// geht wenn davor 2menschen gespielt haben
					// wenn aber 100% sein soll muss man einen löschen dann auf dem warten
					// servlet sitzen und der andre muss beitreten

				}

				// -------------------für ser und xml:
				// session.getServletContext.setAttribute("spiel" spiel)

			}
			if (selectedFile.getName().endsWith(".ser")) {
				SpielBean save = (SpielBean) spiel.laden(selectedFile);
				System.out.println(selectedFile);
				System.out.println(selectedFile.getName());
				System.out.println(selectedFile.getAbsolutePath());

				spiel.laden(selectedFile);

				session.getServletContext().setAttribute("spiel", save);

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

					// geht wenn davor 2menschen gespielt haben
					// wenn aber 100% sein soll muss man einen löschen dann auf dem warten
					// servlet sitzen und der andre muss beitreten

				}

			} else if (selectedFile.getName().endsWith(".xml")) {
					SpielBean save = (SpielBean) spiel.laden(selectedFile);
					System.out.println(selectedFile);
					System.out.println(selectedFile.getName());
					System.out.println(selectedFile.getAbsolutePath());

					spiel.laden(selectedFile);

					session.getServletContext().setAttribute("spiel", save);

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

						// geht wenn davor 2menschen gespielt haben
						// wenn aber 100% sein soll muss man einen löschen dann auf dem
						// warten
						// servlet sitzen und der andre muss beitreten

					}
				}
			}
		}
}
