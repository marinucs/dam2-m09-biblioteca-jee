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
	
	<table border=1>
	<tr><h2><td>ID<td>TITULO<td>AUTOR<td>EDITORIAL<td>FECHA<td>CATEGORIA<td>NOVEDAD</h2>
	
	<%for (Libro libro : libros) {
		out.print("<tr><h3><td>" + libro.getId() + "</td>");
		out.print("<td>" + libro.getTitulo() + "</td>");
		out.print("<td>" + libro.getAutor() + "</td>");
		out.print("<td>" + libro.getEditorial() + "</td>");
		out.print("<td>" + libro.getFecha() + "</td>");
		out.print("<td>" + libro.getCategoria() + "</td>");
		out.print("<td>" + libro.getNovedad() + "</td></h3>");
	}%>
	
	</table>
	
	<form action="GestionLibrosServlet" method="post">
		<br>
		ID: <input type="text" name="id">
		TITULO: <input type="text" name="titulo">
		AUTOR: <input type="text" name="autor">
		EDITORIAL: <input type="text" name="editorial"><br><br>
		FECHA: <input type="text" name="fecha">
		CATEGORIA: <input type="text" name="categoria">
		NOVEDAD: <input type="text" name="novedad"><br><br>
		<input type="submit" name="submit value" value="Insertar libro">
	</form>
	
</body>
</html>