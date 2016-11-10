package com.znufe.moder;

public class User {
	private String User_Name,User_No,User_Psw,sex,field,domitory,college,classNum;
	//private int privacyLv; 
	public User(){};

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getUser_No() {
		return User_No;
	}

	public void setUser_No(String user_No) {
		User_No = user_No;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getUser_Psw() {
		return User_Psw;
	}

	public void setUser_Psw(String user_Psw) {
		User_Psw = user_Psw;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDomitory() {
		return domitory;
	}

	public void setDomitory(String domitory) {
		this.domitory = domitory;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String ClassNum) {
		classNum = ClassNum;
	}

	/*public int getPrivacyLv() {
		return privacyLv;
	}

	public void setPrivacyLv(int privacyLv) {
		this.privacyLv = privacyLv;
	}*/
}
