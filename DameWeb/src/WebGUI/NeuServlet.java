package WebGUI;

import Logik.FarbEnum;
import Logik.SpielBean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NeuServlet")
public class NeuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NeuServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html><head></head><body>");

		String name = request.getParameter("spielername");
		String auswahl = request.getParameter("auswahl");
		String farbe = request.getParameter("farbe");

		FarbEnum farbeEnum = null;
		boolean istKi = false;
		boolean pruef = false;

		if (farbe != null) {

			if (farbe.equals("Schwarz")) {
				farbeEnum = FarbEnum.SCHWARZ;
				pruef = true;
			} else if (farbe.equals("Weiss")) {
				farbeEnum = FarbEnum.WEIß;
				pruef = true;
			} else {
				pruef = false;
				response.sendRedirect("Neu.html");
			}
		} else {
			response.sendRedirect("Neu.html");
		}
		if (auswahl != null) {
			if (auswahl.equals("Mensch")) {
				pruef = true;
				istKi = false;

			} else if (auswahl.equals("KI")) {
				pruef = true;
				istKi = true;
			} else {
				pruef = false;
				response.sendRedirect("Neu.html");
			}
		} else {
			response.sendRedirect("Neu.html");
		}
		SpielBean s = null;
		if (farbeEnum != null && pruef == true) {

			s = new SpielBean();
			s.spielBauen(12);
			s.spielerErstellen(name, farbeEnum, istKi);
			response.sendRedirect("Spielwarten.jsp");
		}
		out.println(auswahl + " " + farbe);
		out.println(name);

		if (s != null) {

			out.println("<br/>");
			out.println(s.getS1());
			out.println("<br/>");
			out.println(s.getS2());
		}
		out.println("</body></html>");
		out.close();
	}

}
