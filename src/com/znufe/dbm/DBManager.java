package com.znufe.dbm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.znufe.moder.User;
import com.znufe.ui.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager {

	public static final String AD_No = "AD_No";
	public static final String AD_password = "AD_password";
	public static final String User_No = "User_No";
	public static final String User_password = "User_password";
	public static final String User_name = "User_name";
	public static final String sex = "sex";
	public static final String field = "field";
	public static final String college = "college";
	public static final String dormitory = "dormitory";
	public static final String classNum = "classNum";
	public static final String PrivacyLv = "PrivacyLv";
	public static final String subjecttime = "subjecttime";
	public static final String subjectname = "subjectname";
	public static final String classinformation = "classinformation";
	public static final String No = "No";
	public static final String date = "date";
	public static final String week = "week";
	public static final String time = "time";
	public static final String remarks = "remarks";
	public static final String remindornot = "remindornot";
	public static final String classroom = "classroom";
	public static final String freetime = "freetime";
	public static final String staff_ID = "staff_ID";
	public static final String staff_name = "staff_name";
	public static final String staff_faculty = "staff_faculty";
	public static final String staff_Info = "staff_Info";
	public static final String courseNum = "courseNum";
	public static final String comment_ID = "comment_ID";
	public static final String knol_ID = "knol_ID";
	public static final String comment_Info = "comment_Info";
	public static final String isSpam = "isSpam";
	public static final String commentPeriod = "commentPeriod";
	public static final String area_ID = "area_ID";
	public static final String area_Loc = "area_Loc";
	public static final String area_Info = "area_Info";
	public static final String knol_type = "knol_type";
	public static final String act_ID = "act_ID";
	public static final String sub_ID = "sub_ID";
	public static final String act_Period = "act_Period";
	public static final String act_Info = "act_Info";
	public static final String isConsented = "isConsented";
	public static final String User2_No = "User2_No";
	public static final String mutualconcern = "mutualconcern";

	public static final String DATABASE_NAME = "caidaapp";
	public static final String DATABASE_TABLE1 = "Administrator";
	public static final String DATABASE_TABLE2 = "Member";
	public static final String DATABASE_TABLE3 = "usersubject";
	public static final String DATABASE_TABLE4 = "scheduling";
	public static final String DATABASE_TABLE5 = "wenboclassroom";
	public static final String DATABASE_TABLE6 = "wentaiclassroom";
	public static final String DATABASE_TABLE7 = "wenlanclassroom";
	public static final String DATABASE_TABLE8 = "teacher";
	public static final String DATABASE_TABLE9 = "courseconcern";
	public static final String DATABASE_TABLE10 = "commentation";
	public static final String DATABASE_TABLE11 = "placeinschool";
	public static final String DATABASE_TABLE12 = "knol";
	public static final String DATABASE_TABLE13 = "invitation";
	public static final String DATABASE_TABLE14 = "invitated";
	public static final String DATABASE_TABLE15 = "friend";
	public static final int DATABASE_VERSION = 1;

	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "caidaapp"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "com.znufe.ui";
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME + "/databases"; // 在手机里存放数据库的位置

	private SQLiteDatabase db;
	private Context context;

	public DBManager(Context context) {
		this.context = context;
	}

	public void openDatabase() {
		this.db = this.openDatabase(DB_PATH + "/" + DB_NAME);
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		File dbDir = new File(DB_PATH);
		File dbDir2 = new File(dbfile);
		try {
			if ((!dbDir2.exists()) || !(dbDir.exists())) {
				dbDir.mkdir();
				dbDir2.createNewFile();
				InputStream is = this.context.getResources().openRawResource(
						R.raw.caidaapp); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
		return null;
	}

	public void closeDatabase() {
		this.db.close();
	}

	public long addAdministrator(String number, String password) {
		ContentValues cv = new ContentValues();
		cv.put(AD_No, number);
		cv.put(AD_password, password);
		return db.insert(DATABASE_TABLE1, null, cv);
	}

	public void deleteAdministrator(String _id) {
		db.delete(DATABASE_TABLE1, "AD_No=?", new String[] { _id });
	}

	public Cursor getAdministrator(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE1, new String[] { AD_No,
				AD_password }, AD_No + "=" + number, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateAdministrator(String number, String password) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("AD_No", number);
		contentValues.put("AD_password", password);
		db.update(DATABASE_TABLE1, contentValues, "AD_No=?",
				new String[] { number });
	}

	/*
	 * public long addUser(String number, String name, String password, String
	 * xingbie, String zhuanye, String qinshi, String xueyuan, String banjihao)
	 * { ContentValues cv = new ContentValues(); cv.put(User_No, number);
	 * cv.put(User_name, name); cv.put(User_password, password); cv.put(sex,
	 * xingbie); cv.put(field, zhuanye); cv.put(dormitory, qinshi);
	 * cv.put(college, xueyuan); cv.put(classNum, banjihao); return
	 * db.insert(DATABASE_TABLE2, null, cv); }
	 */

	public long addUser(User user) {
		ContentValues cv = new ContentValues();
		cv.put(User_No, user.getUser_No());
		cv.put(User_name, user.getUser_Name());
		cv.put(User_password, user.getUser_Psw());
		cv.put(sex, user.getSex());
		cv.put(field, user.getField());
		cv.put(dormitory, user.getDomitory());
		cv.put(college, user.getCollege());
		cv.put(classNum, user.getClassNum());
		return db.insert(DATABASE_TABLE2, null, cv);
	}

	public void deleteUser(String _id) {
		db.delete(DATABASE_TABLE2, "User_No=?", new String[] { _id });
	}

	public Cursor getUser(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE2, new String[] {
				User_No, User_name, User_password, sex, field, dormitory,
				college, classNum }, User_No + "=" + number, null, null, null,
				null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// 根据User_No和User_password查找用户，返回条目数
	public int findUserByIdAndPwd(String id, String pwd) {
		int result = 0;
		Cursor mCursor = db.query(DATABASE_TABLE2, null, User_No + "=" + "'"
				+ id + "'" + " and " + User_password + "=" + "'" + pwd + "'",
				null, null, null, null);
		if (mCursor != null) {
			result = mCursor.getCount();
			mCursor.close();
		}
		return result;
	}

	// 根据id查找user其余属性
	public String getStringByColumnName(String columnName, String id) {
		Cursor mCursor = this.getUser(id);
		int columnIndex = mCursor.getColumnIndex(columnName);
		String columnValue = mCursor.getString(columnIndex);
		mCursor.close();
		return columnValue;
	}

	// 根据User_No查找user,返回条目数
	public int findUserById(String id) {
		// Log.i(TAG,"findUserByName , User_No="+id);
		int result = 0;
		Cursor mCursor = db.query(DATABASE_TABLE2, null, User_No + "=" + id,
				null, null, null, null);
		if (mCursor != null) {
			result = mCursor.getCount();
			mCursor.close();
			// Log.i(TAG,"findUserByName , result="+result);
		}
		return result;
	}

	public void updateUser(String number, String name, String password,
			String xingbie, String zhuanye, String qinshi, String xueyuan,
			String banjihao) {
		ContentValues cv = new ContentValues();
		cv.put(User_No, number);
		cv.put(User_name, name);
		cv.put(User_password, password);
		cv.put(sex, xingbie);
		cv.put(field, zhuanye);
		cv.put(dormitory, qinshi);
		cv.put(college, xueyuan);
		cv.put(classNum, banjihao);
		db.update(DATABASE_TABLE2, cv, "User_No=?", new String[] { number });
	}

	public long addUserSubject(String bianhao, String number, String time,
			String name, String classroom, String teacher) {
		ContentValues cv = new ContentValues();
		cv.put(No, bianhao);
		cv.put(User_No, number);
		cv.put(subjecttime, time);
		cv.put(subjectname, name);
		cv.put("classroom", classroom);
		cv.put("teacher", teacher);
		return db.insert(DATABASE_TABLE3, null, cv);
	}

	public void deleteUserSubject(String _id) {
		db.delete(DATABASE_TABLE3, "No=?", new String[] { _id });
	}

	public Cursor getUserSubject(String number, String time)
			throws SQLException {
		Cursor mCursor = db.query(DATABASE_TABLE3, new String[] { User_No,
				subjecttime, subjectname, "classroom", "teacher", No }, User_No
				+ "=" + "'" + number + "'" + " and " + subjecttime + "=" + "'"
				+ time + "'", null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateUserSubject(String bianhao, String number, String time,
			String name, String classroom, String teacher) {
		ContentValues cv = new ContentValues();
		cv.put("No", bianhao);
		cv.put("User_No", number);
		cv.put("subjecttime", time);
		cv.put("subjectname", name);
		cv.put("classroom", classroom);
		cv.put("teacher", teacher);
		db.update(DATABASE_TABLE3, cv, "No=?", new String[] { bianhao });
	}

	public long addScheduling(String number, String riqi, String xingqi,
			String shijian, String beizhu, String tixing) {
		ContentValues cv = new ContentValues();
		cv.put(User_No, number);
		cv.put(date, riqi);
		cv.put(week, xingqi);
		cv.put(time, shijian);
		cv.put(remarks, beizhu);
		cv.put(remindornot, tixing);
		return db.insert(DATABASE_TABLE4, null, cv);
	}

	public void deleteScheduling(String _id) {
		db.delete(DATABASE_TABLE4, "User_No=?", new String[] { _id });
	}

	public Cursor getScheduling(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE4, new String[] {
				User_No, date, week, time, remarks, remindornot }, User_No
				+ "=" + number, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateScheduling(String number, String riqi, String xingqi,
			String shijian, String beizhu, String tixing) {
		ContentValues cv = new ContentValues();
		cv.put(User_No, number);
		cv.put(date, riqi);
		cv.put(week, xingqi);
		cv.put(time, shijian);
		cv.put(remarks, beizhu);
		cv.put(remindornot, tixing);
		db.update(DATABASE_TABLE4, cv, "User_No=?", new String[] { number });
	}

	public long addWenboClassroom(String number, String room, String time) {
		ContentValues cv = new ContentValues();
		cv.put(No, number);
		cv.put(classroom, room);
		cv.put(freetime, time);
		return db.insert(DATABASE_TABLE5, null, cv);
	}

	public void deleteWenboClassroom(String _id) {
		db.delete(DATABASE_TABLE5, "No=?", new String[] { _id });
	}

	public Cursor getWenboClassroom(String time) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE5, new String[] { No,
				classroom, freetime }, freetime + ' ' + "like" + ' ' + '"'
				+ time + "%" + '"', null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateWenboClassroom(String number, String room, String time) {
		ContentValues cv = new ContentValues();
		cv.put("No", number);
		cv.put("classroom", room);
		cv.put("classroom", room);
		db.update(DATABASE_TABLE5, cv, "No=?", new String[] { number });
	}

	public long addWentaiClassroom(String number, String room, String time) {
		ContentValues cv = new ContentValues();
		cv.put(No, number);
		cv.put(classroom, room);
		cv.put(freetime, time);
		return db.insert(DATABASE_TABLE6, null, cv);
	}

	public void deleteWentaiClassroom(String _id) {
		db.delete(DATABASE_TABLE6, "No=?", new String[] { _id });
	}

	public Cursor getWentaiClassroom(String time) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE6, new String[] { No,
				classroom, freetime }, freetime + ' ' + "like" + ' ' + '"'
				+ time + "%" + '"', null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateWentaiClassroom(String number, String room, String time) {
		ContentValues cv = new ContentValues();
		cv.put("No", number);
		cv.put("classroom", room);
		cv.put("classroom", room);
		db.update(DATABASE_TABLE6, cv, "No=?", new String[] { number });
	}

	public long addWenlanClassroom(String number, String room, String time) {
		ContentValues cv = new ContentValues();
		cv.put(No, number);
		cv.put(classroom, room);
		cv.put(freetime, time);
		return db.insert(DATABASE_TABLE7, null, cv);
	}

	public void deleteWenlanClassroom(String _id) {
		db.delete(DATABASE_TABLE7, "No=?", new String[] { _id });
	}

	public Cursor getWenlanClassroom(String time) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE7, new String[] { No,
				classroom, freetime }, freetime + ' ' + "like" + ' ' + '"'
				+ time + "%" + '"', null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateWenlanClassroom(String number, String room, String time) {
		ContentValues cv = new ContentValues();
		cv.put("No", number);
		cv.put("classroom", room);
		cv.put("classroom", room);
		db.update(DATABASE_TABLE7, cv, "No=?", new String[] { number });
	}

	public long addTeacher(String number, String name, String xueyuan,
			String xinxi) {
		ContentValues cv = new ContentValues();
		cv.put(staff_ID, number);
		cv.put(staff_name, name);
		cv.put(staff_faculty, xueyuan);
		cv.put(staff_Info, xinxi);
		return db.insert(DATABASE_TABLE8, null, cv);
	}

	public void deleteTeacher(String _id) {
		db.delete(DATABASE_TABLE8, "staff_ID=?", new String[] { _id });
	}

	public Cursor getTeacher(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE8, new String[] {
				staff_ID, staff_name, staff_faculty, staff_Info }, staff_ID
				+ "=" + number, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateTeacher(String number, String name, String xueyuan,
			String xinxi) {
		ContentValues cv = new ContentValues();
		cv.put(staff_ID, number);
		cv.put(staff_name, name);
		cv.put(staff_faculty, xueyuan);
		cv.put(staff_Info, xinxi);
		db.update(DATABASE_TABLE8, cv, "staff_ID=?", new String[] { number });
	}

	public long addCourseConcern(String number, String Num) {
		ContentValues cv = new ContentValues();
		cv.put(User_No, number);
		cv.put(courseNum, Num);
		return db.insert(DATABASE_TABLE9, null, cv);
	}

	public void deleteCourseConcern(String _id) {
		db.delete(DATABASE_TABLE9, "User_No=?", new String[] { _id });
	}

	public Cursor getCourseConcern(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE9, new String[] {
				User_No, courseNum }, User_No + "=" + number, null, null, null,
				null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateCourseConcern(String number, String Num) {
		ContentValues cv = new ContentValues();
		cv.put(User_No, number);
		cv.put(courseNum, Num);
		db.update(DATABASE_TABLE9, cv, "User_No=?", new String[] { number });
	}

	public long addCommentation(String commentnumber, String knolnumber,
			String xinxi, String panduan, String shijian, String usernumber) {
		ContentValues cv = new ContentValues();
		cv.put(comment_ID, commentnumber);
		cv.put(knol_ID, knolnumber);
		cv.put(comment_Info, xinxi);
		cv.put(isSpam, panduan);
		cv.put(commentPeriod, shijian);
		cv.put(User_No, usernumber);
		return db.insert(DATABASE_TABLE10, null, cv);
	}

	public void deleteCommentation(String _id) {
		db.delete(DATABASE_TABLE10, "comment_ID=?", new String[] { _id });
	}

	public Cursor getCommentation(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE10, new String[] {
				comment_ID, knol_ID, comment_Info, isSpam, commentPeriod,
				User_No }, comment_ID + "=" + number, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateCommentation(String commentnumber, String knolnumber,
			String xinxi, String panduan, String shijian, String usernumber) {
		ContentValues cv = new ContentValues();
		cv.put(comment_ID, commentnumber);
		cv.put(knol_ID, knolnumber);
		cv.put(comment_Info, xinxi);
		cv.put(isSpam, panduan);
		cv.put(commentPeriod, shijian);
		cv.put(User_No, usernumber);
		db.update(DATABASE_TABLE10, cv, "comment_ID=?",
				new String[] { commentnumber });
	}

	public long addPlaceInSchool(String number, String didian, String xinxi) {
		ContentValues cv = new ContentValues();
		cv.put(area_ID, number);
		cv.put(area_Loc, didian);
		cv.put(area_Info, xinxi);
		return db.insert(DATABASE_TABLE11, null, cv);
	}

	public void deletePlaceInSchool(String _id) {
		db.delete(DATABASE_TABLE11, "area_ID=?", new String[] { _id });
	}

	public Cursor getPlaceInSchool(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE11, new String[] {
				area_ID, area_Loc, area_Info }, area_ID + "=" + number, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updatePlaceInSchool(String number, String didian, String xinxi) {
		ContentValues cv = new ContentValues();
		cv.put(area_ID, number);
		cv.put(area_Loc, didian);
		cv.put(area_Info, xinxi);
		db.update(DATABASE_TABLE11, cv, "area_ID=?", new String[] { number });
	}

	public long addknol(String knolnumber, String subnumber, String type) {
		ContentValues cv = new ContentValues();
		cv.put(knol_ID, knolnumber);
		cv.put(sub_ID, subnumber);
		cv.put(knol_type, type);
		return db.insert(DATABASE_TABLE12, null, cv);
	}

	public void deleteknol(String _id) {
		db.delete(DATABASE_TABLE12, "knol_ID=?", new String[] { _id });
	}

	public Cursor getknol(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE12, new String[] {
				knol_ID, sub_ID, knol_type }, knol_ID + "=" + number, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateknol(String knolnumber, String subnumber, String type) {
		ContentValues cv = new ContentValues();
		cv.put(knol_ID, knolnumber);
		cv.put(sub_ID, subnumber);
		cv.put(knol_type, type);
		db.update(DATABASE_TABLE12, cv, "knol_ID=?",
				new String[] { knolnumber });
	}

	public long addInvitation(String actnumber, String usernumber,
			String shijian, String xinxi) {
		ContentValues cv = new ContentValues();
		cv.put(act_ID, actnumber);
		cv.put(User_No, usernumber);
		cv.put(act_Period, shijian);
		cv.put(act_Info, xinxi);
		return db.insert(DATABASE_TABLE13, null, cv);
	}

	public void deleteInvitation(String _id) {
		db.delete(DATABASE_TABLE13, "act_ID=?", new String[] { _id });
	}

	public Cursor getInvitation(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE13, new String[] {
				act_ID, User_No, act_Period, act_Info }, act_ID + "=" + number,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateInvitation(String actnumber, String usernumber,
			String shijian, String xinxi) {
		ContentValues cv = new ContentValues();
		cv.put(act_ID, actnumber);
		cv.put(User_No, usernumber);
		cv.put(act_Period, shijian);
		cv.put(act_Info, xinxi);
		db.update(DATABASE_TABLE13, cv, "act_ID=?", new String[] { actnumber });
	}

	public long addInvited(String actnumber, String usernumber, String panduan) {
		ContentValues cv = new ContentValues();
		cv.put(act_ID, actnumber);
		cv.put(User_No, usernumber);
		cv.put(isConsented, panduan);
		return db.insert(DATABASE_TABLE14, null, cv);
	}

	public void deleteInvited(String _id) {
		db.delete(DATABASE_TABLE14, "act_ID=?", new String[] { _id });
	}

	public Cursor getInvited(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE14, new String[] {
				act_ID, User_No, isConsented }, act_ID + "=" + number, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateInvited(String actnumber, String usernumber,
			String panduan) {
		ContentValues cv = new ContentValues();
		cv.put(act_ID, actnumber);
		cv.put(User_No, usernumber);
		cv.put(isConsented, panduan);
		db.update(DATABASE_TABLE14, cv, "act_ID=?", new String[] { actnumber });
	}

	public long addFriend(String number, String usernumber, String user2number,
			String panduan) {
		ContentValues cv = new ContentValues();
		cv.put(No, number);
		cv.put(User_No, usernumber);
		cv.put(User2_No, user2number);
		cv.put(mutualconcern, panduan);
		return db.insert(DATABASE_TABLE15, null, cv);
	}

	public void deleteFriend(String _id) {
		db.delete(DATABASE_TABLE15, "No=?", new String[] { _id });
	}

	public Cursor getFriend(String number) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE15, new String[] { No,
				User_No, User2_No }, No + "=" + number, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void updateFriend(String number, String usernumber,
			String user2number, String panduan) {
		ContentValues cv = new ContentValues();
		cv.put(No, number);
		cv.put(User_No, usernumber);
		cv.put(User2_No, user2number);
		cv.put(mutualconcern, panduan);
		db.update(DATABASE_TABLE15, cv, "No=?", new String[] { number });
	}

	public Cursor getIConcern(String number) {
		Cursor mCursor = db.query(true, DATABASE_TABLE15,
				new String[] { User2_No }, User_No + "=" + number, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor getConcernMe(String number) {
		Cursor mCursor = db.query(true, DATABASE_TABLE15,
				new String[] { User_No }, User2_No + "=" + number, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor hasConcerned(String number, String number2) {
		Cursor mCursor = db.query(true, DATABASE_TABLE15, new String[] { No },
				User_No + "=" + number + " AND " + User2_No + "=" + number2,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

}
