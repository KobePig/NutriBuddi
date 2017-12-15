package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class FirstNameValidationRule implements ValidationRule{

    @Override
    public ValidationResponse validate(UserVO user) {
        ValidationResponse validationResponse;
        String firstName = user.getFirst();
        if (!firstName.equals("")) {
            validationResponse = new ValidationResponse("OK first", HttpStatus.ACCEPTED);
        } else {
            validationResponse =  new ValidationResponse("First name required", HttpStatus.NOT_ACCEPTABLE);
        }
        return validationResponse;
    }
}
