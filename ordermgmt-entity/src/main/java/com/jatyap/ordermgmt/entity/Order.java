package com.jatyap.ordermgmt.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long orderId;

	@DateTimeFormat (pattern="yyyy-MM-dd")
	protected Date orderDate;
	
	@OneToMany
	protected List<OrderDetail> orderDetails;
	
	public void setOrderId(long orderId){
		this.orderId = orderId;
	}
	public long getOrderId(){
		return this.orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}
	
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public void addOrderDetail(OrderDetail orderDetail){
		if(null == this.orderDetails){
			orderDetails = new ArrayList<OrderDetail>();
		}
		this.orderDetails.add(orderDetail);
	}
	
	/*
	public abstract <E extends OrderDetail> List<E> getOrderDetails();

	public abstract <E extends OrderDetail> void addOrderDetail(E detail);
	
	public abstract <E extends OrderDetail> void setOrderDetails(List<E> orderDetails);
	*/
}
