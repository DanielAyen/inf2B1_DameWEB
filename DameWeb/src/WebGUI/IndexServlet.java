package WebGUI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html><head></head><body>");

		String act = request.getParameter("index");

		if (act == null) {
			response.sendRedirect("Index.html");

		} else if (act.equals("Beitreten")) {//neues servlet das backend schaut und dann neu oder nicht 
			response.sendRedirect("Beitreten.html");

		} else if (act.equals("Neues Spiel")) {
			response.sendRedirect("Neu.html");

		} else {
			response.sendRedirect("Index.html");
		}

		out.println("</body></html>");
		out.close();
	}

}
