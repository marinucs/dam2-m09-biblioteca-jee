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

	private boolean yaIniciado = false;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String usuario=request.getParameter("usuario");
		String password=request.getParameter("password");
		
		if(usuario.equals("pepe") && password.equals("pepe")) {
			response.setContentType("text/html;charset=UTF8");
			try (PrintWriter out = response.getWriter()) {
				out.println("<!DOCTYPE html>");
				out.println("<html><head><title>Biblioteca J2EE</title></head>");
				out.println("<body>");
					if(!yaIniciado) {
						yaIniciado = true;
						out.println("<h2>Conectado a la base de datos (GET)</h2>");
					}
					out.println("<h1>HOLA MUNDO GET</h1>");
					out.println("</body>");
					out.println("</html>");
			}
		} else response.sendRedirect("error.html");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String usuario=request.getParameter("usuario");
		String password=request.getParameter("password");
		response.setContentType("text/html;charset=UTF8");

		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html><head><title>Biblioteca J2EE</title></head>");
			out.println("<body>");
			if(usuario.equals("pepe") && password.equals("pepe")) {
				if(!yaIniciado) {
					yaIniciado = true;
					out.println("<h2>Conectado a la base de datos (POST)</h2>");
				}
				out.println("<h1>HOLA MUNDO POST</h1>");
			} else out.println("<h1> ACCESO NO PERMITIDO USUARIO " + usuario + " </h1>");
			out.println("</body>");
			out.println("</html>");

		}
		
	}

}
