package com.Temple.NutriBuddi.UserManagement.bdd.steps.Image;

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

public class ImageStepsUpdate extends SpringFeatureTest {
    @Autowired
    private MockMvc mockMvc;
    private ResultActions response;

    @When("^Ken provides the new information$")
    public void kenProvidesTheNewInformation() throws Throwable {
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        response = mockMvc.perform(get("http://localhost:8080/imageClassifier/addNewImage")
                .header("Authorization", userAuthorization)
                .param("email", "test@test.com")
                .param("oldFileName", "muffin")
                .param("newFileName", "muffinbutton")
                .param("latitude", "11234.3")
                .param("longitude", "23432.2")
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^the image should update accordingly$")
    public void theImageShouldUpdateAccordingly() throws Throwable {
        response.andExpect(status().is4xxClientError()).andDo(print()).andReturn();
    }
}
