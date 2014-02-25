package app.prueba.caso_android.dropbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import app.prueba.caso_android.epub.BookItem;
import app.prueba.caso_android.epub.util.EpubFilter;

import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxException.Unauthorized;
import com.dropbox.sync.android.DbxFileInfo;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;
import com.dropbox.sync.android.DbxPath.InvalidPathException;

public class DBoxConnection implements IDBoxConnection {
	
	
	static final int REQUEST_LINK_TO_DBX = 0;

	//Objeto necesario para conectar la cuenta
	private DbxAccountManager mAccountManager;
	private DbxFileSystem dbxFs;
	
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
	
	

	@Override
	public List<BookItem> getListaBooks() throws Exception {
		
		//Si no se ha inicializado, el listado se recorren los directorios en busca de epub
		if(listadoEpub==null){
			
			listadoEpub=new HashMap<String, DbxFileInfo>();
			DbxPath raiz= new DbxPath("/");
			//Una vez recorridos los guarda en un hashmap por nombre del fichero como keys
			recorrerDirectorio(raiz);
		}
		
		
		ArrayList<BookItem> listado=new ArrayList<BookItem>();
		
		for(String name: listadoEpub.keySet()){
			
			DbxFileInfo file = listadoEpub.get(name);
			listado.add(
					new BookItem(file.path.getName(), 
							file.modifiedTime));
		}		
		
		return listado;
	}


	//Funcion recursiva donde sacamos un listado de todos los directorios que hay en dropbox y lo devolvemos en una lista
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
	
	
}
