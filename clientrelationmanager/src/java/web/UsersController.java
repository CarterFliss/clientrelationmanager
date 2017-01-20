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
/**
 *
 * @author Carter
 */
public class UsersController {
    @Autowired
    UsersDAO dao;
    
    private static final Logger logger = Logger.getLogger(UsersController.class.getName());
    
    @RequestMapping("/users/viewusers")
    public ModelAndView showusers(){
        return new ModelAndView("viewusers","command",new Users());
    }
    
    @RequestMapping(value = "/users/addusers", method = RequestMethod.POST)
    public ModelAndView save (@ModelAttribute("user") Users user, HttpServletRequest request){
        int x = dao.addUser(user);
        
        Messages msg = null;
        if (x == 1){
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
    public ModelAndView edit(@ModelAttribute("user") Users user, HttpServletRequest request){
        int x = dao.updateUser(user);
        
        Messages msg = null;
        if (x==1){
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
       
       Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"user successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing user.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/users/viewusers");       
    }
}
