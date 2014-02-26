package app.prueba.caso_android.android;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import app.prueba.caso_android.R;
import app.prueba.caso_android.android.adapters.ListBookAdapter;
import app.prueba.caso_android.dropbox.DBoxConnectionFactory;
import app.prueba.caso_android.dropbox.TestDBox;
import app.prueba.caso_android.epub.BookItem;
import app.prueba.caso_android.util.Constants;



public class ListBookActivity extends Activity {
	
	private ListView listaLibros;
	//La declaramos estatica para que solo la inicialice una vez
	private static ArrayList<BookItem> libros;
	
	private  int posicionClick=-1;

	private long DELAY=0,INTERVAL=400;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booklist);

		try{
			//Recogemos de dropbox la lista de libros
			libros= DBoxConnectionFactory.getConnection().getListaBooks();
		} catch (Exception e) {			
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "No se pueden cargar la lista de libros", Toast.LENGTH_SHORT).show();
		}

		
		//Obtenemos el objeto list view de la actividad
		listaLibros = (ListView) findViewById(R.id.listViewBooks);
		//Inicializamos el list view
		mostrarDatosLista();
		
		
	}
	
	
	private void mostrarDatosLista() {



		//Le ponemos una cabecera al list view
		View header = getLayoutInflater().inflate(R.layout.list_view_header,
				null);
		listaLibros.addHeaderView(header, null, false);

		//Inicializamos el adaptador para el list view
		listaLibros
		.setAdapter(new ListBookAdapter(this,libros));
		listaLibros.getSelectedItemPosition();
		listaLibros.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		//Le añadimos los listener de los clicks
		listaLibros.setOnItemClickListener(new BookClickListener() );


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu, sacamos del menu en xml las opciones
		getMenuInflater().inflate(R.menu.list_book, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_settings_name:
	            ordenarLibros(Constants.ORDER_BY_NAME);
	            return true;
	        case R.id.menu_settings_date:
	        	 ordenarLibros(Constants.ORDER_BY_DATE);
	            return true;	            
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	
	private void ordenarLibros(int orderBy) {
		
		libros=BookItem.ordernarlista(libros, orderBy);
		listaLibros
			.setAdapter(new ListBookAdapter(this,libros));
		listaLibros.invalidateViews();
		
		
	}


	private class BookClickListener implements OnItemClickListener{		
		

		@Override
		public void onItemClick(final AdapterView<?> adapterView,
				final View view, final int position, final long ident) {
			view.setSelected(true);

			int prevClick=posicionClick;			
			posicionClick = position - 1;// Sacamos la posicion teneiendo en cuenta el header	
			
			
			//Si ha hecho click en la ultima posicion y hace menos del intervalo, consideramos doble click
			if(prevClick==posicionClick 
					&& System.currentTimeMillis()-DELAY<INTERVAL){
				
				DELAY=0;				
				
				Intent mIntent = new Intent(getApplicationContext(),
						BookDetailActivity.class);				
				
				//Le pasamos al intent de la nueva actividad el fichero del epub
				mIntent.putExtra(Constants.PARAM_FILENAME, libros.get(posicionClick).getFilename());
				startActivity(mIntent);				
				
			}else{
				DELAY=System.currentTimeMillis();
			}
		}		
		
	}	
	
	
	
	
	public void onBackPressed() {
		
		//Sobreescribimos el boton volver para que al pulsarlo salga un mensaje de si desea salir de la app
		 new AlertDialog.Builder(this)
         .setMessage("¿Desea salir de la aplicacion?")
         .setCancelable(false)
         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
            	salir();
             }
         })
         .setNegativeButton("No", null)
         .show();
	}


	protected void salir() {
		finish();
		System.exit(0);
	}
	
	
	
	
}
