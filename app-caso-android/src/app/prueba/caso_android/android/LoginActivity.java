package app.prueba.caso_android.android;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import app.prueba.caso_android.R;
import app.prueba.caso_android.dropbox.DBoxConnection;
import app.prueba.caso_android.dropbox.DBoxConnectionFactory;
import app.prueba.caso_android.dropbox.IDBoxConnection;
import app.prueba.caso_android.util.Constants;



public class LoginActivity extends Activity {
	
	private TextView textoLogin;
	public Timer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		textoLogin=(TextView) findViewById(R.id.textLogin);
		
		//Iniciamos una conexion de la aplicación con el factory a dropbox
		new doLoginAsyncTask(this).execute();
	}
	
	public void doEntrar(View view) {
		
	}
	
	//Clase que usamos para lanzar en segundo plano la conexion con dropbox
	private class doLoginAsyncTask extends AsyncTask<String, Void, Void> {
		
		private static final int UPDATE_INTERVAL = 500;
		private LoginActivity activity;
		
		private doLoginAsyncTask(LoginActivity activity){
			this.activity=activity;
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected Void doInBackground(String... params) {
			
			IDBoxConnection conn=DBoxConnectionFactory.initConnection(activity,null,null);	
			try {
				//Vamos mostrando periodicamente los libros que se van cargando por feedback
					timer =new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {					  
				        @Override
				        public void run() {
				        	//Ponemos en la pantalla el texto Leyendo # libros
				        	setTextoInfo(Constants.READING.replace("#",""+DBoxConnection.countStatus));
				        }
				 
				    }, 0, UPDATE_INTERVAL);
					
				//Intentamos sacar de esa conexion todos los libros				
				conn.getListaBooks();
			} catch (Exception e) {
				//Error que no se ha podido cargar el listado de libros
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			//Paramos el timer que es el que nos muestra el texto de informacion
			if (timer != null) {
	            timer.cancel();
	        }
			
			//Cuando acabe la conexion iniciamos la siguiente actividad
			Intent mIntent = new Intent(getApplicationContext(),
					ListBookActivity.class);
			startActivity(mIntent);
		}
	}


	private void setTextoInfo(final String texto){
		//Como vamos a llamar a este metodo desde hilos distintos, usamos el runOnUiThread
		 runOnUiThread(new Runnable() {  
             @Override
             public void run() {
            	 textoLogin.setText(texto);
             }
         });
		
	}
	
}
