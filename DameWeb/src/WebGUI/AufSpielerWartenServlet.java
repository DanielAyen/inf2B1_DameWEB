package WebGUI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logik.SpielBean;
@WebServlet("/AufSpielerWartenServlet")
public class AufSpielerWartenServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpielBean spiel;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		spiel = (SpielBean) session.getServletContext().getAttribute("spiel");

		if(spiel.getSpielerAnzahl()==2){

			response.sendRedirect("refreshServlet");
			
			
			// Falls noch kein zweiter Spieler vorhanden ist
		} else {
			response.sendRedirect("AufSpielerWarten.jsp");

		}
	}
}
