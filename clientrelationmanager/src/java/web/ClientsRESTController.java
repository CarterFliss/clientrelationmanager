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
import objects.Clients;
import objects.EventLog;
import repository.ClientsDAO;
import repository.EventLogDAO;
/**
 *
 * @author Carter
 */
@RestController
public class ClientsRESTController {
    
    @Autowired
    ClientsDAO dao;
    
    @Autowired
    EventLogDAO adao;
    
    @RequestMapping(value="/api/clients/",method=RequestMethod.GET)
    public ResponseEntity<List<Clients>> listClients(){
        List<Clients> clients = dao.getClientsList();
        
        if(clients.isEmpty()){
            return new ResponseEntity<List<Clients>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Clients>>(clients,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/clients/clientinfo/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clients> getClientInfo(@PathVariable("id") int id){
        Clients client = null;
                
        try{
            client = dao.getClientByID(id);
            }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Clients>(client,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/clients/clientevents/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventLog>> getClientEvents(@PathVariable("id") int id){
        List<EventLog> clientEL = null;
        
        try{
            clientEL = adao.getEventsByClientID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<List<EventLog>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<EventLog>>(clientEL,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/clients/",method=RequestMethod.POST)
    public ResponseEntity<Void> addClient(@RequestBody Clients client, UriComponentsBuilder ucBuilder){
        dao.addClient(client);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/clients/clientinfo/{id}").buildAndExpand(client.getClientid()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/api/clients/clientinfo/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Clients> updateClient(@PathVariable("id") int id,@RequestBody Clients client){
        Clients currentClient = null;
        
        try{
            currentClient = dao.getClientByID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setUserStatus(client.getUserStatus());
        currentClient.setAddress(client.getAddress());
        currentClient.setCity(client.getCity());
        currentClient.setHomeState(client.getHomeState());
        currentClient.setZip(client.getZip());
        currentClient.setPhone(client.getPhone());
        currentClient.setEmail(client.getEmail());
        
        dao.updateClient(currentClient);
        return new ResponseEntity<Clients>(currentClient,HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/clients/clientinfo/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Clients> deleteClient(@PathVariable("id") int id){
        Clients client = null;
        try{
            client = dao.getClientByID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        dao.deleteClient(id);
        return new ResponseEntity<Clients>(HttpStatus.NO_CONTENT);
    }
}
