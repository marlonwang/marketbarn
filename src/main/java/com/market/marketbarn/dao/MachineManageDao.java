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

import com.market.marketbarn.model.Machine;

/**
 * 电器商品信息数据访问接口
 * @author wangwei
 * @date 2015-5-7
 */

@Repository
public class MachineManageDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MachineManageDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询电器表中的所有电器信息
	 * @param 
	 * @date 2015-5-7
	 * @return
	 * @exception
	 */
	public List<Machine> getAllMachines()
	{
		List<Machine> machineList = null;
		String querySql = "SELECT * FROM mkt_items_machine ";
		try {
			machineList = jdbcTemplate.query(querySql, new MachineMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get machine list ~", e);
		}
		return machineList;
	}
	
	/**
	 * 根据id查找单个电器信息
	 * @param machineId
	 * @return
	 * @exception
	 */
	public Machine getMachineById(int machineId)
	{
		Machine machine = null;
		String querySql = "SELECT * FROM mkt_items_machine WHERE mc_id = ? LIMIT 1";
		try {
			machine = jdbcTemplate.queryForObject(querySql, new Object[]{ machineId }, new MachineMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to find machine by id ~",e);
		}
		return machine;
	}
	
	/**
	 * 根据电器名称获取电器列表
	 * @param machineName
	 * @return
	 * @exception
	 */
	public List<Machine> getMachineByName(String machineName)
	{
		List<Machine> machineList = null;
		String querySql = "SELECT * FROM mkt_items_machine WHERE mc_name = ? ";
		try {
			machineList = jdbcTemplate.query(querySql, new Object[]{ machineName }, new MachineMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to  find machine by name ~",e);
		}
		return machineList;
	}
	
	/**
	 * 根据状态查询电器信息列表
	 * @param status
	 * @return
	 * @exception
	 */
	public List<Machine> getMachineByStatus(String status)
	{
		List<Machine> machineList = null;
		String querySql = "SELECT * FROM mkt_items_machine WHERE mc_status = ? ";
		try {
			machineList = jdbcTemplate.query(querySql, new Object[]{ status }, new MachineMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to find machine list by status");
		}
		return machineList;
	}
	
	/**
	 * 添加电器信息
	 * @param machine
	 * @return
	 * @exception
	 */
	public int insertMachineInfo(Machine machine)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_items_machine ( mc_name, mc_code, mc_barcode, mc_description, mc_size, "
				+ "mc_is_qualified, mc_perform_standard, mc_producer, mc_producer_addr, mc_producer_phone, "
				+ "mc_producer_mail, mc_produced_time, mc_voltage, mc_current, mc_material, mc_addition) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		try {
			rows = jdbcTemplate.update(insertSql,
					new Object[]{
					machine.getMachineName(),
					machine.getMachineCode(),
					machine.getBarcode(),
					machine.getDescription(),
					machine.getSize(),
					machine.getIsQualified(),
					machine.getStandard(),
					machine.getProducer(),
					machine.getAddress(),
					machine.getTelnumber(),
					machine.getEmail(),
					machine.getProduceDate(),
					machine.getVoltage(),
					machine.getCurrent(),
					machine.getMaterial(),
					machine.getAddition()
			});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to insert machine into DB ~", e);
		}
		return rows;
	}
	
	/**
	 * 批量添加电器信息
	 * @param machineQueue
	 * @return void
	 * @exception
	 */
	public void batchInsertMachineInfo(BlockingQueue<Machine> machineQueue)
	{

		String insertSql = "INSERT INTO mkt_items_machine ( mc_name, mc_code, mc_barcode, mc_description, mc_size, "
				+ "mc_is_qualified, mc_perform_standard, mc_producer, mc_producer_addr, mc_producer_phone, "
				+ "mc_producer_mail, mc_produced_time, mc_voltage, mc_current, mc_material, mc_addition) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		List<Object[]> batch = new ArrayList<Object[]>();
		
		try {
			while(!machineQueue.isEmpty())
			{
				Machine machine = machineQueue.take();
				Object[] values =new Object[]{
						machine.getMachineName(),
						machine.getMachineCode(),
						machine.getBarcode(),
						machine.getDescription(),
						machine.getSize(),
						machine.getIsQualified(),
						machine.getStandard(),
						machine.getProducer(),
						machine.getAddress(),
						machine.getTelnumber(),
						machine.getEmail(),
						machine.getProduceDate(),
						machine.getVoltage(),
						machine.getCurrent(),
						machine.getMaterial(),
						machine.getAddition()
				};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql,batch);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("batch insert failed ~", e);
		}

	}
	
	/**
	 * 根据电器id删除电器信息
	 * @param machineId
	 * @return
	 * @exception
	 */
	public int deleteMachineById(int machineId)
	{
		int rows = 0;
		String delSql = "DELETE FROM mkt_items_machine WHERE mc_id = ? ";
		try {
			rows = jdbcTemplate.update(delSql, new Object[]{ machineId });
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to delete machine by id ~",e);
		}
		return rows;
	}
	
	/**
	 * 根据
	 * @param machine
	 * @return
	 */
	public int updateMachineInfoById(Machine machine)
	{
		int rows = 0;
		String updateSql = "UPDATE mkt_items_machine SET  mc_name = ?, mc_code = ?, mc_barcode = ?, mc_description = ?, "
				+ "mc_size = ?, mc_is_qualified = ?, mc_perform_standard = ?, mc_producer = ?, "
				+ "mc_producer_addr = ?, mc_producer_phone = ?, mc_producer_mail = ?, mc_produced_time = ?, mc_voltage = ?, "
				+ "mc_current = ?, mc_material = ?, mc_addition = ? WHERE mc_id = ? ";
		try {
			rows = jdbcTemplate.update(
					updateSql, 
					new  Object[]{
							machine.getMachineName(),
							machine.getMachineCode(),
							machine.getBarcode(),
							machine.getDescription(),
							machine.getSize(),
							machine.getIsQualified(),
							machine.getStandard(),
							machine.getProducer(),
							machine.getAddress(),
							machine.getTelnumber(),
							machine.getEmail(),
							machine.getProduceDate(),
							machine.getVoltage(),
							machine.getCurrent(),
							machine.getMaterial(),
							machine.getAddition(),
							machine.getMachineId()
					});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to update machine info ~", e);
		}
		return rows;
	}
	
	/**
	 * 映射数据库machine表到 电器类
	 * @param 
	 * @author wangwei
	 * @date 2015-5-5
	 *
	 */
	class MachineMapper implements RowMapper<Machine>
	{
		@Override
		public Machine mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Machine machine = new Machine();
			
			machine.setMachineId(rs.getInt("mc_id"));
			machine.setMachineName(rs.getString("mc_name"));
			machine.setMachineCode(rs.getString("mc_code"));
			machine.setBarcode(rs.getString("mc_barcode"));
			machine.setDescription(rs.getString("mc_description"));
			machine.setSize(rs.getString("mc_size"));
			machine.setIsQualified(rs.getByte("mc_is_qualified"));
			machine.setStandard(rs.getString("mc_perform_standard"));
			machine.setProducer(rs.getString("mc_producer"));
			machine.setAddress(rs.getString("mc_producer_addr"));
			machine.setTelnumber(rs.getString("mc_producer_phone"));
			machine.setEmail(rs.getString("mc_producer_mail"));
			machine.setProduceDate(rs.getDate("mc_produced_time"));
			machine.setVoltage(rs.getString("mc_voltage"));
			machine.setCurrent(rs.getString("mc_current"));
			machine.setMaterial(rs.getString("mc_material"));
			machine.setAddition(rs.getString("mc_addition"));
			
			return machine;
		}
	}
}
