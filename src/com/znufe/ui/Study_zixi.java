package com.znufe.ui;

import com.znufe.dbm.DBManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Study_zixi extends Activity {
	private Button add;
	View contentView;
	View maincontent;
	private ImageButton xz;
	private ImageButton xb;
	private TextView tianjia;
	private ImageView jiantou;
	private DBManager dbm;
	private String xh;
	private PopupWindow zixistyle_popupWindow;

	protected void onCreate(Bundle savedInstanceState) {
		dbm = new DBManager(this);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.zixi_noplan);

		Intent intent = getIntent();
		xh = intent.getStringExtra("num");

		add = (Button) findViewById(R.id.button_zixi_add);
		tianjia = (TextView) findViewById(R.id.textView_tianjia);
		jiantou = (ImageView) findViewById(R.id.imageView_jiantou);

		init();
		setListener();
		
	}

	@SuppressLint("NewApi")
	private void init() {
		contentView = getLayoutInflater().inflate(R.layout.zixi_planstyle,
				null, true);
		xz = (ImageButton) contentView.findViewById(R.id.xz_style);
		xb = (ImageButton) contentView.findViewById(R.id.xb_style);

		// PopupWindow弹出的窗口显示的view,第二和第三参数：分别表示此弹出窗口的大小
		zixistyle_popupWindow = new PopupWindow(contentView,
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
		zixistyle_popupWindow.setBackgroundDrawable(new BitmapDrawable());
		zixistyle_popupWindow.setOutsideTouchable(true);
		zixistyle_popupWindow.setAnimationStyle(R.style.pop_add_style);
	}

	private void setListener() {
		contentView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				zixistyle_popupWindow.dismiss();
			}
		});

		add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {

					if (zixistyle_popupWindow.isShowing()) {

						zixistyle_popupWindow.dismiss();
						tianjia.setText(R.string.zixi_tianjia);
						jiantou.setImageResource(R.drawable.jiantou);
					}
					zixistyle_popupWindow.showAsDropDown(add, -4, -4);
					tianjia.setText(R.string.no);
					jiantou.setImageResource(R.drawable.top);

					xz.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(),
									"你选择了学渣style", Toast.LENGTH_SHORT).show();

							Intent xz_intent = new Intent(Study_zixi.this,
									Zixi_plan_style_xz.class);
							xz_intent.putExtra("num", xh);
							startActivity(xz_intent);

						}
					});
					xb.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(),
									"你选择了学霸style", Toast.LENGTH_SHORT).show();

							Intent xb_intent = new Intent(Study_zixi.this,
									Zixi_plan_style_xb.class);
							xb_intent.putExtra("num", xh);
							startActivity(xb_intent);
						}
					});

				} catch (Exception e) {
					Toast.makeText(Study_zixi.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}

			}

		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (zixistyle_popupWindow != null
					&& zixistyle_popupWindow.isShowing()) {
				zixistyle_popupWindow.dismiss();
				tianjia.setText(R.string.zixi_tianjia);
				jiantou.setImageResource(R.drawable.jiantou);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);

	}

}
