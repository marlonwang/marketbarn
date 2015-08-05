package com.springmvc.marketbarn.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.marketbarn.dao.ItemsDao;
import com.springmvc.marketbarn.model.Items;
import com.springmvc.marketbarn.model.LeftItems;

@Service
public class ItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemsDao itemsDao;
	
	/**
	 * 查询所有 商品
	 */
	public List<Items> findGoods()
	{	
		List<Items> list = null;
		list=itemsDao.getAllItems();
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("item-list size is: {}",list.size());
		
		return list;
	}
	
	/**
	 * 查询剩余商品
	 * @return
	 */
	public List<LeftItems> findleftGoods()
	{
		List<LeftItems> list = null;
		list=itemsDao.getAllLeftItems();
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("left-item list size is: {}",list.size());
		
		return list;
	}
	
	/**
	 * 根据名称查询
	 */
	public List<Items> findGoodsByName(String name)
	{
		List<Items> list = null;
		list=itemsDao.getItemByName(name);
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("item-list size is: {}",list.size());
		return list;
	}
	
	/**
	 * 根据商品类别查询
	 */
	public List<Items> findGoodsByCate(String cateCode)
	{
		List<Items> list = null;
		list=itemsDao.getItemsByCode(cateCode);
		
		if(!CollectionUtils.isEmpty(list))
			LOGGER.info("item-list length is: {}",list.size());
		return list;
	}
	
	/**
	 * 根据商品类别和名称查询
	 * @param name
	 * @param cate
	 * @return
	 */
	public List<Items> findGoodsByNameOrCate(String name,String cate)
	{
		List<Items> list = null;
		if(name != null && cate != null)
		{
			list = itemsDao.getItemsByNameAndCode(name, cate);
		}
		else if(name != null && cate ==null)
		{
			list = itemsDao.getItemByName(name);
		}
		else if(name == "" && cate != null)
		{
			list = itemsDao.getItemsByCode(cate);
		}
		else {
			list = itemsDao.getAllItems() ;
		}
		return list;
	}
	
	/**
	 * 添加商品
	 */
	public boolean addGoods(Items item)
	{
		int size = 0;
		if(null == item)
		{
			size = itemsDao.insertItemInfo(item);	
			if( size > 0)
				LOGGER.info("add {} items done ",size);		
		}
		else {
			LOGGER.error("item is empty ~");
			return false;
		}
		return true;
	}

}
