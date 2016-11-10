package com.znufe.ui;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.znufe.dbm.DBManager;
import com.znufe.ui.R.id;

public class Plan_example extends Activity {
	DBManager dbm;
	private LinearLayout[] date;// 星期,第几节
	private LinearLayout[] zx;
	private TextView[] kc;// 教室
	private ToggleButton[] nz;// 闹钟
	private int[] ids;
	private String[] table;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.zixi_plan);
		dbm = new DBManager(this);

		table = new String[25];
		getZixi("1109030208");
		ids = new int[5];
		ids[0] = R.id.zixi_class1;
		ids[1] = R.id.zixi_class2;
		ids[2] = R.id.zixi_class3;
		ids[3] = R.id.zixi_class4;
		ids[4] = R.id.zixi_class5;

		date = new LinearLayout[5];
		date[0] = (LinearLayout) findViewById(R.id.mon);
		date[1] = (LinearLayout) findViewById(R.id.tues);
		date[2] = (LinearLayout) findViewById(R.id.wed);
		date[3] = (LinearLayout) findViewById(R.id.thus);
		date[4] = (LinearLayout) findViewById(R.id.fri);
		zx = new LinearLayout[25];
		kc = new TextView[25];
		nz = new ToggleButton[25];

		setPlan();
	}

	public void setPlan() {
		for (int m = 0; m < 5; m++) {
			for (int n = 0; n < 5; n++) {
				zx[m * 5 + n] = (LinearLayout) date[m].findViewById(ids[n]);
				kc[m * 5 + n] = (TextView) zx[m * 5 + n]
						.findViewById(R.id.plance1);
				kc[m * 5 + n].setText(table[m*5+n]);
				nz[m * 5 + n] = (ToggleButton) zx[m * 5 + n]
						.findViewById(R.id.ring1);
				if (!(table[m * 5 + n].equals("有课")))
					nz[m * 5 + n].setChecked(true);
			}
		}

	}

	public String[] getZixi(String user_no) {
		String dormitary;
		String building = null;
		String classroom = null;
		dbm.openDatabase();
		Cursor c = dbm.getUser(user_no);
		dormitary = c.getString(5);
		c.close();
		if (dormitary.equals("滨湖")) {
			building = "文波";
		} else if (dormitary.equals("环湖")) {
			building = "文泰";
		} else if (dormitary.equals("中区")) {
			building = "文泰";
		} else if (dormitary.equals("临湖")) {
			building = "文澜";
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				String time = Integer.toString((i + 1) * 10 + j + 1);
				Cursor c1 = dbm.getUserSubject(user_no, time);
				if (c1.getCount() == 0) {
					if (building.equals("文波")) {
						Cursor c2 = dbm.getWenboClassroom(time);
						if (c2.getCount() > 0) {
							classroom = c2.getString(1);
						}
						c2.close();
					} else if (building.equals("文泰")) {
						Cursor c3 = dbm.getWentaiClassroom(time);
						if (c3.getCount() > 0) {
							classroom = c3.getString(1);
						}
						c3.close();
					} else if (building.equals("文澜")) {
						Cursor c4 = dbm.getWenlanClassroom(time);
						if (c4.getCount() > 0) {
							classroom = c4.getString(1);
							c4.close();
						}
					}
					table[5 * i + j] = building + classroom;
				} else {
					table[5 * i + j] = "有课";
				}
				c1.close();
			}
		}
		dbm.closeDatabase();
		return table;
	}

}