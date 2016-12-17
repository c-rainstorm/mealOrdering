package com.chenswe.ordermanager.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {

    private String orderID;
    private String payer;
    private String receiverPhone;
    private String receiverName;
    private String receiverAddr;
    private Timestamp orderTime;
    private Timestamp finishTime;
    private int payway;
    private int status = 0;
    private String comment;
    private int deliveryFee;
    private float total;
    private ArrayList<FoodInOrder> foods = new ArrayList<>(20);

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public int getPayway() {
        return payway;
    }

    public void setPayway(int payway) {
        this.payway = payway;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ArrayList<FoodInOrder> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<FoodInOrder> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "Order [orderID=" + orderID + ", payer=" + payer + ", receiverPhone=" + receiverPhone
                + ", receiverName=" + receiverName + ", receiverAddr=" + receiverAddr
                + ", orderTime=" + orderTime + ", finishTime=" + finishTime + ", payway=" + payway
                + ", status=" + status + ", comment=" + comment + ", deliveryFee=" + deliveryFee
                + ", total=" + total + ", foods=" + foods + "]";
    }

}
