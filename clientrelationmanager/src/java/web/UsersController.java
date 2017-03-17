/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import objects.Messages;
import objects.Users;
import repository.UsersDAO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import javax.validation.Valid;
import validation.UsersValidator;
import objects.EventLog;
import repository.EventLogDAO;

/**
 *
 * @author Carter
 * Users Controller
 */
@Controller
public class UsersController {
    //wiring DAOs and Validators to controller
    @Autowired
    UsersDAO dao;
    
    @Autowired
    UsersValidator usersValidator;
    
    @Autowired
    EventLogDAO adao;
           
    private static final Logger logger = Logger.getLogger(UsersController.class.getName());
    //provides list of all Users
    @RequestMapping("/users/viewusers")
    public ModelAndView showusers(){
        List<Users> user = dao.getUsersList();
        return new ModelAndView("viewusers","users",user);
    }
    //allows detailed view of specific User, including Event Log in which current
    //User was a part
    @RequestMapping(value="/users/viewuser/{id}",method=RequestMethod.GET)
    public ModelAndView showUsersByUserID(@PathVariable int id,HttpServletRequest request){
        List<Users> x = dao.getUsersById(id);
        List<EventLog> y = adao.getEventsByUserID(id);
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("User",x);
        context.put("Events",y);
        return new ModelAndView("viewuser",context);
    }
    //adds User to database
    @RequestMapping(value="/users/adduser")
    public ModelAndView addUser(){
        return new ModelAndView("adduser","users",new Users());
    }
    //saves new added Users to database
    @RequestMapping(value="/users/addsave",method=RequestMethod.POST)
    public ModelAndView addUserSave (@ModelAttribute("users") @Valid Users users, BindingResult result,HttpServletRequest request){
        //returns error message if editing page fails
        if(result.hasErrors()){
            logger.info(result.getFieldErrors().toString());
            return new ModelAndView("adduser","users",users);
        }
        int x = dao.addUser(users);
        //returns either a successful message or failure message        
        Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"user successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing user.");
        }
        //displays appropriate message and redirects to general Users list
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/users/viewusers");
    }
    //applies pagination to jsp page
    @RequestMapping("/users/viewusers/{pageid}")
    public ModelAndView showusersPager (@PathVariable int pageid,HttpServletRequest request){
        int total = 25;
        int start = 1;
        //displays page if user isn't on first page
        if (pageid != 1){
            start = (pageid - 1) + total + 1;
        }
        //totals up results of pagination method in DAO to provide page numbers
        List<Users> users = dao.getUsersByPage(start, total);
        
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("users",users);
        
        int count = dao.getUsersCount();
        context.put("pages",Math.ceil((float)count/(float)total));
        context.put("page",pageid);
        
        Messages msg = (Messages)request.getSession().getAttribute("message");
        if (msg != null){
            context.put("message",msg);
            request.getSession().removeAttribute("message");
        }
        
        return new ModelAndView("viewusers",context);
    }
    //gets User from database for editing
    @RequestMapping(value="/users/edituser/{id}")
    public ModelAndView edit(@PathVariable int id){
       Users users = dao.getUserById(id);
       return new ModelAndView("edituser","users",users);
    }
    //saves edits to a User
    @RequestMapping(value="/users/editsave",method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("users") @Valid Users users, BindingResult result,HttpServletRequest request){
        //returns error message if editing page fails
        if(result.hasErrors()){
            logger.info(result.getFieldErrors().toString());
            return new ModelAndView("viewusers","users",users);
        }
        int x = dao.updateUser(users);
        //returns either a successful message or failure message        
        Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"user successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing user.");
            logger.info(result.getFieldErrors().toString());
        }
        //displays appropriate message and redirects to general Users list
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/users/viewusers");
    }
    //method deletes User from database    
    @RequestMapping(value="/users/removeuser/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id,HttpServletRequest request){
       int x = dao.deleteUser(id);
        //returns error message if editing page fails   
        Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"user successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing user.");
        }
        //returns either a successful message or failure message
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/users/viewusers");       
    }
    //binds validator to controller
    @InitBinder("users")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(usersValidator);
    }

    public UsersValidator getUsersValidator() {
        return usersValidator;
    }

    public void setUsersValidator(UsersValidator usersValidator) {
        this.usersValidator = usersValidator;
    }
}
