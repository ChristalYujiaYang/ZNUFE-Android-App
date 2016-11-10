package com.znufe.ui;

import com.znufe.dbm.DBManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow; 
import android.widget.TextView;

public class HisPage extends Activity {

	private DBManager dbm;
	private String myXh;
	private String strXh; 
	
	private TableLayout table;
	private TextView tv_title;
	private TextView tv1;
	private TextView tv2;
	private TableRow tr1;
	private TableRow tr2; 
	private EditText et;
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4; 
	private Button btn5;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.his_page);  
		myXh = "1";
		Intent intent = getIntent();  
		strXh = intent.getStringExtra("id"); 
		
		et = (EditText)findViewById(R.id.mypage_et);
		tv_title = (TextView)findViewById(R.id.his_page_title);
		tv1 = (TextView)findViewById(R.id.his_page_tv1);
		tv2 = (TextView)findViewById(R.id.his_page_tv2);
		tr1 = (TableRow)findViewById(R.id.mypage_tr1);
		tr2 = (TableRow)findViewById(R.id.mypage_tr2);  
		table = (TableLayout)findViewById(R.id.his_page_table); 
		btn0 = (Button)findViewById(R.id.his_page_btn0); 
		btn1 = (Button)findViewById(R.id.his_page_btn1); 
		btn2 = (Button)findViewById(R.id.his_page_btn2); 
		btn3 = (Button)findViewById(R.id.his_page_btn3); 
		btn4 = (Button)findViewById(R.id.his_page_btn4); 
		btn5 = (Button)findViewById(R.id.his_page_btn5);
		
		redraw(this);
		
		
		dbm=new DBManager(this);
		dbm.openDatabase();
		Cursor cur = dbm.getUser(strXh);  
		  
		if (cur.moveToFirst())  
		{
			tv_title.setText(cur.getString(1));
			tv1.setText(cur.getString(1) + " " + cur.getString(3));
			tv2.setText(cur.getString(4) + cur.getString(7));
		}
		
		cur = dbm.hasConcerned(myXh, strXh);
		if (cur.getCount() == 0) {
			btn5.setText("添加关注"); 
		}
		else {
			btn5.setText("取消关注"); 
		} 
		
		dbm.closeDatabase();
		
		btn0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 
				finish();
			}
		});
		
		btn5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub  
				dbm.openDatabase();
				Cursor cur = dbm.hasConcerned(myXh, strXh);
				if (cur.getCount() == 0) {
					btn5.setText("取消关注");
					dbm.addFriend(Integer.toString((int)(Math.random()*1000)), myXh, strXh, "0");
				}
				else {
					btn5.setText("添加关注");
					dbm.deleteFriend(cur.getString(0));
				} 
				dbm.closeDatabase(); 
			}
		});
	} 
	
	public void redraw(final Context ct){  
		ViewTreeObserver vto = btn1.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() { 
                int btn_width = btn2.getMeasuredWidth();   
            	LinearLayout.LayoutParams lp3 = (android.widget.LinearLayout.LayoutParams) table.getLayoutParams();
            	lp3.height = dip2px(ct, btn_width + 44);  
            	// info_et.setText(Float.toString(btn_width)+Float.toString(aaa));
                return true;
            }	
        });  
	}
	
	public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }
	
}
