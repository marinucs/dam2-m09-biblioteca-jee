package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			throws ServletException, IOException, ParseException {

		BaseDatos db = new BaseDatos();
		String boton = request.getParameter("submit");
		String filtro = "";
		if (boton.equals("Consulta libros")) {
			filtro = request.getParameter("titulo");
			
		} else if (boton.equals("Insertar libro")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = (Date) formatter.parse(request.getParameter("fecha"));
			
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			
			Libro libro = new Libro(id, titulo, autor, editorial, fecha, categoria, novedad);
			db.insertarLibro(libro);
			
		} else if (boton.equals("Eliminar libros")) {
			String[] ids = request.getParameterValues("eliminados");
			for (String id: ids) {
				db.eliminarLibro(id);
			}
		}
		
		ArrayList<Libro> libros = db.consultaLibros(filtro);
		request.setAttribute("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
		
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BaseDatos bd = new BaseDatos();
		String filtro = request.getParameter("titulo");
		ArrayList<Libro> libros = bd.consultaLibros(filtro);
		request.setAttribute("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
		
	}
	/*
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		BaseDatos bd = new BaseDatos();
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formatter.parse(request.getParameter("fecha"));
		String categoria = request.getParameter("categoria");
		int novedad = Integer.parseInt(request.getParameter("novedad"));
		Libro libro = new Libro(id, titulo, autor, editorial, fecha, categoria, novedad);
		bd.insertarLibro(libro);
	}
	*/
	
}
