package app.prueba.caso_android.android;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import app.prueba.caso_android.R;
import app.prueba.caso_android.dropbox.DBoxConnectionFactory;
import app.prueba.caso_android.dropbox.IDBoxConnection;



public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//Iniciamos una conexion de la aplicación con el factory a dropbox
		new doLoginAsyncTask(this).execute();
	}
	
	public void doEntrar(View view) {
		
	}
	
	//Clase que usamos para lanzar en segundo plano la conexion con dropbox
	private class doLoginAsyncTask extends AsyncTask<String, Void, Void> {
		
		private Activity activity;
		
		private doLoginAsyncTask(Activity activity){
			this.activity=activity;
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected Void doInBackground(String... params) {
			
			IDBoxConnection conn=DBoxConnectionFactory.initConnection(activity,null,null);	
			try {
				conn.getListaBooks();
			} catch (Exception e) {
				//Mostrar el error que no se ha podido cargar el listado de libros
				
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			//Cuando acabe la conexion iniciamos la siguiente actividad
			Intent mIntent = new Intent(getApplicationContext(),
					ListBookActivity.class);
			startActivity(mIntent);
		}
	}
	
}
