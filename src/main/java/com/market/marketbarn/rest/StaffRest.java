package com.market.marketbarn.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	private StaffService staffService;
	
	/* enum Rank {manager,inclerk,outclerk,masses };
	 * java自定义枚举 及 枚举的值
	 */
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
			staffLists.add(staffService.findStaff(Integer.parseInt(userId)));
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
	 * 用户登录
	 * @param name
	 * @param passwd
	 * @addition 此处使用GET不妥
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
					//TODO web前端调用 跳转 session什么的
					break;
				}
				else 
				{
					LOGGER.info("staff: {} does not exist", name);
					//TODO 前端页面调用 跳转
				}
			}
		}else{
			LOGGER.error("find staff failed ");
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
	@RequestMapping(value="market/manage/register",method = RequestMethod.PUT)
	public void StaffRegister(@RequestBody String registerInfo)
	{
		if(StringUtils.isEmpty(registerInfo))
		{
			LOGGER.error("sign in staff's register info is null");
			return ;
		}
		/*registerInfo 格式为
			${name},${age},${passwd},${sex},${mail},${phone},${rank}
			xiaoli,12,123456,女,123@123.com,13800001111,7
		*/
		try 
		{
			String[] str = registerInfo.split("[,]");
			StaffInfo staff = new StaffInfo();
			
			staff.setStaffName(str[0]);
			//为空时强转抛出异常
			if( !StringUtils.isEmpty(str[1]) )
				staff.setStaffAge(Integer.parseInt(str[1]));
			
			staff.setStaffPsswd(str[2]);
			staff.setStaffSex(str[3]);
			staff.setStaffMail(str[4]);
			staff.setStaffPhone(str[5]);
			
			if(!StringUtils.isEmpty(str[6]))
				staff.setStaffRank(Integer.parseInt(str[6]));
			
			if(staffService.appendStaff(staff))
			{
				System.out.println("staff register success");
				LOGGER.info("staff register succeed");
			}
			else{
				LOGGER.error("staff register failed ~");
			}
			
		} catch (Exception e) 
		{
			LOGGER.error("register failed ", e);
		}	
	}
	
	/**
	 * 员工辞职 删除员工信息
	 * @param staffId
	 * @return void
	 */
	@RequestMapping(value="market/manage/resign/{staffId}",method = RequestMethod.DELETE)
	public void staffResign(@PathVariable int staffId)
	{
		LOGGER.info("resign staff, staff id :{}",staffId);
		if(null != staffService.findStaff(staffId) )
		{
			if(staffService.deleteStaffInfo(staffId))
				LOGGER.info("resign staff completed.");
			else 
				LOGGER.info("delete staff id {} failed.",staffId);
		}
		else{
			LOGGER.error("No such staff found ");
		}
	}
	
	/**
	 * 修改员工信息
	 * @param staffId
	 * @param staffStr
	 * @return null
	 */
	@RequestMapping(value="market/manage/alter/{staffId}",method = RequestMethod.PUT)
	public void reviseStaffInfo(@PathVariable int staffId, @RequestBody String staffStr)
	{
		LOGGER.info("alter staff information , staff id :{}", staffId);
		
		/*	staffStr 格式为
			${name},${age},${passwd},${sex},${mail},${phone},${rank}
			xiaoli,12,123456,女,123@123.com,13800001111,7
		 */
		if(StringUtils.isEmpty(staffStr)){
			LOGGER.error("new information is empty");
			return ;
		}
		else if(!StringUtils.isEmpty(staffStr) && staffId > 0)
		{
			try 
			{
				String[] str = staffStr.split("[,]");
				StaffInfo staff = new StaffInfo();
				
				staff.setStaffName(str[0]);
				//为空时强转抛出异常
				if( !StringUtils.isEmpty(str[1]) )
					staff.setStaffAge(Integer.parseInt(str[1]));
				
				staff.setStaffPsswd(str[2]);
				staff.setStaffSex(str[3]);
				staff.setStaffMail(str[4]);
				staff.setStaffPhone(str[5]);
				
				if(!StringUtils.isEmpty(str[6]))
					staff.setStaffRank(Integer.parseInt(str[6]));
				//被修改员工id
				staff.setStaffId(staffId);
				
				if(null != staffService.findStaff(staffId))
				{
					if(staffService.deleteStaffInfo(staffId))
					{
						LOGGER.info("alter staff information succeed");
					}
					else {
						LOGGER.error("delete staff failed ~");
					}
				}
				else{
					LOGGER.error("staff {} does not exists ~", staffId);
				}
				
			} catch (Exception e) 
			{
				LOGGER.error("delete staff failed ~", e);
			}
		}
	}
	
	///////////////////////////

}
