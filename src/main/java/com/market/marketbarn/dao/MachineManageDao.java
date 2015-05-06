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
	
	/*  电器信息
	 * 	mc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		mc_name VARCHAR(50) NOT NULL,
		mc_code CHAR(5),
		mc_barcode CHAR(13),
		mc_description TEXT,
		mc_size VARCHAR(20) COMMENT '尺寸—l-w-h',
		mc_status ENUM("在库","出库","损坏","丢失"),
		mc_is_qualified TINYINT(1),
		mc_perform_standard VARCHAR(15),
		mc_producer CHAR(50),
		mc_producer_addr VARCHAR(100),
		mc_producer_phone VARCHAR(15),
		mc_producer_mail VARCHAR(50),
		mc_produced_time TIMESTAMP DEFAULT NOW(),
		mc_voltage VARCHAR(20) COMMENT '电压',
		mc_current VARCHAR(20) COMMENT '电流',
		mc_material VARCHAR(50) COMMENT '材质',
		mc_addition TEXT
	 */
	
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
			machine.setStatus(rs.getString("mc_status"));
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
