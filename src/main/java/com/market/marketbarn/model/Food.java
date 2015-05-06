package com.market.marketbarn.model;

import java.sql.Timestamp;

/*  食物
 * 	fd_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fd_name VARCHAR(50) NOT NULL,
	fd_code CHAR(5),
	fd_barcode CHAR(13),
	fd_description TEXT,
	fd_capacity VARCHAR(20),
	fd_status ENUM("在库","出库","损坏","丢失"),
	fd_is_qualified TINYINT(1),
	fd_perform_standard VARCHAR(15),
	fd_producer CHAR(50),
	fd_producer_addr VARCHAR(100),
	fd_producer_phone VARCHAR(15),
	fd_producer_mail VARCHAR(50),
	fd_produced_time TIMESTAMP DEFAULT NOW(),
	fd_ingredient VARCHAR(50) COMMENT '成分',
	fd_addition TEXT 
 */
public class Food {
	
	// 食品id
	private int foodId;
	
	// 食品名称
	private String foodName;
	
	// 食品类别码
	private String foodCode;
	
	// 食品条形码
	private String barcode;
	
	// 食品描述信息
	private String description;
	
	// 容量
	private String capacity;
	
	// 库存状态
	private String status;
	
	// 是否合格
	private byte isQualified; 
	
	// 执行标准
	private String standard;
	
	// 生产商
	private String producer;
	
	// 生产地址
	private String address;
	
	// 生产商联系电话
	private String telnumber;
	
	// 生产商邮件
	private String email;
	
	// 生产日期
	private Timestamp produceDate;
	
	// 食品成分
	private String ingredient;
	
	// 附加信息
	private String addition;

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodCode() {
		return foodCode;
	}

	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(byte isQualified) {
		this.isQualified = isQualified;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelnumber() {
		return telnumber;
	}

	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Timestamp produceDate) {
		this.produceDate = produceDate;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}
	
}
