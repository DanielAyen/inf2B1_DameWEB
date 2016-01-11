package WebGUI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logik.SpielBean;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SpielBean spiel = null;

	public IndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Hier wird das SessionObject geholt und wird mit der Variable session referenziert
		HttpSession session = request.getSession();

		if (spiel == null) {
			spiel = new SpielBean();
			spiel.aufbauen(12);  //wird in NeuServlet aufgebaut //bei auskommentieren fehler
			// Hier wird das spiel an die Session gehängt (Application Scope)
			session.getServletContext().setAttribute("spiel", spiel);
			response.sendRedirect("Neu.jsp");// Weiterleitung an die Neu.jsp

		} else if (spiel.getSpielerAnzahl() == 1) {
			// Falls ein Spieler schon vorhanden ist, kann man dem Spiel
			// beitreten
			response.sendRedirect("Beitreten.jsp");

		} else {
			// Falls es schon zwei Spieler gibt, wird man auf die Voll.jsp
			// weitergeleitet
			response.sendRedirect("Voll.jsp");

		}

	}

}
