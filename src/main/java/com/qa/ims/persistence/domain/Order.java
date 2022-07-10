package com.qa.ims.persistence.domain;

import java.sql.Date;

public class Order {

	private Long order_id;
	private Long fk_customer_id;
	private Long fk_item_id;
	private Date purchase_date;
	private Integer quantity;

	public Order(Long fk_customer_id, Long fk_item_id, Integer quantity) {
		super();
		this.fk_customer_id = fk_customer_id;
		this.fk_item_id = fk_item_id;
		this.quantity = quantity;
	}

	public Order(Long order_id, Long fk_customer_id, Long fk_item_id, Integer quantity) {
		this.order_id = order_id;
		this.fk_customer_id = fk_customer_id;
		this.fk_item_id = fk_item_id;
		this.quantity = quantity;
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

	public Long getFk_item_id() {
		return fk_item_id;
	}

	public void setFk_item_id(Long fk_item_id) {
		this.fk_item_id = fk_item_id;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
		if (getFk_item_id() == null) {
			if (other.getFk_item_id() != null)
				return false;
		} else if (!getFk_item_id().equals(other.getFk_item_id()))
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
		if (purchase_date == null) {
			if (other.purchase_date != null)
				return false;
		} else if (!purchase_date.equals(other.purchase_date))
			return false;
		return true;
	}
}
