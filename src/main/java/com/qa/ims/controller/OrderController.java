package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
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
		Long fk_customer_id = utils.getLong();
		LOGGER.info("Please enter the id of the order you would like to update");
		Long order_id = utils.getLong();
		LOGGER.info(
				"Would you like to update an existing item or add or remove an item from an existing order? \n Please Enter Update, Add or Remove");
		String addRemove = utils.getString();
		addRemove = addRemove.toLowerCase();
		if (addRemove.equals("update")) {
			LOGGER.info("Please enter the ID of the item in the order you want to update");
			Long item_id = utils.getLong();
			LOGGER.info("Please enter a new quantity");
			Integer quantity = utils.getInt();
			Order order = orderDAO.update(new Order(fk_customer_id, order_id, item_id, quantity));
			LOGGER.info("Item Updated");
			return order;
		} else if (addRemove.equals("add")) {
			LOGGER.info("Please enter the ID of the item you want to add");
			Long item_id = utils.getLong();
			LOGGER.info("Please enter a quantity");
			Integer quantity = utils.getInt();
			Item item = new Item(item_id);
			ItemDAO itemDAO = new ItemDAO();
			item = itemDAO.read(item.getId());
			Order order = orderDAO.addItem(new Order(fk_customer_id, order_id, item.getId(), quantity));
			LOGGER.info("Item Added to Order");
			return order;
		} else if (addRemove.equals("remove")) {
			LOGGER.info("Please enter the id of the item to remove");
			Long item_id = utils.getLong();
			Order order = new Order();
			order.setOrder_id(order_id);
			order.setItem_id(item_id);
			orderDAO.removeItem(order);
			LOGGER.info("Item Removed from Order");
			return order;
		}
		return null;

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
		LOGGER.info("Order Successfully Deleted!");
		return orderDAO.delete(order_id);
	}

}
