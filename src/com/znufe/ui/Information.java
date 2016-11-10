package com.znufe.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.znufe.dbm.DBManager;
import com.znufe.moder.User;

public class Information extends Activity {
	
	private DBManager dbm ;
	
	private Button backbutton;//返回首界面按钮
	private Button quedingbutton;//确定按钮
	private Spinner spinnerXY;//学院选项
	private Spinner spinnerZY;//专业选项
	private Spinner spinnerQS;//寝室选项
    private Context context; //
    private String[] XY;//学院项目
    private String[] ZY;//专业项目
    private String[] QS;//寝室项目
    
    private EditText xingming,banji;
    private RadioGroup xingbie;
    //user信息
	String xm=null,xb="男",xy=null,zy=null,qs=null,mm=null;
	String bj=null,xuehao=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_information);

        context = this; 
        dbm=new DBManager(context);  
        
        Intent intent = getIntent();  
        xuehao = intent.getStringExtra("num");  
        mm= intent.getStringExtra("psw");
                
        XY = getResources().getStringArray(R.array.XueYuan);
        QS = getResources().getStringArray(R.array.qinshi);
        //下拉菜单传值
        spinnerQS = (Spinner) findViewById(R.id.spinner_qinshi); 
        ArrayAdapter<String> adapterQS = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,QS);  
        spinnerQS.setAdapter(adapterQS);
        adapterQS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //寝室
        spinnerQS.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0)
					qs=null;
				else
					qs=QS[arg2];
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub		
				qs=null;
			}
        	
        });

        
        xingming=(EditText) findViewById(R.id.editText1);
        banji=(EditText) findViewById(R.id.editText_banji);
		       
        xingbie=(RadioGroup) findViewById(R.id.radioGroup1);
        //性别
        xingbie.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(arg1==R.id.radio0)
					xb="男";else if(arg1==R.id.radio1)xb="女";
			}});

        spinnerXY = (Spinner) findViewById(R.id.spinner_xueyuaun); 
        ArrayAdapter<String> adapterXY = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,XY);  
        spinnerXY.setAdapter(adapterXY); 
        adapterXY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //学院，专业
        spinnerXY.setOnItemSelectedListener(spinnerSelectedListener_zy);  
        
        backbutton=(Button)findViewById(R.id.button_info_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Information.this, Main_Enter.class);
				startActivity(it);
				finish();
			}
		});
        
        quedingbutton=(Button)findViewById(R.id.button_info_queding);
        quedingbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//姓名、班级
		        xm=xingming.getText().toString().trim();	
		        bj=banji.getText().toString().trim();		        
				register();	
			}
		});
    }
    
    //注册
    public void register(){   	
	
		if(xm.equals("")==false&&xb!=null&&bj.equals("")==false&&
				xy!=null&&zy!=null&&qs!=null&&!xuehao.equals("")&&mm.equals("")==false){
			dbm.openDatabase();
			int count = dbm.findUserById(xuehao);
			if(count!=0){
				Toast.makeText(this, "此学号已注册",
						Toast.LENGTH_SHORT).show();
				return ;
			}
			//初始化用户
			User user=new User();
			user.setUser_No(xuehao);
			user.setUser_Psw(mm);		
			user.setClassNum(bj);
			user.setCollege(xy);
			user.setDomitory(qs);
			user.setField(zy);
			//user.setPrivacyLv('0');
			user.setSex(xb);
			user.setUser_Name(xm);

		long flag = dbm.addUser(user);
		dbm.closeDatabase();
		if (flag == -1) {
			Toast.makeText(this, "注册失败",
					Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "注册成功",
					Toast.LENGTH_SHORT).show();
			Intent it = new Intent(Information.this, Registered.class);
			it.putExtra("num", xuehao);  
    		it.putExtra("psw", mm);
			startActivity(it);
			finish();
		}}else Toast.makeText(Information.this, "信息不能为空", Toast.LENGTH_SHORT)
		.show();
		}
    
    
    //关联学院与专业
    private Spinner.OnItemSelectedListener spinnerSelectedListener_zy = new Spinner.OnItemSelectedListener()    
    {    
    	
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,    
                long arg3) {
        	
        	if(arg2!=0)
            	xy=XY[arg2];
            	else xy=null;

        	switch(arg2){
        	case 0:ZY= getResources().getStringArray(R.array.Unknow) ;break;
        	case 1:ZY= getResources().getStringArray(R.array.GongChengzhuanye) ;break;
        	case 2:ZY= getResources().getStringArray( R.array.TongJizhuanye);break;
        	case 3:ZY=getResources().getStringArray(R.array.GongGongzhuanye);break;
        	case 4:ZY=getResources().getStringArray(R.array.KuaiJizhuanye);break;
        	case 5:ZY=getResources().getStringArray(R.array.XinWenzhuanye);break;
        	case 6:ZY=getResources().getStringArray(R.array.WaiYuzhuanye);break;
        	case 7:ZY=getResources().getStringArray(R.array.XingSizhuanye);break;
        	case 8:ZY=getResources().getStringArray(R.array.FaXuezhuanye);break;
        	case 9:ZY=getResources().getStringArray(R.array.JingRongzhuanye);break;
        	case 10:ZY=getResources().getStringArray(R.array.JingJizhuanye);break;
        	case 11:ZY=getResources().getStringArray(R.array.CaiShuizhuanye);break;
        	case 12:ZY=getResources().getStringArray(R.array.ZheXuezhuanye);break;
        	case 13:ZY=getResources().getStringArray(R.array.GongShangzhuanye);break;
        	}
            spinnerZY = (Spinner) findViewById(R.id.spinner_zhuangye);    
            ArrayAdapter<String> adapterZY = new ArrayAdapter<String>(context,  
                    android.R.layout.simple_spinner_item, ZY);  
            spinnerZY.setAdapter(adapterZY); 
            adapterZY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerZY.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
    			@Override
    			public void onItemSelected(AdapterView<?> arg0, View arg1,
    					int arg2, long arg3) {
    				// TODO Auto-generated method stub
    				if(arg2==0)
    					zy=null;
    				else
    					zy=ZY[arg2];
    			}
    			@Override
    			public void onNothingSelected(AdapterView<?> arg0) {
    				// TODO Auto-generated method stub		
    				zy=null;
    			}
            	
            });

        }    
            
        public void onNothingSelected(AdapterView<?> arg0) {    
            // TODO Auto-generated method stub     
        }           
    };  

   
}
