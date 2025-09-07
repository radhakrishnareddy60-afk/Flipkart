package com.flipkart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")

public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "orderID")
	private long orderId;
	@Column(name = "Orderdelivery")
	private boolean orderDelivery;
	@Column(name = "orderLocation")
	private String orderLocation;
	
	public String getOrderLocation() {
		return orderLocation;
	}

	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}

	public long getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(long orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Column(name="orderPrice")
	private long orderPrice;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public boolean isOrderDelivery() {
	return orderDelivery;
	}

	public void setOrderDelivery(boolean orderDelivery) {
		this.orderDelivery = orderDelivery;
	}

}
