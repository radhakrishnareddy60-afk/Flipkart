package com.flipkart.dto;

public class OrderDto {
	
	private long orderId;

	private boolean orderDelivery;
	private String orderLocation;
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

}
