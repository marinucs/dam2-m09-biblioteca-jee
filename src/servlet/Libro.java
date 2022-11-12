package servlet;
import java.sql.Date;


public class Libro {

	private int id;
	private String titulo;
	private String autor;
	private String editorial;
	private Date fecha;
	private String categoria;
	private int novedad;
	
	public Libro(int id, String titulo, String autor, String editorial, Date fecha, String categoria, int novedad) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.fecha = fecha;
		this.categoria = categoria;
		this.novedad = novedad;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getCategoria() {
		return categoria;
	}

	public int getNovedad() {
		return novedad;
	}

	
}
