package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerID = resultSet.getLong("customerID");
		Long itemID = resultSet.getLong("itemID");
		Integer quantity = resultSet.getInt("quantity");
		return new Order(id, customerID, itemID, quantity);
	}

	/**
	 * Reads all items from the database
	 * 
	 * @return A list of items
	 */

}
