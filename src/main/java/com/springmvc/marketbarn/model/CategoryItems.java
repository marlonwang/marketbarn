package com.springmvc.marketbarn.model;

public class CategoryItems {
	
	// 电器 id
		private int itemId;
		
		// 电器名称
		private String itemName;
		
		// 电器类别码
		private String itemCode;
		
		// 分类名称
		private String itemCodeName;
		
		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getItemCode() {
			return itemCode;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}

		public String getItemCodeName() {
			return itemCodeName;
		}

		public void setItemCodeName(String itemCodeName) {
			this.itemCodeName = itemCodeName;
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

		public String getAddition() {
			return addition;
		}

		public void setAddition(String addition) {
			this.addition = addition;
		}

		// 电器 条形码
		private String barcode;
		
		// 电器描述信息
		private String description;
		
		// 执行标准
		private String standard;

		// 生产商
		private String producer;
		
		// 生产地址
		private String address;
		
		// 生产商联系电话
		private String telnumber;
		
		// 附加说明
		private String addition;

}
