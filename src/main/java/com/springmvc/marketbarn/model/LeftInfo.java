package com.springmvc.marketbarn.model;

import java.sql.Timestamp;

public class LeftInfo {
	
	//leftID
	private int leftId;
	
	//条形码
	private String barcode;
	
	//剩余数量
	private int quantity;
	
	//统计时间
	private Timestamp countTime;
	
	//其他信息
	private String addition;

	public int getLeftId() {
		return leftId;
	}

	public void setLeftId(int leftId) {
		this.leftId = leftId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getCountTime() {
		return countTime;
	}

	public void setCountTime(Timestamp countTime) {
		this.countTime = countTime;
	}

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}
	
}
