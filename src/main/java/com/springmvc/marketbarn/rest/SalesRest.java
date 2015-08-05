package com.springmvc.marketbarn.rest;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.marketbarn.model.SalesInfo;
import com.springmvc.marketbarn.service.SalesService;

@RestController
public class SalesRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesRest.class);
	
	@Autowired
	private SalesService salesService;
	
	/**
	 * 返回所有出库记录
	 * @return
	 */
	@RequestMapping(value="/sales/saleList",method=RequestMethod.GET)
	public ModelAndView listSalesAll()
	{
		LOGGER.info("query for all sales .");
		
		ModelAndView view = new ModelAndView();
		List<SalesInfo> result = salesService.findSales();
		
		if(!CollectionUtils.isEmpty(result))
		{
			view.addObject("ALL_SALES", result);
		}
		view.setViewName("sales");
		return view;
	}
	
	/**
	 * 查询指定时间内的出货信息
	 * @param type
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value="/sales/querySales", method = RequestMethod.GET)
	public ModelAndView listSalesDuration(@RequestParam(value="itemType",required=false)String type,
			@RequestParam(value="salesDate1",required=false)String beginDate,
			@RequestParam(value="salesDate2",required=false)String endDate)
	{
		LOGGER.info("find sales begin:{}, end:{}",beginDate,endDate);
		ModelAndView view = new ModelAndView();
		List<SalesInfo> result = null;
		
		result = salesService.findSalesDuration(beginDate, endDate);
		if(!CollectionUtils.isEmpty(result))
		{
			LOGGER.info("salses in list size: {}",result.size());
			view.addObject("ALL_SALES", result);
		}
		view.setViewName("sales");
		return view;
	}

}
