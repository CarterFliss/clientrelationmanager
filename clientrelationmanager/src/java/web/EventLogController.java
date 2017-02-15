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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import javax.validation.Valid;
import validation.EventLogValidator;
/**
 *
 * @author Carter
 */
@Controller
public class EventLogController {
    @Autowired
    EventLogDAO dao;
    
    @Autowired
    EventLogValidator eventLogValidator;
    
    private static final Logger logger = Logger.getLogger(EventLogController.class.getName());
    
    @RequestMapping("/eventlog/vieweventlog")
    public ModelAndView showEventLog(){
        List<EventLog> eventLog = dao.getEventsList();
        return new ModelAndView("vieweventlog","eventlog",eventLog);
    }
    @RequestMapping(value="/eventlog/addevent",method=RequestMethod.GET)
    public ModelAndView addEvent(){
        return new ModelAndView("addevent","eventlog",new EventLog());
    }
    @RequestMapping(value = "/eventlog/save", method = RequestMethod.POST)
    public ModelAndView save (@ModelAttribute("eventlog") @Valid EventLog eventlog, BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            logger.info(result.getFieldErrors().toString());
            return new ModelAndView("vieweventlog","eventlog",new EventLog());
        }
        int x = dao.addEvent(eventlog);
        
        Messages msg = null;
        if (x == 1){
            msg = new Messages(Messages.Level.SUCCESS,"Event successfullly added.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error adding event to database.");
        }
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/eventlog/vieweventlog");
    }
    
    @RequestMapping("/eventlog/vieweventlog/{pageid}")
    public ModelAndView showEventLogPager (@PathVariable int pageid,HttpServletRequest request){
        int total = 25;
        int start = 1;
        
        if (pageid != 1){
            start = (pageid - 1) + total + 1;
        }
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
        
        return new ModelAndView("vieweventlog",context);
    }
    
    @RequestMapping(value="/eventlog/editevent/{id}")
    public ModelAndView edit(@PathVariable int id){
        EventLog eventlog = dao.getEventsById(id);
        return new ModelAndView("editevent","eventlog",eventlog);
    }
    @RequestMapping(value="/eventlog/editsave",method=RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("eventlog") @Valid EventLog eventlog, BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            return new ModelAndView("vieweventlog","eventlog",new EventLog());
        }
        int x = dao.updateEvent(eventlog);
        
        Messages msg = null;
        if (x==1){
            msg = new Messages(Messages.Level.SUCCESS,"Event Log successfullly edited.");
        } else{
            msg = new Messages(Messages.Level.ERROR,"Error editing Event Log.");
        }
        
        request.getSession().setAttribute("message",msg);
        return new ModelAndView("redirect:/eventlog/vieweventlog");
    }
    
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
