package com.springmvc.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.springmvc.marketbarn.model.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 商品类别表接口
 * @author wangwei
 * @date 2015-05-09
 */
@Repository
public class CategoryDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取所有物品分类
	 * @return
	 */
	public List<Category> getAllCategory()
	{
		List<Category> categoryList = null;
		String querySql = "SELECT * FROM mkt_items_category";
		try {
			categoryList = jdbcTemplate.query(querySql, new CategoryMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get all category ~",e);
		}
		return categoryList;
	}
	
	/**
	 * 根据类别名称查找类别信息 主要是  类别码ct_code
	 * @param categoryName
	 * @return
	 */
	public Category getCategoryCodeByName(String categoryName)
	{
		Category category = null;
		String querySql = "SELECT * FROM mkt_items_category WHERE ct_category = ? LIMIT 1";
		try {
			category = jdbcTemplate.queryForObject(querySql, new Object[]{ categoryName }, new CategoryMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get category by category name {}",categoryName,e);
		}
		return category;
	}
	
	/**
	 * 根据类别编号查找 类别信息 主要是类别名称
	 * @param categoryCode
	 * @return
	 */
	public Category getCategoryNameByCode(String categoryCode)
	{
		Category category = null;
		String querySql = "SELECT * FROM mkt_items_category WHERE ct_code = ? LIMIT 1";
		try {
			category = jdbcTemplate.queryForObject(querySql, new Object[]{ categoryCode }, new CategoryMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get category by category code {}",categoryCode,e);
		}
		return category;
	}
	
	
	
	/**
	 * 添加类别信息
	 * @param category
	 * @return
	 */
	public int insertCategory(Category category)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_items_category (ct_code,ct_category,ct_desc) VALUES (?,?,?)";
		try {
			rows = jdbcTemplate.update(insertSql, 
					new Object[]{
					category.getCategoryCode(),
					category.getCategoryName(),
					category.getCategoryDesc()
			});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to insert category ~",e);
		}
		return rows;
	}
	
	/**
	 * 更新category内容
	 * @param category
	 * @return
	 */
	public int updateCategory(Category category)
	{
		int rows = 0;
		String updateSql = "UPDATE mkt_items_category SET ct_code = ?, ct_category = ?, ct_desc = ? WHERE ct_id = ?";
		try {
			rows = jdbcTemplate.update(updateSql, 
					new Object[]{
					category.getCategoryCode(), 
					category.getCategoryName(), 
					category.getCategoryDesc(),
					category.getCategoryId()
				});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to update category ~",e);
		}
		return rows;
	}
	
	/**
	 * 映射表mkt_items_category中字段到 Category类
	 * @author wangwei
	 * @return
	 * @date 2015-05-09
	 */
	class CategoryMapper implements RowMapper<Category>
	{
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Category category = new Category();
			category.setCategoryId(rs.getInt("ct_id"));
			category.setCategoryCode(rs.getString("ct_code"));
			category.setCategoryName(rs.getString("ct_category"));
			category.setCategoryDesc(rs.getString("ct_desc"));
			
			return category;
		}
	}
}
