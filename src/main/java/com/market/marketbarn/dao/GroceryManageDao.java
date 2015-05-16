package com.market.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.collections.CollectionUtils;
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
	
	/**
	 * 添加百货信息
	 * @param grocery
	 * @return
	 */
	public int insertGroceryInfo(Grocery grocery)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_items_groceries (gc_name, gc_code, gc_barcode, gc_description, gc_spec, gc_is_qualified, "
				+ "gc_perform_standard, gc_producer, gc_producer_addr, gc_producer_phone, gc_producer_mail, gc_produced_time, "
				+ "gc_ingredient, gc_addition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			rows = jdbcTemplate.update(insertSql, 
					new Object[]{
					grocery.getGroceryName(),
					grocery.getGroceryCode(),
					grocery.getBarcode(),
					grocery.getDescription(),
					grocery.getSpecific(),
					grocery.getIsQualified(),
					grocery.getStandard(),
					grocery.getProducer(),
					grocery.getAddress(),
					grocery.getTelnumber(),
					grocery.getEmail(),
					grocery.getProduceDate(),
					grocery.getComponent(),
					grocery.getAddition()
			});
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to insert grocery info ~", e);
		}
		return rows;
	}
	
	/**
	 * 批量添加百货信息
	 * @param groceryQueue
	 */
	public void batchInsertGroceryInfo(BlockingQueue<Grocery> groceryQueue)
	{
		String insertSql = "INSERT INTO mkt_items_groceries (gc_name, gc_code, gc_barcode, gc_description, gc_spec, gc_status, gc_is_qualified, "
				+ "gc_perform_standard, gc_producer, gc_producer_addr, gc_producer_phone, gc_producer_mail, gc_produced_time, "
				+ "gc_ingredient, gc_addition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		List<Object[]> batch = new ArrayList<Object[]>();
		try {
			while(!CollectionUtils.isEmpty(groceryQueue))
			{
				Grocery grocery = groceryQueue.take();
				Object[] values = new Object[]{
						grocery.getGroceryName(),
						grocery.getGroceryCode(),
						grocery.getBarcode(),
						grocery.getDescription(),
						grocery.getSpecific(),
						grocery.getIsQualified(),
						grocery.getStandard(),
						grocery.getProducer(),
						grocery.getAddress(),
						grocery.getTelnumber(),
						grocery.getEmail(),
						grocery.getProduceDate(),
						grocery.getComponent(),
						grocery.getAddition()
				};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql, batch);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("bach insert grocery information failed ~", e);
		}
	}
	
	/**
	 * 删除对应id的百货信息
	 * @param groceryId
	 * @return int
	 */
	public int deleteGroceryByid(int groceryId)
	{
		int rows = 0;
		String delSql = "DELETE FROM mkt_items_groceries WHERE gc_id = ? ";
		try {
			jdbcTemplate.update(delSql, new Object[]{ groceryId });
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("delete grocery by id failed ~", e);
		}
		return rows;
	}
	
	/**
	 * 更改指定id的百货信息
	 * @param grocery
	 * @return int
	 */
	public int updateGroceryById(Grocery grocery)
	{
		int rows = 0;
		String updateSql = "UPDATE mkt_items_groceries SET gc_name = ?, gc_code = ?, gc_barcode = ?, gc_description = ?, gc_spec = ?, "
				+ "gc_is_qualified = ?, gc_perform_standard = ?, gc_producer = ?, gc_producer_addr = ?, gc_producer_phone = ?, "
				+ "gc_producer_mail = ?, gc_produced_time = ?, gc_ingredient = ?, gc_addition = ? WHERE gc_id = ?";
		try {
			rows = jdbcTemplate.update(updateSql, 
					new Object[]{
					grocery.getGroceryName(),
					grocery.getGroceryCode(),
					grocery.getBarcode(),
					grocery.getDescription(),
					grocery.getSpecific(),
					grocery.getIsQualified(),
					grocery.getStandard(),
					grocery.getProducer(),
					grocery.getAddress(),
					grocery.getTelnumber(),
					grocery.getEmail(),
					grocery.getProduceDate(),
					grocery.getComponent(),
					grocery.getAddition(),
					grocery.getGroceryId()
			});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to update grocery by id ~",e);
		}
		return rows;
	}
/*	
	gc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gc_name VARCHAR(50) NOT NULL,
	gc_code CHAR(5),
	gc_barcode CHAR(13),
	gc_description TEXT,
	gc_spec VARCHAR(20) COMMENT '规格',

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
