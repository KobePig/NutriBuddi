package com.Temple.NutriBuddi.UserManagement.bdd.steps.Eats;

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

public class EatsStepsGetByFoodName extends SpringFeatureTest{
    @Autowired
    private MockMvc mockMvc;
    private ResultActions response;

    @When("^she makes the call to /eats/getEatsByFoodName$")
    public void sheMakesTheCallToEatsGetEatsByFoodName() throws Throwable {
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        response = mockMvc.perform(get("http://localhost:8080/food/getFoodNutrition")
                .header("Authorization", userAuthorization)
                .param("foodName", "tomato")
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^a response should be retrieved with status (\\d+)$")
    public void aResponseShouldBeRetrievedWithStatus(int arg0) throws Throwable {
        response.andExpect(status().isOk()).andDo(print()).andReturn();
    }
}
