package com.market.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.market.marketbarn.model.Grocery;

/**
 * 百货商品信息接口
 * @author wangwei
 * @version
 * @date 2015-5-7
 */
@Repository
public class GroceryManageDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GroceryManageDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取百货表百货信息
	 * @return List<Grocery>
	 * @exception
	 */
	public List<Grocery> getAllGroceries()
	{
		List<Grocery> groceryList = null;
		String querySql = "SELECT * FROM mkt_items_groceries ";
		try {
			groceryList = jdbcTemplate.query(querySql, new GroceryMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get grocery list ~", e);
		}
		return groceryList;
	}
	
	/**
	 * 根据百货id查询
	 * @param groceryId
	 * @return Grocery
	 */
	public Grocery getGroceryById(int groceryId)
	{
		Grocery grocery = null;
		String querySql = "SELECT * FROM mkt_items_groceries WHERE gc_id = ? LIMIT 1 ";
		try {
			grocery = jdbcTemplate.queryForObject(querySql, new Object[]{ groceryId }, new GroceryMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get grocery by id ~", e);
		}
		return grocery;
	}
	
	/**
	 * 根据百货名称查询
	 * @param groceryName
	 * @return
	 * @exception
	 */
	public List<Grocery> getGroceryByName(String groceryName)
	{
		List<Grocery> groceryList = null;
		String querySql = "SELECT * FROM mkt_items_groceries WHERE gc_name = ? ";
		try {
			groceryList = jdbcTemplate.query(querySql, new Object[]{ groceryName }, new GroceryMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get grocery by name ~", e);
		}
		return groceryList;
	}
	
	/**
	 * 根据百货在库状态查询
	 * @param status
	 * @return
	 */
	public List<Grocery> getGroceryByStatus(String status)
	{
		List<Grocery> groceryList = null;
		String querySql = "SELECT * FROM mkt_items_groceries WHERE gc_status = ? ";
		try {
			groceryList = jdbcTemplate.query(querySql, new Object[]{ status }, new GroceryMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get grocery by status ~", e);
		}
		return groceryList;
	}
	
/*	
	gc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gc_name VARCHAR(50) NOT NULL,
	gc_code CHAR(5),
	gc_barcode CHAR(13),
	gc_description TEXT,
	gc_spec VARCHAR(20) COMMENT '规格',
	gc_status ENUM("在库","出库","损坏","丢失"),
	gc_is_qualified TINYINT(1),
	gc_perform_standard VARCHAR(15),
	gc_producer CHAR(50),
	gc_producer_addr VARCHAR(100),
	gc_producer_phone VARCHAR(15),
	gc_producer_mail VARCHAR(50),
	gc_produced_time TIMESTAMP DEFAULT NOW(),
	gc_ingredient VARCHAR(50) COMMENT '成分',
	gc_addition TEXT 
	*/
	
	class GroceryMapper implements RowMapper<Grocery>
	{
		@Override
		public Grocery mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Grocery grocery = new Grocery();
			
			grocery.setGroceryId(rs.getInt("gc_id"));
			grocery.setGroceryName(rs.getString("gc_name"));
			grocery.setGroceryCode(rs.getString("gc_code"));
			grocery.setBarcode(rs.getString("gc_barcode"));
			grocery.setDescription(rs.getString("gc_description"));
			grocery.setSpecific(rs.getString("gc_spec"));
			grocery.setStatus(rs.getString("gc_status"));
			grocery.setIsQualified(rs.getByte("gc_is_qualified"));
			grocery.setStandard(rs.getString("gc_perform_standard"));
			grocery.setProducer(rs.getString("gc_producer"));
			grocery.setAddress(rs.getString("gc_producer_addr"));
			grocery.setTelnumber(rs.getString("gc_producer_phone"));
			grocery.setEmail(rs.getString("gc_producer_mail"));
			grocery.setProduceDate(rs.getDate("gc_produced_time"));
			grocery.setComponent(rs.getString("gc_ingredient"));
			grocery.setAddition(rs.getString("gc_addition"));
			
			return grocery;
		}
	}

}
