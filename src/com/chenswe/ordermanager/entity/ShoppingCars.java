package com.chenswe.ordermanager.entity;

import java.util.ArrayList;

public class ShoppingCars {
	private ArrayList<ShoppingCar> shoppingCars = new ArrayList<>(50);

	public ArrayList<ShoppingCar> getShoppingCars() {
		return shoppingCars;
	}

	public void setShoppingCars(ArrayList<ShoppingCar> shoppingCars) {
		this.shoppingCars = shoppingCars;
	}

	@Override
	public String toString() {
		return "ShoppingCars [shoppingCars=" + shoppingCars + "]";
	}
	
}

class ShoppingCar{
	private ArrayList<Food> foods = new ArrayList<>(20);

	public ArrayList<Food> getFoods() {
		return foods;
	}

	@Override
	public String toString() {
		return "ShoppingCar [foods=" + foods + "]";
	}

	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}
	
}

class Food{
	private String foodID;
	private String foodCount;
	public String getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(String foodCount) {
		this.foodCount = foodCount;
	}
	@Override
	public String toString() {
		return "Food [foodID=" + foodID + ", foodCount=" + foodCount + "]";
	}
	public String getFoodID() {
		return foodID;
	}
	public void setFoodID(String foodID) {
		this.foodID = foodID;
	}
	
	
}
