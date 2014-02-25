package app.prueba.caso_android.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import app.prueba.caso_android.R;
import app.prueba.caso_android.dropbox.DBoxConnectionFactory;
import app.prueba.caso_android.util.Constants;




public class BookDetailActivity extends Activity {
	
	private Bitmap caratula=null;
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_detail);
		
		Bundle bundle = getIntent().getExtras();
		String filename=bundle.getString(Constants.PARAM_FILENAME);
		
		try{
			//Recogemos de dropbox la caratula
			caratula=DBoxConnectionFactory.getConnection().getCaratulaFromFile(filename);
		} catch (Exception e) {			
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "No se pueden cargar la lista de libros", Toast.LENGTH_SHORT).show();
		}
		
		imageView=(ImageView)findViewById(R.id.imageCover);
		imageView.setImageBitmap(caratula);
	}
	
}
