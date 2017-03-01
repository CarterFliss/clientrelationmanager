/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import objects.Clients;
import objects.EventLog;
import objects.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.EventLogDAO;
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
    //redirects from home.jsp to index.jsp; displays general Event Log list
    @RequestMapping("/")
    public ModelAndView viewhome(){
        List<EventLog> eventLog = dao.getEventsList();
        return new ModelAndView("index","eventlog",eventLog);
    }
}
