package app.prueba.caso_android.dropbox;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import app.prueba.caso_android.epub.BookItem;



/**
 * @author fjnavarro
 *	Interfaz que tiene los metodos a implementar las clases que usemos para dropbox u otros interfaces externos
 */
public interface IDBoxConnection {

	
	/**
	 * @return Devuelve e listado de los libros que hay almacenados en esta conexion
	 * @throws Exception
	 */
	public ArrayList<BookItem> getListaBooks() throws Exception;
	
	


	/**
	 * Metodo en el que le pasamos el nombre de  un fichero y nos devuelve la caratula en bitmap
	 * 
	 * @param filename - nombre del fichero fisico epub 
	 * @return Devuelve la caratula
	 * @throws Exception
	 */
	public Bitmap getCaratulaFromFile(String filename) throws Exception;
	
}
