package com.market.marketbarn.dao;

import com.market.marketbarn.model.StaffInfo;

public class StaffManageDao {
	
	// 进货员工
	private StaffInfo stockStaff;
	
	// 出货员工
	private StaffInfo salesStaff;
	
	// 查询进货人员
	public StaffInfo getStockStaffById(int staffId){
		
		return stockStaff;
	}
	
	// 查询出货人员
	public StaffInfo getSalesStaffById(int staffId){
		
		return salesStaff;
	}
}
