package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
}
