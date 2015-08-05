package com.springmvc.marketbarn.service;

import java.util.List;

import com.springmvc.marketbarn.dao.CategoryDao;
import com.springmvc.marketbarn.model.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品分类操作
 * @author wangwei
 * @date 2015-5-11
 */

@Service
public class CategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryDao categoryDao;
	
	/**
	 * 所有商品类别
	 * @return
	 */
	public List<Category> findAllCategories()
	{
		List<Category> categoryList = categoryDao.getAllCategory();
		LOGGER.info("find all categories , size: {}",categoryList.size());
		return categoryList;
	}
	
	/**
	 * 
	 * @param categoryName
	 * @return
	 */
	public Category findCategoryByName(String categoryName)
	{
		Category category = categoryDao.getCategoryCodeByName(categoryName);
		if(null != category)
			LOGGER.info("find category by name:{}",categoryName);
		else 
			LOGGER.info("nothing find by name");
		return category;
	}

}
