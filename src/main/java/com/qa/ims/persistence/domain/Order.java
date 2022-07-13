package com.qa.ims.persistence.domain;

import java.text.DecimalFormat;

public class Order {

	private Long order_id;
	private Long fk_customer_id;
	private Long item_id;
	private Integer quantity;
	private Double total;
	private String customer_name;
	DecimalFormat df = new DecimalFormat("#0.00");

	public Order(Long fk_customer_id, Long item_id, Integer quantity) {
		super();
		this.setFk_customer_id(fk_customer_id);
		this.setItem_id(item_id);
		this.setQuantity(quantity);
	}

	public Order(Long order_id, Long fk_customer_id, Long item_id, Integer quantity, Double total,
			String customer_name) {
		this.setOrder_id(order_id);
		this.setFk_customer_id(fk_customer_id);
		this.setItem_id(item_id);
		this.setQuantity(quantity);
		this.setTotal(total);
		this.setCustomer_name(customer_name);
	}

	public Order(Long fk_customer_id2, Long order_id2, Long item_id2, Integer quantity2) {
		this.order_id = order_id2;
		this.fk_customer_id = fk_customer_id2;
		this.item_id = item_id2;
		this.quantity = quantity2;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getFk_customer_id() {
		return fk_customer_id;
	}

	public void setFk_customer_id(Long fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	@Override
	public String toString() {
		return "Order Number: " + order_id + ", Customer: " + customer_name + ", Total: $" + df.format(total);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((fk_customer_id == null) ? 0 : fk_customer_id.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (getFk_customer_id() == null) {
			if (other.getFk_customer_id() != null)
				return false;
		} else if (!getFk_customer_id().equals(other.getFk_customer_id()))
			return false;
		if (getItem_id() == null) {
			if (other.getItem_id() != null)
				return false;
		} else if (!getItem_id().equals(other.getItem_id()))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

}
