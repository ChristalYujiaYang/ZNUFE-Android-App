package com.znufe.ui;



import android.app.Activity; 
import android.os.Bundle; 
import android.view.MotionEvent; 
import android.view.View; 
import android.view.View.OnClickListener; 
import android.widget.RelativeLayout;
import android.widget.Toast; 

public class Edit_pop extends Activity{
	private RelativeLayout layout; 
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
	super.onCreate(savedInstanceState); 
	SysAplication.getInstance().addActivity(this);
	setContentView(R.layout.richeng_edit); 
	layout=(RelativeLayout)findViewById(R.id.RelativeLayout1); 
	layout.setOnClickListener(new OnClickListener() { 
	@Override 
	public void onClick(View v) { 
	// TODO Auto-generated method stub 
	Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
	Toast.LENGTH_SHORT).show(); 
	} 
	}); 
	} 
	@Override 
	public boolean onTouchEvent(MotionEvent event){ 
	finish(); 
	return true; 
	} 

}
