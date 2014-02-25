package app.prueba.caso_android.dropbox;

import android.app.Activity;
import android.content.Context;

import com.dropbox.sync.android.DbxAccountManager;

public class DBoxConnection {
	
	
	static final int REQUEST_LINK_TO_DBX = 0;

	//Objeto necesario para conectar la cuenta
	private DbxAccountManager mAccountManager;
	
	DBoxConnection (Activity applicationContext,String APP_KEY, String APP_SECRET){
		
		 mAccountManager = DbxAccountManager.getInstance(applicationContext, APP_KEY, APP_SECRET);
		 
		 //Inicializamos el flujo
		 mAccountManager.startLink(applicationContext, REQUEST_LINK_TO_DBX);
		 
		 if (mAccountManager.hasLinkedAccount()) {
			 
		 }else{
			 
		 }
	}
	
	
}
