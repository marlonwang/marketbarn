package com.market.marketbarn.model;

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
	
	// 物品标志 (物品类别编号+物品id 唯一)
	private String itemId;
	
	// 物品类别编号
	private String itemCategoryCode;
	
	// 进货价格
	private double stockPrice;
	
	// 进货量
	private int stockQuantity;
	
	// 进货批次
	private int batchFlag;
	
	// 进货日期
	private Date stockDate;
	
	// 进货操作人员
	private String operatorName;
	
	public int getStockId(){
		return stockId;
	}
	
	public void setStockId(int stockId){
		this.stockId = stockId;
	}
	
	public String getItemId(){
		return itemId;
	}
	
	public void setItemId(String itemId){
		this.itemId = itemId;
	}
	
	public String getItemCategoryCode(){
		return itemCategoryCode;
	}
	
	public void setItemCategoryCode(String itemCategoryCode){
		this.itemCategoryCode = itemCategoryCode;
	}
	
	public double getStockPrice(){
		return stockPrice;
	}
	
	public void setStockPrice(double stockPrice){
		this.stockPrice = stockPrice;
	}
	
	public int getStockQuantity(){
		return stockQuantity;
	}
	
	public void setStockQuantity(int stockQuantity){
		this.stockQuantity = stockQuantity;
	}
	
	public int getBatchFlag(){
		return batchFlag;
	}
	
	public void setBatchFlag(int batchFlag){
		this.batchFlag = batchFlag;
	}
	
	public Date getStockDate(){
		return stockDate;
	}
	
	public void setStockDate(Date stockDate){
		this.stockDate = stockDate;
	}
	
	public String getOperator(){
		return operatorName;
	}
	
	public void setOperator(String operatorName){
		this.operatorName = operatorName;
	}
}
