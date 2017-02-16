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
import javax.validation.Valid;
import objects.Messages;
import objects.Clients;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import repository.ClientsDAO;
import validation.ClientsValidator;
import repository.EventLogDAO;
import objects.EventLog;
/**
 *
 * @author Carter
 */
@Controller
public class ClientsController {
    @Autowired
    ClientsDAO dao;
    
    @Autowired
    ClientsValidator clientsValidator;
    
    @Autowired
    EventLogDAO adao;
    
    private static final Logger logger = Logger.getLogger(ClientsController.class.getName());
    
    @RequestMapping("/clients/viewclients")
    public ModelAndView showClients(){
        List<Clients> client = dao.getClientsList();
        return new ModelAndView("viewclients","clients",client);
    }
    
    @RequestMapping(value="/clients/viewclient/{id}",method = RequestMethod.GET)
    public ModelAndView showClientsByClientID(@PathVariable int id,HttpServletRequest request){
       List<Clients> y = dao.getClientsById(id);
        List<EventLog> x = adao.getEventsByClientID(id);
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("Client",y);
        context.put("Events",x);
       return new ModelAndView("viewclient",context);
    }
    
    @RequestMapping(value="/clients/addclient", method = RequestMethod.GET)
    public ModelAndView addClient(){
        return new ModelAndView("addclient","clients",new Clients());
    }
    
    @RequestMapping(value = "/clients/editclient/{id}")
    public ModelAndView save(@PathVariable int id){
        Clients clients = dao.getClientByID(id);
        return new ModelAndView("editclient","clients",clients);
    }
    
    @RequestMapping(value="/clients/editsave",method=RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("clients") @Valid Clients clients, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            logger.info(result.getFieldErrors().toString());
            return new ModelAndView("viewclients","clients",clients);
        }
        int x = dao.updateClient(clients);
        
        Messages msg = null;
        if (x == 1){
            msg = new Messages(Messages.Level.SUCCESS,"Client successfullly edited.  Please add client creation to Event Log.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing client.");
            logger.info(result.getFieldErrors().toString());
        }
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/clients/viewclients");
    }
    @RequestMapping(value="/clients/addsave",method=RequestMethod.POST)
    public ModelAndView addSave(@ModelAttribute("clients") @Valid Clients clients, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            logger.info(result.getFieldErrors().toString());
            return new ModelAndView("addclient","clients",new Clients());
        }
        int x = dao.addClient(clients);
        
        Messages msg = null;
        if (x == 1){
            msg = new Messages(Messages.Level.SUCCESS,"Client successfullly added.  Please add client creation to Event Log.");
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
    
        
    @RequestMapping(value="/clients/removeclient/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id,HttpServletRequest request){
       int x = dao.deleteClient(id);
       
       Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"Client successfullly deleted.  Please add client deletion to Event Log.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing client.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/clients/viewclients");       
    }
    
    @InitBinder("clients")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(clientsValidator);
    }

    public ClientsValidator getClientsValidator() {
        return clientsValidator;
    }

    public void setClientsValidator(ClientsValidator clientsValidator) {
        this.clientsValidator = clientsValidator;
    }
    
}
