package app.prueba.caso_android.dropbox;

import java.util.List;

import android.graphics.Bitmap;
import app.prueba.caso_android.epub.BookItem;


//Clase interfaz que contengan los metodos que llamaremos
public interface IDBoxConnection {

	
	public List<BookItem> getListaBooks() throws Exception;

	public Bitmap getCaratulaFromFile(String filename) throws Exception;
	
}
