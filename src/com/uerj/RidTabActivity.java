package com.uerj;

import java.io.File;

import com.uerj.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class RidTabActivity extends Activity {
	/** Called when the activity is first created. */

	private static String matricula;
	private AutoCompleteTextView txMatricula;
	Button btEnviar;
	private String matriculas[];
	
	public void setMatricula(String mat){		
		matricula = mat;
	}

	static String getMatricula(){		
		return matricula;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ridtab);
		txMatricula = (AutoCompleteTextView)findViewById(R.id.matricula);
		Button btEnviar = (Button)findViewById(R.id.enviar);

		File ridMobileDir = new File(Environment.getExternalStorageDirectory()+"/RIDMobile/");
		if (!ridMobileDir.isDirectory()){
			ridMobileDir.mkdir();
			new HistoryFile();
		}
		else{
			 matricula = new String();
			 matriculas = HistoryFile.loadHistory();
			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, matriculas);
			 txMatricula.setAdapter(adapter);
		}
		
		
		btEnviar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!HistoryFile.verificaMatricula(txMatricula.getText().toString())){
					Log.i("RIDMobile", "****************** Não existe matricula");
					HistoryFile.AdcionaMatricula(txMatricula.getText().toString());
				}
				ConnectivityManager coManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
				if(coManager.getAllNetworkInfo()[0].getState()==State.CONNECTED||coManager.getAllNetworkInfo()[1].getState()==State.CONNECTED) {
					if(txMatricula.getText().length()==12){
			    		setMatricula(txMatricula.getText().toString());
			    		startActivity(new Intent(v.getContext(),RidPageActivity.class));
			    		finish();
			    	}
			    	else{
						Toast.makeText(v.getContext(), "Insira uma matricula válida (digite apenas números)", Toast.LENGTH_LONG).show();
			    	}
				}
				else if(coManager.getAllNetworkInfo()[0].getState()==State.CONNECTING||coManager.getAllNetworkInfo()[1].getState()==State.CONNECTING) {
					Toast.makeText(v.getContext(), "Espere, está conectando...", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(v.getContext(), "Por favor, conecte-se na internet", Toast.LENGTH_LONG).show();
				}
			}
		});		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stubnetworkType
		super.onConfigurationChanged(newConfig);
		txMatricula.setText(txMatricula.getText().toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_inicio,menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	    switch (item.getItemId()) {
	    case R.id.clear:
	    	HistoryFile.clearHistory();
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item,HistoryFile.loadHistory());
			txMatricula.setAdapter(adapter);
	    	return true;
	    case R.id.about:
	    	startActivity(new Intent(this,AboutActivity.class));
			finish();
	    	return  true;
	    case R.id.exit:
	    	System.exit(1);
	    	return true;
	    default:
	    	return super.onOptionsItemSelected(item);
	    }

	}

}		
