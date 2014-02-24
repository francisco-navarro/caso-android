package app.prueba.caso_android.epub;

import java.io.Serializable;

public class BookItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373047714186518163L;
	private String id;
	private String nombre;
	private String fecha;
	
	public BookItem(String nombre){
		this.nombre=nombre;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	

}
