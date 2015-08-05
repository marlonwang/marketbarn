package com.springmvc.marketbarn.service;

import java.util.List;

import com.springmvc.marketbarn.dao.StaffInfoDao;
import com.springmvc.marketbarn.model.StaffInfo;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 员工管理service接口
 * @author wangwei
 * @date 2015-5-5
 */
@Service
public class StaffService 
{
	// 日志记录
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffService.class);
	
	@Autowired
	private StaffInfoDao staffMngDao;
	
	/**
	 * 添加
	 * @param staff
	 * @return
	 */
	public boolean appendStaff(StaffInfo staff){
		//是否受影响
		int status = 0;
		//空则直接返回false
		if(null == staff )
			return false;
		
		status = staffMngDao.insertStaffInfo(staff);
		
		if(status > 0){
			LOGGER.info("add staff to DB succeed, staff:{}",staff.getStaffName());
			return true;
		}
		else{
			LOGGER.info("failed to add new staff");
			return false;
		}	
	}
	
	/**
	 * 默认无参全部员工
	 * @return List<StaffInfo>
	 */
	public List<StaffInfo> findStaff(){
		
		List<StaffInfo> staffList = null;
		staffList = staffMngDao.getAllStaff();
		if(CollectionUtils.isEmpty(staffList))
			LOGGER.info("empty staff list");
		return staffList;
	}
	
	/**
	 * 参数为 id 根据id查询
	 * @param staffId
	 * @return List<StaffInfo>
	 */
	public List<StaffInfo> findStaff(int id){
		List<StaffInfo> staffList = null;
		staffList = staffMngDao.getStaffById(id);
		if(null == staffList)
			LOGGER.info("empty staff list");
		return staffList;
	}
	
	/**
	 * 参数为name 根据name查询
	 * @param staffName
	 * @return List<StaffInfo>
	 */
	public  List<StaffInfo> findStaff(String name){
		List<StaffInfo> staffList = null;
		staffList = staffMngDao.getStaffByName(name);
		if(CollectionUtils.isEmpty(staffList))
			LOGGER.info("empty staff list");
		return staffList;
	}
	
	/**
	 * 参数为rank 根据rank查询
	 * @param staffRank
	 * @return List<StaffInfo>
	 */
	public List<StaffInfo> findStaffGroup(int rank){
		List<StaffInfo> staffList = null;
		staffList = staffMngDao.getStaffGroup(rank);
		if(CollectionUtils.isEmpty(staffList))
			LOGGER.info("empty staff list");
		return staffList;
	}
	
	/**
	 * 修改员工信息
	 * @param StaffInfo
	 * @return boolean
	 */
	public boolean modifyStaffInfo(StaffInfo staff)
	{
		boolean status =false;
		if(null == staff)
		{
			LOGGER.info("staff is null");
			return false;
		}
		if(staffMngDao.updateStaffById(staff) > 0){
			LOGGER.info("modify staff: {} succeed ", staff.getStaffId());
			status = true;
		}else{
			LOGGER.info("modify staff: {} failed ",staff.getStaffId());
		};
		return status;
	}
	
	/**
	 * 删除员工信息
	 * @param id
	 * @return boolean
	 */
	public boolean deleteStaffInfo(int id)
	{
		boolean status = false;
		if(staffMngDao.deleteStaffById(id) > 0){
			LOGGER.info("modify staff: {} succeed ",id);
			status = true;
		}else{
			LOGGER.info("modify staff: {} failed ",id);
		};
		return status;
	}
}
