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
import objects.EventLog;
import repository.EventLogDAO;

/**
 *
 * @author Carter
 * Event Log REST Controller for API implementation
 */
@RestController
public class EventLogRESTController {
    //wires DAOs for access by API
    @Autowired
    EventLogDAO dao;
    //provides list of all available Events
    @RequestMapping(value="/api/eventlog/",method=RequestMethod.GET)
    public ResponseEntity<List<EventLog>> listEventLog(){
        List<EventLog> el = dao.getEventsList();
        //returns NO_CONTENT error if no Events are provided
        if(el.isEmpty()){
            return new ResponseEntity<List<EventLog>>(HttpStatus.NO_CONTENT);
        }
        //otherwise returns list of all Events
        return new ResponseEntity<List<EventLog>>(el,HttpStatus.OK);
    }
    //pulls Event info, based on specific EventID
    @RequestMapping(value="/api/eventlog/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventLog> getEventInfo(@PathVariable("id") int id){
        EventLog el = null;
        //returns a NOT_FOUND error if no Event is tied to specific EventID
        try{
            el = dao.getEventsById(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<EventLog>(HttpStatus.NOT_FOUND);
        }
        //otherwise returns specific Event's info
        return new ResponseEntity<EventLog>(el,HttpStatus.OK);
    }
    //for adding an Event to the database through REST API
    @RequestMapping(value="/api/eventlog/",method=RequestMethod.POST)
    public ResponseEntity<Void> addEvent(@RequestBody EventLog el,UriComponentsBuilder ucBuilder){
        dao.addEvent(el);
        //returns newly added Event info
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/eventlog/{id}").buildAndExpand(el.getEventid()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
    //method for updating Event through REST API
    @RequestMapping(value="/api/eventlog/{id}",method=RequestMethod.PUT)
    public ResponseEntity<EventLog> updateEvent(@PathVariable("id") int id,@RequestBody EventLog el){
      EventLog currentEL = null;
      //gets Event info.  returns NOT_FOUND error if no Event exists
      try{
         currentEL = dao.getEventsById(id);
      }catch(EmptyResultDataAccessException ex){
          return new ResponseEntity<EventLog>(HttpStatus.NOT_FOUND);
      }
      //updates Event info and returns the info
      currentEL.setClientFirstName(el.getClientFirstName());
      currentEL.setClientLastName(el.getClientLastName());
      currentEL.setUsername(el.getUsername());
      currentEL.setInteraction(el.getInteraction());
      currentEL.setDate(el.getDate());
      
      dao.updateEvent(currentEL);
      return new ResponseEntity<EventLog>(currentEL,HttpStatus.OK);
    }
    //method for deleting Event through REST API
    @RequestMapping(value="/api/eventlog/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<EventLog> deleteEvent(@PathVariable("id") int id){
        EventLog el = null;
        //gets Event info.  Returns NOT_FOUND error if no Event exists
        try{
            el = dao.getEventsById(id);
        }catch(EmptyResultDataAccessException ex){
          return new ResponseEntity<EventLog>(HttpStatus.NOT_FOUND);  
        }
        //otherwise returns NO_CONTENT status after deletion
        dao.deleteEvent(id);
        return new ResponseEntity<EventLog>(HttpStatus.NO_CONTENT);}
    
}
