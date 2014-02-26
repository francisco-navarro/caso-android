package app.prueba.caso_android.epub;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.dropbox.sync.android.DbxPath;

import app.prueba.caso_android.util.Constants;

/**
 * @author fjnavarrol
 * Clase que usaremos como bean para contener los datos de un libro
 */
public class BookItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373047714186518163L;
	
	//Con este formatter parsearemos la fecha tal y como la queramos poner en constantes
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
	
	private String nombre;
	private String fecha;
	private String filename;
	private DbxPath ruta;
	
	
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

	//Aquí recogemos el nombre recortado si se sale de la pantalla, dependiendo de la constante de max length
	public String getNombreShort() {
		if(nombre.length()>Constants.MAX_TITLE_LENGHT)
			return nombre.substring(0,Constants.MAX_TITLE_LENGHT-3)+"...";
		
		return nombre;
	
	}

	public DbxPath getRuta() {
		return ruta;
	}

	public void setRuta(DbxPath ruta) {
		this.ruta = ruta;
	}

	public void setFecha(Date modifiedTime) {
		this.fecha = dateFormat.format(modifiedTime);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String toString(){
		return nombre;
	}
	
	/**
	 * @param another Objeto a comparar
	 * @param orden - 
	 * @return Devuelve:
	 * 	Si son iguales 0
	 *  Si el objeto es mayor 1
	 *  Si el objeto es menor al que le pasamos -1
	 */
	public int compareTo(BookItem another,int orden) {
		// Metodo en el que le decimos si el objeto es anterior o no segun un campo
		if(orden==Constants.ORDER_BY_DATE){
			
			try {
				Date time=dateFormat.parse(this.getFecha());
				Date time2=dateFormat.parse(another.getFecha());
				
				return (int) (time.getTime()-time2.getTime());
				
			} catch (ParseException e) {
				
			}
			
			
		}else if(orden==Constants.ORDER_BY_NAME){
			return this.getNombre().compareTo(
					another.getNombre());
		}
		return 0;
	}
	

	
	public static ArrayList<BookItem> ordernarlista(ArrayList<BookItem> lista, int orden){
		
		BookItem[] ordenacion= lista.toArray(new BookItem[0]);
		BookItem aux;
		
		//Hacemos la ordenacion de la burbuja
		for(int i=0;i<lista.size();i++){
			for(int j=0+i;j<lista.size();j++){
				//Hago la comparacion
				if( ordenacion[i].compareTo( ordenacion[j], orden)>0){
					//Es mayor el i, por lo que lo ponemos posteriormente
					aux=ordenacion[i];
					ordenacion[i]=ordenacion[j];
					ordenacion[j]=aux;
				}
			}
		}
		
		//Devolvemos un nuevo arraylist con el array que hemos ordenado
		return new ArrayList<BookItem>(Arrays.asList(ordenacion));
	}

	
	

}
