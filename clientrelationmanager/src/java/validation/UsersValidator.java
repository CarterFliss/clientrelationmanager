/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

import objects.Users;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
 *
 * @author Carter
 */
@Component
public class UsersValidator implements Validator{

    private static Logger logger = Logger.getLogger(UsersValidator.class.getName());
    
    @Override
    public boolean supports(Class<?> classy){
        return Users.class.isAssignableFrom(classy);
    }
    
    @Override
    public void validate (Object target, Errors errors){
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "user.username.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "user.password.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userStatus", "user.status.required");
       ValidationUtils.rejectIfEmpty(errors, "userrole", "user.userrole.required");
       
       Users users = (Users) target;
       if(users.getUsername().length() > 120){
           errors.rejectValue("username", "user.username.length");
       }
       if(!users.getUsername().matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")){
           errors.rejectValue("username","user.username.pattern");
       }
       if(users.getPassword().length() > 120){
           errors.rejectValue("password", "user.password.length");
       }
       if(!users.getPassword().matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")){
           errors.rejectValue("password","user.password.pattern");
       }
       if(users.getUserrole().length() > 20){
           errors.rejectValue("userrole", "user.userrole.length");
       }
       if(!users.getUserrole().matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")){
           errors.rejectValue("userrole","user.userrole.pattern");
       }
    }
}
