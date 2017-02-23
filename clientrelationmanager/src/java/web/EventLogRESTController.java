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
 */
@RestController
public class EventLogRESTController {
    @Autowired
    EventLogDAO dao;
    
    @RequestMapping(value="/api/eventlog/",method=RequestMethod.GET)
    public ResponseEntity<List<EventLog>> listEventLog(){
        List<EventLog> el = dao.getEventsList();
        
        if(el.isEmpty()){
            return new ResponseEntity<List<EventLog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<EventLog>>(el,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/eventlog/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventLog> getEventInfo(@PathVariable("id") int id){
        EventLog el = null;
        
        try{
            el = dao.getEventsById(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<EventLog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EventLog>(el,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/eventlog/",method=RequestMethod.POST)
    public ResponseEntity<Void> addEvent(@RequestBody EventLog el,UriComponentsBuilder ucBuilder){
        dao.addEvent(el);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/eventlog/{id}").buildAndExpand(el.getEventid()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/api/eventlog/{id}",method=RequestMethod.PUT)
    public ResponseEntity<EventLog> updateEvent(@PathVariable("id") int id,@RequestBody EventLog el){
      EventLog currentEL = null;
      
      try{
         currentEL = dao.getEventsById(id);
      }catch(EmptyResultDataAccessException ex){
          return new ResponseEntity<EventLog>(HttpStatus.NOT_FOUND);
      }
      currentEL.setClientFirstName(el.getClientFirstName());
      currentEL.setClientLastName(el.getClientLastName());
      currentEL.setUsername(el.getUsername());
      currentEL.setInteraction(el.getInteraction());
      currentEL.setDate(el.getDate());
      
      dao.updateEvent(currentEL);
      return new ResponseEntity<EventLog>(currentEL,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/eventlog/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<EventLog> deleteEvent(@PathVariable("id") int id){
        EventLog el = null;
        try{
            el = dao.getEventsById(id);
        }catch(EmptyResultDataAccessException ex){
          return new ResponseEntity<EventLog>(HttpStatus.NOT_FOUND);  
        }
        dao.deleteEvent(id);
        return new ResponseEntity<EventLog>(HttpStatus.NO_CONTENT);}
    
}
