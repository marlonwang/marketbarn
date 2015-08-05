package com.springmvc.marketbarn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.springmvc.marketbarn.model.StaffInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 员工信息数据库接口
 * @author wangwei
 * @date 2015-5-4
 */
@Repository
public class StaffInfoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffInfoDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询进货/出货员工
	 * @author wangwei
	 * @param staffId
	 * @exception database
	 * @return StaffInfo
	 * @date 2015-4-28
	 */
	public List<StaffInfo> getStaffById(int staffId)
	{
		List<StaffInfo> staff = null;
		
		String querySql = "SELECT * FROM mkt_users WHERE u_id = ? LIMIT 1";
		try{
		staff = jdbcTemplate.query(querySql, new Object[]{ staffId }, new StaffMapper());
		}catch (Exception e)
		{
			LOGGER.info("Failed to find staff by id~", e);
		}
		return staff;
	}
	
	/**
	 * 根据姓名查找员工
	 * @param staffName
	 * @return StaffInfo
	 */
	public List<StaffInfo> getStaffByName(String staffName)
	{
		List<StaffInfo> staffList = null;
		String querySql = "SELECT * FROM mkt_users WHERE u_name = ? ";
		try {
			staffList = jdbcTemplate.query(querySql, new Object[]{ staffName }, new StaffMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to find staff by name~", e);
		}
		return staffList;
	}
	
	/**
	 * 查询所有员工
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
		//staffRank = 0;	//default
		List<StaffInfo> staffGroup = null;
		String querySql = "SELECT * FROM mkt_users WHERE u_is_admin = ? ";
		try {
			staffGroup = jdbcTemplate.query(querySql, new Object[] { staffRank },new StaffMapper());
		} catch ( Exception e) {
			// TODO: handle exception
			LOGGER.info("get staff group failed ~", e);
		}
		return staffGroup;
	}
	
	/**
	 * 添加进货/出货员工
	 * @param StaffInfo
	 * @return 受影响的行数
	 * @date 2015-4-28
	 */
	public int insertStaffInfo(StaffInfo staff){
		int rows = 0;
		String insertSql = "INSERT INTO mkt_users (u_name,u_age,u_passwd,u_sex,u_mail,u_is_admin)"
				+"VALUES (?,?,?,?,?,?)";
		try{
			rows = jdbcTemplate.update(insertSql,new Object[]{staff.getStaffName(),staff.getStaffAge(),staff.getStaffPasswd(),
					staff.getStaffSex(),staff.getStaffMail(),staff.getStaffRank()});
		}catch(Exception e){
			LOGGER.info("Failed to insert staff into DB ", e);
		}
		return rows;
	}
	
	/**
	 * 批量添加员工
	 * @param StaffInfoQueue
	 * @return void
	 * @date 2015-4-28
	 */
	public void batchInsertStaffInfo(BlockingQueue<StaffInfo> StaffInfoQueue){
		String insertSql = "INSERT INTO mkt_users (u_name,u_age,u_passwd,u_sex,u_mail,u_is_admin)"
				+"VALUES (?,?,?,?,?,?)";
		
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
						staffinfo.getStaffRank()
				};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql, batch);
		}catch(Exception e){
			LOGGER.info("batch insert failed ~", e);
		}
		
	}
	
	/**
	 * 根据员工id删除员工
	 * @param staffId
	 * @return int
	 */
	public int deleteStaffById(int staffId)
	{
		int rows = 0;
		String delSql = "DELETE FROM mkt_users WHERE u_id = ? ";
		try {
			rows = jdbcTemplate.update(delSql,new Object[] { staffId });
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to delete staff ", e);
		}
		return rows;
	}
	
	/**
	 * 修改员工信息
	 * @param staffInfo
	 * @return rows
	 * @date 2015-5-5
	 */
	public int updateStaffById(StaffInfo staff)
	{
		int rows = 0;
		String updateSql = "UPDATE mkt_users SET u_name = ?, u_age = ?, u_passwd = ?, u_sex = ?, u_mail = ?, "
				+ "u_is_admin = ? WHERE u_id = ? ";
		try {
			rows = jdbcTemplate.update(
					updateSql, 
					new Object[]{
						staff.getStaffName(),
						staff.getStaffAge(),
						staff.getStaffPasswd(),
						staff.getStaffSex(),
						staff.getStaffMail(),
						staff.getStaffRank(),
						staff.getStaffId()	
						});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to update staff info ", e);
		}
		return rows;
	} 
	
	/**
	 * 映射数据库表的字段到StaffInfo
	 * @param 
	 * @author wangwei
	 * @date 2015-4-28
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
			staff.setStaffPasswd(rs.getString("u_passwd"));
			staff.setStaffRank(rs.getInt("u_is_admin"));
			staff.setStaffSex(rs.getString("u_sex"));

			return staff;
		}
	}
}
