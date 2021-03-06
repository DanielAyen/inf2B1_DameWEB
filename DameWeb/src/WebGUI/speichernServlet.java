package WebGUI;

import java.io.File;
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
 * Servlet implementation class speichernServlet
 */
@WebServlet("/speichernServlet")
public class speichernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public speichernServlet() {
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
		SpielBean spiel = (SpielBean) session.getServletContext().getAttribute("spiel");
		String name = request.getParameter("dateiName");
		String auswahl1 = request.getParameter("auswahl1");

		// String location =
		// "/home/informatik/repository_lokal/inf2B1_DameWEBB/DameWeb/WebContent/WebSaves/";//
		// Hannes
		String location = "/home/informatik/repository_lokal/inf2B1_DameWEB/inf2B1_DameWEB/DameWeb/Saves/";// Daniel
		String location2 = "/home/informatik/repository_lokal/inf2B1_DameWEB/inf2B1_DameWEB/DameWeb/WebContent/WebSaves/";// Daniel
		// String
		// location="/home/informatik/LokalRepo/inf2B1_DameWEB/DameWeb/Saves/";//Baris

		// System.out.println(name);
		// System.out.println(auswahl1);

		if (name != null && auswahl1 != null) {
			if ((Pattern.matches("[a-z A-Z 0-9]*", name))) {
				// System.out.println("JOP");
				// System.out.println(spiel.getBrett());

				if (auswahl1.equals("CSV")) {
					File selectedFile = new File(location + name + ".csv");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					response.sendRedirect("OK.jsp");

				} else if (auswahl1.equals("SER")) {
					File selectedFile = new File(location + name + ".ser");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					response.sendRedirect("OK.jsp");

				} else if (auswahl1.equals("PDF")) {
					File selectedFile = new File(location2 + name + ".pdf");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					session.getServletContext().setAttribute("pfad", "http://localhost:8080/DameWeb/WebSaves/" + name + ".pdf");
					
					
					//session.getServletContext().setAttribute("pfad", "WebSaves/" + name + ".pdf");
					
					response.sendRedirect("PDF.jsp");

				} else if (auswahl1.equals("XML")) {
					File selectedFile = new File(location + name + ".xml");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					response.sendRedirect("OK.jsp");
				} else {
					response.sendRedirect("Speichern.jsp");
				}
			} else {
				// System.out.println("NOP");
				response.sendRedirect("Speichern.jsp");
			}
		} else {
			response.sendRedirect("Speichern.jsp");
		}
	}
}
