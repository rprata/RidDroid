package com.uerj;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import com.uerj.R;

public class RidMobileTab extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);

		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec; 
		Intent intent;  
				
		intent = new Intent().setClass(this, RidTabActivity.class);
		tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		View rid = createTabView(this, "RID");
		spec = tabHost.newTabSpec("rid").setIndicator(rid).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, NotasTabActivity.class);
		View notas = createTabView(this, "Notas do Período");
		spec = tabHost.newTabSpec("notas").setIndicator(notas).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, HistoricoTabActivity.class);
		View historico = createTabView(this, "Histórico");
		spec = tabHost.newTabSpec("historico").setIndicator(historico).setContent(intent);
		tabHost.addTab(spec);


		tabHost.setCurrentTab(0);

	}

	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}


}
