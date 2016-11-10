package com.znufe.ui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Xieyi extends Activity{
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.xieyi);
		
		back=(Button)findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Xieyi.this, NumChecked.class);
				startActivity(it);
				finish();
			}
		});
	}
}
