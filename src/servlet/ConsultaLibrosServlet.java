package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ConsultaLibrosServlet")
public class ConsultaLibrosServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titulo = request.getParameter("titulo");
		BaseDatos db = new BaseDatos();
		ArrayList<Libro> libros = db.consultaLibros(titulo);
		
		request.setAttribute("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String titulo = request.getParameter("titulo");
		BaseDatos db = new BaseDatos();
		ArrayList<Libro> libros = db.consultaLibros(titulo);
		
		request.setAttribute("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
		
	}
	
}
