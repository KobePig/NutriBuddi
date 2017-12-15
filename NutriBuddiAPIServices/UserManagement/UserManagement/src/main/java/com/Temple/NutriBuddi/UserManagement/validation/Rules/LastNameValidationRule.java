package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;


public class LastNameValidationRule implements ValidationRule {
    @Override
    public ValidationResponse validate(UserVO user) {
        ValidationResponse validationResponse;
        String lastName = user.getUserName();
        if (!lastName.equals("")) {
            validationResponse = new ValidationResponse("OK", HttpStatus.ACCEPTED);
        } else {
            validationResponse =  new ValidationResponse("Last name required", HttpStatus.NOT_ACCEPTABLE);
        }
        return validationResponse;
    }
}
