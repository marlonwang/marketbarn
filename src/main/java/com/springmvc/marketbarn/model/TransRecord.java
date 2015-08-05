package com.springmvc.marketbarn.model;

import java.sql.Date;

public class TransRecord {
	
/*	mkt_sync_record
 *  rec_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	rec_type VARCHAR(50) COMMENT '自动,手动',
	rec_origin  VARCHAR(255),
	rec_destination  VARCHAR(255),
	rec_desc VARCHAR(255) COMMENT '同步内容描述, 哪个表',
	rec_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	*/
	
	//record id
	private int recordId;
	
	//同步类型
	private String recordType;
	
	//交换 源
	private String origin;
	
	//交换 目的地
	private String destination;
	
	//描述
	private String description;
	
	//交换时间
	private Date recordTime;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

}
