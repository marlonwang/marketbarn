package com.market.marketbarn.model;

/*	物品分类
 * 	ct_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ct_code CHAR(5) NOT NULL,
	ct_category VARCHAR(50) NOT NULL,
	ct_units CHAR(5) COMMENT '单位'
 */

public class ItemCategory {
	
	// 分类 id
	private int categoryId;
	
	// 分类编码
	private String categoryCode;
	
	// 分类名称
	private String categoryName;
	
	// 分类 计量单位
	private String categoryUnit;
	
	public int getCategoryId(){
		return categoryId;
	}
	
	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}
	
	public String getCategoryCode(){
		return categoryCode;
	}
	
	public void setCategoryCode(String categoryCode){
		this.categoryCode = categoryCode;
	}
	
	public String getCategoryName(){
		return categoryName;
	}
	
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	
	public String getCategoryUnit(){
		return categoryUnit;
	}
	
	public void setCategoryUnit(String categoryUnit){
		this.categoryUnit = categoryUnit;
	}
}
