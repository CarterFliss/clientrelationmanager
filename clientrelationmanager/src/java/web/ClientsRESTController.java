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
 * Clients REST Controller for API implementation
 */
@RestController
public class ClientsRESTController {
    //wires DAOs for access by API
    @Autowired
    ClientsDAO dao;
    
    @Autowired
    EventLogDAO adao;
    //provides list of all available Clients

    /**
     * Lists all clients through a REST API
     * @return
     */
    @RequestMapping(value="/api/clients/",method=RequestMethod.GET)
    public ResponseEntity<List<Clients>> listClients(){
        List<Clients> clients = dao.getClientsList();
        //returns NO_CONTENT error if no Clients are provided
        if(clients.isEmpty()){
            return new ResponseEntity<List<Clients>>(HttpStatus.NO_CONTENT);
        }
        //otherwise returns list of all Clients
        return new ResponseEntity<List<Clients>>(clients,HttpStatus.OK);
    }
    //pulls Client info, based on specific ClientID

    /**
     * Gets a specific client's info based on a Client ID
     * @param id
     * @return
     */
    @RequestMapping(value="/api/clients/clientinfo/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clients> getClientInfo(@PathVariable("id") int id){
        Clients client = null;
        //returns a NOT_FOUND error if no Client is tied to specific ClientID        
        try{
            client = dao.getClientByID(id);
            }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        //otherwise returns specific Client's info
        return new ResponseEntity<Clients>(client,HttpStatus.OK);
    }
    //pulls Event Log history, based on specific ClientID

    /**
     * Gets a list of events tied to a client's Client ID
     * @param id
     * @return
     */
    @RequestMapping(value="/api/clients/clientevents/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventLog>> getClientEvents(@PathVariable("id") int id){
        List<EventLog> clientEL = null;
        //returns a NOT_FOUND error if no Event Log history for Client exists
        try{
            clientEL = adao.getEventsByClientID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<List<EventLog>>(HttpStatus.NOT_FOUND);
        }
        //otherwise returns Event Log history for that Client
        return new ResponseEntity<List<EventLog>>(clientEL,HttpStatus.OK);
    }
    //for adding a Client to the database through REST API

    /**
     * Adds a client to the database through a REST API
     * @param client
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value="/api/clients/",method=RequestMethod.POST)
    public ResponseEntity<Void> addClient(@RequestBody Clients client, UriComponentsBuilder ucBuilder){
        dao.addClient(client);
        //returns newly added Client info
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/clients/clientinfo/{id}").buildAndExpand(client.getClientid()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
    //method for updating Client through REST API

    /**
     * Updates a client's info through a REST API
     * @param id
     * @param client
     * @return
     */
    @RequestMapping(value="/api/clients/clientinfo/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Clients> updateClient(@PathVariable("id") int id,@RequestBody Clients client){
        Clients currentClient = null;
        //gets Client info.  returns NOT_FOUND error if no Client exists
        try{
            currentClient = dao.getClientByID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        //updates Client info and returns the info
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
    //method for deleting Client through REST API

    /**
     * Deletes a client from the database through a REST API
     * @param id
     * @return
     */
    @RequestMapping(value="/api/clients/clientinfo/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Clients> deleteClient(@PathVariable("id") int id){
        Clients client = null;
        //gets Client info.  Returns NOT_FOUND error if no Client exists
        try{
            client = dao.getClientByID(id);
        }catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        //otherwise returns NO_CONTENT status after deletion
        dao.deleteClient(id);
        return new ResponseEntity<Clients>(HttpStatus.NO_CONTENT);
    }
}
