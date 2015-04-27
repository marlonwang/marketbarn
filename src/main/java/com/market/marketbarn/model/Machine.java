package com.market.marketbarn.model;

import java.sql.Date;

/*  电器信息
 * 	mc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	mc_name VARCHAR(50) NOT NULL,
	mc_code CHAR(5),
	mc_barcode CHAR(13),
	mc_description TEXT,
	mc_size VARCHAR(20) COMMENT '尺寸—l-w-h',
	mc_status ENUM("在库","出库","损坏","丢失"),
	mc_is_qualified TINYINT(1),
	mc_perform_standard VARCHAR(15),
	mc_producer CHAR(50),
	mc_producer_addr VARCHAR(100),
	mc_producer_phone VARCHAR(15),
	mc_producer_mail VARCHAR(50),
	mc_produced_time TIMESTAMP DEFAULT NOW(),
	mc_voltage VARCHAR(20) COMMENT '电压',
	mc_current VARCHAR(20) COMMENT '电流',
	mc_material VARCHAR(50) COMMENT '材质',
	mc_addition TEXT
 */

public class Machine {
	
	// 电器 id
	private int machineId;
	
	// 电器名称
	private String machineName;
	
	// 电器类别码
	private String machineCode;
	
	// 电器 条形码
	private String barcode;
	
	// 电器描述信息
	private String description;
	
	// 电器 大小 尺寸
	private String size;
	
	// 电器 库存状态
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
	
	// 生产商联系邮件
	private String email;
	
	// 生产日期
	private Date produceDate;
	
	// 电压
	private String voltage;
	
	// 电流
	private String current;
	
	// 材质
	private String material;
	
	// 附加说明
	private String addition;
	
	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	public int getMachineId(){
		return machineId;
	}
	
	public void setMachineId(int machineId){
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}
	
}
