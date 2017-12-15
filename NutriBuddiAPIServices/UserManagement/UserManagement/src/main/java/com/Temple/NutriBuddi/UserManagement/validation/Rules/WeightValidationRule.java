package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;


public class WeightValidationRule implements ValidationRule {
    @Override
    public ValidationResponse validate(UserVO user) {
        ValidationResponse validationResponse;
        String weight = user.getWeight();
        if (!weight.equals("")) {
            validationResponse = new ValidationResponse("OK weight", HttpStatus.ACCEPTED);
        } else {
            validationResponse =  new ValidationResponse("Weight required", HttpStatus.NOT_ACCEPTABLE);
        }
        return validationResponse;
    }
}
