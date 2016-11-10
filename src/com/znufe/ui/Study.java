package com.znufe.ui;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znufe.dbm.Dao;
import com.znufe.dbm.DBManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class Study extends TabActivity implements OnCheckedChangeListener{
    private RadioGroup studytab;
    private TabHost tabhost;
    private DBManager dbm;
    private String xh;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.study);
			
			Intent intent = getIntent();  
	        xh = intent.getStringExtra("num"); 
	        
        studytab=(RadioGroup)findViewById(R.id.radioGroup1);
        studytab.setOnCheckedChangeListener(this);
        
        tabhost = this.getTabHost();
        
        Intent richeng = new Intent(this, Study_richeng.class);
        richeng.putExtra("num", xh);
        tabhost.addTab(tabhost.newTabSpec("richeng")
                .setIndicator(getResources().getString(R.string.richeng), getResources().getDrawable(R.drawable.icon_study_richeng_on))
                .setContent(richeng));
        
        Intent zixi = new Intent(this, Study_zixi.class);
        zixi.putExtra("num", xh);
        tabhost.addTab(tabhost.newTabSpec("zixi")
                .setIndicator(getResources().getString(R.string.zixi), getResources().getDrawable(R.drawable.icon_study_zixi_on))
                .setContent(zixi));
        
        Intent fuxi = new Intent(this, Study_fuxi.class);
        fuxi.putExtra("num", xh);
        tabhost.addTab(tabhost.newTabSpec("fuxi")
                .setIndicator(getResources().getString(R.string.fuxi), getResources().getDrawable(R.drawable.icon_study_fuxi_on))
                .setContent(fuxi));

    }
   

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    	if(checkedId==R.id.radio_button0||checkedId==R.id.radio_button1)
        {
        	if(checkedId==R.id.radio_button0)
        		{tabhost.setCurrentTabByTag("richeng");}
        	else
        		tabhost.setCurrentTabByTag("zixi");} 
        else 
        	tabhost.setCurrentTabByTag("fuxi");
     }
   
    
    
}
