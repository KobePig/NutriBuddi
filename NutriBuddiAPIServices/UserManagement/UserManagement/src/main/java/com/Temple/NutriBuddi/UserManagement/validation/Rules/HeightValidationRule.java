package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;


public class HeightValidationRule implements ValidationRule {
    @Override
    public ValidationResponse validate(UserVO user) {
        ValidationResponse validationResponse;
        String height = user.getHeight();
        if (!height.equals("")) {
            validationResponse = new ValidationResponse("OK height", HttpStatus.ACCEPTED);
        } else {
            validationResponse =  new ValidationResponse("Height required", HttpStatus.NOT_ACCEPTABLE);
        }
        return validationResponse;
    }
}
