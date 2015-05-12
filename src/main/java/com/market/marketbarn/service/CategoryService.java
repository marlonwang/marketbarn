package com.market.marketbarn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.market.marketbarn.dao.CategoryDao;
import com.market.marketbarn.model.ItemCategory;

/**
 * 商品分类操作
 * @author wangwei
 * @date 2015-5-11
 */

public class CategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<ItemCategory> findAllCategories()
	{
		List<ItemCategory> categoryList = categoryDao.getAllCategory();
		LOGGER.info("find all categories , size: {}",categoryList.size());
		return categoryList;
	}
	
	public ItemCategory findCategoryByName(String categoryName)
	{
		ItemCategory category = categoryDao.getCategoryCodeByName(categoryName);
		if(null != category)
			LOGGER.info("find category by name:{}",categoryName);
		else 
			LOGGER.info("nothing find by name");
		return category;
	}

}
