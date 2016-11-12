package com.chenswe.sellermanager.entity;

public class Food {
	private String foodID;
	private String name;
	private String imageAddr;
	private float price;
	private int soldNum = 0;
	private int status = 1;
	
 	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Food [foodID=" + foodID + ", name=" + name + ", imageAddr=" + imageAddr + ", price=" + price
				+ ", soldNum=" + soldNum + "]";
	}
	public String getFoodID() {
		return foodID;
	}
	public void setFoodID(String foodID) {
		this.foodID = foodID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageAddr() {
		return imageAddr;
	}
	public void setImageAddr(String imageAddr) {
		this.imageAddr = imageAddr;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(int soldNum) {
		this.soldNum = soldNum;
	}
	
	
}
