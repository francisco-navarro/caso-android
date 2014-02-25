package app.prueba.caso_android.epub;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;

public class ReaderEpubs {
	

	public static BookItem leerLibro( InputStream epubInputStream){
		
		BookItem libro=new BookItem("");
		try {
			
			
			//Abrimos el objeto del input stream		
			Book book = (new EpubReader()).readEpub(epubInputStream);
			
			libro.setNombre(book.getTitle());
			
			//Leer el cover del libro, no la vamos a cargar en la primera vez, para ahorrar memoria
			//Bitmap coverImage = BitmapFactory.decodeStream(book.getCoverImage() .getInputStream());			

			return libro;

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("epublib", e.getMessage());
			return null;
		}
		
	}
	
	public static Bitmap leerCaratula( InputStream epubInputStream){
		
		try {
			//Abrimos el objeto del input stream		
			Book book = (new EpubReader()).readEpub(epubInputStream);
			
			if(book.getCoverImage()!=null)
				return BitmapFactory.decodeStream(book.getCoverImage() .getInputStream());			

			
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("epublib", e.getMessage());
			return null;
		}
		return null;
	
	}

}
