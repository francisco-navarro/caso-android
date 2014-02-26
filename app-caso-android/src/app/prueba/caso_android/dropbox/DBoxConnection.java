package app.prueba.caso_android.dropbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import app.prueba.caso_android.epub.BookItem;
import app.prueba.caso_android.epub.ReaderEpubs;
import app.prueba.caso_android.epub.util.EpubFilter;

import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxException.Unauthorized;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileInfo;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;
import com.dropbox.sync.android.DbxPath.InvalidPathException;

public class DBoxConnection implements IDBoxConnection {
	
	static public int countStatus=0;
	
	static final int REQUEST_LINK_TO_DBX = 0;

	//Objetos necesarios para conectar la cuenta
	private DbxAccountManager mAccountManager;
	private DbxFileSystem dbxFs;
	
	//Lista con todos los libros
	private ArrayList<BookItem> listado;	
	
	//HashMap con el nombre del fichero y la info del fichero para luego acceder a la caratula
	private HashMap<String,DbxFileInfo> listadoEpub;
	
	DBoxConnection (Activity activity,String APP_KEY, String APP_SECRET) throws Unauthorized{
		
		 mAccountManager = DbxAccountManager.getInstance(activity.getApplicationContext(), APP_KEY, APP_SECRET);
		
		 //Comprobamos si tiene una cuenta ya enlazada
		 if (mAccountManager.hasLinkedAccount()) {
			 //Ya hay cuenta enlazada, no hacemos nada
		 }else{
			 //Si no hay cuenta inicializamos el flujo
			 mAccountManager.startLink(activity, REQUEST_LINK_TO_DBX);			 
		 }
		 
		 //Asignamos el objeto filesysetm que usaremos para esta cuenta dropbox
		 dbxFs = DbxFileSystem.forAccount(mAccountManager.getLinkedAccount());
	}
	
	

	/* (non-Javadoc)
	 * @see app.prueba.caso_android.dropbox.IDBoxConnection#getListaBooks()
	 */
	@Override
	public ArrayList<BookItem> getListaBooks() throws Exception {
		
		//Si no se ha inicializado, el listado se recorren los directorios en busca de epub
		if(listadoEpub==null){
			
			listadoEpub=new HashMap<String, DbxFileInfo>();
			DbxPath raiz= new DbxPath("/");
			//Una vez recorridos los guarda en un hashmap por nombre del fichero como keys
			recorrerDirectorio(raiz);
			
		}else{
			return listado;
		}
		
		
		listado=new ArrayList<BookItem>();
		
		for(String name: listadoEpub.keySet()){
			countStatus++;
			//Cojo del hashmap la informacion del fichero
			DbxFileInfo fileInfo = listadoEpub.get(name);
			//Abro el fichero de dropbox
			DbxFile  file = dbxFs.open(fileInfo.path) ;
			//Leemos el fichero con la clase del reader de epubs
			BookItem libro = ReaderEpubs.leerLibro(file.getReadStream());			
			//Si el nombre del epub esta vacio, ponemos el del fichero
			if(libro.getNombre()==null)
				libro.setNombre(fileInfo.path.getName());
			libro.setFilename(fileInfo.path.getName());
			//Cerramos el objeto del fichero
			file.close();
			libro.setRuta(fileInfo.path);
			libro.setFecha(fileInfo.modifiedTime);
			listado.add(libro);
			
		}		
		
		return listado;
	}


	
	/**
	 *  Metodo recursivo donde sacamos un listado de todos los directorios que hay en dropbox y los acumulamos en listadoEpub
	 * 
	 * @param dir - Objeto con el path del directorio
	 * @throws InvalidPathException
	 * @throws DbxException
	 */
	private void recorrerDirectorio(DbxPath dir) throws InvalidPathException, DbxException {
		
		for(DbxFileInfo file :  dbxFs.listFolder(dir)){
			
			if(file.isFolder){
				//Si es directorio, lo recorremos recursivamente
				recorrerDirectorio(file.path);
			}else if(EpubFilter.isValidEpub(file.path.getName())){
				//Si es un tipo de fichero epub, lo guardamos en un hashmap por nombre del fichero como keys
				listadoEpub.put(file.path.getName(), file);
			}
		}
		
	}
	
	/** 
	 * Metodo que nos devuelve 
	 * @param filename - nombre del fichero del libro epub
	 */
	public Bitmap getCaratulaFromFile(String filename){
		
		Bitmap caratula=null;
		
		//Cojo del hashmap la informacion del fichero
		DbxFileInfo fileInfo = listadoEpub.get(filename);
		if(fileInfo!=null){
			//Abro el fichero de dropbox
			try {
				DbxFile  file = dbxFs.open(fileInfo.path) ;
				caratula=ReaderEpubs.leerCaratula(file.getReadStream());
				file.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}

		}
		
		return caratula;
		
	}



	
	
}
