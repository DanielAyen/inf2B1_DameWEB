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

	private SpielBean spiel;

	public IndexServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		if (spiel == null) {
			
			HttpSession session=request.getSession(true);
			spiel = new SpielBean();
			spiel.aufbauen(12);
			
			session.getServletContext().setAttribute("spiel", spiel);

			response.sendRedirect("Neu.html");

		} else if (spiel.getSpielerAnzahl() == 1) {

			response.sendRedirect("Beitreten.jsp");

		} else {

			response.sendRedirect("Voll.jsp");

		}

	}

}
