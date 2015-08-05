package com.springmvc.marketbarn.model;

import java.sql.Timestamp;

public class LeftItems {
		
		// 电器名称
		private String itemName;
		
		// 剩余数量
		private int itemNum;
		
		// 电器类别码
		private String itemCode;
		
		// 电器 条形码
		private String barcode;
		
		// 电器描述信息
		private String description;

		// 生产商
		private String producer;
		
		// 生产商联系电话
		private String telnumber;
		
		// 统计时间
		private Timestamp countTime;

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public int getItemNum() {
			return itemNum;
		}

		public void setItemNum(int itemNum) {
			this.itemNum = itemNum;
		}

		public String getItemCode() {
			return itemCode;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
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

		public String getProducer() {
			return producer;
		}

		public void setProducer(String producer) {
			this.producer = producer;
		}

		public String getTelnumber() {
			return telnumber;
		}

		public void setTelnumber(String telnumber) {
			this.telnumber = telnumber;
		}

		public Timestamp getCountTime() {
			return countTime;
		}

		public void setCountTime(Timestamp countTime) {
			this.countTime = countTime;
		}
		

}
