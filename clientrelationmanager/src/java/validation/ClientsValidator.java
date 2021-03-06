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
//regex import kept for validation purposes
import java.util.regex.Pattern;

/**
 *
 * @author Carter
 * Clients Validator, for validating requirements of Client form input fields
 */
@Component
public class ClientsValidator implements Validator {
    private static Logger logger = Logger.getLogger(ClientsValidator.class.getName());
    //method connects form inputs to POJO variables

    /**
     * Allows linkage of validator to ClientsController and Clients POJO
     *  for validation
     * @param classy
     * @return
     */
    @Override
    public boolean supports(Class<?> classy){
        return Clients.class.isAssignableFrom(classy);
    }
    //validates input fields, based on requirements set in messages.properties

    /**
     * Validates various fields for a Clients object
     * @param target
     * @param errors
     */
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
       if(clients.getFirstName().matches("^[A-Z]'?[- a-zA-Z]([a-zA-Z])*$")){
       } else {
           errors.rejectValue("firstName","client.firstName.pattern");
        }
       if (clients.getLastName().length() > 60){
           errors.rejectValue("lastName", "client.lastName.length");
       }
       if(clients.getLastName().matches("^[A-Z]'?[- a-zA-Z]([a-zA-Z])*$")){
       } else {
           errors.rejectValue("lastName","client.lastName.pattern");
        }
       if (clients.getUserStatus().length() > 120){
           errors.rejectValue("userStatus", "client.status.length");
       }
       if(clients.getUserStatus().matches("^[A-Z]'?[- a-zA-Z]([a-zA-Z])*$")){
       } else {
           errors.rejectValue("userStatus","client.status.pattern");
        }
       if (clients.getAddress().length() > 120){
           errors.rejectValue("address", "client.address.length");
       }
       if (clients.getCity().length() > 60){
           errors.rejectValue("city", "client.city.length");
       }
       if(clients.getCity().matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")){
       } else {
           errors.rejectValue("city","client.city.pattern");
        }
       if (clients.getHomeState().length() > 2){
           errors.rejectValue("homeState", "client.homeState.length");
       }
       if(clients.getHomeState().matches("^(?-i:A[LKSZRAEP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$")){
       } else {
           errors.rejectValue("homeState","client.homeState.pattern");
        }
       if (clients.getPhone().length() > 12){
           errors.rejectValue("phone", "client.phone.length");
       }
       if(clients.getPhone().matches("^\\d{10}|^(\\(\\d{3}\\)\\s?)?\\d{3}-\\d{4}$|^\\d{3}([.-])\\d{3}\\2\\d{4}$")){
       } else {
           errors.rejectValue("phone","client.phone.pattern");
        }
       if (clients.getEmail().length() > 120){
           errors.rejectValue("email", "client.email.length");
       }
       if(!clients.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
           errors.rejectValue("email", "client.email.pattern");
       }
    }
}
