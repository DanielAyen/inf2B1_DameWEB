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
		String auswahl1 = request.getParameter("auswahl1");
		String auswahl2 = request.getParameter("auswahl2");
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

			}
		} else {
			pruef = false;
		}
		if (auswahl1 != null) {
			if (auswahl1.equals("Mensch")) {
				pruef = true;
				istKi = false;

			} else if (auswahl1.equals("KI")) {
				pruef = true;
				istKi = true;
			} else {
				pruef = false;

			}
		} else {
			pruef = false;
		}

		if (auswahl2 != null && pruef == true) {

			if (auswahl2.equals("Mensch")) {

				response.sendRedirect("Spielwarten.html");
			}

			else if (auswahl2.equals("KI")){

				response.sendRedirect("SpielJSP.jsp");
			}

		} else {
			pruef = false;

		}

		SpielBean s = null;
		if (farbeEnum != null && pruef == true) {

			s = new SpielBean();
			s.spielBauen(12);
			s.spielerErstellen(name, farbeEnum, istKi);

			if (auswahl2.equals("KI")) {

				if (farbeEnum == FarbEnum.SCHWARZ) {
					farbeEnum = FarbEnum.WEIß;
				} else {
					farbeEnum = FarbEnum.SCHWARZ;
				}
				s.spielerErstellen("KI", farbeEnum, true);
			}
		} else {
			response.sendRedirect("Neu.html");
		}

		out.println(auswahl1 + " " + farbe);
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
