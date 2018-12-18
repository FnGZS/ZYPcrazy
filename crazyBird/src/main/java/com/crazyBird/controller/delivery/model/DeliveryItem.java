package com.crazyBird.controller.delivery.model;

public class DeliveryItem {

	private Long id;
	private String deliveryNumber;
	private String task;
	private String commission;
	private String address;
	private String requirement;
	private String isAccept;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public String getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	
}
