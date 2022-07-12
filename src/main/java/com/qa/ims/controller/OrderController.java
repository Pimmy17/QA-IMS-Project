package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please select a customer");
		// Create a drop-down menu to select an existing customer
		Long fk_customer_id = utils.getLong();
		LOGGER.info("Please add an item to the order");
		// Create a drop-down menu to select an existing item
		Long item_id = utils.getLong();
		LOGGER.info("Please enter a quantity");
		Integer quantity = utils.getInt();
		Order order = orderDAO.create(new Order(fk_customer_id, item_id, quantity));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the customer whose order you would like to update");
		Long fk_customer_id2 = utils.getLong();
		LOGGER.info("Please enter the id of the order you would like to update");
		Long order_id2 = utils.getLong();
		LOGGER.info("Please enter an item id");
		// Create a drop-down menu of current items in the order
		Long item_id2 = utils.getLong();
		LOGGER.info("Please enter a quantity");
		Integer quantity2 = utils.getInt();
		Order order = orderDAO.update(new Order(fk_customer_id2, order_id2, item_id2, quantity2));
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long order_id = utils.getLong();
		return orderDAO.delete(order_id);
	}

}
