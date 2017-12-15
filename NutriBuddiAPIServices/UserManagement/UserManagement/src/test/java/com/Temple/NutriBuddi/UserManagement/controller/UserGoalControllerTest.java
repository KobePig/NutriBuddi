package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.UserManagementApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = UserManagementApplication.class
)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserGoalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String userAuthorization;
    private static final Logger log = LoggerFactory.getLogger(UserGoalControllerTest.class);

    @Before
    public void setup() throws Exception {

        userAuthorization = "Basic " +
                Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        String response = mockMvc.perform(get("/user/addNewUser")
                .header("Authorization", userAuthorization)
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
                .andReturn().getResponse().getContentAsString();
        
        String response2 = mockMvc.perform(get("/user/addNewUser")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTesterWithGoal@tester.com")
                .param("password", "qualitypasssword")
                .param("password2", "qualitypasssword")
                .param("userName", "Testy McTesterson")
                .param("first", "Bill")
                .param("last", "Bobaggins")
                .param("height", "47")
                .param("weight", "147")
                .param("age", "37")
                .param("gender", "1"))
                .andReturn().getResponse().getContentAsString();
        
        String response3 = mockMvc.perform(get("/userGoal/addUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTesterWithGoal@tester.com")
                .param("protein", "9")
                .param("carbs", "11")
                .param("calories", "9")
                .param("sodium", "11")
                .param("totalFat", "9")
                .param("weightGoal", "11"))
                .andReturn().getResponse().getContentAsString();
    }
    
    @Test
    public void addUserGoal() throws Exception {
        mockMvc.perform(get("/userGoal/addUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("protein", "9")
                .param("carbs", "11")
                .param("calories", "9")
                .param("sodium", "11")
                .param("totalFat", "9")
                .param("weightGoal", "11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    
    @Test
    public void addUserGoalAlreadyExists() throws Exception {
        mockMvc.perform(get("/userGoal/addUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTesterWithGoal@tester.com")
                .param("protein", "9")
                .param("carbs", "11")
                .param("calories", "9")
                .param("sodium", "11")
                .param("totalFat", "9")
                .param("weightGoal", "11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andReturn().getResponse().getContentAsString();
    }
    
    @Test
    public void addUserGoalBadEmail() throws Exception {
        mockMvc.perform(get("/userGoal/addUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "loser@tester.com")
                .param("protein", "9")
                .param("carbs", "11")
                .param("calories", "9")
                .param("sodium", "11")
                .param("totalFat", "9")
                .param("weightGoal", "11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andReturn().getResponse().getContentAsString();
    }
    
    @Test
    public void UpdateUserGoal() throws Exception {
        mockMvc.perform(get("/userGoal/updateUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTesterWithGoal@tester.com")
                .param("protein", "11")
                .param("carbs", "11")
                .param("calories", "11")
                .param("sodium", "11")
                .param("totalFat", "11")
                .param("weightGoal", "11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    
    @Test
    public void UpdateUserGoalDoesNotExist() throws Exception {
        mockMvc.perform(get("/userGoal/updateUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("protein", "11")
                .param("carbs", "11")
                .param("calories", "11")
                .param("sodium", "11")
                .param("totalFat", "11")
                .param("weightGoal", "11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andReturn().getResponse().getContentAsString();
    }
    
    @Test
    public void UpdateUserGoalEmailDoesNotExist() throws Exception {
        mockMvc.perform(get("/userGoal/updateUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "loser@tester.com")
                .param("protein", "11")
                .param("carbs", "11")
                .param("calories", "11")
                .param("sodium", "11")
                .param("totalFat", "11")
                .param("weightGoal", "11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andReturn().getResponse().getContentAsString();
    }
  
    @Test
    public void deleteUserGoal() throws Exception {
        mockMvc.perform(get("/userGoal/deleteUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getUserGoal() throws Exception {
        mockMvc.perform(get("/userGoal/getUserGoals")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/userGoal/all")
                .header("Authorization", userAuthorization)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

}