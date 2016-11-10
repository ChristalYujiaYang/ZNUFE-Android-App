package com.znufe.ui;

import com.znufe.dbm.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Study_fuxi extends Activity {

	private DBManager dbm;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fixi_list);

		Intent intent = getIntent();
		String xh = intent.getStringExtra("num");

	}
}
