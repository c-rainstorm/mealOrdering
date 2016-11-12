package com.chenswe.sellermanager.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

import com.chenswe.sellermanager.dao.sellerDAO;
import com.chenswe.sellermanager.dao.impl.SellerDAOImpl;
import com.chenswe.sellermanager.entity.Food;
import com.chenswe.sellermanager.entity.Seller;
import com.chenswe.sellermanager.entity.SellerBrief;
import com.chenswe.sellermanager.service.SellerService;
import com.chenswe.util.StringUtil;

public class SellerServiceImpl implements SellerService {

	private sellerDAO dao = new SellerDAOImpl();

	@Override
	public boolean checkRegistrationID(Seller seller) {
		return dao.checkRegistrationID(seller);
	}

	@Override
	public boolean checkPhone(Seller seller) {
		return dao.checkPhone(seller);
	}

	@Override
	public boolean addSeller(Seller seller) {
		return dao.addSeller(seller);
	}

	@Override
	public boolean checkLoginInfo(Seller seller) {
		return dao.checkLoginInfo(seller);
	}

	@Override
	public String getRegistrationID(Seller seller) {
		return dao.getRegistrationID(seller);
	}

	@Override
	public ArrayList<SellerBrief> getSellerBrief(String pageContent, int page) {
		return dao.getSellerBrief(pageContent, page);
	}

	@Override
	public Seller getSellerDetail(Seller seller) {
		return dao.getSellerDetail(seller);
	}

	@Override
	public ArrayList<Food> getFoodsBrief(String registrationID, String pageContent, int page) {
		return dao.getFoodsBrief(registrationID, pageContent, page);
	}

	@Override
	public Food getFoodBrief(Food food) {
		return dao.getFoodBrief(food);
	}

	@Override
	public int getStoreStatus(String registrationID) {
		return dao.getStoreStatus(registrationID);
	}

	@Override
	public void changeStoreStatus(String registrationID) {
		dao.changeStoreStatus(registrationID);
	}

	@Override
	public boolean updateSellerInfo(Seller seller) {
		return dao.updateSellerInfo(seller);
	}

	@Override
	public ArrayList<Food> getSellerFoods(String registrationID, int page) {
		return dao.getSellerFoods(registrationID, page);
	}

	@Override
	public boolean modifyFoodInfo(HttpServletRequest request, InputStream imageInputStream) {
		Food food = new Food();
		food.setFoodID(request.getParameter("foodID"));
		food = getFoodBrief(food);
		food.setName(request.getParameter("newFoodName"));

		food.setPrice(Integer.parseInt(request.getParameter("newFoodPrice")));
		
		System.out.println(food);
			
		if (imageInputStream != null) {
			String targetFile = request.getServletContext().getRealPath("/") + "style/images/foods/"
					+ food.getFoodID() + ".jpg";
			try {
				FileUtils.copyInputStreamToFile(imageInputStream,
						new File(targetFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(StringUtil.isEmpty(food.getImageAddr()))
				food.setImageAddr("../../style/images/foods/" + food.getFoodID() + ".jpg");
		}

		boolean success = dao.modifyFoodInfo(food);

		return success;
	}

	
	@Override
	public void changeFoodStatus(String foodID) {
		dao.changeFoodStatus(foodID);
	}

	@Override
	public boolean deleteFood(String foodID) {
		return dao.deleteFood(foodID);
	}

	@Override
	public boolean addFood(HttpServletRequest request, InputStream imageInputStream) {
		String registrationID = request.getParameter("registrationID");
		dao.increaseFoodNum(registrationID);
		int foodNum = dao.getFoodNum(registrationID);
		
		Food food = new Food();
		
		food.setFoodID(registrationID + foodNum);
		food.setName(request.getParameter("foodName"));
		food.setPrice(Integer.parseInt(request.getParameter("foodPrice")));
			
		if (imageInputStream != null) {
			String targetFile = request.getServletContext().getRealPath("/") + "/style/images/foods/" + food.getFoodID() + ".jpg";
			try {
				FileUtils.copyInputStreamToFile(imageInputStream, new File(targetFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			food.setImageAddr("../../style/images/foods/" + food.getFoodID() + ".jpg");
		}

		boolean success = dao.addFood(food);

		return success;
	}
}
