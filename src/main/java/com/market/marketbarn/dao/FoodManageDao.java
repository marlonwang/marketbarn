package com.market.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.market.marketbarn.model.Food;


/**
 * 食品类商品信息 接口
 * @author wangwei
 * @date 2015-5-6
 *
 */
@Repository
public class FoodManageDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FoodManageDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取所有食品信息
	 * @param 
	 * @return List<Food>
	 */
	public List<Food> getAllFood(){
		List<Food> foodList = null;
		String querySql = "SELECT * FROM mkt_items_food";
		try {
			foodList = jdbcTemplate.query(querySql, new FoodMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get food list ~", e);
		}
		return foodList;
	}
	
	/**
	 * 根据id查询食品信息
	 * @param foodId
	 * @return
	 */
	public Food getFoodById(int foodId)
	{
		Food food = null;
		String querySql = "SELECT * FROM mkt_items_food WHERE fd_id = ? LIMIT 1";
		try {
			food = jdbcTemplate.queryForObject(querySql, new Object[]{ foodId },new FoodMapper());
		} catch (Exception e) {
			LOGGER.info("failed to find food by id ~", e);
		}
		return food;
	}
	
	/**
	 * 根据食品名称返回food
	 * @param foodName
	 * @return
	 */
	public List<Food> getFoodByName(String foodName)
	{
		List<Food> foodList = null;
		String querySql = "SELECT * FROM mkt_items_food WHERE fd_name = ? ";
		try {
			foodList = jdbcTemplate.query(querySql, new Object[]{ foodName }, new FoodMapper());
		} catch (Exception e) {
			LOGGER.info("failed to find food by id ~", e);
		}
		return foodList;
	}
	
	/**
	 * 根据食品库存状态查询
	 * 状态: 在库、出库、损坏、丢失
	 * @param status
	 * @return
	 */
	public List<Food> getFoodByStatus(String status)
	{
		List<Food> foodList = null;
		String querySql = "SELECT * FROM mkt_items_food WHERE fd_status = ? ";
		try {
			foodList = jdbcTemplate.query(querySql, new Object[]{ status }, new FoodMapper());
		} catch (Exception e) {
			LOGGER.info("failed to find food by status ~", e);
		}
		return foodList;
	}
	
	/**
	 * 添加食品信息
	 * @param food
	 * @return
	 */
	public int inserFoodInfo(Food food)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_items_food (fd_name, fd_code, fd_barcode, fd_description, fd_capacity, fd_status, "
				+ "fd_is_qualified, fd_perform_standard, fd_producer, fd_producer_addr, fd_producer_phone, fd_producer_mail, "
				+ "fd_produced_time, fd_ingredient, fd_addition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			rows = jdbcTemplate.update(insertSql, 
					new Object[]{
					food.getFoodName(),
					food.getFoodCode(),
					food.getBarcode(),
					food.getDescription(),
					food.getCapacity(),
					food.getStatus(),
					food.getIsQualified(),
					food.getStandard(),
					food.getProducer(),
					food.getAddress(),
					food.getTelnumber(),
					food.getEmail(),
					food.getProduceDate(),
					food.getIngredient(),
					food.getAddition()} 
			);
		} catch (Exception e) {
			LOGGER.info("failed to insert food info ~", e);
		}
		return rows;
	}
	
	/**
	 * 批量添加食品商品信息
	 * @param foodQueue
	 * @return void
	 */
	public void batchInsertFoodInfo(BlockingQueue<Food> foodQueue)
	{
		String insertSql = "INSERT INTO mkt_items_food (fd_name, fd_code, fd_barcode, fd_description, fd_capacity, fd_status, "
				+ "fd_is_qualified, fd_perform_standard, fd_producer, fd_producer_addr, fd_producer_phone, fd_producer_mail, "
				+ "fd_produced_time, fd_ingredient, fd_addition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		List<Object[]> batch = new ArrayList<Object[]>();
		
		try {
			while (!foodQueue.isEmpty()){
				Food food = foodQueue.take();
				Object[] values = new Object[]{
						food.getFoodName(),
						food.getFoodCode(),
						food.getBarcode(),
						food.getDescription(),
						food.getCapacity(),
						food.getStatus(),
						food.getIsQualified(),
						food.getStandard(),
						food.getProducer(),
						food.getAddress(),
						food.getTelnumber(),
						food.getEmail(),
						food.getProduceDate(),
						food.getIngredient(),
						food.getAddition()	
				};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql, batch);
		} catch (Exception e) {
			LOGGER.info("batch insert failed ~", e);
		}
		
	}
	
	/**
	 * 根据id删除食品商品
	 * @param foodId
	 * @return
	 */
	public int deleteFoodById(int foodId)
	{
		int rows = 0;
		String delSql = "DELETE FROM mkt_items_food WHERE fd_id = ? ";
		try {
			rows = jdbcTemplate.update(delSql, new Object[]{ foodId });
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to delete food ~", e);
		}
		return rows;
	}
	
	/**
	 * 根据id更新食品商品信息
	 * @param food
	 * @return
	 */
	public int updateFoodById(Food food)
	{
		int rows = 0;
		String updateSql = "UPDATE mkt_items_food SET fd_name = ?, fd_code = ?, fd_barcode = ?, fd_description = ?, "
				+ "fd_capacity = ?, fd_status = ?, fd_is_qualified = ?, fd_perform_standard = ?, fd_producer = ?, "
				+ "fd_producer_addr = ?, fd_producer_phone = ?, fd_producer_mail = ?, fd_produced_time = ?, "
				+ "fd_ingredient = ?, fd_addition = ? WHERE fd_id = ? ";
		try {
			rows = jdbcTemplate.update(
					updateSql, 
					new Object[]{
							food.getFoodName(),
							food.getFoodCode(),
							food.getBarcode(),
							food.getDescription(),
							food.getCapacity(),
							food.getStatus(),
							food.getIsQualified(),
							food.getStandard(),
							food.getProducer(),
							food.getAddress(),
							food.getTelnumber(),
							food.getEmail(),
							food.getProduceDate(),
							food.getIngredient(),
							food.getAddition(),
							food.getFoodId()
			});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to update food info ~", e);
		}
		return rows;
	}
	
	/**
	 * 映射食品信息表字段到 Food类
	 * @author wangwei
	 * @date 2015-5-6
	 */
	class FoodMapper implements RowMapper<Food>
	{
		@Override
		public Food mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Food food = new Food();

			food.setFoodId(rs.getInt("fd_id"));
			food.setFoodName(rs.getString("fd_name"));
			food.setFoodCode(rs.getString("fd_code"));
			food.setBarcode(rs.getString("fd_barcode"));
			food.setDescription(rs.getString("fd_description"));
			food.setCapacity(rs.getString("fd_capacity"));
			food.setStatus(rs.getString("fd_status"));
			food.setIsQualified(rs.getByte("fd_is_qualified"));
			food.setStandard(rs.getString("fd_perform_standard"));
			food.setProducer(rs.getString("fd_producer"));
			food.setAddress(rs.getString("fd_producer_addr"));
			food.setTelnumber(rs.getString("fd_producer_phone"));
			food.setEmail(rs.getString("fd_producer_mail"));
			food.setProduceDate(rs.getTimestamp("fd_produced_time"));
			food.setIngredient(rs.getString("fd_ingredient"));
			food.setAddition(rs.getString("fd_addition"));

			return food;
		}
	}
}
