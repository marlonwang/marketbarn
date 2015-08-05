package com.springmvc.marketbarn.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.marketbarn.dao.ItemsDao;
import com.springmvc.marketbarn.dao.LeftInfoDao;
import com.springmvc.marketbarn.model.LeftInfo;
import com.springmvc.marketbarn.model.LeftItems;
import com.springmvc.marketbarn.utils.JsonUtils;
import com.springmvc.marketbarn.utils.XmlUtils;

@Service
public class LeftItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LeftItemService.class);
	
	@Autowired
	private ItemsDao itemsDao;
	
	@Autowired 
	private LeftInfoDao leftDao;
	
	String basePath = "D:/market";
	
	/**
	 * 保存XMl到服务器
	 * @param filePath
	 * @param file
	 * @return
	 */
	public boolean uploadXmlFile(MultipartFile file)
	{
		LOGGER.info("save file to server");
		//获取上传文件名
		String fileName = file.getOriginalFilename();
		//取扩展名的前部分
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
		//保存文件成filename_20150529122311.xml形式
		DateTime dt=new DateTime();
		String finalFileName = fileName+"_"+dt.toString("yyyyMMddHHmmss")+".xml";
		
		String targetPath = basePath+File.separator+"upload"+File.separator+finalFileName;  
		
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(targetPath)));
                stream.write(bytes);
                stream.close();
            }catch(Exception e) {
            	LOGGER.info("failed to upload file ~");
            }
                return false;
            }
		
 		return true;
	}

	/**
	 * 生成库存信息到xml文件
	 * @return
	 */
	public boolean createRemainsXmlFile()
	{
		List<LeftInfo> result = leftDao.getAllLeft();

		DateTime dt=new DateTime();
		
		final String xmlPath =basePath+File.separator+dt.toString("yyyyMMdd")+".xml";
		
	    Map<String, String> values = new HashMap<>();
	    List<String> attributeList = new ArrayList<>();
	    for(LeftInfo rs : result)
	    {
	    	//设置 id
	    	attributeList.add("lft_id"+":"+String.valueOf(rs.getLeftId()));
	 	    //设置条形码
	 	    values.put("lft_item_barcode", rs.getBarcode());
	 	    //剩余数量
	 	    values.put("lft_item_num", String.valueOf(rs.getQuantity()) );
	 	    //统计时间
	 	    values.put("lft_count_time", rs.getCountTime().toString());
	 	    //附加信息
	 	    values.put("lft_addition", rs.getAddition());
	    }
	    
	    //设置根节点
	   	Document doc = DocumentHelper.createDocument();
	   	Element roots = DocumentHelper.createElement("leftItems");
	   	doc.setRootElement(roots);
	   	int count = attributeList.size();
	   	for(int i = 0;i<count;i++)
	   	{
	   		XmlUtils.buildXML(values,attributeList.get(i),doc,xmlPath); 
	   	}
		
		return true;
	}
	
	/**
	 * 添加XML文件中剩余商品到数据库
	 * @param leftItem
	 */
	public void addLeftItems(LeftItems leftItem)
	{
		LOGGER.info("add file to db ~");
		itemsDao.insetLeftItems(leftItem);
	}

}
