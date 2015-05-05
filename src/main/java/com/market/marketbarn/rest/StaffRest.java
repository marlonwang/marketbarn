package com.market.marketbarn.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.market.marketbarn.model.StaffInfo;
import com.market.marketbarn.service.StaffService;
import com.market.marketbarn.utils.GeneralResult;
import com.market.marketbarn.utils.JsonUtils;

/**
 * 超市员工rest接口
 * @author wangwei
 * @date 2015-5-5
 *
 */
@RestController
public class StaffRest 
{
	// 日志记录
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffRest.class);
	
	//enum Rank {manager,inclerk,outclerk,masses };
	public enum Rank
	{
	     manager(7), inclerk(2), outclerk(1), masses(0);
	     private int _value;
	     private Rank(int value){
	         _value = value;
	     }
	 
	    public int value(){
	         return _value;
	     }
	 }
	
	@Autowired
	private StaffService staffService;
	
	/**
	 * 查询员工
	 * 无参查询所有人员、姓名查询、id查询
	 * @param userName 
	 * @param userId
	 * @addition 参数可null
	 * @return GeneralResult
	 */
	@RequestMapping(value="market/manage/find-staff",method = RequestMethod.GET)
	public GeneralResult findStaffResult(@RequestParam(value = "userName", required = false)String userName,
			@RequestParam(value = "userId",required = false)String userId)
	{
		LOGGER.info("find staff: staff id {} , staff name {}",userId, userName);
		
		GeneralResult result = new GeneralResult();
		List<StaffInfo> staffLists = new ArrayList<>();
		//无参时返回全部员工
		if(StringUtils.isEmpty(userName) && StringUtils.isEmpty(userId))
		{
			staffLists = staffService.findStaff();
			result.setResultData(staffLists);
			result.setResultStatus(true);
		}
		else if(!StringUtils.isEmpty(userName))
		{
			staffLists = staffService.findStaff(userName);
			result.setResultData(staffLists);
			result.setResultStatus(true);
		}
		// 不妥之处 Integer.parseInt(userId)强转会出现异常
		else if(Integer.parseInt(userId) > 0)
		{
			staffLists = staffService.findStaff(Integer.parseInt(userId));
			result.setResultData(staffLists);
			result.setResultStatus(true);
		}
	
		LOGGER.info("find staff result {}", JsonUtils.obj2json(result));
		return result;
	}
	
	/**
	 * 按员工级别查询
	 * @param int
	 * @return GeneralResult
	 */
	@RequestMapping(value="market/manage/find-group",method = RequestMethod.GET)
	public GeneralResult findGroupResult(@RequestParam(value = "rank", required = true)int rank)
	{
		GeneralResult result = new GeneralResult();
		List<StaffInfo> staffLists = null;
		LOGGER.info("find staff group, staff rank:{}",rank);
		if(rank >= 0)
		{
			staffLists = staffService.findStaffGroup(rank);
			result.setResultStatus(true);
			result.setResultData(staffLists);
		}else{
			result.setErrorMessage("rank is invalid ");
			LOGGER.error("invalid rank value {}", rank);
		}
		return result;
	}
	
	/**
	 * 
	 * @param name
	 * @param passwd
	 */
	@RequestMapping(value="market/manage/login",method = RequestMethod.GET)
	public void login(@RequestParam(value = "name", required = true)String name,
			@RequestParam(value = "passwd", required = true)String passwd)
	{
		List<StaffInfo> staff = new ArrayList<>();
		staff = staffService.findStaff(name);
		if(!CollectionUtils.isEmpty(staff))
		{
			Iterator<StaffInfo> iterator = staff.iterator();
			while(iterator.hasNext())
			{
				if(passwd == iterator.next().getStaffPasswd())
				{
					LOGGER.info("staff: {} exist",name);
					//TO DO
					break;
				}
				else 
				{
					LOGGER.info("staff: {} does not exist", name);
					//TO DO
				}
			}
		}
	}
	
	/**
	 * 新员工注册
	 * @param id
	 * @param name
	 * @param age
	 * @param password
	 * @param sex
	 * @param mail
	 * @param phone
	 * @param rank
	 */
	
	// 此处应为put方法
	@RequestMapping(value="market/manage/register",method = RequestMethod.GET)
	public void StaffRegister(String name, int age, String password, String sex, String mail,
			String phone, int rank)
	{
		if(StringUtils.isEmpty(name))
			LOGGER.error("sign in staff's name is null");
		
		StaffInfo staff = new StaffInfo();
		
		staff.setStaffName(name);
		staff.setStaffAge(age);
		staff.setStaffPsswd(password);
		staff.setStaffSex(sex);
		staff.setStaffMail(mail);
		staff.setStaffPhone(phone);
		staff.setStaffRank(rank);
		
		if(staffService.appendStaff(staff))
		{
			System.out.println("staff register success");
			LOGGER.info("sign in staff succeed");
		}
		else{
			LOGGER.error("sign in failed ~");
		}
	}

}
