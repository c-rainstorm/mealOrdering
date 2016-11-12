package com.chenswe.sellermanager.entity;

public class SellerBrief {
	private String registrationID;
	private String store_photo;
	private String store_name;
	private int soldNum;
	private int deliveryFee;
	public String getRegistrationID() {
		return registrationID;
	}
	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
	

	public String getStore_photo() {
		return store_photo;
	}
	public void setStore_photo(String store_photo) {
		this.store_photo = store_photo;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public int getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(int soldNum) {
		this.soldNum = soldNum;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	
}
