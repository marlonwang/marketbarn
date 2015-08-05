package com.springmvc.marketbarn.dao;

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

import com.springmvc.marketbarn.model.StockInfo;

@Repository
public class StockInfoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockInfoDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	 * @param dateStr 截止日期
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
	 * @param dateStr 起始日期
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
	 * 添加进货信息到进货表
	 * @param stock 
	 * @return int 受影响行数
	 */
	public int insertStockInfo(StockInfo stock)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_in_items (in_item_barcode,in_item_code,in_get_price,in_get_quantity,"
				+ "in_get_time, in_get_worker) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			rows = jdbcTemplate.update(
					insertSql, 
					new Object[]{
							stock.getBarcode(),
							stock.getCategoryCode(),
							stock.getStockPrice(),
							stock.getStockQuantity(),
							stock.getStockDate(),
							stock.getOperator()
					});
			} catch (Exception e) {
			// TODO: handle exception
				LOGGER.info("failed to insert stock info ~",e);
		}
		return rows;
	}
	
	/**
	 * 批量添加进货信息到进货表
	 * @param stockQueue
	 * @return void
	 */
	public void batchInsertStockInfo(BlockingQueue<StockInfo> stockQueue)
	{
		String insertSql = "INSERT INTO mkt_in_items (in_item_barcode,in_item_code,in_get_price,in_get_quantity,"
				+ "in_get_time, in_get_worker) VALUES (?, ?, ?, ?, ?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		
		try {
			while(!stockQueue.isEmpty())
			{
				StockInfo stock = stockQueue.take();
				Object[] values = new Object[]{
						stock.getBarcode(),
						stock.getCategoryCode(),
						stock.getStockPrice(),
						stock.getStockQuantity(),
						stock.getStockDate(),
						stock.getOperator()
				};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql, batch);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to insert batch stock info ~",e);
		}
	}
	
	/**
	 * 映射进货表字段到StockInfo类
	 * @author wangwei
	 * @return StockInfo
	 */
	class StockMapper implements RowMapper<StockInfo>
	{
		@Override
		public StockInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			StockInfo stock = new StockInfo();
			stock.setStockId(rs.getInt("in_id"));
			stock.setBarcode(rs.getString("in_item_barcode"));
			stock.setCategoryCode(rs.getString("in_item_code"));
			stock.setStockPrice(rs.getDouble("in_get_price"));
			stock.setStockQuantity(rs.getInt("in_get_quantity"));
			stock.setStockDate(rs.getDate("in_get_time"));
			stock.setOperator(rs.getString("in_get_worker"));
			
			return stock;
		}
	}

}
