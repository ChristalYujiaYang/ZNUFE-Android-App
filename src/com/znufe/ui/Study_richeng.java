package com.znufe.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.znufe.dbm.DBManager;

public class Study_richeng extends Activity {

	private DBManager dbm;
	
	private Button editbt1, editbt2, editbt3, editbt4, editbt5;// 备注按钮
	private Button quxiao;// 取消编辑按钮
	private Button queding;// 确定编辑按钮
	View contentView;
	private LinearLayout class1, class2, class3, class4, class5;
	private PopupWindow m_popupWindow;// 编辑备注弹窗
	private EditText edit_beizhu;
	private TextView bz1, bz2, bz3, bz4, bz5;// 备注
	private TextView time1, time2, time3, time4, time5;// 时间
	private TextView teacher1, teacher2, teacher3, teacher4, teacher5;// 教师
	private TextView cn1, cn2, cn3, cn4, cn5;// 课名
	private TextView cr1, cr2, cr3, cr4, cr5;// 教室
	private int nyear, nmonth, ndate, nday, ndays;
	private TextView date, day, zhouci;// 日期
	String xh;
	final Calendar c = Calendar.getInstance();

	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.study_richeng);

		dbm=new DBManager(this);
		Intent intent = getIntent();
		xh = intent.getStringExtra("num");

		date = (TextView) findViewById(R.id.textView_ymd);
		day = (TextView) findViewById(R.id.textView_day);
		zhouci = (TextView) findViewById(R.id.textView_zhouci);

		class1 = (LinearLayout) findViewById(R.id.class1_content);
		class2 = (LinearLayout) findViewById(R.id.class2_content);
		class3 = (LinearLayout) findViewById(R.id.class3_content);
		class4 = (LinearLayout) findViewById(R.id.class4_content);
		class5 = (LinearLayout) findViewById(R.id.class5_content);

		time1 = (TextView) class1.findViewById(R.id.time);
		time2 = (TextView) class2.findViewById(R.id.time);
		time3 = (TextView) class3.findViewById(R.id.time);
		time4 = (TextView) class4.findViewById(R.id.time);
		time5 = (TextView) class5.findViewById(R.id.time);

		teacher1 = (TextView) class1.findViewById(R.id.teacher);
		teacher2 = (TextView) class2.findViewById(R.id.teacher);
		teacher3 = (TextView) class3.findViewById(R.id.teacher);
		teacher4 = (TextView) class4.findViewById(R.id.teacher);
		teacher5 = (TextView) class5.findViewById(R.id.teacher);

		cn1 = (TextView) class1.findViewById(R.id.classname);
		cn2 = (TextView) class2.findViewById(R.id.classname);
		cn3 = (TextView) class3.findViewById(R.id.classname);
		cn4 = (TextView) class4.findViewById(R.id.classname);
		cn5 = (TextView) class5.findViewById(R.id.classname);

		cr1 = (TextView) class1.findViewById(R.id.classroom);
		cr2 = (TextView) class2.findViewById(R.id.classroom);
		cr3 = (TextView) class3.findViewById(R.id.classroom);
		cr4 = (TextView) class4.findViewById(R.id.classroom);
		cr5 = (TextView) class5.findViewById(R.id.classroom);

		editbt1 = (Button) findViewById(R.id.buttonedit_class1);
		bz1 = (TextView) class1.findViewById(R.id.beizhu);

		editbt2 = (Button) findViewById(R.id.buttonedit_class2);
		bz2 = (TextView) class2.findViewById(R.id.beizhu);

		editbt3 = (Button) findViewById(R.id.buttonedit_class3);
		bz3 = (TextView) class3.findViewById(R.id.beizhu);

		editbt4 = (Button) findViewById(R.id.buttonedit_class4);
		bz4 = (TextView) class4.findViewById(R.id.beizhu);

		editbt5 = (Button) findViewById(R.id.buttonedit_class5);
		bz5 = (TextView) class5.findViewById(R.id.beizhu);
		setDate();
		init();
		setListener();
		setclass();
	}

	private void setclass() {
		// 设置时间
		time1.setText("8:00-9:35");
		time2.setText("10:00-11:35");
		time3.setText("14:00-15:35");
		time4.setText("16:00-17:35");
		time5.setText("19:30-21:35");
		String a1 = Integer.toString(nday - 1);

		dbm.openDatabase();
		Cursor c1 = dbm.getUserSubject(xh, a1.trim() + '1');
		if (c1.getCount() == 0) {
			cn1.setText("");
			teacher1.setText("");
			cr1.setText("");
		} else {
			cn1.setText(c1.getString(2));
			teacher1.setText(c1.getString(4));
			cr1.setText(c1.getString(3));
		}
		c1.close();
		dbm.closeDatabase();

		dbm.openDatabase();
		Cursor c2 = dbm.getUserSubject(xh, a1.trim() + '2');
		if (c2.getCount() == 0) {
			cn2.setText("");
			teacher2.setText("");
			cr2.setText("");
		} else {
			cn2.setText(c2.getString(2));
			teacher2.setText(c2.getString(4));
			cr2.setText(c2.getString(3));
		}
		c2.close();
		dbm.closeDatabase();

		dbm.openDatabase();
		Cursor c3 = dbm.getUserSubject(xh, a1.trim() + '3');
		if (c3.getCount() == 0) {
			cn3.setText("");
			teacher3.setText("");
			cr3.setText("");
		} else {
			cn3.setText(c3.getString(2));
			teacher3.setText(c3.getString(4));
			cr3.setText(c3.getString(3));
		}
		c3.close();
		dbm.closeDatabase();

		dbm.openDatabase();
		Cursor c4 = dbm.getUserSubject(xh, a1.trim() + '4');
		if (c4.getCount() == 0) {
			cn4.setText("");
			teacher4.setText("");
			cr4.setText("");
		} else {
			cn4.setText(c4.getString(2));
			teacher4.setText(c4.getString(4));
			cr4.setText(c4.getString(3));
		}
		c4.close();
		dbm.closeDatabase();

		dbm.openDatabase();
		Cursor c5 = dbm.getUserSubject(xh, a1.trim() + '5');
		if (c5.getCount() == 0) {
			cn5.setText("");
			teacher5.setText("");
			cr5.setText("");
		} else {
			cn5.setText(c5.getString(2));
			teacher5.setText(c5.getString(4));
			cr5.setText(c5.getString(3));
		}
		c5.close();
		dbm.closeDatabase();
	}

	@SuppressLint("NewApi")
	private void init() {
		contentView = getLayoutInflater().inflate(R.layout.richeng_edit, null,
				true);
		quxiao = (Button) contentView.findViewById(R.id.button_quxiao);
		queding = (Button) contentView.findViewById(R.id.button_queding);
		edit_beizhu = (EditText) contentView.findViewById(R.id.editText1);

		// PopupWindow弹出的窗口显示的view,第二和第三参数：分别表示此弹出窗口的大小
		m_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, true);

		m_popupWindow.setBackgroundDrawable(new BitmapDrawable());// 有了这句才可以点击返回（撤销）按钮dismiss()popwindow
		m_popupWindow.setOutsideTouchable(true);
		m_popupWindow.setAnimationStyle(R.style.pop_style);

	}

	private void setListener() {
		contentView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				m_popupWindow.dismiss();
			}
		});
		editbt1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {

					if (m_popupWindow.isShowing()) {

						m_popupWindow.dismiss();
					}
					m_popupWindow.showAtLocation(getCurrentFocus(),
							BIND_AUTO_CREATE, 10, 10);

					queding.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							bz1.setText(edit_beizhu.getText().toString());
							m_popupWindow.dismiss();
							Toast.makeText(Study_richeng.this, "已保存",
									Toast.LENGTH_SHORT).show();
						}

					});
					quxiao.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							m_popupWindow.dismiss();
						}

					});

				} catch (Exception e) {
					Toast.makeText(Study_richeng.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}
			}
		});
		editbt2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {

					if (m_popupWindow.isShowing()) {

						m_popupWindow.dismiss();
					}
					m_popupWindow.showAtLocation(getCurrentFocus(),
							BIND_AUTO_CREATE, 10, 10);

					queding.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							bz2.setText(edit_beizhu.getText().toString());
							m_popupWindow.dismiss();
							Toast.makeText(Study_richeng.this, "已保存",
									Toast.LENGTH_SHORT).show();
						}

					});
					quxiao.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							m_popupWindow.dismiss();
						}

					});

				} catch (Exception e) {
					Toast.makeText(Study_richeng.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}
			}
		});
		editbt3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {

					if (m_popupWindow.isShowing()) {

						m_popupWindow.dismiss();
					}
					m_popupWindow.showAtLocation(getCurrentFocus(),
							BIND_AUTO_CREATE, 10, 10);

					queding.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							bz3.setText(edit_beizhu.getText().toString());
							m_popupWindow.dismiss();
							Toast.makeText(Study_richeng.this, "已保存",
									Toast.LENGTH_SHORT).show();
						}

					});
					quxiao.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							m_popupWindow.dismiss();
						}

					});

				} catch (Exception e) {
					Toast.makeText(Study_richeng.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}
			}
		});
		editbt4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {

					if (m_popupWindow.isShowing()) {

						m_popupWindow.dismiss();
					}
					m_popupWindow.showAtLocation(getCurrentFocus(),
							BIND_AUTO_CREATE, 10, 10);

					queding.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							bz4.setText(edit_beizhu.getText().toString());
							m_popupWindow.dismiss();
							Toast.makeText(Study_richeng.this, "已保存",
									Toast.LENGTH_SHORT).show();
						}

					});
					quxiao.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							m_popupWindow.dismiss();
						}

					});

				} catch (Exception e) {
					Toast.makeText(Study_richeng.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}
			}
		});
		editbt5.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {

					if (m_popupWindow.isShowing()) {

						m_popupWindow.dismiss();
					}
					m_popupWindow.showAtLocation(getCurrentFocus(),
							BIND_AUTO_CREATE, 10, 10);

					queding.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							bz5.setText(edit_beizhu.getText().toString());
							m_popupWindow.dismiss();
							Toast.makeText(Study_richeng.this, "已保存",
									Toast.LENGTH_SHORT).show();
						}

					});
					quxiao.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							m_popupWindow.dismiss();
						}

					});

				} catch (Exception e) {
					Toast.makeText(Study_richeng.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (m_popupWindow != null && m_popupWindow.isShowing()) {
				m_popupWindow.dismiss();

				return true;
			}
		}
		return super.onKeyDown(keyCode, event);

	}

	public void setDate() {
		// TODO Auto-generated method stub
		// 获取时间
		nyear = c.get(Calendar.YEAR);
		nmonth = c.get(Calendar.MONTH) + 1;
		ndate = c.get(Calendar.DAY_OF_MONTH);
		nday = c.get(Calendar.DAY_OF_WEEK);
		// 设置日期
		date.setText(nyear + "年" + nmonth + "月" + ndate + "日");
		// 设置星期
		switch (nday) {
		case 1:
			day.setText("星期日");
			break;
		case 2:
			day.setText("星期一");
			break;
		case 3:
			day.setText("星期二");
			break;
		case 4:
			day.setText("星期三");
			break;
		case 5:
			day.setText("星期四");
			break;
		case 6:
			day.setText("星期五");
			break;
		case 7:
			day.setText("星期六");
			break;
		}
		Calendar calendar = new GregorianCalendar(2013, 9, 01, 0, 0, 0);
		Date firstdate = calendar.getTime();
		Calendar calendarNow = new GregorianCalendar(nyear, nmonth, ndate, 0,
				0, 0);
		Date endDate = calendarNow.getTime();
		long days = (endDate.getTime() - firstdate.getTime())
				/ (24 * 60 * 60 * 1000);
		if (days % 7 == 0)
			ndays = (int) (days / 7);
		else
			ndays = (int) (days / 7 + 1);
		zhouci.setText("第" + ndays + "周");
	}
}
