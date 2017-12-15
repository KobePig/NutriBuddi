package com.Temple.NutriBuddi.UserManagement.bdd.steps.User;

import com.Temple.NutriBuddi.UserManagement.bdd.SpringFeatureTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Base64;


public class UserStepsAll extends SpringFeatureTest{
    @Autowired
    private MockMvc mockMvc;
    private String userAuthorization;
    private MockHttpServletRequestBuilder request;
    private ResultActions response;
    Logger log = LoggerFactory.getLogger(UserStepsAll.class);


    //TODO: Add and check tokenization
    @Given("^Mike is authorized$")
    public void mikeIsAuthorized() throws Throwable {
        userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        request = MockMvcRequestBuilders.get("http://localhost:8080/user/all").header("Authorization", userAuthorization);
    }

    @When("^he makes the call to /user/all$")
    public void heMakesTheCallToUserAll() throws Throwable {
        response = mockMvc.perform(request);
        response.andExpect(status().isOk()).andReturn();
    }



    @Then("^a response should be retrieved with status (\\d+) of type json$")
    public void aResponseShouldBeRetrievedWithStatusOfTypeJson(int arg0) throws Throwable {
        response.andExpect(status().is2xxSuccessful());

    }

    @And("^of type json$")
    public void ofTypeJson() throws Throwable {
        response.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @And("^is populated with user names$")
    public void isPopulatedWithUserNames() throws Throwable {
        response
                .andExpect(jsonPath("@").isNotEmpty())
                .andDo(print())
                .andReturn();
    }


}
