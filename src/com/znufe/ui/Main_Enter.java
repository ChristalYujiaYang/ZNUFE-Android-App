package com.znufe.ui;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class Main_Enter extends Activity {

	ImageView enter_show_img;//����ͼƬ
	int index=0;
	int [] int_Image = {R.drawable.p2,R.drawable.p1,R.drawable.p3,R.drawable.p4,R.drawable.p5};  //����ͼƬ��Դ
    Button denglu_button;//��¼��ť
    Button zhuce_button;//ע�ᰴť
    Button youke_button;//�ο�ģʽ��ť
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main__enter);//��ʾ�׽���
     
        this.enter_show_img = (ImageView) this.findViewById(R.id.iv_show_image);  
        this.enter_show_img.setBackgroundResource(R.drawable.p2);
        
        autoShowImage();
        
        this.denglu_button=(Button)findViewById(R.id.button_mainenter_denglu);
        //��ת����½����
        this.denglu_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Main_Enter.this, Login.class);
				startActivity(it);				
			}
		});
        
        this.zhuce_button=(Button)findViewById(R.id.button_zhuce);
        //��ת��ע�����
        this.zhuce_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Main_Enter.this, NumChecked.class);
				startActivity(it);				
			}
		});
         
        this.youke_button=(Button)findViewById(R.id.button_mainenter_youke);
        //���ο�ģʽ����
        this.youke_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Main_Enter.this, MainActivity.class);
				String strXh ="",mm ="";
				it.putExtra("num", strXh);  
	    		it.putExtra("psw", mm);
				startActivity(it);				
			}
		});
        
    }
	//�Զ����ű���ͼƬ
	public void autoShowImage()
	{
        new Thread(new Runnable() {  
  
            @Override  
            public void run() {  
  
                while (true) {  
                    Message msg =  new Message();  
                    msg.obj = index;  
                    handler.sendMessage(msg);  
                    index ++; 
                    if(index >= int_Image.length){  
                        index = 0;  
                    }  
                    try {  
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {  
                        // TODO Auto-generated catch block   
                        e.printStackTrace();  
                    }  
                }  
            }  
        }).start();  
    }  
	
	public Handler handler = new Handler(){  
        public void handleMessage(android.os.Message msg) { 
        	enter_show_img.setBackgroundResource(int_Image[(Integer) msg.obj]);
        };  
    };
    
    private long exitTime = 0; 
    @Override 
    //˫�����ذ�ť�˳�ϵͳ
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
    	
    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){ 
    if((System.currentTimeMillis()-exitTime) > 2000){ 
    Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show(); 
    exitTime = System.currentTimeMillis(); 
    } else { 
    	Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        SysAplication.getInstance().exit();//�˳�����
    } 
    return true; 
    } 
    return super.onKeyDown(keyCode, event); 
    } 
    
    


}