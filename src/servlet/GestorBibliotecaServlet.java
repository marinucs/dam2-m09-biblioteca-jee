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
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=UTF8");

		if (usuario != null && password != null) {
			if (usuario.equals("pepe") && password.equals("pepe")) {
				boolean iniciado = yaIniciado;
				response.sendRedirect("bienvenida.jsp?usuario=" + usuario + "&iniciado=" + iniciado + "&method=GET");
			} else {
				response.sendRedirect("error.jsp?usuario=" + usuario);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=UTF8");

		if (usuario != null && password != null) {
			if (usuario.equals("pepe") && password.equals("pepe")) {
				boolean iniciado = yaIniciado;
				response.sendRedirect("bienvenida.jsp?usuario=" + usuario + "&iniciado=" + iniciado + "&method=POST");

			} else {
				response.sendRedirect("error.jsp?usuario=" + usuario);
			}
		}
	}
}
