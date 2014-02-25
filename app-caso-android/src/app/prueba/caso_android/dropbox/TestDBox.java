package app.prueba.caso_android.dropbox;

import java.util.List;

import android.graphics.Bitmap;
import app.prueba.caso_android.epub.BookItem;

public class TestDBox implements IDBoxConnection{
	
	public static String[] librosTest={"libro1", "libro2","libro3"};

	@Override
	public List<BookItem> getListaBooks() {
		
		
		return null;
	}

	@Override
	public Bitmap getCaratulaFromFile(String filename) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
