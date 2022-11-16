package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.log.Log;

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
			String sqlQuery = "SELECT count(*) FROM usuarios WHERE usuario ='" + usuario + "' "
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
			String sqlQuery = "SELECT * FROM libros WHERE TITULO LIKE '%" + filtro + "%'";
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
		
		String query = "SELECT MAX(id) FROM libros";
		Logger log = Logger.getLogger("servlet.BaseDatos");
		int last_id = 0;
		int generatedKeys = 0;
		
		try {
			Statement stmt = conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			last_id = rs.getInt(1);
								
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				generatedKeys = rs.getInt(1);
				log.log(Level.INFO, "GENERATED KEYS: " + generatedKeys + "LAST_ID: " + last_id);
				
				/* System.out.println("GENERATED KEYS: " + generatedKeys + "\t \n LAST_ID: " +
				 * last_id); */
			}
			
			if (generatedKeys > last_id) {
				query = "ALTER TABLE libros AUTO_INCREMENT = " + last_id;
				
				try {
					stmt = conexion.prepareStatement(query);
				
					stmt.executeUpdate(query);
					
				} catch(SQLException ex) {
					System.out.print(ex.getMessage());
				}
			}
			
		} catch(SQLException ex) {
			System.out.print(ex.getMessage());
		}
		
		String sqlQuery = "INSERT INTO libros (titulo, autor, editorial, fecha, categoria, novedad)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlQuery);
			ps.setString(1, libro.getTitulo());
			ps.setString(2, libro.getAutor());
			ps.setString(3, libro.getEditorial());
			ps.setDate(4, new java.sql.Date(libro.getFecha().getTime()));
			ps.setString(5, libro.getCategoria());
			ps.setInt(6, libro.getNovedad());
			ps.executeUpdate();
			
		} catch(SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public void eliminarLibro(String id) {
		String query = "DELETE FROM libros WHERE id=" + id;
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
		
			ps.executeUpdate();
			
		} catch(SQLException ex) {
			System.out.print(ex.getMessage());
		}
		
	}
	
	public Libro recuperarLibro(String id) {
		Libro libro = null;
		
		try {
			
			Statement s = conexion.createStatement();
			String sqlQuery = "SELECT * FROM libros WHERE id=" + id;
			s.execute(sqlQuery);
			ResultSet rs = s.getResultSet();
			rs.next();
			libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7));
			
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		
		return libro;
	}
	
	public void modificarLibro(Libro libro) {
		String sqlQuery = 
				"UPDATE libros SET id=?, titulo=?, autor=?, editorial=?," +
				" fecha=?, categoria=?, novedad=? WHERE id=" + libro.getId();
		
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
	
}
