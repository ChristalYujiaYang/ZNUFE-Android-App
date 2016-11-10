package com.znufe.ui;

import com.znufe.dbm.DBManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class EditInfo extends Activity {

	private DBManager dbm;

	private Button btn1;
	private Button btn2;
	private EditText et1;
	private EditText et2;
	private String strXh;
	private Spinner spinnerXY;// ѧԺѡ��
	private Spinner spinnerZY;// רҵѡ��
	private Spinner spinnerQS;// ����ѡ��
	private String[] XY;// ѧԺ��Ŀ
	private String[] ZY;// רҵ��Ŀ
	private String[] QS;// ������Ŀ
	private RadioButton rbtn1;
	private RadioButton rbtn2;
	private String qs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_info);

		btn1 = (Button) findViewById(R.id.edit_info_btn1);
		btn2 = (Button) findViewById(R.id.edit_info_btn2);
		et1 = (EditText) findViewById(R.id.edit_info_et1);
		et2 = (EditText) findViewById(R.id.edit_info_et2);
		rbtn1 = (RadioButton) findViewById(R.id.rbtn_male);
		rbtn2 = (RadioButton) findViewById(R.id.rbtn_female);
		Intent intent=getIntent();
		strXh=intent.getStringExtra("Xh");

		qs = null;
		XY = getResources().getStringArray(R.array.XueYuan);
		QS = getResources().getStringArray(R.array.qinshi);
		// �����˵���ֵ
		spinnerQS = (Spinner) findViewById(R.id.sp1);
		ArrayAdapter<String> adapterQS = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, QS);
		spinnerQS.setAdapter(adapterQS);
		adapterQS
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ����
		spinnerQS
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						if (arg2 == 0)
							qs = null;
						else
							qs = QS[arg2];
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						qs = null;
					}

				});
		  
		dbm = new DBManager(this);
		dbm.openDatabase();
		Cursor cur = dbm.getUser(strXh);
		et1.setText(cur.getString(1));
		et2.setText(cur.getString(7));
		if (cur.getString(3).equals("Ů"))
			rbtn2.setChecked(true);
		else
			rbtn1.setChecked(true);  
		 
		if (cur.getString(5).equals("����"))
			spinnerQS.setSelection(1);
		else if (cur.getString(5).equals("����"))
			spinnerQS.setSelection(2);
		else if (cur.getString(5).equals("����"))
			spinnerQS.setSelection(3);
		else if(cur.getString(5).equals("�ٺ�"))
			spinnerQS.setSelection(4);
		  
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// �����Ǳ��水ť
				Cursor cur = dbm.getUser(strXh);
				String xb;
				if (rbtn2.isSelected())
					xb = "Ů";
				else 
					xb = "��";
				dbm.updateUser(cur.getString(0),
						et1.getText().toString(),
						cur.getString(2), 
						xb, 
						cur.getString(4), 
						qs, 
						cur.getString(6), 
						et2.getText().toString());
				finish();
			}
		});
	}
	   
    
}
