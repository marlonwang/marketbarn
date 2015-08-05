package com.springmvc.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.springmvc.marketbarn.model.SalesInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 出货表数据库操作
 * 
 * @author wangwei
 * @date 2015-5-10
 *
 */
@Repository
public class SalesInfoDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalesInfoDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询出货表的信息
	 * 
	 * @author wangwei
	 * @return
	 */
	public List<SalesInfo> getAllSales() {
		List<SalesInfo> salesList = null;
		String querySql = "SELECT * FROM mkt_out_items";
		try {
			salesList = jdbcTemplate.query(querySql, new SalesMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get all sales list ~", e);
		}
		return salesList;
	}

	/**
	 * 查询指定日期的出库明细
	 * 
	 * @param outTime
	 * @return
	 */
	public List<SalesInfo> getSalesByDate(String outTime) {
		List<SalesInfo> salesList = null;
		String querySql = "SELECT * FROM mkt_out_items WHERE out_sold_time = ?";
		try {
			salesList = jdbcTemplate.query(querySql, new Object[] { outTime },
					new SalesMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get sales info by date~", e);
		}
		return salesList;
	}

	/**
	 * 查询指定起止时间的出货详情
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<SalesInfo> getSalesBetweenDate(String beginDate, String endDate) {
		List<SalesInfo> salesList = null;
		String querySql = "SELECT * FROM mkt_out_items WHERE out_sold_time BETWEEN ? AND ?";
		try {
			salesList = jdbcTemplate.query(querySql, new Object[] { beginDate,
					endDate }, new SalesMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get sales list between {} and {}",
					beginDate, endDate, e);
		}
		return salesList;
	}

	/**
	 * 查询指定日期前的出库详情
	 * 
	 * @param dateStr
	 * @return
	 */
	public List<SalesInfo> getSalesBeforeDate(String dateStr) {
		List<SalesInfo> salesList = null;
		String querySql = "SELECT * FROM mkt_out_items WHERE out_sold_time < ?";
		try {
			salesList = jdbcTemplate.query(querySql, new Object[] { dateStr },
					new SalesMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get sales before date {}", dateStr, e);
		}
		return salesList;
	}

	/**
	 * 查询指定日期后的出库详情
	 * 
	 * @param dateStr
	 * @return
	 */
	public List<SalesInfo> getSalesAfterDate(String dateStr) {
		List<SalesInfo> salesList = null;
		String querySql = "SELECT * FROM mkt_out_items WHERE out_sold_time > ?";
		try {
			salesList = jdbcTemplate.query(querySql, new Object[] { dateStr },
					new SalesMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get sales after date {}", dateStr, e);
		}
		return salesList;
	}

	/**
	 * 添加出库信息
	 * 
	 * @param sales
	 * @return int 受影响行数
	 */
	public int insertSalesInfo(SalesInfo sales) {
		int rows = 0;
		String insertSql = "INSERT INTO mkt_out_items (out_item_barcode, out_item_code, out_sold_price, "
				+ "out_sold_quantity, out_sold_time, out_sold_worker) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			rows = jdbcTemplate.update(
					insertSql,
					new Object[] { 
							sales.getBarcode(),
							sales.getCategoryCode(), 
							sales.getSalesPrice(),
							sales.getSalesQuantity(), 
							sales.getSalesDate(),
							sales.getOperator() 
							});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to insert sales info ~", e);
		}
		return rows;
	}

	/**
	 * 
	 * @param salesQueue
	 * @return
	 */
	public void batchInsertSalesInfo(BlockingQueue<SalesInfo> salesQueue) {
		String insertSql = "INSERT INTO mkt_out_items (out_item_barcode, out_item_code, out_sold_price, "
				+ "out_sold_quantity, out_sold_time, out_sold_worker) VALUES (?, ?, ?, ?, ?, ?)";

		List<Object[]> batch = new ArrayList<Object[]>();

		try {
			while (!salesQueue.isEmpty()) {
				SalesInfo sales = salesQueue.take();
				Object[] values = new Object[] { 
						sales.getBarcode(),
						sales.getCategoryCode(), 
						sales.getSalesPrice(),
						sales.getSalesQuantity(), 
						sales.getSalesDate(),
						sales.getOperator() 
						};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql, batch);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to insert batch sales info ~", e);
		}
	}

	/**
	 * 映射出货表字段到SalesInfo类
	 * 
	 * @author wangwei
	 * @date
	 * @return
	 */
	class SalesMapper implements RowMapper<SalesInfo> {
		@Override
		public SalesInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SalesInfo sales = new SalesInfo();
			
			sales.setSalesId(rs.getInt("out_id"));
			sales.setBarcode(rs.getString("out_item_barcode"));
			sales.setCategoryCode(rs.getString("out_item_code"));
			sales.setSalesPrice(rs.getDouble("out_sold_price"));
			sales.setSalesQuantity(rs.getInt("out_sold_quantity"));
			sales.setSalesDate(rs.getDate("out_sold_time"));
			sales.setOperator(rs.getString("out_sold_worker"));

			return sales;
		}
	}

}
