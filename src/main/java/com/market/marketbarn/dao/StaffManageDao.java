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

import com.market.marketbarn.model.StaffInfo;

public class StaffManageDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffManageDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询进货/出货人员
	 * @param staffId
	 * @exception database
	 * @return StaffInfo
	 * @date 2015-4-28
	 */
	public StaffInfo getStaffById(int staffId)
	{
		StaffInfo staff = null;
		String querySql = "SELECT * FROM mkt_users WHERE u_is_admin = 2 AND u_id = " + staffId;
		try{
		staff = (StaffInfo) jdbcTemplate.query(querySql, new StaffMapper());
		}catch (Exception e)
		{
			LOGGER.error("Failed to find ipsec vpn list~", e);
		}
		return staff;
	}
	
	/**
	 * 添加进货/出货人员
	 * @param StaffInfo
	 * @return 受影响的行数
	 */
	public int insertStaffInfo(StaffInfo staff){
		int rows = 0;
		String insertSql = "INSERT INTO mkt_users (u_name,u_age,u_passwd,u_sex,u_mail,u_phone,u_is_admin)"
				+"VALUES (?,?,?,?,?,?,?)";
		try{
			rows = jdbcTemplate.update(insertSql,new Object[]{staff.getStaffName(),staff.getStaffAge(),staff.getStaffPasswd(),
					staff.getStaffSex(),staff.getStaffMail(),staff.getStaffPhone(),staff.getStaffRank()});
		}catch(Exception e){
			LOGGER.debug("Failed to insert staff into DB",e);
		}
		return rows;
	}
	
	public void batchInsertStaffInfo(BlockingQueue<StaffInfo> StaffInfoQueue){
		String insertSql = "INSERT INTO mkt_users (u_name,u_age,u_passwd,u_sex,u_mail,u_phone,u_is_admin)"
				+"VALUES (?,?,?,?,?,?,?)";
		
		List<Object[]> batch = new ArrayList<Object[]>();
				
		try{
			while (!StaffInfoQueue.isEmpty()){
				StaffInfo staffinfo = StaffInfoQueue.take();
				Object[] values = new Object[] {
						staffinfo.getStaffName(),
						staffinfo.getStaffAge(),
						staffinfo.getStaffPasswd(),
						staffinfo.getStaffSex(),
						staffinfo.getStaffMail(),
						staffinfo.getStaffPhone(),
						staffinfo.getStaffRank()
				};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql, batch);
		}catch(Exception e){
			LOGGER.info("batch insert failed ~");
		}
		
	}
	
	/**
	 * 映射数据库表的字段到StaffInfo
	 * @param 
	 * @author wangwei
	 */
	class StaffMapper implements RowMapper<StaffInfo>
	{

		@Override
		public StaffInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			StaffInfo staff = new StaffInfo();

			staff.setStaffId(rs.getInt("u_id"));
			staff.setStaffAge(rs.getInt("u_age"));
			staff.setStaffName(rs.getString("u_name"));
			staff.setStaffMail(rs.getString("u_mail"));
			staff.setStaffPhone(rs.getString("u_phone"));
			staff.setStaffPsswd(rs.getString("u_passwd"));
			staff.setStaffRank(rs.getInt("u_is_admin"));
			staff.setStaffSex(rs.getString("u_sex"));

			return staff;
		}
	}
}
