/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.util.HashMap;
import objects.Clients;
import objects.EventLog;
import objects.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.EventLogDAO;
import repository.ClientsDAO;
import repository.UsersDAO;
/**
 *
 * @author Carter
 * Home Controller
 */
@Controller
public class HomeController {
    //wires Event Log DAO to display "recent history" on home page
    @Autowired
    EventLogDAO dao;
    
    @Autowired
    ClientsDAO adao;
    
    @Autowired
    UsersDAO bdao;
    //redirects from home.jsp to index.jsp; displays general Event Log list
    @RequestMapping("/")
    public ModelAndView viewhome(){
        List<EventLog> eventLog = dao.getEventsList();
        int eventCount = dao.getEventsCount();
        int usersCount = bdao.getUsersCount();
        int clientsCount = adao.getClientsCount();
        HashMap<String,Object> context = new HashMap<String,Object>();
        context.put("EventCount",eventCount);
        context.put("eventlog",eventLog);
        context.put("userscount",usersCount);
        context.put("clientscount",clientsCount);
        return new ModelAndView("index",context);
    }
}
