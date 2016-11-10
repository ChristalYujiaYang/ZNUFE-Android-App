package com.znufe.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.znufe.dbm.DBManager;

public class MyPage extends Activity {

	private DBManager dbm;

	private TableLayout mypage_table;
	private TextView mypage_tv1;
	private TextView mypage_tv2;
	private TableRow mypage_tr1;
	private TableRow mypage_tr2;
	private EditText mypage_et;
	private Button mypage_btn0;
	private Button mypage_btn1;
	private Button mypage_btn2;
	private Button mypage_btn3;
	private Button mypage_btn4;
	private Button mypage_btn5;
	private Button mypage_btn6;
	private TextView XMXB, ZYBJ;

	String myXh;
	String strXh, a;
	String xm, xb, zy, bj;

	protected void onResume() {
		super.onResume();
		dbm.openDatabase();
		Cursor cur = dbm.getUser(myXh);

		if (cur.moveToFirst()) {
			mypage_tv1.setText(cur.getString(1) + " " + cur.getString(3));
			mypage_tv2.setText(cur.getString(4) + cur.getString(7));
		}
		cur.close();
		dbm.closeDatabase();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_page);
		dbm = new DBManager(this);
		dbm.openDatabase();

		XMXB = (TextView) findViewById(R.id.textView1);
		ZYBJ = (TextView) findViewById(R.id.textView2);

		Intent intent = getIntent();
		strXh = intent.getStringExtra("num");
		myXh = strXh;
		// if(!strXh.equals("")){
		// xh=Integer.parseInt(strXh);
		// xm=dbm.getStringByColumnName("User_name", strXh);
		// xb=dbm.getStringByColumnName("sex", strXh);
		// zy=dbm.getStringByColumnName("field", strXh);
		// bj=dbm.getStringByColumnName("classNum", strXh);

		// XMXB.setText(xm+" "+xb);
		// ZYBJ.setText(zy+" "+bj);
		// }else {
		// XMXB.setText("ÇëµÇÂ¼");
		// ZYBJ.setText("");}

		mypage_et = (EditText) findViewById(R.id.mypage_et);
		mypage_tv1 = (TextView) findViewById(R.id.my_page_tv1);
		mypage_tv2 = (TextView) findViewById(R.id.my_page_tv2);
		mypage_tr1 = (TableRow) findViewById(R.id.mypage_tr1);
		mypage_tr2 = (TableRow) findViewById(R.id.mypage_tr2);
		mypage_table = (TableLayout) findViewById(R.id.mypage_table);
		mypage_btn0 = (Button) findViewById(R.id.mypage_btn0);
		mypage_btn1 = (Button) findViewById(R.id.mypage_btn1);
		mypage_btn2 = (Button) findViewById(R.id.mypage_btn2);
		mypage_btn3 = (Button) findViewById(R.id.mypage_btn3);
		mypage_btn4 = (Button) findViewById(R.id.mypage_btn4);
		mypage_btn5 = (Button) findViewById(R.id.mypage_btn5);
		mypage_btn6 = (Button) findViewById(R.id.mypage_btn6);

		redraw(this);

		Cursor cur = dbm.getUser(myXh);

		if (cur.moveToFirst()) {
			mypage_tv1.setText(cur.getString(1) + " " + cur.getString(3));
			mypage_tv2.setText(cur.getString(4) + cur.getString(7));
		}
		cur.close();
		dbm.closeDatabase();

		mypage_btn0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyPage.this, EditInfo.class);
				intent.putExtra("Xh", myXh);
				startActivity(intent);
			}
		});

		mypage_et.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (KeyEvent.KEYCODE_ENTER == keyCode
						&& event.getAction() == KeyEvent.ACTION_DOWN
						&& !mypage_et.getText().toString().trim().equals("")) {
					startActivity(new Intent(MyPage.this, SearchResult.class));
					return true;
				}
				return false;
			}
		});

		mypage_et.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (KeyEvent.KEYCODE_ENTER == keyCode
						&& event.getAction() == KeyEvent.ACTION_DOWN
						&& !mypage_et.getText().toString().trim().equals("")) {
					startActivity(new Intent(MyPage.this, SearchResult.class));
					return true;
				}
				return false;
			}
		});

		mypage_et.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (KeyEvent.KEYCODE_ENTER == keyCode
						&& event.getAction() == KeyEvent.ACTION_DOWN
						&& !mypage_et.getText().toString().trim().equals("")) {
					startActivity(new Intent(MyPage.this, SearchResult.class));
					return true;
				}
				return false;
			}
		});

		mypage_btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyPage.this, IConcern.class);
				intent.putExtra("Xh", myXh);
				startActivity(intent);
			}
		});

		mypage_btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyPage.this, ConcernMe.class);
				intent.putExtra("Xh", myXh);
				startActivity(intent);
			}
		});

		mypage_btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyPage.this, MyCourse.class));
			}
		});
		dbm.closeDatabase();

	}

	public void redraw(final Context ct) {
		ViewTreeObserver vto = mypage_btn1.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				int btn_width = mypage_btn2.getMeasuredWidth();
				LinearLayout.LayoutParams lp3 = (android.widget.LinearLayout.LayoutParams) mypage_table
						.getLayoutParams();
				lp3.height = dip2px(ct, btn_width + 44);
				// info_et.setText(Float.toString(btn_width)+Float.toString(aaa));
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}
