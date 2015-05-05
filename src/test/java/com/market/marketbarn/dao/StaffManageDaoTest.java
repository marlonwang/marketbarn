package com.market.marketbarn.dao;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.market.marketbarn.AbstractTestBase;
import com.market.marketbarn.model.StaffInfo;
import com.market.marketbarn.utils.JsonUtils;

public class StaffManageDaoTest extends AbstractTestBase
{
	@Autowired
	private StaffManageDao staffDao;
	
	@Test
	public void testGetStaffById() 
	{
		List<StaffInfo> staffList = null;
		staffList = staffDao.getStaffById(1);
		System.out.println(JsonUtils.obj2json(staffList));
	}

	@Test
	public void testGetAllStaff() 
	{
		List<StaffInfo> allStaffList = null;
		allStaffList = staffDao.getAllStaff();
		//遍历list
		Iterator<StaffInfo> it = allStaffList.iterator();
		while(it.hasNext())
		{
			System.out.println(JsonUtils.obj2json(it.next()));
		}
	}

	@Test
	public void testGetStaffGroup() {
		List<StaffInfo> staffGroup = null;
		staffGroup = staffDao.getStaffGroup(7);
		System.out.print(JsonUtils.obj2json(staffGroup));
	}

	@Test
	public void testInsertStaffInfo() {
		StaffInfo staff = new StaffInfo();
		int affectRows = 0;
		
		//非空字段
		staff.setStaffName("zhangsan");
		staff.setStaffSex("男");
		
		staff.setStaffAge(13);
		staff.setStaffPsswd("112233");
		staff.setStaffMail("zhangsan@123.com");
		staff.setStaffRank(0);
		
		affectRows = staffDao.insertStaffInfo(staff);
		if(affectRows > 0)
			System.out.println(affectRows+" rows affected");
		else
			System.out.println("insert staff failed");
	}

	@Test
	public void testBatchInsertStaffInfo() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateStaffById(){
		StaffInfo staff = new StaffInfo();
		int rows = 0;
		//非空字段
		//更新前要保证非空字段不为空
		staff.setStaffName("zhangsan");
		staff.setStaffSex("男");
		
		staff.setStaffId(5);
		//更新字段
		staff.setStaffPhone("18900001111");
		rows = staffDao.updateStaffById(staff);
		System.out.println("update rows " + rows );
	}
	
	@Test
	public void testDeleteStaffById(){
		int rows = 0;
		int id = 5;
		rows = staffDao.deleteStaffById(id);
		System.out.println("affected rows " + rows );
	}
}
