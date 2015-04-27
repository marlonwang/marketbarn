package com.market.marketbarn.model;

import java.sql.Date;

/*	出货信息
 *  out_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	out_item_id VARCHAR(20) NOT NULL,
	out_item_code CHAR(5),
	out_sold_price DOUBLE,
	out_sold_quantity INT DEFAULT 1,
	out_sold_flag INT,
	out_sold_time TIMESTAMP DEFAULT NOW(),
	out_sold_worker VARCHAR(20)
 */

public class SalesInfo {
	
	// 出货id
	private int salesId;
	
	// 物品标志 (物品类别编号+物品id 唯一)
	private String itemId;
	
	// 物品分类编号
	private String itemCategoryCode;
	
	// 出货价格
	private double salesPrice;
	
	// 出货数量
	private int salesQuantity;
	
	// 出货批次
	private int batchFlag;
	
	// 出货时间
	private Date salesDate;
	
	// 出货操作人员
	private String operatorName;
	
	public int getSalesId(){
		return salesId;
	}
	
	public void setSalesId(int salesId){
		this.salesId = salesId;
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
	
	public double getSalesPrice(){
		return salesPrice;
	}
	
	public void setSalesPrice(double salesPrice){
		this.salesPrice = salesPrice;
	}
	
	public int getSalesQuantity(){
		return salesQuantity;
	}
	
	public void setSalesQuantity(int salesQuantity){
		this.salesQuantity = salesQuantity;
	}
	
	public int getBatchFlag(){
		return batchFlag;
	}
	
	public void setBatchFlag(int batchFlag){
		this.batchFlag = batchFlag;
	}
	
	public Date getSalesDate(){
		return salesDate;
	}
	
	public void setSalesDate(Date salesDate){
		this.salesDate = salesDate;
	}
	
	public String getOperator(){
		return operatorName;
	}
	
	public void setOperator(String operatorName){
		this.operatorName = operatorName;
	}
}
