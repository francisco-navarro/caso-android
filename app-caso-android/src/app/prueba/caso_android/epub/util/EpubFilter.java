package app.prueba.caso_android.epub.util;

public class EpubFilter {

	private static final String EXTENSION=".epub";
	
	
	//metodo publico en el que haremos las comprobaciones para decir si un fichero es epub
	public static boolean isValidEpub(String name){
		
		//solo comprobamos que el nombre del fichero acabe por la extension epub
		if(name.endsWith(EXTENSION))
			return true;
		
		return false;
		
	}
}
