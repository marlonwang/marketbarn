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

import com.springmvc.marketbarn.model.StockInfo;
import com.springmvc.marketbarn.service.StockService;

@RestController
public class StockRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockRest.class);
	
	@Autowired
	private StockService stockService ;
	
	/**
	 * 列出全部进货记录
	 * @return
	 */
	@RequestMapping(value="/stock/stockList",method=RequestMethod.GET)
	public ModelAndView listStocksAll()
	{
		LOGGER.info("query for all stocks .");
		ModelAndView view = new ModelAndView();
		List<StockInfo> result = stockService.findStocks();
		
		if(!CollectionUtils.isEmpty(result))
		{
			view.addObject("ALL_STOCKS",result);
		}
		view.setViewName("stock");
		return view;
	}
	
	/**
	 * 进货
	 * @param barCode
	 * @param categoryCode
	 * @param stockNum
	 * @param stockPrice
	 * @param worker
	 * @return
	 */
	@RequestMapping(value="/stock/addStock",method = RequestMethod.POST)
	public ModelAndView addStock(@RequestParam(value="goodsBarcode",required =true)String barCode,
			@RequestParam(value="goodsCate",required=true)String categoryCode,
			@RequestParam(value="goodsQuantity",required=true)int stockNum,
			@RequestParam(value="goodsPrice",required=true)double stockPrice,
			@RequestParam(value="goodsOperator",required=true)String worker)
			
	{
		LOGGER.info("add stock info, barcode:{}, quantity:{}",barCode,stockNum);
		ModelAndView view = new ModelAndView("error");
		StockInfo stockInfo = new StockInfo();
		
		if(null!=barCode && null!=categoryCode && stockNum > 0 && stockPrice > 0)
		{
			stockInfo.setBarcode(barCode);
			stockInfo.setCategoryCode(categoryCode);
			stockInfo.setStockPrice(stockPrice);
			stockInfo.setStockQuantity(stockNum);
			stockInfo.setOperator(worker);
			
			stockService.addStock(stockInfo);
			
			view.setViewName("stock");
		}
		else {
			view.setViewName("error");
		}
		
		return view;
	}
	
	/**
	 * 查询进货信息
	 * @param type
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value="/stock/queryStock",method = RequestMethod.GET)
	public ModelAndView listStockDuration(@RequestParam(value="itemType",required=false)String type,
			@RequestParam(value="stockDate1",required=false)String beginDate,
			@RequestParam(value="stockDate2",required=false)String endDate)
	{
		ModelAndView view = new ModelAndView();
		
		List<StockInfo> result=null;
		result=stockService.findStocksDuration(beginDate, endDate);
		if(!CollectionUtils.isEmpty(result))
		{
			LOGGER.info("stock info list size: ",result.size());
			view.addObject("ALL_STOCKS",result);
		}
		view.setViewName("stock");
		return view;
	}

}
