package com.Temple.NutriBuddi.UserManagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.Temple.NutriBuddi.UserManagement.UserManagementApplication;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = UserManagementApplication.class
)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class FoodControllerTest {
	 @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    FoodRepository foodRepository;
    
    
	public String authorization;
    private static final Logger log = LoggerFactory.getLogger(FoodControllerTest.class);
    
    Food quantumKumquat;
    
    @Before
    public void setup() throws Exception, Exception {

        authorization = "Basic " +
                Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());

        /*String response = mockMvc.perform(get("/user/addNewUser")
                .header("Authorization", authorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "qualitypasssword")
                .param("password2", "qualitypasssword")
                .param("userName", "Testy McTesterson")
                .param("first", "Bill")
                .param("last", "Bobaggins")
                .param("height", "47")
                .param("weight", "147")
                .param("age", "37")
                .param("gender", "1"))
                .andReturn().getResponse().getContentAsString();*/
        
        quantumKumquat = new Food();
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
    
    }
    
    @Test
    public void getFoodNutrition() throws Exception {
    	String response = mockMvc.perform(get("/food/getFoodNutrition")
                .header("Authorization", authorization)
                .param("foodName", "quantumKumquat"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    	
    }

    @Test
	public void getAllFoods() throws Exception {
		String response = mockMvc.perform(get("/food/getAllFoods")
				.header("Authorization", authorization))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}
}
