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
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.Temple.NutriBuddi.UserManagement.UserManagementApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = UserManagementApplication.class
)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
	private String userAuthorization;
    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

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
    }

    @Test
    public void addNewUser() throws Exception {
        mockMvc.perform(get("/user/addNewUser")
                .header("Authorization", userAuthorization)
                .param("email", "fubar@tester.com")
                .param("password", "1ring2rulthemAll")
                .param("password2", "1ring2rulthemAll")
                .param("userName", "SauronIsLife")
                .param("first", "Bilbo")
                .param("last", "Bobaggins")
                .param("height", "50")
                .param("weight", "110")
                .param("age", "203")
                .param("gender", "2"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void addNewUserWithDuplicateEmail() throws Exception {
        mockMvc.perform(get("/user/addNewUser")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "qualitypasssword")
                .param("password2", "qualitypasssword")
                .param("userName", "Testasdfjkl;")
                .param("first", "Elliot")
                .param("last", "Alderson")
                .param("height", "162")
                .param("weight", "150")
                .param("age", "32")
                .param("gender", "1"))
                .andExpect(status().isNotAcceptable())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(get("/user/updateUser")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "qualitypasssword")
                .param("password2", "qualitypasssword")
                .param("height", "50")
                .param("weight", "1")
                .param("age", "500")
                .param("gender", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void updateUserWithNonMatchingPasswords() throws Exception {
        mockMvc.perform(get("/user/updateUser")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "1")
                .param("password2", "2")
                .param("height", "50")
                .param("weight", "1")
                .param("age", "500")
                .param("gender", "1"))
                .andExpect(status().isNotAcceptable())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void updateUserWithEmptyFields() throws Exception {
        mockMvc.perform(get("/user/updateUser")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "2")
                .param("password2", "2")
                .param("height", "")
                .param("weight", "")
                .param("age", "")
                .param("gender", ""))
                .andExpect(status().isNotAcceptable())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void getAllUsers() throws Exception{
    	
    	String response = mockMvc.perform(get("/user/all")
                .header("Authorization", userAuthorization)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        
        log.info("getAllUsers Response: " + response);
    }

    @Test
    public void userLoginSuccess() throws Exception {
    	String response = mockMvc.perform(get("/user/login")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "qualitypasssword")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        
        log.info("userLoginSuccess Response: " + response);
    }
    
    @Test
    public void userLoginFailEmail() throws Exception {
    	String response = mockMvc.perform(get("/user/login")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester")
                .param("password", "qualitypasssword")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();
        
        log.info("userLoginFailureEmail Response: " + response);
    }
    
    @Test
    public void userLoginFailPassword() throws Exception {
    	String response = mockMvc.perform(get("/user/login")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "qualitypasss")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();
        
        log.info("userLoginFailurePassword Response: " + response);
    }
    
    @Test
    public void userLoginFailEmpty() throws Exception {
    	String response = mockMvc.perform(get("/user/login")
                .header("Authorization", userAuthorization)
                .param("email", "")
                .param("password", "")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();
        
        log.info("userLoginFailureEmpty Response: " + response);
    }
    
    @Test
    public void userLoginFailEverything() throws Exception {
    	String response = mockMvc.perform(get("/user/login")
                .header("Authorization", userAuthorization)
                .param("email", "butts@butt.com")
                .param("password", "test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();
        
        log.info("userLoginFailureEverything Response: " + response);
    }

}