package com.Temple.NutriBuddi.UserManagement.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/Temple/NutriBuddi/UserManagement/bdd/features"})
//@ActiveProfiles(value="test")
public class CucumberTest  {
}
