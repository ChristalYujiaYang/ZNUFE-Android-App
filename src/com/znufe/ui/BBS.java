package com.znufe.ui;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class BBS extends Activity {

	 private ViewPager mPager;//页卡内容
	    private List<View> listViews; // Tab页面列表
	    private TextView t1, t2;// 页卡头标
	    private int currIndex = 0;// 当前页卡编号
	    private RadioGroup title;
	    private RadioButton baike,guanzhu;
	    private Button teachersbt,coursesbt,placesbt,searchbt;
	   private EditText scontent;//搜索框
	   private ToggleButton kecheng,haoyou;
	   private ListView listkc,listhy;//关注列表
		private View vbaike,vguanzhu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.bbs);
		
		
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		t1.setOnClickListener(new TitleListener(0));
	    t2.setOnClickListener(new TitleListener(1));

		InitViewPager();
	}

	//title选择监听
   public class TitleListener implements View.OnClickListener {
	  
	        private int index = 0;
	        public TitleListener(int i) {
	            index = i;
	        }
	        @Override
	        public void onClick(View v) {
	            mPager.setCurrentItem(index);
	        }
	    }
	
   // 初始化ViewPager
   private void InitViewPager() {
       mPager = (ViewPager) findViewById(R.id.vPager1);
       listViews = new ArrayList<View>();
       LayoutInflater mInflater = getLayoutInflater();
       InitBaike();
       InitGuanzhu();
       listViews.add(vbaike);      
       listViews.add(vguanzhu);
       mPager.setAdapter(new MyPagerAdapter(listViews));
       mPager.setCurrentItem(0);
       
	    Resources resource = (Resources) getBaseContext().getResources();
		ColorStateList white = (ColorStateList) resource.getColorStateList(R.color.text_white);
		ColorStateList green = (ColorStateList) resource.getColorStateList(R.color.text_green);
       t1.setTextColor(white);
       t2.setBackgroundResource(R.drawable.bbs_choose_bt);
       mPager.setOnPageChangeListener(new MyOnPageChangeListener());
   }
   
  //ViewPager适配器

   public class MyPagerAdapter extends PagerAdapter {
       public List<View> mListViews;

       public MyPagerAdapter(List<View> mListViews) {
           this.mListViews = mListViews;
       }

       @Override
       public void destroyItem(View arg0, int arg1, Object arg2) {
           ((ViewPager) arg0).removeView(mListViews.get(arg1));
       }

       @Override
       public void finishUpdate(View arg0) {
       }

       @Override
       public int getCount() {
           return mListViews.size();
       }

       @Override
       public Object instantiateItem(View arg0, int arg1) {
           ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
           return mListViews.get(arg1);
       }

       @Override
       public boolean isViewFromObject(View arg0, Object arg1) {
           return arg0 == (arg1);
       }

       @Override
       public void restoreState(Parcelable arg0, ClassLoader arg1) {
       }

       @Override
       public Parcelable saveState() {
           return null;
       }

       @Override
       public void startUpdate(View arg0) {
       }
   }

   public class MyOnPageChangeListener implements OnPageChangeListener {


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onPageSelected(int arg0) {
		 
	    Resources resource = (Resources) getBaseContext().getResources();
		ColorStateList white = (ColorStateList) resource.getColorStateList(R.color.text_white);
		ColorStateList green = (ColorStateList) resource.getColorStateList(R.color.text_green);
		// TODO Auto-generated method stub
		if(arg0==0){
			t2.setBackgroundResource(R.drawable.bbs_choose_bt);
			t2.setTextColor(green);
			t1.setBackgroundResource(R.drawable.bbs_choose_bt_on);	
			t1.setTextColor(white);
			}
		else if(arg0==1){
			t1.setBackgroundResource(R.drawable.bbs_choose_bt);
			t1.setTextColor(green);
			t2.setBackgroundResource(R.drawable.bbs_choose_bt_on);
			t2.setTextColor(white);
			}
		currIndex = arg0;

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub	
	}}
   //初始化guanzhu页面
   public void InitGuanzhu(){
	   vguanzhu=getLayoutInflater().inflate(R.layout.bbs_guanzhu, null,true);
		   
	   //列表
	   listkc=(ListView)vbaike.findViewById(R.id.listView_course);
	   listhy=(ListView)vbaike.findViewById(R.id.listView1_freinds);
	   //推荐
	   kecheng=(ToggleButton)vbaike.findViewById(R.id.guanzhu_course);
	   haoyou=(ToggleButton)vbaike.findViewById(R.id.guanzhu_freinds);
   }
   //初始化baike页面
   public void InitBaike(){
	   vbaike=getLayoutInflater().inflate(R.layout.baike, null,true);
	   //搜索框
	   scontent=(EditText)vbaike.findViewById(R.id.search_content);
	   searchbt=(Button)vbaike.findViewById(R.id.button_search);
	   //推荐
	   teachersbt=(Button)vbaike.findViewById(R.id.bbs_teacher);
	   teachersbt.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) { 
	 		// TODO Auto-generated method stub 		 		
	 		Intent hot_teacher=new Intent(BBS.this,Hot_teacher.class);
	 		startActivity(hot_teacher);
		}});
	   coursesbt=(Button)vbaike.findViewById(R.id.bbs_course);
	   placesbt=(Button)vbaike.findViewById(R.id.bbs_place);
   }
}
