package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final Long customer_id = 1L, item_id = 1L;
		final Integer quant = 5;
		final Order created = new Order(customer_id, item_id, quant);

		Mockito.when(utils.getLong()).thenReturn(customer_id, item_id);
		Mockito.when(utils.getInt()).thenReturn(quant);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getInt();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testHashcode() {
		Order x = new Order(1L, 1L, 1L, 2, 5.50, "Barry Scott", "Jenga");
		Order y = new Order(1L, 1L, 1L, 2, 5.50, "Barry Scott", "Jenga");
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testHashcode2() {
		Order x = new Order(1L, 1L, 1L, 4);
		Order y = new Order(1L, 1L, 1L, 4);
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testHashcode3() {
		Order x = new Order(1L, 1L, 1L);
		Order y = new Order(1L, 1L, 1L);
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 2));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

//	@Test
//	public void testAddItemUpdate() {
//		Order updated = new Order(1L, 1L, 6);
//
//		Mockito.when(this.utils.getLong()).thenReturn(updated.getFk_customer_id(), updated.getOrder_id());
//		Mockito.when(this.utils.getString()).thenReturn("add");
//		Mockito.when(this.utils.getLong()).thenReturn(updated.getItem_id());
//		Mockito.when(this.utils.getInt()).thenReturn(updated.getQuantity());
//		Mockito.when(this.dao.update(updated)).thenReturn(updated);
//
//		assertEquals(updated, this.controller.update());
//
//		Mockito.verify(this.utils, Mockito.times(3)).getLong();
//		Mockito.verify(this.utils, Mockito.times(1)).getString();
//		Mockito.verify(this.utils, Mockito.times(1)).getInt();
//		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
//	}

//	@Test
//	public void testDeleteItemUpdate() {
//		final long order_id = 1L;
//		final long customer_id = 1L;
//		final long item_id = 1L;
//		Order updated = new Order();
//
//		Mockito.when(this.utils.getLong()).thenReturn(customer_id, order_id);
//		Mockito.when(this.utils.getString()).thenReturn("remove");
//		Mockito.when(this.utils.getLong()).thenReturn(item_id);
//		Mockito.when(this.dao.update(updated)).thenReturn(updated);
//
//		assertEquals(updated, this.controller.update());
//
//		Mockito.verify(this.utils, Mockito.times(3)).getLong();
//		Mockito.verify(this.utils, Mockito.times(1)).getString();
//		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
//	}

//	@Test
//	public void testNullUpdate() {
//		final long order_id = 1L;
//		final long customer_id = 1L;
//		Order updated = new Order();
//
//		Mockito.when(this.utils.getLong()).thenReturn(customer_id, order_id);
//		Mockito.when(this.utils.getString()).thenReturn("");
//		Mockito.when(this.dao.update(updated)).thenReturn(updated);
//
//		assertEquals(null, this.controller.update());
//	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
