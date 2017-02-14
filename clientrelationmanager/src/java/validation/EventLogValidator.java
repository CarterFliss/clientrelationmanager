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

import objects.EventLog;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
 *
 * @author Carter
 */
@Component
public class EventLogValidator implements Validator {
    private static Logger logger = Logger.getLogger(EventLogValidator.class.getName());
    
    @Override
    public boolean supports(Class<?> classy){
        return EventLog.class.isAssignableFrom(classy);
    }
    
    @Override
    public void validate (Object target,Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"clientid", "event.clientid.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userid", "event.userid.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"clientFirstName", "event.firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"clientLastName", "event.lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "event.username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"interaction", "event.interaction.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"date", "event.date.required");
        
        EventLog eventlog = (EventLog) target;
        if (eventlog.getClientFirstName().length() > 60){
           errors.rejectValue("clientFirstName", "event.firstName.length");
       }
        if(!eventlog.getClientFirstName().matches("^[A-Z]'?[- a-zA-Z]([a-zA-Z])*$")){
           errors.rejectValue("clientFirstName","event.firstName.pattern");}
        if (eventlog.getClientLastName().length() > 60){
           errors.rejectValue("clientLastName", "event.lastName.length");
       }
        if(!eventlog.getClientLastName().matches("^[A-Z]'?[- a-zA-Z]([a-zA-Z])*$")){
           errors.rejectValue("clientLastName","event.lastName.pattern");}
        if (eventlog.getUsername().length() > 60){
           errors.rejectValue("username", "event.username.length");
       }
        if(!eventlog.getUsername().matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")){
           errors.rejectValue("username","event.username.pattern");}
        if(!eventlog.getDate().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}")){
           errors.rejectValue("date","event.date.pattern");}
    }
    
}
