package com.znufe.ui;



import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class SearchResult extends Activity {
	
	private Button btn;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
 
		setContentView(R.layout.search_result);       
		
		btn = (Button)findViewById(R.id.search_result_btn);
	}
}
