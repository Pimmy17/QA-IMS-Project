package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		Long fk_customer_id = resultSet.getLong("fk_customer_id");
		Long item_id = resultSet.getLong("items_id");
		Integer quantity = resultSet.getInt("quantity");
		Double total = resultSet.getDouble("total");
		String customer_name = resultSet.getString("customer_name");
		String item_name = resultSet.getString("item_name");
		return new Order(order_id, fk_customer_id, item_id, quantity, total, customer_name, item_name);
	}

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT *, Concat(customers.first_name, ' ', customers.surname) AS customer_name, items.item_name AS item_name, SUM(orders_items.quantity*items.price) AS total FROM orders_items JOIN orders ON orders.order_id=orders_items.orders_id JOIN customers ON customers.id=orders.fk_customer_id JOIN items ON items.id=orders_items.items_id GROUP BY orders_items.orders_id, items.id ORDER BY orders_items.orders_id ASC");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT *, Concat(customers.first_name, ' ', customers.surname) AS customer_name, SUM(orders_items.quantity*items.price) AS total FROM orders_items JOIN orders ON orders.order_id=orders_items.orders_id JOIN customers ON customers.id=orders.fk_customer_id JOIN items ON items.id=orders_items.items_id GROUP BY orders_items.orders_id ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an order in the database
	 * 
	 * @param order - takes in an order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (fk_customer_id) VALUES (?);");) {
			statement.setLong(1, order.getFk_customer_id());
			statement.executeUpdate();
			PreparedStatement statement2 = connection.prepareStatement(
					"INSERT INTO orders_items (orders_id, items_id, quantity) VALUES (LAST_INSERT_ID(), ?, ?)");
			statement2.setLong(1, order.getItem_id());
			statement2.setInt(2, order.getQuantity());
			statement2.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long fk_customer_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM orders_items JOIN orders ON orders.order_id=orders_items.orders_id JOIN items ON items.id=orders_items.items_id WHERE orders.fk_customer_id = ? ORDER BY order_id");) {
			statement.setLong(1, fk_customer_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an order in the database
	 * 
	 * @param order - takes in an order object, the id field will be used to update
	 *              that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE orders_items JOIN orders ON orders.order_id=orders_items.orders_id JOIN items ON items.id=orders_items.items_id SET items.id = ?, orders_items.quantity = ? WHERE orders_items.orders_id = ?");) {
			statement.setLong(1, order.getItem_id());
			statement.setInt(2, order.getQuantity());
			statement.setLong(3, order.getOrder_id());
			statement.executeUpdate();
			return read(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order addItem(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO orders_items (quantity, items_id, orders_id) VALUES (?, (SELECT id FROM items WHERE id = ?), (SELECT order_id FROM orders WHERE order_id = ?));");) {
			statement.setInt(1, order.getQuantity());
			statement.setLong(2, order.getItem_id());
			statement.setLong(3, order.getOrder_id());
			statement.executeUpdate();
			return read(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order removeItem(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statementOne = connection
						.prepareStatement("UPDATE orders_items SET quantity = 0 WHERE items_id = ? && orders_id = ?");
				PreparedStatement statementTwo = connection
						.prepareStatement("DELETE FROM orders_items WHERE quantity = 0");) {
			statementOne.setLong(1, order.getItem_id());
			statementOne.setLong(2, order.getOrder_id());
			statementOne.executeUpdate();
			statementTwo.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an order in the database
	 * 
	 * @param id - id of the order
	 */
	@Override
	public int delete(long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE orders_id = ?");) {
			statement.setLong(1, order_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
