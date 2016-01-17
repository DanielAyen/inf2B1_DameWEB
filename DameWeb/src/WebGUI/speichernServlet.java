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
		String auswahl1 = request.getParameter("auswahl1");
		System.out.println(name);
		System.out.println(auswahl1);

		if (name != null && auswahl1 != null) {
			if ((Pattern.matches("[a-z A-Z 0-9]*", name))) {
				System.out.println("JOP");
				System.out.println(spiel.getBrett());

				if (auswahl1.equals("CSV")) {
					File selectedFile = new File("/home/informatik/repository_lokal/inf2B1_DameWEBB/DameWeb/Saves/" + name + ".csv");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					response.sendRedirect("OK.jsp");
					
				} else if (auswahl1.equals("SER")) {
					File selectedFile = new File("/home/informatik/repository_lokal/inf2B1_DameWEBB/DameWeb/Saves/" + name + ".ser");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					response.sendRedirect("OK.jsp");
					
				} else if (auswahl1.equals("PDF")) {
					File selectedFile = new File("/home/informatik/repository_lokal/inf2B1_DameWEBB/DameWeb/Saves/" + name + ".pdf");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					response.sendRedirect("OK.jsp");
					
				} else if (auswahl1.equals("XML")) {
					File selectedFile = new File("/home/informatik/repository_lokal/inf2B1_DameWEBB/DameWeb/Saves/" + name + ".xml");
					selectedFile.createNewFile();
					spiel.Speichern(selectedFile);
					response.sendRedirect("OK.jsp");
				} else {
					response.sendRedirect("Speichern.jsp");
				}

			} else {
				System.out.println("NOP");

				response.sendRedirect("Speichern.jsp");

			}

		} else {
			response.sendRedirect("Speichern.jsp");

		}

	}
}
