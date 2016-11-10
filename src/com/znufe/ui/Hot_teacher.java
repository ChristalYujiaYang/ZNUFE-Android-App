package com.znufe.ui;



import android.app.Activity;
import android.os.Bundle;

public class Hot_teacher extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.hot_teacher);
	}
}

