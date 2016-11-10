package com.znufe.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class NumChecked extends Activity {

	private Button backbutton;// �����׽��水ť
	private Button yanzhengbutton;// ��֤��ť
	private EditText xuehao, mima;
	private CheckBox checkbox;
	private String mm;
	private String xh;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysAplication.getInstance().addActivity(this);
		setContentView(R.layout.activity_numchecked);

		xuehao = (EditText) findViewById(R.id.editText1);
		mima = (EditText) findViewById(R.id.editText2);

		backbutton = (Button) findViewById(R.id.button_numchecked_back);
		yanzhengbutton = (Button) findViewById(R.id.buttoncheck);
		checkbox = (CheckBox) findViewById(R.id.checkBox1);
		checkbox.setButtonDrawable(R.drawable.checkbox_selector);
		backbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(NumChecked.this, Main_Enter.class);
				startActivity(it);
				finish();
			}
		});
		yanzhengbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkbox.isChecked()) {
					if (!xuehao.getText().toString().trim().equals("")
							&& !mima.getText().toString().trim().equals("")) {
						xh = xuehao.getText().toString().trim();
						mm = mima.getText().toString().trim();
						// List<org.apache.http.cookie.Cookie> cookies;
						HttpClient client = new DefaultHttpClient();
						HttpResponse httpResponse;
						String urlAPI = "http://202.114.224.81:7777/pls/wwwbks/bks_login2.login";
						HttpPost httpRequest = new HttpPost(urlAPI);
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("pwd", mm)); // ����
						params.add(new BasicNameValuePair("stuid", xh)); // ѧ��
						try {
							// ����HTTP request
							httpRequest.setEntity(new UrlEncodedFormEntity(
									params, HTTP.UTF_8));
							// ȡ��HTTP response
							httpResponse = client.execute(httpRequest); // ִ��
							// ��״̬��Ϊ200
							if (httpResponse.getStatusLine().getStatusCode() == 200
									&& !(((AbstractHttpClient) client)
											.getCookieStore().getCookies()
											.isEmpty())) {
								// ����ֵ����
								Toast.makeText(NumChecked.this, "��֤ͨ��",
										Toast.LENGTH_LONG).show();
								Intent it = new Intent(NumChecked.this,
										Information.class);
								it.putExtra("num", xh);
								it.putExtra("psw", mm);
								startActivity(it);
								finish();

							} else {
								Toast.makeText(NumChecked.this, "ѧ�Ż��������",
										Toast.LENGTH_LONG).show();
							}
						} catch (Exception e) {
							Toast.makeText(NumChecked.this, "�����쳣"+xh+mm,
									Toast.LENGTH_LONG).show();
							e.getStackTrace();
						}
					} else
						Toast.makeText(NumChecked.this, "����дѧ������",
								Toast.LENGTH_SHORT).show();
				}

				/*
				 * if(!xuehao.getText().toString().trim().equals("")&&!mima.getText
				 * ().toString().trim().equals("")){ String
				 * data=xuehao.getText().toString().trim(); if (data != null &&
				 * data.length() > 0) { xh=Integer.parseInt(data);}else xh=0;
				 * mm=mima.getText().toString().trim(); Intent it = new
				 * Intent(NumChecked.this, Information.class);
				 * it.putExtra("num", data); it.putExtra("psw", mm);
				 * startActivity(it); } else Toast.makeText(NumChecked.this,
				 * "����дѧ������", Toast.LENGTH_SHORT).show(); }
				 */
			}
		});
	}

}
