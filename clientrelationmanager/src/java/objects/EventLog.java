/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Carter
 * Event Log/Interactions POJO
 */
public class EventLog implements Serializable {
    //initializing class-wide variables
    private int eventid;
    private int clientid;
    private int userid;
    private String clientFirstName;
    private String clientLastName;
    private String username;
    private String interaction;
    private String date;
    
    private Clients client;
    private Map<Integer,String> clients;
    private Users user;
    private Map<Integer,String> users;
    
    //getters and setters

    /**
     * Gets User object
     * @return
     */
    public Users getUser() {
        return user;
    }

    /**
     * Gets a map of Users objects
     * @return
     */
    public Map<Integer, String> getUsers() {
        return users;
    }

    /**
     * Sets a Users object
     * @param user
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * Sets a map of Users objects
     * @param users
     */
    public void setUsers(Map<Integer, String> users) {
        this.users = users;
    }
    
    /**
     * Sets a Clients object
     * @param client
     */
    public void setClient(Clients client) {
        this.client = client;
    }

    /**
     * Sets a map of Clients objects
     * @param clients
     */
    public void setClients(Map<Integer, String> clients) {
        this.clients = clients;
    }

    /**
     * Gets an Event ID
     * @return
     */
    public int getEventid() {
        return eventid;
    }

    /**
     * Gets a Clients object
     * @return
     */
    public Clients getClient() {
        return client;
    }

    /**
     * Gets a map of Clients objects
     * @return
     */
    public Map<Integer, String> getClients() {
        return clients;
    }

    /**
     * Gets the Client ID
     * @return
     */
    public int getClientid() {
        return clientid;
    }

    /**
     * Gets the User ID
     * @return
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Gets the Client's first name
     * @return
     */
    public String getClientFirstName() {
        return clientFirstName;
    }

    /**
     * Gets the Client's last name
     * @return
     */
    public String getClientLastName() {
        return clientLastName;
    }

    /**
     * Gets the Username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the Interaction information
     * @return
     */
    public String getInteraction() {
        return interaction;
    }

    /**
     * Gets the date of an event
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the Event ID
     * @param eventid
     */
    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    /**
     * Sets the Client ID
     * @param clientid
     */
    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    /**
     * Sets the User ID
     * @param userid
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * Sets the Client's first name
     * @param clientFirstName
     */
    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    /**
     * Sets the Client's last name
     * @param clientLastName
     */
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    /**
     * Sets the Username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the interaction info
     * @param interaction
     */
    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    /**
     * Set's the event's date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    //prints variables to String for testing purposes
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Event ID: " + this.eventid);
        buffer.append("Client ID: " + this.clientid);
        buffer.append("Client Name: " + clientFirstName+" "+clientLastName);
        buffer.append("Client: "+client);
        buffer.append("User ID: " + this.userid);
        buffer.append("Username: " + this.username);
        buffer.append("User: "+user);
        buffer.append("Event Description: " + interaction);
        buffer.append("Date of Event: " + date);
        return buffer.toString();
    }
}
