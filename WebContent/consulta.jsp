<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.ArrayList" 
	import="servlet.Libro"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Consulta de llibres</title>
</head>
<body>
	<h1>LIBROS DE LA BIBLIOTECA</h1>
	<%ArrayList<Libro> libros = (ArrayList<Libro>)request.getAttribute("lista");%>
	
	<form action="ConsultaLibrosServlet" method="post">
	<table border=1>
	<tr><h2><td>ID<td>TITULO<td>AUTOR<td>EDITORIAL
	<td>FECHA<td>CATEGORIA<td>NOVEDAD<td>ELIMINAR<td>MODIFICAR</h2>
	
	<%for (Libro libro : libros) {
		out.print("<tr><h3><td>" + libro.getId() + "</td>");
		out.print("<td>" + libro.getTitulo() + "</td>");
		out.print("<td>" + libro.getAutor() + "</td>");
		out.print("<td>" + libro.getEditorial() + "</td>");
		out.print("<td>" + libro.getFecha() + "</td>");
		out.print("<td>" + libro.getCategoria() + "</td>");
		out.print("<td>" + libro.getNovedad() + "</td></h3>");
		out.print("<td><center><input type=checkbox name=eliminados value=" + libro.getId() + " /></center>");
		out.print("<td><center><input type=checkbox name=recuperados value=" + libro.getId() + " /></center>");
	}%>
	
	</table><br>
	
	<input type="submit" name="submit" value="Eliminar libros">
	<input type="submit" name="submit" value="Recuperar libro"><br><br>
	
	</form>
	
	<% Object mod = request.getAttribute("FlagModificar");
		if (mod==null) { %>
		<form action="ConsultaLibrosServlet" method="post">
			<br>
			TITULO: <input type="text" name="titulo">
			AUTOR: <input type="text" name="autor">
			EDITORIAL: <input type="text" name="editorial"><br><br>
			FECHA: <input type="text" name="fecha">
			CATEGORIA: <input type="text" name="categoria">
			NOVEDAD: <input type="text" name="novedad"><br><br>
			<input type="submit" name="submit" value="Insertar libro">
		</form>
	
	<%} else {
		Libro libro = (Libro)request.getAttribute("libro");%>
		<form action="ConsultaLibrosServlet" method="post">
			ID: <input type="text" name="id" value="<%=libro.getId()%>" readonly>
			TITULO: <input type="text" name="titulo" value="<%=libro.getTitulo()%>">
			AUTOR: <input type="text" name="autor" value="<%=libro.getAutor()%>">
			EDITORIAL: <input type="text" name="editorial" value="<%=libro.getEditorial()%>"><br><br>
			FECHA: <input type="text" name="fecha" value="<%=libro.getFecha()%>">
			CATEGORIA: <input type="text" name="categoria" value="<%=libro.getCategoria()%>">
			NOVEDAD: <input type="text" name="novedad" value="<%=libro.getNovedad()%>"><br><br>
			<input type="submit" name="submit" value="Actualiza libro">
			<input type="submit" name="submit" value="Cancelar">
		</form>
	<%}%>
	</body>
</html>