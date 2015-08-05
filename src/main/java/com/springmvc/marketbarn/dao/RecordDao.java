package com.springmvc.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.marketbarn.model.TransRecord;

public class RecordDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(RecordDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 全部交换记录
	 * @return
	 */
	public List<TransRecord> getRecordList()
	{
		List<TransRecord> recordList = null;
		String querySql = "SELECT * FROM mkt_sync_record";
		try {
			recordList = jdbcTemplate.query(querySql, new RecordMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get stock info ~",e);
		}
		return recordList;
	}
	
	/**
	 * 添加交换记录
	 * 
	 */
	public int insertTransRecord(TransRecord record)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_sync_record (rec_type,rec_origin,rec_destination,rec_desc,rec_time) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			rows = jdbcTemplate.update(
					insertSql, 
					new Object[]{
							record.getRecordType(),
							record.getOrigin(),
							record.getDestination(),
							record.getDescription(),
							record.getRecordTime()
					});
			} catch (Exception e) {
			// TODO: handle exception
				LOGGER.info("failed to insert stock info ~",e);
		}
		return rows;
	}
	
	/**
	 * 映射交换记录表
	 * @author marlon
	 *
	 */
	class RecordMapper implements RowMapper<TransRecord>
	{
		@Override
		public TransRecord mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			TransRecord record = new TransRecord();
			record.setRecordId(rs.getInt("rec_id"));
			record.setRecordType(rs.getString("rec_type"));
			record.setOrigin(rs.getString("rec_origin"));
			record.setDestination(rs.getString("rec_destination"));
			record.setDescription(rs.getString("rec_desc"));
			record.setRecordTime(rs.getDate("rec_time"));
			
			return record;
		}
	}

}
