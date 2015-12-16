package WebGUI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html><head></head><body>");

		String p1 = request.getParameter("param1");
		String ausgabe = "";
		if ((p1 == null || (p1.length() < 1)))
			ausgabe = "<h2>Sie haben nix eingegeben</h2>";
		else
			ausgabe = "<h2>Sie haben " + p1 + " eingegeben</h2>";
		out.println(ausgabe);
		out.println("<a href='HalloZuServlet.html'zurück...</a>");
		out.println("</body></html>");
		out.close();
	}

}
