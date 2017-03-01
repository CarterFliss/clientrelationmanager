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
 * Users REST Controller for API implementation
 */
@RestController
public class UsersRESTController {
    //wires DAOs for access by API
    @Autowired
    UsersDAO dao;
    
    @Autowired
    EventLogDAO adao;
    //provides list of all available Users
    @RequestMapping(value="/api/users/",method=RequestMethod.GET)
    public ResponseEntity<List<Users>> listUsers(){
        List<Users> users = dao.getUsersList();
        //returns NO_CONTENT error if no Users are provided
        if(users.isEmpty()){
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
        }
        //otherwise returns list of all Users
        return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
    }
    //pulls Client info, based on specific UserID
    @RequestMapping(value="/api/users/userinfo/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUserInfo(@PathVariable("id") int id){
        Users user = null;
        //returns a NOT_FOUND error if no User is tied to specific UserID        
        try{
            user = dao.getUserById(id);
            }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        //otherwise returns specific User's info
        return new ResponseEntity<Users>(user,HttpStatus.OK);
    }
    //pulls Event Log history, based on specific UserID
    @RequestMapping(value="/api/users/userevents/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventLog>> getUserEvents(@PathVariable("id") int id){
        List<EventLog> userEL = null;
        //returns a NOT_FOUND error if no Event Log history for User exists
        try{
            userEL = adao.getEventsByUserID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<List<EventLog>>(HttpStatus.NOT_FOUND);
        }
        //otherwise returns Event Log history for that User
        return new ResponseEntity<List<EventLog>>(userEL,HttpStatus.OK);
    }
    //for adding a User to the database through REST API
    @RequestMapping(value="/api/users/",method=RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody Users user, UriComponentsBuilder ucBuilder){
        dao.addUser(user);
        //returns newly added User info
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/users/userinfo/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
    //method for updating User through REST API
    @RequestMapping(value="/api/users/userinfo/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Users> updateUser(@PathVariable("id") int id,@RequestBody Users user){
        Users currentUser = null;
        //gets User info.  returns NOT_FOUND error if no User exists
        try{
            currentUser = dao.getUserById(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        //updates User info and returns the info
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setUserStatus(user.getUserStatus());
        currentUser.setUserrole(user.getUserrole());
        
        
        dao.updateUser(currentUser);
        return new ResponseEntity<Users>(currentUser,HttpStatus.OK);
    }
    //method for deleting User through REST API
    @RequestMapping(value="/api/users/userinfo/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id") int id){
        Users user = null;
        //gets User info.  Returns NOT_FOUND error if no User exists
        try{
            user = dao.getUserById(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        //otherwise returns NO_CONTENT status after deletion
        dao.deleteUser(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
}
