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

import objects.Clients;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Carter
 */
@Component
public class ClientsValidator implements Validator {
    private static Logger logger = Logger.getLogger(ClientsValidator.class.getName());
    
    @Override
    public boolean supports(Class<?> classy){
        return Clients.class.isAssignableFrom(classy);
    }
    
    @Override
    public void validate (Object target, Errors errors){
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName", "client.firstName.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName", "client.lastName.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userStatus", "client.status.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address", "client.address.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"city", "client.city.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"homeState", "client.homeState.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"zip", "client.ZIP.required");
       
       Clients clients = (Clients) target;
       if (clients.getFirstName().length() > 60){
           errors.rejectValue("firstName", "client.firstName.length");
       }
       if(!clients.getFirstName().matches("/^[a-z ,.'-]+$/i")){
           errors.rejectValue("firstName","client.firstName.pattern");
       }
       if (clients.getLastName().length() > 60){
           errors.rejectValue("lastName", "client.lastName.length");
       }
       if(!clients.getLastName().matches("/^[a-z ,.'-]+$/i")){
           errors.rejectValue("lastName","client.lastName.pattern");
       }
       if (clients.getUserStatus().length() > 120){
           errors.rejectValue("userStatus", "client.status.length");
       }
       if(!clients.getUserStatus().matches("/^[a-z ,.'-]+$/i")){
           errors.rejectValue("userStatus","client.status.pattern");
       }
       if (clients.getAddress().length() > 120){
           errors.rejectValue("address", "client.address.length");
       }
       if(!clients.getAddress().matches("\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?")){
           errors.rejectValue("address","client.address.pattern");
       }
       if (clients.getCity().length() > 60){
           errors.rejectValue("city", "client.city.length");
       }
       if(!clients.getCity().matches("(?:[A-Z][a-z.-]+[ ]?)+")){
           errors.rejectValue("city","client.city.pattern");
       }
       if (clients.getHomeState().length() > 2){
           errors.rejectValue("homeState", "client.homeState.length");
       }
       if(!clients.getHomeState().matches("AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT\n" +
"|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY")){
           errors.rejectValue("homeState","client.homeState.pattern");
       }
       if (clients.getPhone().length() > 12){
           errors.rejectValue("phone", "client.phone.length");
       }
       if(!clients.getPhone().matches("(^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")){
           errors.rejectValue("phone","client.phone.pattern");
       }
       if (clients.getEmail().length() > 120){
           errors.rejectValue("email", "client.email.length");
       }
    }
}
