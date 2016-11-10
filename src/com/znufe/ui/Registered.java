package com.znufe.ui;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znufe.dbm.DBManager;
import com.znufe.dbm.Dao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Registered extends Activity {

	Button denglubutton;
	DBManager dbHelper;
	Dao dao;
	String xh, mm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		setContentView(R.layout.activity_registered);

		Intent intent = getIntent();
		xh = intent.getStringExtra("num");
		mm = intent.getStringExtra("psw");

		dao = new Dao();
		dbHelper = new DBManager(this);
		dbHelper.openDatabase();
		filterHtmlSchedule(xh, dao.getHtml(xh, mm));

		denglubutton = (Button) findViewById(R.id.button_registered_denglu);
		denglubutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Registered.this, Login.class);
				startActivity(it);
				finish();
			}
		});
	}

	String filterHtmlSchedule(String number, String source) {
		if (null == source) {
			return "";
		}
		ArrayList<String> text = new ArrayList<String>();
		StringBuffer sff = new StringBuffer();
		int i = 0, j = 1, k = 1, m = 1;
		Document doc = Jsoup.parse(source);// 把HTML代码加载到doc中
		try {
			Element table = doc.select("table[bgcolor=#F2EDF8]").first();
			Elements tds = table.select("td[width=112]");
			for (Element td : tds) {
				++i;
				text.add(td.text() + "\n");
			}
			for (; j <= 5; j++) {
				// sff.append("新的一天\n");
				for (k = 1; k <= i; k++)
					if (k % 7 == j) {
						String temp = k % 7 + "" + (m % 5 == 0 ? 5 : m % 5)
								+ text.get(k - 1);
						m++;
						String[] s = dao.getData(temp);
						if (s != null) {
							dbHelper.addUserSubject(number + s[0], number,
									s[0], s[1], s[2], s[3]);
						}
					}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		String html = sff.toString();
		dbHelper.closeDatabase();
		return html;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(Registered.this, Main_Enter.class));
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
