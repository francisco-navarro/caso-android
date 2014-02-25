package app.prueba.caso_android.dropbox;

import android.app.Activity;


public class DBoxConnectionFactory {
	
	private static final String APP_KEY="blvvs7v37225pkg";
	private static final String APP_SECRET="th7q1y9cbcsq1d8";
	
	public static DBoxConnection initConnection (Activity applicationContext,String login, String pwd){
		
		DBoxConnection conn= new  DBoxConnection(applicationContext,APP_KEY,APP_SECRET);
		
		return conn;
	}

}
