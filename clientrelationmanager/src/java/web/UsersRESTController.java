/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import objects.Users;
import repository.UsersDAO;
import objects.EventLog;
import repository.EventLogDAO;
/**
 *
 * @author Carter
 */
@RestController
public class UsersRESTController {
    @Autowired
    UsersDAO dao;
    
    @Autowired
    EventLogDAO adao;
    
    @RequestMapping(value="/api/users/",method=RequestMethod.GET)
    public ResponseEntity<List<Users>> listUsers(){
        List<Users> users = dao.getUsersList();
        
        if(users.isEmpty()){
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/users/userinfo/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUserInfo(@PathVariable("id") int id){
        Users user = null;
                
        try{
            user = dao.getUserById(id);
            }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Users>(user,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/users/userevents/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventLog>> getUserEvents(@PathVariable("id") int id){
        List<EventLog> userEL = null;
        
        try{
            userEL = adao.getEventsByUserID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<List<EventLog>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<EventLog>>(userEL,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/users/",method=RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody Users user, UriComponentsBuilder ucBuilder){
        dao.addUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/users/userinfo/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/api/users/userinfo/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Users> updateUser(@PathVariable("id") int id,@RequestBody Users user){
        Users currentUser = null;
        
        try{
            currentUser = dao.getUserById(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setUserStatus(user.getUserStatus());
        currentUser.setUserrole(user.getUserrole());
        
        
        dao.updateUser(currentUser);
        return new ResponseEntity<Users>(currentUser,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/users/userinfo/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id") int id){
        Users user = null;
        try{
            user = dao.getUserById(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        dao.deleteUser(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
}
