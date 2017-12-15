package com.Temple.NutriBuddi.UserManagement.bdd.steps.UserGoal;

import com.Temple.NutriBuddi.UserManagement.bdd.SpringFeatureTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Base64;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserGoalStepsDelete extends SpringFeatureTest{
    @Autowired
    private MockMvc mockMvc;
    private ResultActions response;

    @When("^she makes a request to usergoal/deleteUserGoal$")
    public void sheMakesARequestToUsergoalDeleteUserGoal() throws Throwable {
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        response = mockMvc.perform(get("http://localhost:8080/userGoal/deleteUserGoal")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^she should get back a message saying delete successful with status (\\d+)$")
    public void sheShouldGetBackAMessageSayingDeleteSuccessfulWithStatus(int arg0) throws Throwable {
        response.andExpect(status().is4xxClientError()).andDo(print()).andReturn();
    }
}