package com.znufe.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

public class More extends Activity{
	private Button us,app,zhuxiao,exit;
	private Button quxiao;//取消exit按钮
	private Button queding;//确定exit按钮
	private PopupWindow m_popupWindow;
	View contentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.more);
		us=(Button)this.findViewById(R.id.button_zixi_add);
		us.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(More.this,AboutUs.class));
			}
		});
		app=(Button)this.findViewById(R.id.button2);
		app.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(More.this,Introduction.class));
			}
		});
		zhuxiao=(Button)this.findViewById(R.id.button3);
		zhuxiao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sp=More.this.getSharedPreferences("actm", Context.MODE_PRIVATE);//获得SharedPreferences对象 
				String uname=sp.getString(                 //从SharedPreferences中读取用户名 
						                   null,              //键值 
						                   null );            //默认值  
				if(uname==null){                       //判断uname是否为空 
					Toast.makeText(More.this, "请登录", Toast.LENGTH_SHORT).show();  
				    //gotoLoginView();
				    }                       
			}
		});
		exit=(Button)this.findViewById(R.id.button4);
		init();
		setListener();
		
	}
	
	@SuppressLint("NewApi")
	private void init() {
		contentView = getLayoutInflater().inflate(R.layout.exit, null,
				true);
				  quxiao = (Button) contentView.findViewById(R.id.bt_qx);
				  queding = (Button) contentView.findViewById(R.id.bt_qd);				  
				// PopupWindow弹出的窗口显示的view,第二和第三参数：分别表示此弹出窗口的大小
				m_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, true);

				  m_popupWindow.setBackgroundDrawable(new BitmapDrawable());//有了这句才可以点击返回（撤销）按钮dismiss()popwindow
				  m_popupWindow.setOutsideTouchable(true);
				  m_popupWindow.setAnimationStyle(R.style.pop_style);
	}
	private void setListener() {
		 
		  exit.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					try {

					if (m_popupWindow.isShowing()) {

					      m_popupWindow.dismiss();
					}
					     m_popupWindow.showAtLocation(getCurrentFocus(), BIND_AUTO_CREATE, 10, 10);
					     
					     queding.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Intent startMain = new Intent(Intent.ACTION_MAIN);
					                startMain.addCategory(Intent.CATEGORY_HOME);
					                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					                startActivity(startMain);
					                System.exit(0);//退出程序
								}
							});
					     quxiao.setOnClickListener(new OnClickListener() {
					 		@Override
					 		public void onClick(View v) {
					 		    m_popupWindow.dismiss();
					 		}

					 		});
					     
					     

					} catch (Exception e) {
					Toast.makeText(More.this, e.getMessage(),
					Toast.LENGTH_SHORT);
					}
					}
					});
		  }

}
