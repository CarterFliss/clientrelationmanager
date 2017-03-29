/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Carter
 * Security Controller
 */
@Controller
public class SecurityController {
    //establishes login page

    /**
     * Presents error messages when logging into the app
     * @param error
     * @param logout
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error",required = false) String error,
                              @RequestParam(value = "logout",required = false) String logout){

        ModelAndView model = new ModelAndView();
        //returns message if username or password is incorrect
        if(error != null){
            model.addObject("error","Invalid username and password!");
        }
        //returns message if user logs out from web app
        if(logout != null){
            model.addObject("msg","You've been logged out successfully.");
        }
        
        model.setViewName("login");

        return model;
    }
    //for when a user attempts access to page where they aren't allowed, based
    //on User Role

    /**
     * Displays the 403 page when user attempts to access a page for which they
     *  don't have rights
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(){

        ModelAndView model = new ModelAndView();
        //authenticates User Role from applicationContext-Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if User Role is not in line w/ pre-determined access, User is sent to 
        //403 page.
        if(!(auth instanceof AnonymousAuthenticationToken)){
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;
    }
}
