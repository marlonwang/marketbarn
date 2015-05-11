package com.market.marketbarn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.market.marketbarn.dao.CategoryDao;

/**
 * 商品分类操作
 * @author wangwei
 *
 */

public class CategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryDao categoryDao;
	
	

}
