package com.springmvc.marketbarn.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.marketbarn.dao.StockInfoDao;
import com.springmvc.marketbarn.model.StockInfo;

@Service
public class StockService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);
	
	@Autowired
	private StockInfoDao stockInfoDao;
	
	/**
	 * 查询所有进货信息
	 */
	public List<StockInfo> findStocks()
	{
		List<StockInfo> list = null;
		list=stockInfoDao.getAllStockInfo();
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("stock-list size is: {}",list.size());
		
		return list;
	}
	
	/**
	 * 指定起始时间
	 * 入库情况
	 */
	public List<StockInfo> findStocksDuration(String beginDate,String endDate)
	{
		List<StockInfo> list = null;
		if(beginDate!=null && endDate !=null)
		{
			list=stockInfoDao.getStockBetweenDate(beginDate, endDate);
		}
		else if(beginDate == null && endDate !=null)
		{
			list = stockInfoDao.getStockBeforeDate(endDate);
		}
		else if(beginDate != null && endDate ==null)
		{
			list = stockInfoDao.getStockAfterDate(beginDate);
		}
		else
		{
			list = stockInfoDao.getAllStockInfo();
		}
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("stock-list size is: {}",list.size());
		
		return list;
	}
	
	/**
	 * 添加进货信息
	 * @param stockInfo
	 */
	public void addStock(StockInfo stockInfo)
	{
		LOGGER.info("add stock info .");
		stockInfoDao.insertStockInfo(stockInfo);
	}

}
