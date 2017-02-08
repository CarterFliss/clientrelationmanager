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
import objects.Roles;
import repository.RolesDAO;
import validation.RolesValidator;
/**
 *
 * @author Carter
 */
@Controller
public class UsersController {
    @Autowired
    UsersDAO dao;
    
    @Autowired
    UsersValidator usersValidator;
    
    @Autowired
    EventLogDAO adao;
    
    @Autowired
    RolesDAO bdao;
    
    @Autowired
    RolesValidator rolesValidator;
    
    private static final Logger logger = Logger.getLogger(UsersController.class.getName());
    
    @RequestMapping("/users/viewusers")
    public ModelAndView showusers(){
        return new ModelAndView("viewusers","users",new Users());
    }
    
    @RequestMapping(value="/users/viewusers/{id}",method=RequestMethod.GET)
    public ModelAndView showUsersByUserID(@PathVariable int id,HttpServletRequest request){
        List<Users> x = dao.getUsersById(id);
        List<EventLog> y = adao.getEventsByUserID(id);
        List<Roles> z = bdao.getRolesById(id);
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("User",x);
        context.put("Role",z);
        context.put("Events",y);
        return new ModelAndView("viewusers","users",context);
    }
    
    @RequestMapping(value = "/users/addusers", method = RequestMethod.POST)
    public ModelAndView save (@ModelAttribute("users") @Valid Users users, BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            return new ModelAndView("viewusers","users",new Users());
        }
        int x = dao.addUser(users);
        Roles b = new Roles();
        b.setUser(users);
        int y = bdao.addRole(b);
        
        Messages msg = null;
        if (x == 1 && y == 1){
            msg = new Messages(Messages.Level.SUCCESS,"User successfullly added.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error adding user to database.");
        }
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/users/viewusers");
    }
    
    @RequestMapping("/users/viewusers/{pageid}")
    public ModelAndView showusersPager (@PathVariable int pageid,HttpServletRequest request){
        int total = 25;
        int start = 1;
        
        if (pageid != 1){
            start = (pageid - 1) + total + 1;
        }
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
    
    @RequestMapping(value="/users/edituser/{id}")
    public ModelAndView edit(@ModelAttribute("users") @Valid Users users, BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            return new ModelAndView("viewusers","users",new Users());
        }
        int x = dao.updateUser(users);
        Roles b = new Roles();
        b.setUser(users);
        int y = bdao.updateRole(b);
        
        Messages msg = null;
        if (x==1 && y == 1){
            msg = new Messages(Messages.Level.SUCCESS,"user successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing user.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/users/viewusers");
    }
    
    @RequestMapping(value="/users/removeuser/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id,HttpServletRequest request){
       int x = dao.deleteUser(id);
       int y = bdao.deleteRole(id);
       
       
       Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"user successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing user.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/users/viewusers");       
    }
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
