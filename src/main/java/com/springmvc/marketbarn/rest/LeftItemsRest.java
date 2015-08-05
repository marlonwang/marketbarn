package com.springmvc.marketbarn.rest;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.marketbarn.service.LeftItemService;
import com.springmvc.marketbarn.utils.XmlUtils;

@RestController
public class LeftItemsRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LeftItemsRest.class);
	
	@Autowired
	LeftItemService leftItemService;
	
	/**
	 * 上传文件保存到本地
	 * @param file
	 */
	@RequestMapping(value="/report/leftReport",method=RequestMethod.POST)
	public ModelAndView uploadXmlFile(@RequestParam(value="fileToUpload",required=true)MultipartFile file,
			@RequestParam(value="operator",required=false)String operator)
	{
		LOGGER.info("beginning upload xml file: ",file.getOriginalFilename());
		
		ModelAndView view = new ModelAndView();
		if(leftItemService.uploadXmlFile(file))
		{
			LOGGER.info("upload file succeed ");
			view.setViewName("success");
		}
		return view;
		
	}
	
	/**
	 * 生成库存信息到XML
	 * @return
	 */
	@RequestMapping(value="/report/generate")
	public ModelAndView generateXmlFile()
	{
		ModelAndView view=new ModelAndView();
		if(leftItemService.createRemainsXmlFile())
		{
			LOGGER.info("generate left info to xml ");
			view.setViewName("success");
		}
		return view;
	}
	
	/**
	 * 导入xml文件到数据库
	 * @param file
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/report/leftImport")
	public ModelAndView importXmlFile(@RequestParam(value="fileToImport",required=true)MultipartFile file,
			@RequestParam(value="operator",required=false)String operator,
			HttpServletRequest request)
	{
		//取session中的用户名
		operator = request.getSession().getAttribute("G_NAME").toString();
		if(operator == null)
			operator = "null";
		
		ModelAndView view = new ModelAndView();
		
		//读取xml文件内容
		File file2 = null;
		try {
			file.transferTo(file2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XmlUtils.readXML(file2);
		return view;
	}
}
