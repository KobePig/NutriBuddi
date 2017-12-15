package com.Temple.NutriBuddi.UserManagement.bdd.steps.Food;

import com.Temple.NutriBuddi.UserManagement.bdd.SpringFeatureTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Base64;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FoodStepsGetByFoodName extends SpringFeatureTest{
    @Autowired
    private MockMvc mockMvc;
    private ResultActions response;

    @When("^Demogorgon is hungry and makes a request$")
    public void demogorgonIsHungryAndMakesARequest() throws Throwable {
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        response = mockMvc.perform(get("http://localhost:8080/food/getEatsByFoodName")
                .header("Authorization", userAuthorization)
                .param("foodName", "eggos")
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^it gets back a failure and goes hunting$")
    public void itGetsBackASuccessAndGoesHunting() throws Throwable {
        response.andExpect(status().is4xxClientError()).andDo(print()).andReturn();
    }
}
