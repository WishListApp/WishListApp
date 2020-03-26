package com.wlt.wla.auth.validator;

import com.wlt.wla.auth.service.UserService;
import com.wlt.wla.auth.model.DBWishItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class InputValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return DBWishItems.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //User user = (User) o;
       
        DBWishItems wishItem = (DBWishItems) o;
        ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty");
       if (wishItem.getPrice()<0.01f) {
           errors.rejectValue("price", "lowvalue");}
}
}
