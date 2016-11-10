package com.znufe.ui;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.Toast;

import com.znufe.dbm.DBManager;
public class MainActivity extends TabActivity implements OnCheckedChangeListener{
    private RadioGroup mainTab;//底部按钮
    private RadioButton home,classroom;
    private TabHost tabhost;//显示界面
    private DBManager dbm;
    String xh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);//显示主功能界面
        
        Intent intent = getIntent();  
         xh = intent.getStringExtra("num");  
        String mm = intent.getStringExtra("psw");  
        
        home=(RadioButton)findViewById(R.id.radio_button4);
        classroom=(RadioButton)findViewById(R.id.radio_button1);
        if(xh.equals("")){home.setChecked(false);
        classroom.setChecked(true);
        }
        
        
        mainTab=(RadioGroup)findViewById(R.id.main_tab);
        mainTab.setOnCheckedChangeListener(this);
        //添加跳转信息
        
        Intent mainpage=new Intent(this,MyPage.class);
        mainpage.putExtra("num", xh);
        tabhost = this.getTabHost();
        
        if(!xh.equals("")){
        tabhost.addTab(tabhost.newTabSpec("mypage")
                .setIndicator(getResources().getString(R.string.main), 
                		getResources().getDrawable(R.drawable.mainpage))
                .setContent(mainpage));
        }
        
        
        Intent classroom = new Intent(this, Classroom.class);
        classroom.putExtra("num", xh);
        tabhost.addTab(tabhost.newTabSpec("classroom")
                .setIndicator(getResources().getString(R.string.classroom), 
                		getResources().getDrawable(R.drawable.classroom))
                .setContent(classroom));
        
        
        Intent more = new Intent(this, More.class);
        more.putExtra("num", xh);
        tabhost.addTab(tabhost.newTabSpec("more")
                .setIndicator(getResources().getString(R.string.more),
                		getResources().getDrawable(R.drawable.more))
                .setContent(more));        
       
        Intent study = new Intent(this, Study.class);
        study.putExtra("num", xh);
        tabhost.addTab(tabhost.newTabSpec("study")
                .setIndicator(getResources().getString(R.string.study),
                		getResources().getDrawable(R.drawable.study))
                .setContent(study));
        
       Intent bbs = new Intent(this,BBS.class);
       bbs.putExtra("num", xh);
        tabhost.addTab(tabhost.newTabSpec("exchangeinformation")
                .setIndicator(getResources().getString(R.string.exchangeinformation),
                		getResources().getDrawable(R.drawable.exchangeinformation))
                .setContent(bbs));
 }
   
    //点击底部按钮显示相应界面
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if(checkedId==R.id.radio_button0||checkedId==R.id.radio_button1)
        {
        	if(checkedId==R.id.radio_button0)
        	{tabhost.setCurrentTabByTag("more");}
        	else
        		tabhost.setCurrentTabByTag("classroom");}
        else if(checkedId==R.id.radio_button2||checkedId==R.id.radio_button3)
        {
        	if(checkedId==R.id.radio_button2)
        		{
        		if(!xh.equals("")){tabhost.setCurrentTabByTag("study");
                }else Toast.makeText(this, "请登录", Toast.LENGTH_SHORT);}
        	else
        		if(!xh.equals("")){tabhost.setCurrentTabByTag("exchangeinformation");
        		}else Toast.makeText(this, "请登录", Toast.LENGTH_SHORT);} 
        else 
        	if(!xh.equals("")){tabhost.setCurrentTabByTag("mypage");}
        	else Toast.makeText(this, "请登录", Toast.LENGTH_SHORT);
     }
   
}
