package com.uerj;

import com.uerj.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class NotasPageActivity extends Activity{

	private static final FrameLayout.LayoutParams ZOOM_PARAMS =
			new FrameLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT,
					Gravity.CENTER|Gravity.BOTTOM);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ridpage);
		PostRid postRid = new PostRid(NotasTabActivity.getMatricula());
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.getSettings().setSupportZoom(true);       
		myWebView.getSettings().setBuiltInZoomControls(true);
		FrameLayout mContentView = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
		final View zoom = myWebView.getZoomControls();
		mContentView.addView(zoom, ZOOM_PARAMS);
		zoom.setVisibility(View.GONE);
		myWebView.postUrl(postRid.getUrlNotas(), postRid.postData());

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
