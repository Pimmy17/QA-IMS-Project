package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();
//	private final ItemDAO itemDAO = new ItemDAO();
//	private final CustomerDAO custDAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

//	@Test
//	public void testCreate() {
//		Customer createCust = new Customer(3L, "Bob", "Loblaw");
//		Item createItem = new Item(2L, "Hungry Hippos", 6.44);
//		Order created = new Order(3L, 1L, 2L, 1);
//		custDAO.create(createCust);
//		itemDAO.create(createItem);
//		assertEquals(created, DAO.create(created));
//	}

	@Test
	public void testNullCreate() {
		Customer createdCust = new Customer("Bob", "Bobby");
		Item createdItem = new Item("Jenga", 2.55);
		Order created = new Order(createdCust.getId(), createdItem.getId(), 2);
		assertEquals(null, DAO.create(created));
	}

//	@Test
//	public void testReadAll() {
//		List<Order> expected = new ArrayList<>();
//		expected.add(new Order(2L, 2L, 2L, 2, 6.44, "Bob", "Loblaw", "Hungry Hippos"));
//		assertEquals(expected, DAO.readAll());
//	}

//	@Test
//	public void testUpdate() {
//		Customer createdCust = new Customer("Bob", "Bobby");
//		Item createdItem = new Item("Jenga", 2.55);
//		Order original = new Order(createdCust.getId(), 1L, createdItem.getId(), 2, createdItem.getPrice(),
//				createdCust.getFirstName() + " " + createdCust.getSurname(), createdItem.getItemName());
//		assertEquals(original, DAO.create(original));
//		Order updated = new Order(1L, 1L, 1L, 5);
//		assertEquals(updated, DAO.update(updated));
//	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1L));
	}

}