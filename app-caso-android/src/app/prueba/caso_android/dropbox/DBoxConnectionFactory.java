package app.prueba.caso_android.dropbox;

import android.app.Activity;
import android.util.Log;
import app.prueba.caso_android.util.Constants;


public class DBoxConnectionFactory {

	
	
	private static IDBoxConnection conn=null;
	
	
	/**
	 * Metodo en el que llamamos a la interfaz de dropbox y la inicializamos
	 * Para probar en test mode devolvemos un objeto TestDBox
	 * @param applicationContext
	 * @param login
	 * @param pwd
	 * @return
	 */
	public static IDBoxConnection initConnection (Activity applicationContext,String login, String pwd){
		
		//Podemos implementar la aplicacion con otro interfaz que no sea dropbox si implementa IDBoxConnection
		//return new TestDBox();
		try{
			conn= new  DBoxConnection(applicationContext,Constants.APP_KEY,Constants.APP_SECRET);
		}catch(Exception e){
			Log.e("DBox", "No se pudo inicializar la conexion "+e.getMessage());
		}
		
		return conn;
	}
	
	public static IDBoxConnection getConnection(){
		return conn;
	}

}
