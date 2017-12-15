package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;


public class GenderValidationRule implements ValidationRule {
    @Override
    public ValidationResponse validate(UserVO user) {
        ValidationResponse validationResponse;
        String gender = user.getGender();
        if (!gender.equals("")) {
            validationResponse = new ValidationResponse("OK gender", HttpStatus.ACCEPTED);
        } else {
            validationResponse =  new ValidationResponse("Gender required", HttpStatus.NOT_ACCEPTABLE);
        }
        return validationResponse;
    }
}
