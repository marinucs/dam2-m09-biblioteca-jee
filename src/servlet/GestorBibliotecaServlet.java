package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/GestorBibliotecaServlet")
public class GestorBibliotecaServlet extends HttpServlet {

	private boolean yaIniciado = false;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		BaseDatos bd = new BaseDatos();

		if (bd.compruebaUsuario(usuario, password)) {
			boolean iniciado = yaIniciado;
			if (!yaIniciado) yaIniciado = true;
			response.sendRedirect("bienvenida.jsp?usuario=" + usuario + "&iniciado=" + iniciado + "&method=GET");
		} else {
			response.sendRedirect("error.jsp?usuario=" + usuario);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		BaseDatos bd = new BaseDatos();

		if (bd.compruebaUsuario(usuario, password)) {
			boolean iniciado = yaIniciado;
			if (!yaIniciado) yaIniciado = true;
			response.sendRedirect("bienvenida.jsp?usuario=" + usuario + "&iniciado=" + iniciado + "&method=POST");
		} else {
			response.sendRedirect("error.jsp?usuario=" + usuario);
		}
	}
}
