package com.znufe.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.znufe.dbm.DBManager;
public class Login extends Activity {
	private DBManager dbm ;
	
	Button youke_button;
	Button backbutton,denglu;
	private EditText xuehao,mima;
	private CheckBox checkbox;
	String mm;
	String strXh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_login);
        
        dbm=new DBManager(this);
			dbm.openDatabase();
        
        xuehao=(EditText) findViewById(R.id.editText_login_xuehao);
		 mima=(EditText) findViewById(R.id.editText_login_psw); 
     
		checkbox=(CheckBox)findViewById(R.id.checkbox_login);
	    checkbox.setButtonDrawable(R.drawable.checkbox_selector);
	    
        backbutton=(Button)findViewById(R.id.button_login_quxiao);
        backbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Login.this, Main_Enter.class);
				startActivity(it);				
			}
		});
        
        this.youke_button=(Button)findViewById(R.id.button_login_youke);
        this.youke_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Login.this, MainActivity.class);
				strXh="";
				it.putExtra("num", strXh);  
	    		it.putExtra("psw", mm);
				startActivity(it);				
			}
		});
        
        denglu=(Button)findViewById(R.id.button_login_Login);
        denglu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				login();
			}
			});
		 dbm.closeDatabase();
    }  
    public void login(){
		
    	strXh=xuehao.getText().toString().trim();
		 mm=mima.getText().toString().trim();
		
		 if(!strXh.equals("")&&!mm.equals("")){
			 dbm.openDatabase();
			 int count=dbm.findUserByIdAndPwd(strXh, mm);
			 if(count==1){
	    		 String xm=dbm.getStringByColumnName("User_name", strXh);
	    		 Toast.makeText(Login.this, xm+" µ«¬Ω≥…π¶", Toast.LENGTH_SHORT).show();
	    		 Intent it = new Intent(Login.this, MainActivity.class);
	    		 it.putExtra("num", strXh);  
	    		 it.putExtra("psw", mm);
	    		 startActivity(it);	
		}else if(count==0){
			Toast.makeText(Login.this, "—ß∫≈ªÚ√‹¬Î¥ÌŒÛ", Toast.LENGTH_SHORT).show();
	   	
	    }
		 }else {
				Toast.makeText(Login.this, "«ÎÃÓ–¥—ß∫≈√‹¬Î", Toast.LENGTH_SHORT).show();
		 }

    }
}

