package com.springmvc.marketbarn.model;

import java.sql.Date;

/*	进货信息
 * 	in_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	in_item_id VARCHAR(20) NOT NULL,
	in_item_code CHAR(5),
	in_get_price DOUBLE,
	in_get_quantity INT DEFAULT 1,
	in_get_flag INT,
	in_get_time TIMESTAMP DEFAULT NOW(),
	in_get_worker VARCHAR(20)
 */

public class StockInfo {
	
	// 进货id
	private int stockId;
	
	//条形码
	private String barcode;
	
	// 物品类别编号
	private String categoryCode;
	
	// 进货价格
	private double stockPrice;
	
	// 进货量
	private int stockQuantity;
	
	// 进货日期
	private Date stockDate;
	
	// 进货操作人员
	private String operator;

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
}
