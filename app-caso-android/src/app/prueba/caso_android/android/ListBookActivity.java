package app.prueba.caso_android.android;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import app.prueba.caso_android.R;
import app.prueba.caso_android.android.adapters.ListBookAdapter;
import app.prueba.caso_android.dropbox.TestDBox;



public class ListBookActivity extends Activity {
	
	private ListView listaLibros;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booklist);
		
		listaLibros = (ListView) findViewById(R.id.listViewBooks);
		
		mostrarDatosLista();
		
	}
	
	
	private void mostrarDatosLista() {
		
		View header = getLayoutInflater().inflate(R.layout.list_view_header,
				null);
		listaLibros.addHeaderView(header, null, false);
		listaLibros
				.setAdapter(new ListBookAdapter(this, TestDBox.librosTest));
		listaLibros.getSelectedItemPosition();
		listaLibros.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listaLibros.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(final AdapterView<?> adapterView,
					final View view, final int position, final long ident) {
				view.setSelected(true);

				int pos = position - 1;// Teneiendo en cuenta el header
				
				//TODO ABRIR EL FICHERO


			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_book, menu);
		return true;
	}
	
	
}
