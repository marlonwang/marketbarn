package com.springmvc.marketbarn.model;

/*  员工信息
 *  u_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	u_name VARCHAR(20) NOT NULL,
	u_age INT,
	u_passwd VARCHAR(20),
	u_sex VARCHAR(10),
	u_mail VARCHAR(50),
	u_is_admin INT DEFAULT 0 
 */
public class StaffInfo {
	
	// 员工数据库 id
	private int staffId;
	
	// 员工姓名
	private String staffName;
	
	// 员工年龄
	private int staffAge;
	
	// 员工密码
	private String staffPasswd;
	
	// 员工性别
	private String staffSex;
	
	// 员工 email
	private String staffMail;
	
	// 员工级别
	private int staffRank;
	
	public int getStaffId(){
		return staffId;
	}
	
	public void setStaffId(int uid){
		this.staffId = uid;
	}
	
	public String getStaffName(){
		return staffName;
	}
	
	public void setStaffName(String staffName){
		this.staffName = staffName;
	}
	
	public int getStaffAge(){
		return staffAge;
		
	}
	
	public void setStaffAge(int staffAge){
		this.staffAge = staffAge;
	}
	
	public String getStaffPasswd(){
		return staffPasswd;
	}
	
	public void setStaffPasswd(String staffPasswd){
		this.staffPasswd = staffPasswd;
	}
	
	public String getStaffSex(){
		return staffSex;
	}
	
	public void setStaffSex(String staffSex){
		this.staffSex = staffSex;
	}
	
	public String getStaffMail(){
		return staffMail;
	}
	
	public void setStaffMail(String staffMail){
		this.staffMail = staffMail;
	}
	
	public int getStaffRank(){
		return staffRank;
	}
	
	public void setStaffRank(int staffRank){
		this.staffRank = staffRank;
	}

}
