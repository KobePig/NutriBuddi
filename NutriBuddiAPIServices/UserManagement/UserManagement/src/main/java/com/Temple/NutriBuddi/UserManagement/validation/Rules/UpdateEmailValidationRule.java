package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.http.HttpStatus;

public class UpdateEmailValidationRule implements ValidationRule{
    @Override
    public ValidationResponse validate(UserVO user) {
        String email = user.getEmail();
        if (!email.equals("")) {
            if (user.getUserRepository().findByEmail(email) == null) {
                return new ValidationResponse("No such user found with this email", HttpStatus.NOT_ACCEPTABLE);
            }  else {
                return new ValidationResponse("OK email", HttpStatus.OK);
            }
        } else {
            return new ValidationResponse("Valid email required", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
