package com.znufe.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

import com.znufe.dbm.DBManager;

@SuppressLint("ShowToast")
public class Classroom extends Activity {

	private DBManager dbm;
	private String xh;

	private Button yesterday, tomorrow, building, back;
	private ToggleButton class1, class2, class3, class4, class5;
	private String time, biao;
	private ImageView c1, c2, c3, c4, c5;
	private int ndays, nyear, nmonth, ndate, nday;
	private TextView date, day, zhouci, menuday;
	private ViewSwitcher mSwitcher; // viewSwitcher
	private ListView listView;
	private ListViewAdapter listViewAdapter;
	private List<Map<String, Object>> listItems;
	private PopupWindow building_popupWindow;
	View contentView;
	View maincontent;
	private TextView wenbo, wentai, wenlan, building_name;

	final Calendar c = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		this.setContentView(R.layout.classroom);

		dbm = new DBManager(this);
		dbm.openDatabase();

		Intent intent = getIntent();
		xh = intent.getStringExtra("num");

		date = (TextView) findViewById(R.id.textView_ymd);
		day = (TextView) findViewById(R.id.textView_day);
		menuday = (TextView) findViewById(R.id.textView_nowday);
		zhouci = (TextView) findViewById(R.id.textView_zhouci);
		yesterday = (Button) findViewById(R.id.button_yesterday);
		tomorrow = (Button) findViewById(R.id.button_tomorrow);
		c1 = (ImageView) findViewById(R.id.c1);
		c2 = (ImageView) findViewById(R.id.c2);
		c3 = (ImageView) findViewById(R.id.c3);
		c4 = (ImageView) findViewById(R.id.c4);
		c5 = (ImageView) findViewById(R.id.c5);
		class1 = (ToggleButton) findViewById(R.id.toggleButton_class1);
		class2 = (ToggleButton) findViewById(R.id.toggleButton_class2);
		class3 = (ToggleButton) findViewById(R.id.toggleButton_class3);
		class4 = (ToggleButton) findViewById(R.id.toggleButton_class4);
		class5 = (ToggleButton) findViewById(R.id.toggleButton_class5);

		building_name = (TextView) findViewById(R.id.building_name);

		mSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
		mSwitcher.setDisplayedChild(0);
		building = (Button) findViewById(R.id.building);

		back = (Button) findViewById(R.id.c_back);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mSwitcher.setDisplayedChild(0);
				mSwitcher.setInAnimation(getApplicationContext(),
						R.anim.classroom_detail_in);
			}
		});
		getDate();
		init();
		setListener();
		setDate();

	}

	public void listView(int z) {
		listView = (ListView) findViewById(R.id.classroom);
		listItems = getListItems(z);
		listViewAdapter = new ListViewAdapter(this, listItems); // 创建适配器
		listView.setAdapter(listViewAdapter);
	}

	// 添加空教室信息
	private List<Map<String, Object>> getListItems(int a) {
		String num;
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		// if (biao == "wentaiclassroom") {
		// Cursor mcursor= dbm.getWentaiClassroom(time);
		// if(mcursor != null && mcursor.getCount() >= 1){
		// while (mcursor.moveToNext()) {
		// num=mcursor.getString(1);
		// ceshi.setText(num);
		switch (a) {
		case 0: {
			Cursor mcursor = dbm.getWentaiClassroom(time);
			if (mcursor.getCount() > 0) {
				while (mcursor.moveToNext()) {
					num = mcursor.getString(1);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("classroom_num", num);
					map.put("ke1", R.drawable.classroom_kong);// 第一节
					map.put("ke2", R.drawable.classroom_ke);// 第二节
					map.put("ke3", R.drawable.classroom_ke);// 第三节
					map.put("ke4", R.drawable.classroom_ke);// 第四节
					map.put("ke5", R.drawable.classroom_ke);// 第五节
					listItems.add(map);

				}
			}
			mcursor.close();
		};

			break;

		case 1: {
			Cursor mcursor = dbm.getWenboClassroom(time);
			if (mcursor.getCount() > 0) {
				while (mcursor.moveToNext()) {
					num = mcursor.getString(1);
					Map<String, Object> map = new HashMap<String, Object>();
			map.put("classroom_num", num);
			map.put("ke1", R.drawable.classroom_kong);// 第一节
			map.put("ke2", R.drawable.classroom_ke);// 第二节
			map.put("ke3", R.drawable.classroom_ke);// 第三节
			map.put("ke4", R.drawable.classroom_ke);// 第四节
			map.put("ke5", R.drawable.classroom_ke);// 第五节
			listItems.add(map);
		}
			}
			mcursor.close();
		};
			break;
		case 2: {
			Cursor mcursor = dbm.getWenlanClassroom(time);
			if (mcursor.getCount() > 0) {
				while (mcursor.moveToNext()) {
					num = mcursor.getString(1);
					Map<String, Object> map = new HashMap<String, Object>();
			map.put("classroom_num", num);
			map.put("ke1", R.drawable.classroom_kong);// 第一节
			map.put("ke2", R.drawable.classroom_ke);// 第二节
			map.put("ke3", R.drawable.classroom_ke);// 第三节
			map.put("ke4", R.drawable.classroom_ke);// 第四节
			map.put("ke5", R.drawable.classroom_ke);// 第五节
			listItems.add(map);
		}
			}
			mcursor.close();
		};
			break;
		}
		return listItems;
		// }
		// }
	}

	// 选择building
	@SuppressLint("NewApi")
	private void init() {
		contentView = getLayoutInflater()
				.inflate(R.layout.building, null, true);
		wenbo = (TextView) contentView.findViewById(R.id.wenbo);
		wenlan = (TextView) contentView.findViewById(R.id.wenlan);
		wentai = (TextView) contentView.findViewById(R.id.wentai);
		// PopupWindow弹出的窗口显示的view,第二和第三参数：分别表示此弹出窗口的大小
		building_popupWindow = new PopupWindow(contentView,
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
		building_popupWindow.setBackgroundDrawable(new BitmapDrawable());
		building_popupWindow.setOutsideTouchable(true);
		building_popupWindow.setAnimationStyle(R.style.pop_add_style);
	}

	// 设置时间
	public void setDate() {
		// TODO Auto-generated method stub

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
		menuday.setText(day.getText());
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

	// 获取时间
	public void getDate() {
		nyear = c.get(Calendar.YEAR);
		nmonth = c.get(Calendar.MONTH) + 1;
		ndate = c.get(Calendar.DAY_OF_MONTH);
		nday = c.get(Calendar.DAY_OF_WEEK);
	}

	// 设置listener
	public void setListener() {
		// 第几节课
		class1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					c1.setBackgroundResource(R.drawable.c1);
					time = Integer.toString(nday - 1).trim() + "1";
				} else
					c1.setBackgroundResource(R.drawable.transparent);
			}
		});
		class2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					c2.setBackgroundResource(R.drawable.c2);
					time = Integer.toString(nday - 1).trim() + "2";
				} else
					c2.setBackgroundResource(R.drawable.transparent);
			}
		});
		class3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					c3.setBackgroundResource(R.drawable.c3);
					time = Integer.toString(nday - 1).trim() + "3";
				} else
					c3.setBackgroundResource(R.drawable.transparent);
			}
		});
		class4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					c4.setBackgroundResource(R.drawable.c4);
					time = Integer.toString(nday - 1).trim() + "4";
				} else
					c4.setBackgroundResource(R.drawable.transparent);
			}
		});
		class5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					c5.setBackgroundResource(R.drawable.c5);
					time = Integer.toString(nday - 1).trim() + "5";
				} else
					c5.setBackgroundResource(R.drawable.transparent);
			}
		});
		// 往前一天
		yesterday.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (ndate == 1) {
					if (nmonth == 1) {
						ndate = 31;
						nmonth = 12;
						nyear = nyear - 1;
					} else {
						nmonth = nmonth - 1;
						if (nmonth == 1 || nmonth == 3 || nmonth == 5
								|| nmonth == 7 || nmonth == 8 || nmonth == 10
								|| nmonth == 12) {
							ndate = 31;
						} else if (nmonth == 4 || nmonth == 6 || nmonth == 9
								|| nmonth == 11) {
							ndate = 30;
						} else {
							if (nyear % 4 == 0)
								ndate = 29;
							else
								ndate = 28;
						}
					}
				} else {
					ndate = ndate - 1;
				}
				if (nday == 1)
					nday = 7;
				else
					nday = nday - 1;
				setDate();
			}
		});
		// 往后一天
		tomorrow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (ndate == 31) {
					if (nmonth == 12) {
						ndate = 1;
						nmonth = 1;
						nyear++;
					} else {
						nmonth++;
						ndate = 1;
					}
				} else if (ndate == 30) {
					if (nmonth == 4 || nmonth == 6 || nmonth == 9
							|| nmonth == 11) {
						nmonth++;
						ndate = 1;
					} else {
						ndate++;
					}
				} else if (ndate == 28 && nmonth == 2) {
					if (nyear % 4 != 0) {
						ndate = 1;
						nmonth++;
					} else
						ndate++;
				} else if (ndate == 29 && nmonth == 2) {
					ndate = 1;
					nmonth++;
				} else {
					ndate = ndate + 1;
				}
				if (nday == 7)
					nday = 1;
				else
					nday = nday + 1;
				setDate();
			}

		});
		contentView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				building_popupWindow.dismiss();
			}
		});
		// 教学楼
		building.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {

					if (building_popupWindow.isShowing()) {
						building_popupWindow.dismiss();
					}
					building_popupWindow.showAsDropDown(building, -4, -4);

					wentai.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							building.setText("文泰楼");
							building_popupWindow.dismiss();
							biao = "wentaiclassroom";
							mSwitcher.setDisplayedChild(1);
							mSwitcher.setInAnimation(getApplicationContext(),
									R.anim.pop_in);
							building_name.setText("文泰楼");
							listView(0);
						}
					});
					wenlan.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							building.setText("文澜楼");
							building_popupWindow.dismiss();
							biao = "wenlanclassroom";
							mSwitcher.setDisplayedChild(1);
							mSwitcher.setInAnimation(getApplicationContext(),
									R.anim.pop_in);
							building_name.setText("文澜楼");
							listView(1);
						}
					});
					wenbo.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							building.setText("文波楼");
							building_popupWindow.dismiss();
							biao = "wenboclassroom";
							mSwitcher.setDisplayedChild(1);
							mSwitcher.setInAnimation(getApplicationContext(),
									R.anim.pop_in);
							building_name.setText("文波楼");
							listView(2);
						}
					});
				} catch (Exception e) {
					Toast.makeText(Classroom.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}
			}
		});
	}

	public class ListViewAdapter extends BaseAdapter {
		private Context context; // 运行上下文
		private List<Map<String, Object>> listItems; // 教室信息集合
		private LayoutInflater listContainer; // 视图容器

		final class ListItemView { // 每一行
			public TextView classroom_num;
			public ImageView ke1, ke2, ke3, ke4, ke5;
		}

		public ListViewAdapter(Context context,
				List<Map<String, Object>> listItems) {
			this.context = context;
			listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
			this.listItems = listItems;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listItems.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Log.e("method", "getView");
			final int selectID = position;
			ListItemView listItemView = null;
			if (convertView == null) {
				listItemView = new ListItemView();
				convertView = listContainer.inflate(R.layout.classroom_item,
						null);// 定义每行样式
				listItemView.classroom_num = (TextView) convertView
						.findViewById(R.id.classroom_num);
				listItemView.ke1 = (ImageView) convertView
						.findViewById(R.id.ke1);
				listItemView.ke2 = (ImageView) convertView
						.findViewById(R.id.ke2);
				listItemView.ke3 = (ImageView) convertView
						.findViewById(R.id.ke3);
				listItemView.ke4 = (ImageView) convertView
						.findViewById(R.id.ke4);
				listItemView.ke5 = (ImageView) convertView
						.findViewById(R.id.ke5);
				convertView.setTag(listItemView);
			} else {
				listItemView = (ListItemView) convertView.getTag();
			}
			listItemView.classroom_num.setText((String) listItems.get(position)
					.get("classroom_num"));
			listItemView.ke1.setBackgroundResource((Integer) listItems.get(
					position).get("ke1"));
			listItemView.ke2.setBackgroundResource((Integer) listItems.get(
					position).get("ke2"));
			listItemView.ke3.setBackgroundResource((Integer) listItems.get(
					position).get("ke3"));
			listItemView.ke4.setBackgroundResource((Integer) listItems.get(
					position).get("ke4"));
			listItemView.ke5.setBackgroundResource((Integer) listItems.get(
					position).get("ke5"));

			return convertView;
		}
	}
}