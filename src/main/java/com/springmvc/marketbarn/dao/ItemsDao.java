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

import com.springmvc.marketbarn.model.CategoryItems;
import com.springmvc.marketbarn.model.Items;
import com.springmvc.marketbarn.model.LeftItems;

/**
 * 商品商品信息数据访问接口
 * @author wangwei
 * @date 2015-5-7
 */

@Repository
public class ItemsDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemsDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询商品表中的所有商品信息
	 * @param 
	 * @date 2015-5-7
	 * @return
	 * @exception
	 */
	public List<Items> getAllItems()
	{
		List<Items> itemList = null;
		String querySql = "SELECT * FROM mkt_items_all ";
		try {
			itemList = jdbcTemplate.query(querySql, new itemMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get item list ~", e);
		}
		return itemList;
	}
	
	/**
	 * 查询库存详情
	 * @return
	 */
	public List<LeftItems> getAllLeftItems()
	{
		List<LeftItems> itemList = null;
		String querySql = "select itm_name,lft_item_num,itm_code,itm_barcode,itm_description,itm_producer,itm_producer_phone,"
				+ "lft_count_time from mkt_items_all a, mkt_items_left b where a.itm_barcode = b.lft_item_barcode";
		try {
			itemList = jdbcTemplate.query(querySql, new ItemLeftMapper());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to get left item list ~");
		}
		
		return itemList;
	}
	
	/**
	 * 根据商品名称获取商品列表
	 * @param itemName
	 * @return
	 * @exception
	 */
	public List<Items> getItemByName(String itemName)
	{
		List<Items> itemList = null;
		String querySql = "SELECT * FROM mkt_items_all WHERE itm_name = ? ";
		try {
			itemList = jdbcTemplate.query(querySql, new Object[]{ itemName }, new itemMapper());
		} catch (Exception e) {
			LOGGER.info("failed to  find item by name ~",e);
		}
		return itemList;
	}
	
	/**
	 * 根据 类别查询所有商品
	 * @param category
	 */
	public List<Items> getItemsByCode(String itemCode)
	{
		List<Items> itemList = null;
		String querySql = "SELECT * FROM mkt_items_all a, mkt_items_category b WHERE a.itm_code = b.ct_code AND a.itm_code = ? ";
		try {
			itemList = jdbcTemplate.query(querySql, new Object[]{ itemCode }, new itemMapper());
		} catch (Exception e) {
			LOGGER.info("failed to find item by itemCode ~",e);
		}
		return itemList;
	}
	
	/**
	 * 同时查询 类别和名字
	 * @param itemName
	 * @param itemCode
	 * @return
	 */
	public List<Items> getItemsByNameAndCode(String itemName, String itemCode)
	{
		List<Items> itemList = null;
		String querySql = "SELECT * FROM mkt_items_all WHERE itm_name = ? AND itm_code = ? ";
		try {
			itemList = jdbcTemplate.query(querySql, new Object[]{itemName, itemCode}, new itemMapper());
		} catch (Exception e) {
			LOGGER.info("failed to find item with category name by itemCode ~",e);
		}
		return itemList;
	}
	/**
	 * 添加商品信息
	 * @param item
	 * @return
	 * @exception
	 */
	public int insertItemInfo(Items item)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_items_all (itm_name, itm_code, itm_barcode, itm_description, "
				+ "itm_perform_standard, itm_producer, itm_producer_addr, itm_producer_phone, itm_producer_mail, itm_addition) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		try {
			rows = jdbcTemplate.update(insertSql,
					new Object[]{
					item.getItemName(),
					item.getItemCode(),
					item.getBarcode(),
					item.getDescription(),
					item.getStandard(),
					item.getProducer(),
					item.getAddress(),
					item.getTelnumber(),
					item.getAddition()
			});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to insert item into DB ~", e);
		}
		return rows;
	}
	
	/**
	 * 批量添加商品信息
	 * @param itemQueue
	 * @return void
	 * @exception
	 */
	public void batchInsertItemInfo(BlockingQueue<Items> itemQueue)
	{

		String insertSql = "INSERT INTO mkt_items_all (itm_name, itm_code, itm_barcode, itm_description, "
				+ "itm_perform_standard, itm_producer, itm_producer_addr, itm_producer_phone, itm_producer_mail, itm_addition) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		List<Object[]> batch = new ArrayList<Object[]>();
		
		try {
			while(!itemQueue.isEmpty())
			{
				Items item = itemQueue.take();
				Object[] values =new Object[]{
						item.getItemName(),
						item.getItemCode(),
						item.getBarcode(),
						item.getDescription(),
						item.getStandard(),
						item.getProducer(),
						item.getAddress(),
						item.getTelnumber(),
						item.getAddition()
				};
				batch.add(values);
			}
			jdbcTemplate.batchUpdate(insertSql,batch);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("batch insert failed ~", e);
		}

	}
	
	public int insetLeftItems(LeftItems leftItem)
	{
		int rows = 0;
		String insertSql = "INSERT INTO mkt_items_left (lft_item_barcode,lft_item_num) VALUES (?,?)";
		try {
			rows = jdbcTemplate.update(insertSql,
					new Object[]{
					leftItem.getBarcode(),
					leftItem.getItemNum(),
					//leftItem.getDescription()
					});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("inset left items failed ~");
		}
		return rows;
	}
	
	/**
	 * 根据商品id删除商品信息
	 * @param itemId
	 * @return
	 * @exception
	 */
	public int deleteItemById(int itemId)
	{
		int rows = 0;
		String delSql = "DELETE FROM mkt_items_all WHERE itm_id = ? ";
		try {
			rows = jdbcTemplate.update(delSql, new Object[]{ itemId });
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to delete item by id ~",e);
		}
		return rows;
	}
	
	/**
	 * 根据
	 * @param item
	 * @return
	 */
	public int updateItemInfoById(Items item)
	{
		int rows = 0;
		String updateSql = "UPDATE mkt_items_all SET  itm_name = ?, itm_code = ?, itm_barcode = ?, itm_description = ?, "
				+ "itm_perform_standard = ?, itm_producer = ?, itm_producer_addr = ?, itm_producer_phone = ?, itm_addition = ? WHERE itm_id = ? ";
		try {
			rows = jdbcTemplate.update(
					updateSql, 
					new  Object[]{
							item.getItemName(),
							item.getItemCode(),
							item.getBarcode(),
							item.getDescription(),
							item.getStandard(),
							item.getProducer(),
							item.getAddress(),
							item.getTelnumber(),
							item.getAddition(),
							item.getItemId()
					});
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("failed to update item info ~", e);
		}
		return rows;
	}
	
	/**
	 * 映射数据库item表到 商品类
	 * @param 
	 * @author wangwei
	 * @date 2015-5-5
	 *
	 */
	class itemMapper implements RowMapper<Items>
	{
		@Override
		public Items mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Items item = new Items();
			
			item.setItemId(rs.getInt("itm_id"));
			item.setItemName(rs.getString("itm_name"));
			item.setItemCode(rs.getString("itm_code"));
			item.setBarcode(rs.getString("itm_barcode"));
			item.setDescription(rs.getString("itm_description"));
			item.setStandard(rs.getString("itm_perform_standard"));
			item.setProducer(rs.getString("itm_producer"));
			item.setAddress(rs.getString("itm_producer_addr"));
			item.setTelnumber(rs.getString("itm_producer_phone"));
			item.setAddition(rs.getString("itm_addition"));
			
			return item;
		}
	}
	
	/**
	 * 带类别名称的商品信息
	 * @author marlon
	 * @deprecated
	 */
	class CategoryItemsMapper implements RowMapper<CategoryItems>
	{
		@Override
		public CategoryItems mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			CategoryItems item = new CategoryItems();
			
			item.setItemId(rs.getInt("itm_id"));
			item.setItemName(rs.getString("itm_name"));
			item.setItemCode(rs.getString("itm_code"));
			item.setBarcode(rs.getString("itm_barcode"));
			item.setDescription(rs.getString("itm_description"));
			item.setStandard(rs.getString("itm_perform_standard"));
			item.setProducer(rs.getString("itm_producer"));
			item.setAddress(rs.getString("itm_producer_addr"));
			item.setTelnumber(rs.getString("itm_producer_phone"));
			item.setAddition(rs.getString("itm_addition"));
			item.setItemCodeName(rs.getString("ct_category"));
			
			return item;
		}
	}
	
	/**
	 * 剩余商品 映射到LeftItems类
	 * @author marlon
	 *
	 */
	class ItemLeftMapper implements RowMapper<LeftItems>
	{
		@Override
		public LeftItems mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			LeftItems items = new LeftItems();
			
			items.setItemCode(rs.getString("itm_code"));
			items.setBarcode(rs.getString("itm_barcode"));
			items.setItemName(rs.getString("itm_name"));
			items.setItemNum(rs.getInt("lft_item_num"));
			items.setDescription(rs.getString("itm_description"));
			items.setProducer(rs.getString("itm_producer"));
			items.setTelnumber(rs.getString("itm_producer_phone"));
			items.setCountTime(rs.getTimestamp("lft_count_time"));
			
			return items;
			
		}
	}
	
}
