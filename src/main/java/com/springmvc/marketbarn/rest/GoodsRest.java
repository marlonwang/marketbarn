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

import com.springmvc.marketbarn.model.Category;
import com.springmvc.marketbarn.model.Items;
import com.springmvc.marketbarn.model.LeftItems;
import com.springmvc.marketbarn.service.CategoryService;
import com.springmvc.marketbarn.service.ItemService;

@RestController
public class GoodsRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsRest.class);
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 列出所有商品详情
	 * @return
	 */
	@RequestMapping(value="/goods/allgoods",method = RequestMethod.GET)
	public ModelAndView listGoodsAll()
	{
		LOGGER.info("query for all goods .");
		
		ModelAndView view =new ModelAndView();
		
		List<Category> cateList = categoryService.findAllCategories();
		
		List<Items> result= itemService.findGoods();
		if(!CollectionUtils.isEmpty(result))
		{
			view.addObject("ALL_CATEGORY",cateList);
			view.addObject("ALL_GOODS", result);
		}
		view.setViewName("items");
		return view;
	}
	
	/**
	 * 列出所有剩余商品
	 * @return
	 */
	@RequestMapping(value="/goods/leftgoods",method = RequestMethod.GET)
	public ModelAndView listLeftAll()
	{
		LOGGER.info("query for all left goods ...");
		ModelAndView view = new ModelAndView();
		
		List<LeftItems> result = itemService.findleftGoods();
		if(!CollectionUtils.isEmpty(result))
		{
			view.addObject("LEFT_GOODS",result);
		}
		view.setViewName("leavings");
		return view;
		
	}
	
	/**
	 * 条件查询商品信息
	 * @param itemName
	 * @param typeName
	 * @return
	 */
	@RequestMapping(value="/goods/queryGoods",method = RequestMethod.GET)
	public ModelAndView queryByCate(@RequestParam(value = "itemName", required = false)String itemName,
			@RequestParam(value="typeName",required = false)String typeName	)
	{
		LOGGER.info("query for goods by name: {} and code: {} ...",itemName,typeName);
		ModelAndView view = new ModelAndView();
		
		List<Items> result = itemService.findGoodsByNameOrCate(itemName, typeName);
		if(!CollectionUtils.isEmpty(result))
		{
			view.addObject("ALL_GOODS",result);
		}
		view.setViewName("items");
		return view;
	}
	
	/**
	 * 列出指定类别商品
	 * @param typeCode
	 * @return
	 */
	public ModelAndView listGoodsByType(@RequestParam(value = "goodsType", required = false)String typeCode)
	{
		ModelAndView view = new ModelAndView();
		List<Items> result = itemService.findGoodsByCate(typeCode);
		
		if(!CollectionUtils.isEmpty(result))
		{
			view.addObject("GoodsWithCode", result);
			view.setViewName("items");
		}
		return view;
	}

}
