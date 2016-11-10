package com.znufe.ui;



import com.znufe.dbm.DBManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressWarnings("deprecation")
public class Zixi_plan_style_xb  extends Activity{
	
	private Gallery gallery;
	private Button quxiao;
	private Button queding;
	View contentView;
	private PopupWindow add_popupWindow;
	private Button add;
	private RadioGroup style;
	private DBManager dbm;
	private String xh;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.zixi_plan_xb);
		
		dbm=new DBManager(this);
		dbm.openDatabase();
		
		Intent intent = getIntent();  
         xh = intent.getStringExtra("num"); 
         
		add=(Button)findViewById(R.id.bt_add);
		gallery=(Gallery)findViewById(R.id.gallery_xb);
		gallery.setAdapter(new ImageAdapter(this));	
		
		gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?>parent,View v,int position,long id){
				switch(position){
				case 0:Toast.makeText(Zixi_plan_style_xb.this, "你选择了plan1", Toast.LENGTH_SHORT)
				.show();
				Intent plan1 = new Intent(Zixi_plan_style_xb.this, Plan_example.class);
				plan1.putExtra("num", xh);
				startActivity(plan1);
				break;
				case 1:Toast.makeText(Zixi_plan_style_xb.this, "你选择了plan2", Toast.LENGTH_SHORT)
				.show();
				Intent plan2 = new Intent(Zixi_plan_style_xb.this, Plan_example.class);
				plan2.putExtra("num", xh);
				startActivity(plan2);
				break;
				case 2:Toast.makeText(Zixi_plan_style_xb.this, "你选择了plan3", Toast.LENGTH_SHORT)
				.show();
				Intent plan3 = new Intent(Zixi_plan_style_xb.this, Plan_example.class);
				plan3.putExtra("num", xh);
				startActivity(plan3);
				break;
				}
			}

			public void onNothingSelected(AdapterView<?>arg0){
				
				}
		});

		 init();
		 setListener();
	}
		class ImageAdapter extends BaseAdapter{
			private Integer[] mps={R.drawable.xueba_1,R.drawable.xueba_2,R.drawable.xueba_3};
			private Context mContext;
			public ImageAdapter(Context Context){
				mContext=Context;
			}
			public int getCount(){
				return mps.length;
			}
			public Object getItem(int position){
				return position;
			}
			public long getItemId(int position){
				return position;
			}
			public View getView(int position,View convertView,ViewGroup parent){
				ImageView image=new ImageView(mContext);
				image.setImageResource(mps[position]);
				image.setAdjustViewBounds(true);
				image.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				return image;
				
			} 
		} 

		@SuppressLint("NewApi")
		private void init() {
			contentView = getLayoutInflater().inflate(R.layout.add_zixi_plan_xb, null,
					true);
					  quxiao = (Button) contentView.findViewById(R.id.button_yes);
					  queding = (Button) contentView.findViewById(R.id.button_no);
					  style= (RadioGroup) contentView.findViewById(R.id.radioGroup1);
					//radioButton按钮样式
					  RadioButton a,b,c;
					  a=(RadioButton) contentView.findViewById(R.id.radio0);
					  b=(RadioButton) contentView.findViewById(R.id.radio1);
					  c=(RadioButton) contentView.findViewById(R.id.radio2);
					  a.setButtonDrawable(R.drawable.radio_bt);
					  b.setButtonDrawable(R.drawable.radio_bt);
					  c.setButtonDrawable(R.drawable.radio_bt);
					  
					// PopupWindow弹出的窗口显示的view,第二和第三参数：分别表示此弹出窗口的大小
					add_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT, true);

					  add_popupWindow.setBackgroundDrawable(new BitmapDrawable());//有了这句才可以点击返回（撤销）按钮dismiss()popwindow
					  add_popupWindow.setOutsideTouchable(true);
					  add_popupWindow.setAnimationStyle(R.style.pop_style);
		}
		
		private void setListener() {
			  contentView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
			// TODO Auto-generated method stub

			    add_popupWindow.dismiss();
			}
			});
			// m_popupWindow = new PopupWindow();
			  
			  
			  
			  add.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						try {

						if (add_popupWindow.isShowing()) {

						      add_popupWindow.dismiss();
						}
						add_popupWindow.showAtLocation(gallery, BIND_ABOVE_CLIENT, 10, 10);
						     
						     style.setOnCheckedChangeListener(new OnCheckedChangeListener() {  
						    	 @Override  
						    	 public void onCheckedChanged(RadioGroup group, int checkedId) {  
						    	 // 根据ID判断选择的按钮  
						    	 if (checkedId == R.id.radio0||checkedId == R.id.radio1) {                    
						    		 if(checkedId == R.id.radio0){
						    			 Toast.makeText(Zixi_plan_style_xb.this, "学霸之王", Toast.LENGTH_SHORT)
										.show(); 					    	
						    	 } else {  
						    		 Toast.makeText(Zixi_plan_style_xb.this, "超级学霸", Toast.LENGTH_SHORT)
										.show();  
						    	 } Toast.makeText(Zixi_plan_style_xb.this, "普通学霸", Toast.LENGTH_SHORT)
									.show(); 
						    	 } }}); 
						     
						     queding.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
									    add_popupWindow.dismiss();
									Toast.makeText(Zixi_plan_style_xb.this, "已保存", Toast.LENGTH_SHORT)
									.show();
									}

									});
						     quxiao.setOnClickListener(new OnClickListener() {
						 		@Override
						 		public void onClick(View v) {
						 		    add_popupWindow.dismiss();
						 		}

						 		});
						} catch (Exception e) {
						Toast.makeText(Zixi_plan_style_xb.this, e.getMessage(),
						Toast.LENGTH_SHORT);
						}
						}
						});
		}
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (add_popupWindow != null && add_popupWindow.isShowing()) {
			    add_popupWindow.dismiss();
			return true;
			}
			}
			return super.onKeyDown(keyCode, event);

			}
}