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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Carter
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView viewhome(){
        return new ModelAndView("index");
    }
}
