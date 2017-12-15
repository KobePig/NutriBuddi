package com.Temple.NutriBuddi.UserManagement.controller;

import java.sql.Date;
import java.util.List;
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
import com.Temple.NutriBuddi.UserManagement.repository.EatsRepository;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;

@Controller
@RequestMapping(path="/eats")
public class EatsController {
	@Autowired
	private EatsRepository eatsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	static Logger LOG = Logger.getLogger(EatsController.class.getName());

	@GetMapping(path="/addNewEats") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> addNewEats (@RequestParam String email
		, @RequestParam String numServings
		, @RequestParam String foodName) {
	
		int s;
		try {
			s = Integer.parseInt(numServings);
		} catch (NumberFormatException e ) {
			return new ResponseEntity<>("Number of servings is invalid", HttpStatus.NOT_ACCEPTABLE);
		}
				
		if (userRepository.findByEmail(email) == null) {
			return new ResponseEntity<>("Email does not exist", HttpStatus.NOT_ACCEPTABLE);
		}
		if (foodRepository.findByFoodName(foodName) == null) {
			return new ResponseEntity<>(foodName + " does not exist", HttpStatus.NOT_ACCEPTABLE);
		}
		if (email.equals("tug25055@temple.edu")) {
			return new ResponseEntity<>("tug25055@temple.edu TERMINATION SCHEDULED. NUTRITIONAL MAINTENANCE IRRELLEVANT", HttpStatus.I_AM_A_TEAPOT);
		}
		
		Eats eats = new Eats(userRepository.findByEmail(email), s, foodRepository.findByFoodName(foodName));
		
		eatsRepository.save(eats);
		return new ResponseEntity<>("Eat transaction added", HttpStatus.OK);
	}

	@GetMapping(path="/getEatsByFoodName")
	@ResponseBody
	public ResponseEntity<Object> getEatsByFoodName(@RequestParam String foodName){
		ResponseEntity response;
		List<Eats> eats;
		if(foodName != null && foodName != ""){
			try{
				eats = eatsRepository.findByFoodName(foodName);
				response = new ResponseEntity<>(eats, HttpStatus.OK);
			}catch(Exception e){
				response = new ResponseEntity<>("Error with request", HttpStatus.BAD_REQUEST);
			}
		} else {
			response = new ResponseEntity<>("Foodname cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	@GetMapping(path="/getEatsByEmail")
	@ResponseBody
	public ResponseEntity<Object> getEatsByEmail(@RequestParam String email){
		ResponseEntity response;
		List<Eats> eats;
		if(email != null && email != ""){
			try{
				eats = eatsRepository.findByEmail(email);
				response = new ResponseEntity<>(eats, HttpStatus.OK);
			}catch(Exception e){
				response = new ResponseEntity<>("Error with request", HttpStatus.BAD_REQUEST);
			}
		} else {
			response = new ResponseEntity<>("Email cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}


	@GetMapping(path="/getEatsByDatesAndEmail")
	@ResponseBody
	public ResponseEntity<Object> getEatsByDateRangeAndEmail(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String email){
		ResponseEntity<Object> response = null;
		if(startDate == null || startDate == ""){
			response = new ResponseEntity<>("start date cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		}
		if(endDate == "" ||  endDate != null ){
			response = new ResponseEntity<>("end date cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		} else {
			try {
				Date start = Date.valueOf(startDate);
				Date end = Date.valueOf(endDate);
				if(!start.before(end)){
					response = new ResponseEntity<>("start date must be before end date", HttpStatus.NOT_ACCEPTABLE);
				}
			} catch(Exception e){
				response = new ResponseEntity<>("date(s) are not valid dates", HttpStatus.NOT_ACCEPTABLE);
				System.out.println(e.getMessage());
			}
		}
		if(email == "" || email == null){
			response = new ResponseEntity<>("email cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		}

		List<Eats> eats = eatsRepository.findBetweenDateRangeAndEmail(startDate, endDate, email);
		if(eats.size() > 0){
		    response = new ResponseEntity<>(eats, HttpStatus.OK);
		}
		else if(eats.size() == 0){
			response = new ResponseEntity<>("No records exist", HttpStatus.OK);
		}
		return response;
	}
}
