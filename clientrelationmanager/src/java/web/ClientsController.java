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
import objects.Clients;
import repository.ClientsDAO;
/**
 *
 * @author Carter
 */
@Controller
public class ClientsController {
    @Autowired
    ClientsDAO dao;
    
    private static final Logger logger = Logger.getLogger(ClientsController.class.getName());
    
    @RequestMapping("/clients/viewclients")
    public ModelAndView showClients(){
        return new ModelAndView("viewclients","command",new Clients());
    }
    
    @RequestMapping(value = "/clients/addclient", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("client") Clients client, HttpServletRequest request){
        int x = dao.addClient(client);
        
        Messages msg = null;
        if (x == 1){
            msg = new Messages(Messages.Level.SUCCESS,"Client successfullly added.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error adding client to database.");
        }
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/clients/viewclients");
    }
    
    @RequestMapping("/clients/viewclients/{pageid}")
    public ModelAndView showClientsPager (@PathVariable int pageid,HttpServletRequest request){
        int total = 25;
        int start = 1;
        
        if (pageid != 1){
            start = (pageid - 1) + total + 1;
        }
        List<Clients> clients = dao.getClientsByPage(start, total);
        
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("clients",clients);
        
        int count = dao.getClientsCount();
        context.put("pages",Math.ceil((float)count/(float)total));
        context.put("page",pageid);
        
        Messages msg = (Messages)request.getSession().getAttribute("message");
        if (msg != null){
            context.put("message",msg);
            request.getSession().removeAttribute("message");
        }
        
        return new ModelAndView("viewclients",context);
    }
    
    @RequestMapping(value="/clients/editclient/{id}")
    public ModelAndView edit(@ModelAttribute("client") Clients client, HttpServletRequest request){
        int x = dao.updateClient(client);
        
        Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"Client successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing client.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/clients/viewclients");
    }
    
    @RequestMapping(value="/clients/removeclient/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id,HttpServletRequest request){
       int x = dao.deleteClient(id);
       
       Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"Client successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing client.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/clients/viewclients");       
    }
}
