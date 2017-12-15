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

public class EatsStepsGetByDateRangeAndEmail extends SpringFeatureTest{
    @Autowired
    private MockMvc mockMvc;
    private ResultActions response;

    @When("^he makes the call to /eats/getEatsByDatesAndEmail with valid date range$")
    public void heMakesTheCallToEatsGetEatsByDatesAndEmailWithValidDateRange() throws Throwable {
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        response = mockMvc.perform(get("http://localhost:8080/eats/getEatsByDatesAndEmail")
                .header("Authorization", userAuthorization)
                .param("startDate", "2017/10/01")
                .param("endDate", "2017/11/01")
                .param("email", "qwerty@ecorps.com")
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^a response should be retrieved with status (\\d+) with list of foods$")
    public void aResponseShouldBeRetrievedWithStatusWithListOfFoods(int arg0) throws Throwable {
        response.andExpect(status().isOk()).andDo(print()).andReturn();
    }
}
