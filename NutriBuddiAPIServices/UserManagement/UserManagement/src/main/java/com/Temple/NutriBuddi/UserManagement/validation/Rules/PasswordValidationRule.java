package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;


public class PasswordValidationRule implements ValidationRule {

    @Override
    public ValidationResponse validate(UserVO user) {
        String password = user.getPassword();
        String password2 = user.getPassword2();
        ValidationResponse validationResponse;
        if (!password.equals("")) {
            if (password.equals(password2)) {
                validationResponse =  new ValidationResponse("OK password", HttpStatus.OK);
            } else {
                validationResponse = new ValidationResponse("Passwords must match", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            validationResponse = new ValidationResponse("Password required", HttpStatus.NOT_ACCEPTABLE);
        }
        return validationResponse;
    }
}
