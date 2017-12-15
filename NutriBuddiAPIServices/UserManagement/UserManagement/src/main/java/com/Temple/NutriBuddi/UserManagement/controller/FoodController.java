package com.Temple.NutriBuddi.UserManagement.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/food")
public class FoodController {

	@Autowired
	private FoodRepository foodRepository;
	
	static Logger LOG = Logger.getLogger(EatsController.class.getName());
	
	@GetMapping(path="/getFoodNutrition") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> getFoodNutrition (@RequestParam String foodName) {
		
		if (foodRepository.findByFoodName(foodName) == null) {
			return new ResponseEntity<>(foodName + " does not exist", HttpStatus.NOT_ACCEPTABLE);
		}
	
		return new ResponseEntity<>(foodRepository.findByFoodName(foodName), HttpStatus.OK);
	}

	@GetMapping(path="/getAllFoods")
	public @ResponseBody Iterable<Food> getAllFoods() {

		return foodRepository.findAll();
	}

	@GetMapping(path="/addTestFood") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> addTestFood () {
	
		Food quantumKumquat = new Food();
		quantumKumquat.setFoodName("quantumKumquat");
		quantumKumquat.setCalories(0);
		quantumKumquat.setCarbohydrates(0.0);
		quantumKumquat.setFiber(0.0);
		quantumKumquat.setIron(0.0);
		quantumKumquat.setMagnesium(0.0);
		quantumKumquat.setPhospherous(0.0);
		quantumKumquat.setPotassium(0.0);
		quantumKumquat.setProtein(0.0);
		quantumKumquat.setSatFat(0.0);
		quantumKumquat.setServingUnit("Quarks");
		quantumKumquat.setSodium(19.0);
		quantumKumquat.setSugar(11.0);
		quantumKumquat.setTotalFat(99.0);
		quantumKumquat.setTransFat(4.0);
		quantumKumquat.setVitaminC(1.0);
		quantumKumquat.setVitaminD(69.0);
		quantumKumquat.setZinc(12.0);
		
		foodRepository.save(quantumKumquat);
		
		return new ResponseEntity<>("Food added", HttpStatus.OK);
	}
	
	@GetMapping(path="/addFood") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> addFood (@RequestParam String foodName
			, @RequestParam int calories
			, @RequestParam int carbohydrates
			, @RequestParam int fiber
			, @RequestParam int iron
			, @RequestParam int magnesium
			, @RequestParam int phospherous
			, @RequestParam int potassium
			, @RequestParam int protein
			, @RequestParam int satFat
			, @RequestParam String servingUnit
			, @RequestParam int sodium
			, @RequestParam int sugar
			, @RequestParam int totalFat
			, @RequestParam int transFat
			, @RequestParam int vitaminC
			, @RequestParam int vitaminD
			, @RequestParam int zinc) {
	
		Food food = new Food(foodName, servingUnit, calories, carbohydrates, fiber, iron, magnesium, phospherous, potassium, protein,
				satFat, sodium, sugar, totalFat, transFat, vitaminC, vitaminD, zinc);
			
		foodRepository.save(food);
		
		return new ResponseEntity<>("Food added", HttpStatus.OK);
	}
}
