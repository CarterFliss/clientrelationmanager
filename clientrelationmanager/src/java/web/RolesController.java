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
import objects.Roles;
import repository.RolesDAO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import javax.validation.Valid;
import validation.RolesValidator;
/**
 *
 * @author Carter
 */
@Controller
public class RolesController {
    @Autowired
    RolesDAO dao;
    
    @Autowired
    RolesValidator rolesValidator;
    
    private static final Logger logger = Logger.getLogger(RolesController.class.getName());
    
    @RequestMapping("/roles/viewroles")
    public ModelAndView showroles(){
        return new ModelAndView("viewroles","roles",new Roles());
    }
    
    @RequestMapping(value = "/roles/addroles", method = RequestMethod.POST)
    public ModelAndView save (@ModelAttribute("roles") @Valid Roles roles, BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            return new ModelAndView("viewroles","roles",new Roles());
        }
        int x = dao.addRole(roles);
        
        Messages msg = null;
        if (x == 1){
            msg = new Messages(Messages.Level.SUCCESS,"Role successfullly added.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error adding role to database.");
        }
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/roles/viewroles");
    }
    
    @RequestMapping("/roles/viewroles/{pageid}")
    public ModelAndView showRolesPager (@PathVariable int pageid,HttpServletRequest request){
        int total = 25;
        int start = 1;
        
        if (pageid != 1){
            start = (pageid - 1) + total + 1;
        }
        List<Roles> roles = dao.getRolesByPage(start, total);
        
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("roles",roles);
        
        int count = dao.getRolesCount();
        context.put("pages",Math.ceil((float)count/(float)total));
        context.put("page",pageid);
        
        Messages msg = (Messages)request.getSession().getAttribute("message");
        if (msg != null){
            context.put("message",msg);
            request.getSession().removeAttribute("message");
        }
        
        return new ModelAndView("viewroles",context);
    }
    
    @RequestMapping(value="/roles/editrole/{id}")
    public ModelAndView edit(@ModelAttribute("roles") @Valid Roles roles, BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            return new ModelAndView("viewroles","roles",new Roles());
        }
        int x = dao.updateRole(roles);
        
        Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"Role successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing role.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/roles/viewroles");
    }
    
    @RequestMapping(value="/roles/removerole/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id,HttpServletRequest request){
       int x = dao.deleteRole(id);
       
       Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"role successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing role.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/roles/viewroles");       
    }
    @InitBinder("roles")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(rolesValidator);
    }

    public RolesValidator getRolesValidator() {
        return rolesValidator;
    }

    public void setRolesValidator(RolesValidator rolesValidator) {
        this.rolesValidator = rolesValidator;
    }
}
