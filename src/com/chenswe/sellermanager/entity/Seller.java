package com.chenswe.sellermanager.entity;

public class Seller {
	private String registrationID;
	private String phone;
	private String password;
	private String store_photo_addr;
	private String storeName;
	private String storeAddress;
	private String category;
	private String description = "";
	private String announcement = "";
	private int status = 1;
	private int deliveryFee = 0;
	private int sold_num = 0;
	private int foodNum = 0;
	public String getRegistrationID() {
		return registrationID;
	}
	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStorePhotoAddr() {
		return store_photo_addr;
	}
	public void setStorePhotoAddr(String store_photo_addr) {
		this.store_photo_addr = store_photo_addr;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public int getSoldNum() {
		return sold_num;
	}
	public void setSoldNum(int sold_num) {
		this.sold_num = sold_num;
	}
		public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}

	@Override
	public String toString() {
		return "Seller [registrationID=" + registrationID + ", phone=" + phone + ", password=" + password
				+ ", store_photo_addr=" + store_photo_addr + ", storeName=" + storeName + ", storeAddress="
				+ storeAddress + ", category=" + category + ", description=" + description + ", announcement="
				+ announcement + ", status=" + status + ", deliveryFee=" + deliveryFee + ", sold_num=" + sold_num
				+ ", foodNum=" + foodNum + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
