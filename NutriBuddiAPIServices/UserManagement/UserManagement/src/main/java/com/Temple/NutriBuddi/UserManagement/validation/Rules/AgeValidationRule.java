package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;


public class AgeValidationRule implements ValidationRule {

    @Override
    public ValidationResponse validate(UserVO user) {
        ValidationResponse validationResponse;
        String age = user.getAge();
        if (!age.equals("")) {
            validationResponse = new ValidationResponse("OK age", HttpStatus.ACCEPTED);
        } else {
            validationResponse =  new ValidationResponse("Age required", HttpStatus.NOT_ACCEPTABLE);
        }
        return validationResponse;
    }
}
