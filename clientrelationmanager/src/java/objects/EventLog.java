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
 */
public class EventLog implements Serializable {
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

    public Users getUser() {
        return user;
    }

    public Map<Integer, String> getUsers() {
        return users;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUsers(Map<Integer, String> users) {
        this.users = users;
    }
    
    public void setClient(Clients client) {
        this.client = client;
    }

    public void setClients(Map<Integer, String> clients) {
        this.clients = clients;
    }

    public int getEventid() {
        return eventid;
    }

    public Clients getClient() {
        return client;
    }

    public Map<Integer, String> getClients() {
        return clients;
    }

    public int getClientid() {
        return clientid;
    }

    public int getUserid() {
        return userid;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getUsername() {
        return username;
    }

    public String getInteraction() {
        return interaction;
    }

    public String getDate() {
        return date;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Event ID: " + this.eventid);
        buffer.append("Client ID: " + this.clientid);
        buffer.append("Client Name: " + clientFirstName+" "+clientLastName);
        buffer.append("User ID: " + this.userid);
        buffer.append("Username: " + this.username);
        buffer.append("Event Description: " + interaction);
        buffer.append("Date of Event: " + date);
        return buffer.toString();
    }
}
