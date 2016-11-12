package com.chenswe.ordermanager.entity;

import com.chenswe.sellermanager.entity.Food;

public class FoodInOrder {
	private Food food;
	private int soldNum;

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getSoldNum() {
		return soldNum;
	}

	public void setSoldNum(int soldNum) {
		this.soldNum = soldNum;
	}
}
