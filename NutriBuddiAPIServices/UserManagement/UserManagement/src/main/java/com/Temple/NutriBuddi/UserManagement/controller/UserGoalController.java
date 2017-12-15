package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.model.UserGoal;
import com.Temple.NutriBuddi.UserManagement.repository.UserGoalRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/userGoal")
public class UserGoalController {
    @Autowired
    private UserGoalRepository userGoalRepository;
    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserGoalController.class);
    
    @GetMapping(path="/addUserGoal")
    @ResponseBody
    public ResponseEntity<Object> addUserGoal(@RequestParam String email,
		@RequestParam int protein,
		@RequestParam int carbs,
		@RequestParam int calories,
		@RequestParam int sodium,
		@RequestParam int totalFat,
		@RequestParam int weightGoal){
        
    	ResponseEntity response;
    	
    	User user;
    	
        if(email.equals("") || email == null){
            response = new ResponseEntity("email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        } else if (userRepository.findByEmail(email) == null) {
        	response = new ResponseEntity("email is not in database", HttpStatus.NOT_ACCEPTABLE);
        } else {
        	user = userRepository.findByEmail(email);
        	
        	if (userGoalRepository.findByUserId(user.getId()) != null) {
        		response = new ResponseEntity("User goal already exists", HttpStatus.NOT_ACCEPTABLE);
        	} else {
            
	            UserGoal goal = new UserGoal(user, carbs, protein, calories, sodium, totalFat, weightGoal);
	                        
	            userGoalRepository.save(goal);
	            
	            response = new ResponseEntity("user goal updated", HttpStatus.OK);
        	}
        }
        return response;
    }


    @GetMapping(path="/updateUserGoal")
    @ResponseBody
    public ResponseEntity<Object> updateUserGoal(@RequestParam String email,
		@RequestParam int protein,
		@RequestParam int carbs,
		@RequestParam int calories,
		@RequestParam int sodium,
		@RequestParam int totalFat,
            @RequestParam int weightGoal){
        
    	ResponseEntity response;
    	
    	User user;
    	
        if(email.equals("") || email == null){
            response = new ResponseEntity("email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        } else if (userRepository.findByEmail(email) == null) {
        	response = new ResponseEntity("email is not in database", HttpStatus.NOT_ACCEPTABLE);
        } else {
        	user = userRepository.findByEmail(email);
            
        	if (userGoalRepository.findByUserId(user.getId()) == null) {
        		response = new ResponseEntity("User goal does not exist. Use addUserGoal first", HttpStatus.NOT_ACCEPTABLE);
        	} else {
	            UserGoal goal = userGoalRepository.findByUserId(user.getId());
	            goal.setCalories(calories);
	            goal.setCarbs(carbs);
	            goal.setProtein(protein);
	            goal.setSodium(sodium);
	            goal.setTotalFat(totalFat);
	            goal.setWeight(weightGoal);
	            
	            userGoalRepository.save(goal);
	            
	            response = new ResponseEntity("user goal updated", HttpStatus.OK);
        	}
        }
        return response;
    }
    
    @GetMapping(path="/deleteUserGoal")
    @ResponseBody
    public ResponseEntity<Object> deleteUserGoal(@RequestParam String email){
        ResponseEntity response;
        User user;

        if(email.equals("") || email == null){
            response = new ResponseEntity("email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }else {
             user = userRepository.findByEmail(email);
            if(user == null){
                response = new ResponseEntity("user not found with given email", HttpStatus.NOT_ACCEPTABLE);
            } else {
                userGoalRepository.deleteByUserId(user.getId());
                response = new ResponseEntity("delete successful", HttpStatus.OK);
            }
        }
        return response;
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserGoal> getAllUserGoals() {

        return userGoalRepository.findAll();
    }

    @GetMapping(path="/getUserGoals")
    @ResponseBody
    public ResponseEntity<Object> getUserGoals(@RequestParam String email){
        ResponseEntity response;
        User user;

        if(email.equals("") || email == null){
            response = new ResponseEntity("email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }else {
            user = userRepository.findByEmail(email);
            if(user == null){
                response = new ResponseEntity("user not found with given email", HttpStatus.NOT_ACCEPTABLE);
            } else {
                response = new ResponseEntity(userGoalRepository.findByUserId(user.getId()), HttpStatus.OK);
            }
        }
        return response;
    }
}
