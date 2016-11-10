package com.znufe.ui;

import java.util.LinkedList; 
import java.util.List; 
import android.app.Activity; 
import android.app.AlertDialog; 
import android.app.Application; 
import android.content.DialogInterface; 
import android.content.Intent; 
 
public class SysAplication extends Application { 
   
	private  List<Activity>mList =new LinkedList(); 
    private static SysAplication instance; 
 
    private SysAplication() {   
    } 
    public synchronized static SysAplication getInstance() { 
        if (null == instance) { 
            instance = new SysAplication(); 
        } 
        return instance; 
    } 
    // add Activity  
    public void addActivity(Activity activity) { 
        mList.add(activity); 
    } 
 
    public void exit() { 
        try { 
            for (Activity activity : mList) { 
                if (activity != null) 
                    activity.finish(); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            System.exit(0); 
        } 
    } 
    public void onLowMemory() { 
        super.onLowMemory();     
        System.gc(); 
    }  
}
