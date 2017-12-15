package com.Temple.NutriBuddi.UserManagement.vo;

import java.sql.Date;

import com.Temple.NutriBuddi.UserManagement.repository.EatsRepository;


public class EatsVO {
	
	int numServings;
	int foodId;
	EatsRepository eatsRepository;
	int userId;
	
	public EatsVO(int userId, int numServings, int foodId, EatsRepository eatsRepository) {
		this.userId = userId;
		this.numServings = numServings;
		this.foodId = foodId;
		this.eatsRepository = eatsRepository;
	}
	
	public int getNumServings() {
		return numServings;
	}

	public void setNumServings(int numServings) {
		this.numServings = numServings;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public EatsRepository getEatsRepository() {
		return eatsRepository;
	}

	public void setEatsRepository(EatsRepository eatsRepository) {
		this.eatsRepository = eatsRepository;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


}
