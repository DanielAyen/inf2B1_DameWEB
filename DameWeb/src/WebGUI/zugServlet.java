package WebGUI;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class zugServlet
 */
@WebServlet("/zugServlet")
public class zugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public zugServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request,  response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String x=request.getParameter("X");
		System.out.println(request.getParameter("X"));
//		if (Pattern.matches("[a-l A-L][0-11]",datum)){
//			System.out.println("mhm");
//		}System.out.println("ne");
//		response.sendRedirect("SpielJSP.jsp");
		String y=request.getParameter("Y");
		System.out.println(request.getParameter("Y"));
	
	}
}
