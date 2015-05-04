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

import com.market.marketbarn.model.StaffInfo;

@Repository
public class StaffManageDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffManageDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询进货/出货人员
	 * @author wangwei
	 * @param staffId
	 * @exception database
	 * @return StaffInfo
	 * @date 2015-4-28
	 */
	public List<StaffInfo> getStaffById(int staffId)
	{
		List<StaffInfo> staff = null;
		
		String querySql = "SELECT * FROM mkt_users WHERE u_id = " + staffId;
		try{
		staff = jdbcTemplate.query(querySql, new StaffMapper());
		}catch (Exception e)
		{
			LOGGER.error("Failed to find ipsec vpn list~", e);
		}
		return staff;
	}
	
	/**
	 * 查询所有人员
	 * @author wangwei
	 * @param void
	 * @return List<StaffInfo>
	 * @date 2015-5-4
	 */
	public List<StaffInfo> getAllStaff(){
		List<StaffInfo> staffList = null;
		String querySql = "SELECT * FROM mkt_users";
		try{
			staffList = jdbcTemplate.query(querySql, new StaffMapper());
		}
		catch(Exception e){
			LOGGER.info("failed to get staff list ~", e);
		}
		return staffList;
	}
	
	/**
	 * 根据员工级别查询员工
	 * @param int staffRank
	 * @return List<StaffInfo>
	 * 
	 */
	public List<StaffInfo> getStaffGroup(int staffRank){
		staffRank = 0;	//default
		List<StaffInfo> staffGroup = null;
		String querySql = "SELECT * FROM mkt_users WHERE u_is_admin = "+ staffRank ;
		try {
			staffGroup = jdbcTemplate.query(querySql, new StaffMapper());
		} catch ( Exception e) {
			// TODO: handle exception
			LOGGER.info("get staff group failed ~", e);
		}
		return staffGroup;
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
