package com.znufe.ui;
 

import com.znufe.dbm.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class IConcern extends Activity {
	
	private DBManager dbm;
	String strXh;
	
	private Button btn1;
	private Button btn2;
	private LinearLayout gclist;
	private LinearLayout jrlist;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.i_concern);
		
		gclist = (LinearLayout)findViewById(R.id.gclist);
		jrlist = (LinearLayout)findViewById(R.id.jrlist);
		btn1 = (Button)findViewById(R.id.i_concern_btn1); 
		Intent intent=getIntent();
		strXh = intent.getStringExtra("Xh");
		dbm=new DBManager(this);
		dbm.openDatabase();
		Cursor cur = dbm.getIConcern(strXh);  
		cur.moveToFirst();  
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 160, 1);
		lp.setMargins(10, 10, 10, 10);
		for (int i = 0; i < cur.getCount(); ++i )
		{  
			final Cursor cur2 = dbm.getUser(cur.getString(0));
			Button btn = new Button(this); 
			btn.setTextSize(16);  
			btn.setLayoutParams(lp);
			btn.setGravity(Gravity.LEFT + Gravity.CENTER_HORIZONTAL);
			btn.setBackgroundResource(R.drawable.shape1);
			String str = cur2.getString(1) + "\n\n" + cur2.getString(4) + cur2.getString(7);
			btn.setText(str); 
			btn.setOnClickListener(new View.OnClickListener() {
        	@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub 
        		Intent intent = new Intent(IConcern.this, HisPage.class);
        		
        		intent.putExtra( "id", cur2.getString(0));
        		startActivity(intent);  
				}
	        });
        
			if (cur2.getString(6).equals("工程学院"))
				gclist.addView(btn);
			else 
				jrlist.addView(btn); 
			if (i != cur.getCount() - 1)
				cur.moveToNext();
		} 
		
		btn1.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 这里是取消按钮
        		finish();
			}
        });
		cur.close();
		dbm.closeDatabase();
	/* 	btn2.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 
        		startActivity(new Intent(IConcern.this, HisPage.class)); 
			}
        });
		*/
	}
}
