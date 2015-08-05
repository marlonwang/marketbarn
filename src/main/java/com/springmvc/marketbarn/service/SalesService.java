package com.springmvc.marketbarn.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.marketbarn.dao.SalesInfoDao;
import com.springmvc.marketbarn.model.SalesInfo;

@Service
public class SalesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesService.class);
	
	@Autowired
	private SalesInfoDao salesInfoDao;
	
	/**
	 * 查询所有销售记录
	 * @return
	 */
	public List<SalesInfo> findSales()
	{
		List<SalesInfo> list = null;
		list=salesInfoDao.getAllSales();
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("sales-list size is: {}",list.size());
		
		return list;
	}
	
	/**
	 * 根据时间查询销售记录
	 */
	public List<SalesInfo> findSalesByDate(String dateStr)
	{
		List<SalesInfo> list =null;
		list=salesInfoDao.getSalesByDate(dateStr);
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("sales-list size is: {}",list.size());
		return list;
	}
	
	/**
	 * 查询指定时间间隔的出库信息
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<SalesInfo> findSalesDuration(String beginDate, String endDate)
	{
		List<SalesInfo> list =null;
		if(beginDate != null && endDate !=null)
		{
			list = salesInfoDao.getSalesBetweenDate(beginDate, endDate);
		}
		else if(beginDate == null && endDate != null)
		{
			list = salesInfoDao.getSalesBeforeDate(endDate);
		}
		else if(beginDate !=null && endDate == null)
		{
			list = salesInfoDao.getSalesAfterDate(beginDate);
		}
		else
		{
			list = salesInfoDao.getAllSales();
		}
		return list;
	}

}
