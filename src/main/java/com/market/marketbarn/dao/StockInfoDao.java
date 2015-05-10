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

import com.market.marketbarn.model.StockInfo;

@Repository
public class StockInfoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockInfoDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * 	in_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	in_item_id VARCHAR(20) NOT NULL,
	in_item_code CHAR(5),
	in_get_price DOUBLE,
	in_get_quantity INT DEFAULT 1,
	in_get_flag INT,
	in_get_time TIMESTAMP DEFAULT NOW(),
	in_get_worker VARCHAR(20)
	 */
	/**
	 * 获取全部进货信息
	 * @return List<StockInfo>
	 * @exception
	 */
	public List<StockInfo> getAllStockInfo()
	{
		List<StockInfo> stockList = null;
		String querySql = "SELECT * FROM mkt_in_items";
		try {
			stockList = jdbcTemplate.query(querySql, new StockMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get stock info ~",e);
		}
		return stockList;
	}
	
	/**
	 * 查询指定日期的进货详情
	 * @param inTime
	 * @return
	 */
	public List<StockInfo> getStockByDate(String inTime)
	{
		List<StockInfo> stockList = null;
		String querySql = "SELECT * FROM mkt_in_items WHERE in_get_time = ?";
		try {
			stockList = jdbcTemplate.query(querySql, new Object[]{inTime },new StockMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get stock by date");
		}
		return stockList;
	}
	
	/**
	 * 查询指定起止时间的进货信息
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<StockInfo> getStockBetweenDate(String beginDate, String endDate)
	{
		List<StockInfo> stockList = null;
		String querySql = "SELECT * FROM mkt_in_items WHERE in_get_time BETWEEN ? AND ?";
		try {
			stockList = jdbcTemplate.query(querySql, new Object[]{ beginDate, endDate }, new StockMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get stock between {} and {} ",beginDate,endDate,e);
		}
		return stockList;
	}
	
	/**
	 * 查询指定日前的 进货信息
	 * @param dateStr
	 * @return
	 */
	public List<StockInfo> getStockBeforeDate(String dateStr)
	{
		List<StockInfo> stockList = null;
		String querySql = "SELECT * FROM mkt_in_items WHERE in_get_time < ?";
		try {
			stockList = jdbcTemplate.query(querySql, new Object[]{ dateStr},new StockMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get stock before date {}",dateStr,e);
		}
		return stockList;
	}
	
	/**
	 * 查询指定日期后的进货详情
	 * @param dateStr
	 * @return
	 */
	public List<StockInfo> getStockAfterDate(String dateStr)
	{
		List<StockInfo> stockList = null;
		String querySql = "SELECT * FROM mkt_in_items WHERE in_get_time > ?";
		try {
			stockList = jdbcTemplate.query(querySql, new Object[]{ dateStr},new StockMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get stock after date {}",dateStr,e);
		}
		return stockList;
	}
	
	
	/**
	 * 映射进货表字段到StockInfo类
	 * @author wangwei
	 *
	 */
	class StockMapper implements RowMapper<StockInfo>
	{
		@Override
		public StockInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			StockInfo stock = new StockInfo();
			stock.setStockId(rs.getInt("in_id"));
			stock.setItemId(rs.getString("in_item_id"));
			stock.setItemCategoryCode(rs.getString("in_item_code"));
			stock.setStockPrice(rs.getDouble("in_get_price"));
			stock.setStockQuantity(rs.getInt("in_get_quantity"));
			stock.setBatchFlag(rs.getInt("in_get_flag"));
			stock.setStockDate(rs.getDate("in_get_time"));
			stock.setOperator(rs.getString("in_get_worker"));
			
			return stock;
		}
	}

}
