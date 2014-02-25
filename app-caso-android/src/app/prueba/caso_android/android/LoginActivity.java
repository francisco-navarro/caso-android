package app.prueba.caso_android.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import app.prueba.caso_android.R;
import app.prueba.caso_android.dropbox.DBoxConnectionFactory;



public class LoginActivity extends Activity {
	
	private TextView textViewUser;
	private TextView textViewPass;
	
	static final int REQUEST_LINK_TO_DBX = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		textViewUser = (TextView) findViewById(R.id.login_usuario);
		textViewPass = (TextView) findViewById(R.id.login_pass);
		
		DBoxConnectionFactory.initConnection(this,null,null);
	}
	
	public void doEntrar(View view) {
		Intent mIntent = new Intent(getApplicationContext(),
				ListBookActivity.class);
		startActivity(mIntent);
	}
	
	public void doCancelar(View view) {
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_LINK_TO_DBX) {
	        if (resultCode == Activity.RESULT_OK) {
	            // ... Start using Dropbox files.
	        } else {
	            // ... Link failed or was cancelled by the user.
	        }
	    } else {
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	}
}
