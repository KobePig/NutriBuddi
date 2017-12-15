package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.model.UserGoal;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserGoalRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/r")
public class RecommendationController {

    private static final int GAIN = 0;
    private static final int LOSE = 1;
    private static final int MAINTAIN = 2;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserGoalRepository userGoalRepository;

    static Logger LOG = Logger.getLogger(RecommendationController.class.getName());


    @GetMapping(path = "/recommendation")
    @ResponseBody
    public ResponseEntity<Object> getRecommendation(@RequestParam String email){
        ResponseEntity<Object> response;
        User user = userRepository.findByEmail(email);
        UserGoal userGoal;
        ArrayList<Food> recommendedFoodList = new ArrayList<>();
        if(user == null) {
            response = new ResponseEntity<>("User not found", HttpStatus.NOT_ACCEPTABLE);
        } else {
            userGoal = userGoalRepository.findByUserId(user.getId());
            if(userGoal == null){
                //use the base line
                userGoal = new UserGoal(null, 0, 0, 0, 0, 0, 0);
            }

            List<Food> foodList =  foodRepository.findAll();

            for(Food f: foodList){
                if(userGoal.getWeight() == GAIN) {
                    if (f.getCarbohydrates() >= userGoal.getCarbs() && f.getProtein() >= userGoal.getProtein()
                            && f.getSodium() <= userGoal.getSodium() && f.getTotalFat() >= userGoal.getTotalFat()
                            && f.getCalories() > userGoal.getCalories()){
                        recommendedFoodList.add(f);
                    }
                } else if(userGoal.getWeight() == LOSE) {
                    if (f.getCarbohydrates() <= userGoal.getCarbs() && f.getProtein() >= userGoal.getProtein()
                            && f.getSodium() <= userGoal.getSodium() && f.getTotalFat() <= userGoal.getTotalFat()
                            && f.getCalories() < userGoal.getCalories()){
                        recommendedFoodList.add(f);
                    }
                } else if(userGoal.getWeight() == MAINTAIN){
                    if(f.getCalories() == userGoal.getCalories()){
                        recommendedFoodList.add(f);
                    }
                }
            }
            response = new ResponseEntity<>(recommendedFoodList, HttpStatus.OK);
        }
        return response;
    }
}
