package com.testweb.user.model;

import java.sql.Timestamp;

public class UserVO {
	
	String id;
	String pw;
	String name;
	String phone;
	String email;
	String address_basic;
	String address_detaile;
	Timestamp regdate;
	
	//생성자
	public UserVO() {
		super();
	}


	public UserVO(String id, String pw, String name, String phone, String email, String address_basic,
			String address_detaile) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address_basic = address_basic;
		this.address_detaile = address_detaile;
		this.regdate = null;
	}


	//getter setter
	public String getId() {
		return id;
	}


	public String getPw() {
		return pw;
	}


	public String getName() {
		return name;
	}


	public String getPhone() {
		return phone;
	}


	public String getEmail() {
		return email;
	}


	public String getAddress_basic() {
		return address_basic;
	}


	public String getAddress_detaile() {
		return address_detaile;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setAddress_basic(String address_basic) {
		this.address_basic = address_basic;
	}


	public void setAddress_detaile(String address_detaile) {
		this.address_detaile = address_detaile;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
	
	
	
	
	

}
