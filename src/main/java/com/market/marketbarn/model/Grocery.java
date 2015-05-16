package com.market.marketbarn.model;

import java.sql.Date;

/*  百货(洗浴用品/纸巾)
 * 	gc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gc_name VARCHAR(50) NOT NULL,
	gc_code CHAR(5),
	gc_barcode CHAR(13),
	gc_description TEXT,
	gc_spec VARCHAR(20) COMMENT '规格',

	gc_is_qualified TINYINT(1),
	gc_perform_standard VARCHAR(15),
	gc_producer CHAR(50),
	gc_producer_addr VARCHAR(100),
	gc_producer_phone VARCHAR(15),
	gc_producer_mail VARCHAR(50),
	gc_produced_time TIMESTAMP DEFAULT NOW(),
	gc_ingredient VARCHAR(50) COMMENT '成分',
	gc_addition TEXT
 */

public class Grocery {
	
	// 百货id
	private int groceryId;
	
	// 百货名称
	private String groceryName;
	
	// 百货类别码
	private String groceryCode;
	
	// 百货条形码
	private String barcode;
	
	// 百货描述信息
	private String description;
	
	// 规格
	private String specific;
	
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
		
	// 生产商联系邮件
	private String email;
		
	// 生产日期
	private Date produceDate;
	
	// 成分
	private String component;
	
	// 附加信息
	private String addition;

	public int getGroceryId() {
		return groceryId;
	}

	public void setGroceryId(int groceryId) {
		this.groceryId = groceryId;
	}

	public String getGroceryName() {
		return groceryName;
	}

	public void setGroceryName(String groceryName) {
		this.groceryName = groceryName;
	}

	public String getGroceryCode() {
		return groceryCode;
	}

	public void setGroceryCode(String groceryCode) {
		this.groceryCode = groceryCode;
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

	public String getSpecific() {
		return specific;
	}

	public void setSpecific(String specific) {
		this.specific = specific;
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

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}
	
}
