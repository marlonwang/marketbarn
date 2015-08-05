package com.springmvc.marketbarn.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.springmvc.marketbarn.model.StaffInfo;
import com.springmvc.marketbarn.service.StaffService;
import com.springmvc.marketbarn.utils.JsonUtils;
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
	
	/**
	 * 查询员工
	 * 无参查询所有人员
	 */
	@RequestMapping(value="/manage/allstaff",method = RequestMethod.GET)
	public ModelAndView findStaffAll()
	{
		LOGGER.info("query for all staffs ");
		
		ModelAndView view =new ModelAndView();
		List<StaffInfo> result= staffService.findStaff();
		if(!CollectionUtils.isEmpty(result))
		{
			view.addObject("allStaff", result);
		}
		view.setViewName("staff");
		return view;
	}
	
	
	
	/**
	 * 用户登录
	 * @param name
	 * @param passwd
	 * @addition 
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "name", required = true)String name,
			@RequestParam(value = "password", required = true)String password,
			HttpServletRequest request)
	{
		ModelAndView loginView = new ModelAndView();
		List<StaffInfo> staff = new ArrayList<>();
		staff = staffService.findStaff(name);
		if(!CollectionUtils.isEmpty(staff))
		{
			Iterator<StaffInfo> iterator = staff.iterator();
			while(iterator.hasNext())
			{
				if( iterator.next().getStaffPasswd().equals(password) )
				{
					LOGGER.info("staff: {} exists ",name);
					request.getSession().setAttribute("G_NAME", name);
					loginView.setViewName("items");
				}
				else 
				{
					LOGGER.info("staff: {} does not exist", name);
					//TODO 前端页面调用 跳转
					loginView.setViewName("error");
				}
			}
		}else{
			LOGGER.error("find staff failed ");
		}
		return loginView;
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
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ModelAndView register(@RequestParam(value="staffName",required=true)String staffName,
			@RequestParam(value="email",required=true)String staffEmail,
			@RequestParam(value="staffSex",required=false)String staffSex,
			@RequestParam(value="staffAge",required=false)int staffAge,
			@RequestParam(value="staffRank",required=false)int staffRank,
			@RequestParam(value="staffPasswd",required=true)String staffPasswd)
	{
		//System.out.println(staff.getStaffName());
		ModelAndView registerView = new ModelAndView();
		StaffInfo staff = new StaffInfo();
		
		//前台处传值
		staff.setStaffName(staffName);
		staff.setStaffAge(staffAge);
		staff.setStaffMail(staffEmail);
		staff.setStaffRank(staffRank);
		staff.setStaffSex(staffSex);
		staff.setStaffPasswd(staffPasswd);
		
		LOGGER.info("begin to register staff ");
		try{
			if(staffService.appendStaff(staff))
			{
				LOGGER.info("staff register succeed");
				registerView.setViewName("success");
			}
			else{
				LOGGER.error("staff register failed ~");
				registerView.setViewName("error");
			}
			
		} catch (Exception e) 
		{
			LOGGER.error("register failed ", e);
		}
		return registerView;	
	}
	
	/**
	 * 姓名查询 
	 * @param userName 
	 * @param userId
	 * @addition 参数可null
	 * @return GeneralResult
	 */
	@RequestMapping(value="/manage/findStaff",method = RequestMethod.GET)
	public ModelAndView findStaff(@RequestParam(value = "userName", required = false)String userName)
	{
		LOGGER.info("find staff name: {}",userName);
		ModelAndView view=new ModelAndView();
		List<StaffInfo> staffResult = null;
		
		//无参时返回全部员工
		if(StringUtils.isEmpty(userName))
		{
			staffResult = staffService.findStaff();
			
			view.addObject("allStaff",staffResult);
			view.setViewName("staff");
		}
		//有参返回指定的员工
		else if(!StringUtils.isEmpty(userName))
		{
			staffResult = staffService.findStaff(userName);
			view.addObject("allStaff",staffResult);
			view.setViewName("staff");
		}
	
		LOGGER.info("find staff result: {}", JsonUtils.obj2json(staffResult));
		return view;
	}
	
	/**
	 * 员工辞职 删除员工信息
	 * @param staffId
	 * @return void
	 */
	@RequestMapping(value="/manage/resign/{staffId}",method = RequestMethod.DELETE)
	public void staffResign(@PathVariable int staffId)
	{
		LOGGER.info("resign staff, staff id :{}",staffId);
		if(!CollectionUtils.isEmpty(staffService.findStaff(staffId)) )
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
	

}
