package com.uerj;

import com.uerj.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class AboutActivity extends Activity{
	
	
	
	private void about(){
		setContentView(R.layout.about);
//		TextView txTitulo = (TextView)findViewById(R.id.titulo);
//		txTitulo.setTextColor(Color.GREEN);
//		TextView txSobre = (TextView)findViewById(R.id.sobre);
//		txSobre.setTextColor(Color.GREEN);
//		TextView txContato = (TextView)findViewById(R.id.contato);
//		txContato.setTextColor(Color.GREEN);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		about();
	}	


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			startActivity(new Intent(this,RidMobileTab.class));
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
