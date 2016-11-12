package com.chenswe.usermanager.entity;

public class Receiver {
	private String owner;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddr;
	private int isDefault = 0;
	
	@Override
	public String toString() {
		return "Receiver [owner=" + owner + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone
				+ ", receiverAddr=" + receiverAddr + ", isDefault=" + isDefault + "]";
	}
	public int getIsdefault() {
		return isDefault;
	}
	public void setIsdefault(int isdefault) {
		this.isDefault = isdefault;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverAddr() {
		return receiverAddr;
	}
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
}
