package com.znufe.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 

public class MyCourse extends Activity {
	
	private Button btn1;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_course); 
		btn1=(Button)findViewById(R.id.my_course_btn1); 
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyCourse.this, Infomation.class)); 
			}
		});
		
	}
}