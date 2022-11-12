package servlet;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestorBibliotecaServlet
 */
@SuppressWarnings("serial")
@WebServlet("/GestorBibliotecaServlet")
public class GestorBibliotecaServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF8");

		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html><head><title>Biblioteca J2EE</title></head>");
			out.println("<body>");
			out.println("<h1>HOLA MUNDO GET</h1>");
			out.println("</body>");
			out.println("</html>");

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF8");

		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html><head><title>Biblioteca J2EE</title></head>");
			out.println("<body>");
			out.println("<h1>HOLA MUNDO POST</h1>");
			out.println("</body>");
			out.println("</html>");

		}
		
	}

}
