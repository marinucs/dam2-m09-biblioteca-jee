package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseDatos {
	
	private Connection conexion;
	
	public BaseDatos() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String conex = "jdbc:mysql://localhost:3306/biblioteca_online";
			this.conexion = DriverManager.getConnection(conex, "root", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean compruebaUsuario(String usuario, String password) {
		boolean check=false;
		
		try {
			
			Statement s = conexion.createStatement();
			String sqlQuery = "SELECT count(*) FROM USUARIOS WHERE usuario ='" + usuario + "' "
					+ "AND password='" + password + "'";
			s.execute(sqlQuery);
			ResultSet rs = s.getResultSet();
			rs.next();
			if (rs.getInt(1) > 0) check = true;
		
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		
		return check;
	}
	
	public ArrayList<Libro> consultaLibros(String filtro) {
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			
			Statement s = conexion.createStatement();
			String sqlQuery = "SELECT * FROM LIBROS WHERE TITULO LIKE '%" + filtro + "%'";
			s.execute(sqlQuery);
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				Libro libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7));
				lista.add(libro);
			}
		
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		
		return lista;
	}
	
	public void insertarLibro(Libro libro) {
		String sqlQuery = "INSERT INTO libros (id, titulo, autor, editorial, fecha, categoria, novedad)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlQuery);
			ps.setInt(1, libro.getId());
			ps.setString(2, libro.getTitulo());
			ps.setString(3, libro.getAutor());
			ps.setString(4, libro.getEditorial());
			ps.setDate(5, new java.sql.Date(libro.getFecha().getTime()));
			ps.setString(6, libro.getCategoria());
			ps.setInt(7, libro.getNovedad());
			ps.executeUpdate();
			
		} catch(SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public void eliminarLibro(String id) {
		String query = "DELETE FROM LIBROS WHERE ID=" + id;
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
		
			ps.executeUpdate();
			
		} catch(SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
}
