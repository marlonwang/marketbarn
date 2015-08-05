package com.springmvc.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.marketbarn.model.LeftInfo;

@Repository
public class LeftInfoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LeftInfoDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取所有剩余商品库存
	 * @return
	 */
	public List<LeftInfo> getAllLeft()
	{
		List<LeftInfo> leftList=null;
		String querySql = "SELECT * FROM mkt_items_left";
		try {
			leftList=jdbcTemplate.query(querySql, new LeftInfoMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get left info ~");
		}
		return leftList;
	}
	
	/**
	 * 更新剩余库存表
	 * @param leftInfo
	 * @return
	 */
	public int addLeftInfo(LeftInfo leftInfo)
	{
		int rows =0 ;
		String updateSql = "UPDATE mkt_items_left SET lft_item_num= ?,lft_count_time= ?,lft_addition= ? WHERE lft_item_barcode= ? ";
		try {
			rows = jdbcTemplate.update(
					updateSql, 
					new  Object[]{
							leftInfo.getQuantity(),
							leftInfo.getCountTime(),
							leftInfo.getAddition(),
							leftInfo.getBarcode()
					});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to update left info ~");
		}
		return rows;
	}
	/**
	 * 映射进货表字段到LeftInfo类
	 * @author wangwei
	 * @return LeftInfo
	 */
	class LeftInfoMapper implements RowMapper<LeftInfo>
	{
		@Override
		public LeftInfo mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			LeftInfo items = new LeftInfo();
			
			items.setLeftId(rs.getInt("lft_id"));
			items.setBarcode(rs.getString("lft_item_barcode"));
			items.setQuantity(rs.getInt("lft_item_num"));
			items.setCountTime(rs.getTimestamp("lft_count_time"));
			items.setAddition(rs.getString("lft_addition"));
			
			return items;
		}
	}
}
