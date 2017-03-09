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
import objects.EventLog;
import repository.EventLogDAO;
import repository.ClientsDAO;
import repository.UsersDAO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import javax.validation.Valid;
import validation.EventLogValidator;
/**
 *
 * @author Carter
 * Event Log Controller
 */
@Controller
public class EventLogController {
    //wiring DAOs and Validators to controller
    @Autowired
    EventLogDAO dao;
    
    @Autowired
    ClientsDAO cdao;
    
    @Autowired
    UsersDAO udao;
    
    @Autowired
    EventLogValidator eventLogValidator;
    
    private static final Logger logger = Logger.getLogger(EventLogController.class.getName());
    //provides list of all Event Logs
    @RequestMapping("/eventlog/vieweventlog")
    public ModelAndView showEventLog(HttpServletRequest request){
        //List<EventLog> eventLog = dao.getEventsList();
        //return new ModelAndView("vieweventlog","eventlog",eventLog);
        return this.showEventLogPager(1,request);
    }
    //adds Event Log to database
    @RequestMapping(value="/eventlog/addevent",method=RequestMethod.GET)
    public ModelAndView addEvent(){
        EventLog el = new EventLog();
        el.setClients(dao.getClientsMap());
        el.setUsers(dao.getUsersMap());
        return new ModelAndView("addevent","eventlog",el);
    }
    //saves new added Event Logs to database
    @RequestMapping(value = "/eventlog/save", method = RequestMethod.POST)
    public ModelAndView save (@ModelAttribute("eventlog") @Valid EventLog el, BindingResult result,HttpServletRequest request){
        //returns error message if editing page fails
        if(result.hasErrors()){
            el.setClients(dao.getClientsMap());
            el.setUsers(dao.getUsersMap());
            logger.info(result.getFieldErrors().toString());
            return new ModelAndView("addevent","eventlog",el);
        }
        
        int x = dao.addEvent(el);
        //returns either a successful message or failure message
        Messages msg = null;
        if (x == 1){
            msg = new Messages(Messages.Level.SUCCESS,"Event successfullly added.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error adding event to database.");
        }
        //displays appropriate message and redirects to general Event Logs list
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/eventlog/vieweventlog");
    }
    //applies pagination to jsp page
    @RequestMapping("/eventlog/vieweventlog/{pageid}")
    public ModelAndView showEventLogPager (@PathVariable int pageid,HttpServletRequest request){
        int total = 25;
        int start = 1;
        //displays page if user isn't on first page
        if (pageid != 1){
            start = (pageid - 1) + total + 1;
        }
        //totals up results of pagination method in DAO to provide page numbers
        List<EventLog> eventLogs = dao.getEventsByPage(start, total);
        
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("eventlog",eventLogs);
        
        int count = dao.getEventsCount();
        context.put("pages",Math.ceil((float)count/(float)total));
        context.put("page",pageid);
        
        Messages msg = (Messages)request.getSession().getAttribute("message");
        if (msg != null){
            context.put("message",msg);
            request.getSession().removeAttribute("message");
        }
        logger.info(eventLogs.toString());
        return new ModelAndView("vieweventlog",context);
    }
    //gets Event Log from database for editing
    @RequestMapping(value="/eventlog/editevent/{id}")
    public ModelAndView edit(@PathVariable int id){
        EventLog eventlog = dao.getEventsById(id);
        eventlog.setUsers(dao.getUsersMap());
        eventlog.setClients(dao.getClientsMap());
        return new ModelAndView("editevent","eventlog",eventlog);
    }
    //saves edits to a Event Log
    @RequestMapping(value="/eventlog/editsave",method=RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("eventlog") @Valid EventLog eventlog, BindingResult result,HttpServletRequest request){
        //returns error message if editing page fails
        if(result.hasErrors()){
            return new ModelAndView("editevent","eventlog",eventlog);
        }
        
        int x = dao.updateEvent(eventlog);
        //returns either a successful message or failure message
        Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"Event Log successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing Event Log.");
            logger.info(result.getFieldErrors().toString());
        }
        //displays appropriate message and redirects to general Event Logs list
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/eventlog/vieweventlog");
    }
    //method deletes Event Log from database    
    @RequestMapping(value="/eventlog/removeevent/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id,HttpServletRequest request){
       int x = dao.deleteEvent(id);
       
       Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"Event successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing event.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/eventlog/vieweventlog");       
    }
    //binds validator to controller
    @InitBinder("eventlog")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(eventLogValidator);
    }

    public EventLogValidator getEventLogValidator() {
        return eventLogValidator;
    }

    public void setEventLogValidator(EventLogValidator eventLogValidator) {
        this.eventLogValidator = eventLogValidator;
    }
}
