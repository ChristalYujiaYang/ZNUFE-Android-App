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
	private Button quxiao;//ȡ��exit��ť
	private Button queding;//ȷ��exit��ť
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
				SharedPreferences sp=More.this.getSharedPreferences("actm", Context.MODE_PRIVATE);//���SharedPreferences���� 
				String uname=sp.getString(                 //��SharedPreferences�ж�ȡ�û��� 
						                   null,              //��ֵ 
						                   null );            //Ĭ��ֵ  
				if(uname==null){                       //�ж�uname�Ƿ�Ϊ�� 
					Toast.makeText(More.this, "���¼", Toast.LENGTH_SHORT).show();  
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
				// PopupWindow�����Ĵ�����ʾ��view,�ڶ��͵����������ֱ��ʾ�˵������ڵĴ�С
				m_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, true);

				  m_popupWindow.setBackgroundDrawable(new BitmapDrawable());//�������ſ��Ե�����أ���������ťdismiss()popwindow
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
					                System.exit(0);//�˳�����
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
