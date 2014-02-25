package app.prueba.caso_android.epub;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.prueba.caso_android.util.Constants;

public class BookItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373047714186518163L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
	
	private String nombre;
	private String fecha;
	
	
	public BookItem(String nombre){
		this.nombre=nombre;
	}	
	
	public BookItem(String nombre, String fecha) {
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public BookItem(String name, Date modifiedTime) {
		this.nombre = name;
		this.fecha = dateFormat.format(modifiedTime);
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

	public String getNombreShort() {
		if(nombre.length()>Constants.MAX_TITLE_LENGHT)
			return nombre.substring(0,Constants.MAX_TITLE_LENGHT-2)+"...";
		
		return nombre;
	
	}


	
	

}
