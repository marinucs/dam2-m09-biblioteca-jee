<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>BIENVENIDA</title>
</head>
	<body>
		
		<% String usuario=request.getParameter("usuario"); %>
		<% String iniciado=request.getParameter("iniciado"); %>
		<% String method=request.getParameter("method"); %>
		<% if (iniciado.equals("false")) {%>
			<h1>Conectado a la BD</h1>
		<%}%>
		<h1>BIENVENIDO USUARIO <%=usuario %> (llamada <%=method%>)</h1>
		
	</body>
</html>