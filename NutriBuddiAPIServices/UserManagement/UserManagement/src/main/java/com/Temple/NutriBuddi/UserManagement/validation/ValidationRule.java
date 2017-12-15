package com.Temple.NutriBuddi.UserManagement.validation;

import com.Temple.NutriBuddi.UserManagement.vo.UserVO;


public interface ValidationRule {
    public ValidationResponse validate(UserVO user);
}
