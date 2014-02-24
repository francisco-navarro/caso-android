package app.prueba.caso_android.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import app.prueba.caso_android.R;



public class LoginActivity extends Activity {
	
	private TextView textViewUser;
	private TextView textViewPass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		textViewUser = (TextView) findViewById(R.id.login_usuario);
		textViewPass = (TextView) findViewById(R.id.login_pass);
	}
	
	public void doEntrar(View view) {
		Intent mIntent = new Intent(getApplicationContext(),
				ListBookActivity.class);
		startActivity(mIntent);
	}
	
	public void doCancelar(View view) {
		
	}
}
