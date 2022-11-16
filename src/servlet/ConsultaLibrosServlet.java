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
			
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parseDate = format.parse(request.getParameter("fecha"));
			java.sql.Date fecha = new java.sql.Date(parseDate.getTime());
			
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			
			Libro libro = new Libro(id, titulo, autor, editorial, fecha, categoria, novedad);
			db.insertarLibro(libro);
			
		} else if (boton.equals("Eliminar libros")) {
			
			String[] ids = request.getParameterValues("eliminados");
			for (String id: ids) {
				db.eliminarLibro(id);
			}
			
		} else if (boton.equals("Recuperar libro")) {
			
			String[] ids = request.getParameterValues("recuperados");
			Libro libro = db.recuperarLibro(ids[0]);
			request.setAttribute("libro", libro);
			request.setAttribute("FlagModificar", 1);
			
		} else if (boton.equals("Actualiza libro")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");
			
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parseDate = format.parse(request.getParameter("fecha"));
			java.sql.Date fecha = new java.sql.Date(parseDate.getTime());
			
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			
			Libro libro = new Libro(id, titulo, autor, editorial, fecha, categoria, novedad);
			db.modificarLibro(libro);
			
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
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
			
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parseDate = null;
			try {
				parseDate = format.parse(request.getParameter("fecha"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date fecha = new java.sql.Date(parseDate.getTime());
			
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			
			Libro libro = new Libro(id, titulo, autor, editorial, fecha, categoria, novedad);
			db.insertarLibro(libro);
			
		} else if (boton.equals("Eliminar libros")) {
			
			String[] ids = request.getParameterValues("eliminados");
			for (String id: ids) {
				db.eliminarLibro(id);
			}
			
		} else if (boton.equals("Recuperar libro")) {
			
			String[] ids = request.getParameterValues("recuperados");
			Libro libro = db.recuperarLibro(ids[0]);
			request.setAttribute("libro", libro);
			request.setAttribute("FlagModificar", 1);
			
		} else if (boton.equals("Actualiza libro")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");
			
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parseDate = null;
			try {
				parseDate = format.parse(request.getParameter("fecha"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date fecha = new java.sql.Date(parseDate.getTime());
			
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			
			Libro libro = new Libro(id, titulo, autor, editorial, fecha, categoria, novedad);
			db.modificarLibro(libro);
			
		}
		
		ArrayList<Libro> libros = db.consultaLibros(filtro);
		request.setAttribute("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
		
	}

}
