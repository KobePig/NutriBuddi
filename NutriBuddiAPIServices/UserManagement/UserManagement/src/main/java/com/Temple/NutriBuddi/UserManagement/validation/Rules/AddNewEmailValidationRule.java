package com.Temple.NutriBuddi.UserManagement.validation.Rules;

import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.hibernate.annotations.common.util.impl.Log;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;


public class AddNewEmailValidationRule implements ValidationRule{

    @Override
    public ValidationResponse validate(UserVO user) {
        String email = user.getEmail();
        if (!email.equals("")) {
            if (user.getUserRepository().findByEmail(email) == null) {
                return new ValidationResponse("OK email", HttpStatus.OK);
            } else {
                return new ValidationResponse("Email is already registered", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ValidationResponse("Valid email required", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
