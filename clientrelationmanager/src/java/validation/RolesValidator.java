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

import objects.Roles;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
 *
 * @author Carter
 */
@Component
public class RolesValidator implements Validator{
    private static Logger logger = Logger.getLogger(EventLogValidator.class.getName());
    
    @Override
    public boolean supports(Class<?> classy){
        return Roles.class.isAssignableFrom(classy);
    }
    
    @Override
    public void validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "role.username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userRole", "role.userrole.required");
        
        Roles roles = (Roles) target;
        if(roles.getUsername().length() > 120){
           errors.rejectValue("username", "role.username.length");
       }
       if(!roles.getUsername().matches("/^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$/")){
           errors.rejectValue("username","role.username.pattern");}
       if(roles.getUserRole().length() > 10){
           errors.rejectValue("userRole", "role.userrole.length");
       }
       if(!roles.getUserRole().matches("/^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$/")){
           errors.rejectValue("userRole","role.userRole.pattern");}
    }
}
