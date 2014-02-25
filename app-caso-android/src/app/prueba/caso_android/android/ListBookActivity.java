package app.prueba.caso_android.android;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import app.prueba.caso_android.R;
import app.prueba.caso_android.android.adapters.ListBookAdapter;
import app.prueba.caso_android.dropbox.TestDBox;



public class ListBookActivity extends Activity {
	
	private ListView listaLibros;
	
	private  int posicionClick=-1;

	private long DELAY=0,INTERVAL=400;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booklist);
		
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
				.setAdapter(new ListBookAdapter(this, TestDBox.librosTest));
		listaLibros.getSelectedItemPosition();
		listaLibros.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		listaLibros.setOnItemClickListener(new BookClickListener() );

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu, sacamos del menu en xml las opciones
		getMenuInflater().inflate(R.menu.list_book, menu);
		return true;
	}
	
	
	private class BookClickListener implements OnItemClickListener{		
		

		@Override
		public void onItemClick(final AdapterView<?> adapterView,
				final View view, final int position, final long ident) {
			view.setSelected(true);

			int prevClick=posicionClick;
			
			posicionClick = position - 1;// Teneiendo en cuenta el header	
			
			
			if(prevClick==posicionClick 
					&& System.currentTimeMillis()-DELAY<INTERVAL){
				
				DELAY=0;
				
				
				Intent mIntent = new Intent(getApplicationContext(),
						BookDetailActivity.class);
				mIntent.putExtra("idPosicion", posicionClick);
				startActivity(mIntent);
				
				
			}else{
				DELAY=System.currentTimeMillis();
			}
		}		
		
	}	
	
}
