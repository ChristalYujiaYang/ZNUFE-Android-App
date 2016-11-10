package com.znufe.ui;

import android.app.Activity; 
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class Infomation extends Activity {
	 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomation);
         
        TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();
        
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("详细信息")
                .setContent(R.id.tab1));
         
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("普通评论")
                .setContent(R.id.tab2));
         
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("精华评论")
                .setContent(R.id.tab3));
        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i =0; i < tabWidget.getChildCount(); i++) {  
        	tabWidget.getChildAt(i).getLayoutParams().height = 80;    
            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(15);
            tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
        }
	}
	 
}