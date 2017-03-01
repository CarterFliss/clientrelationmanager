/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
/**
 *
 * @author Carter
 * Clients POJO
 */
public class Clients implements Serializable {
    //initializing class-wide variables
    private int clientid;
    private String firstName;
    private String lastName;
    private String userStatus;
    private String address;
    private String city;
    private String homeState;
    private int zip;
    private String phone;
    private String email;
    
    //getters and setters
    public int getClientid() {
        return clientid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getHomeState() {
        return homeState;
    }

    public int getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHomeState(String homeState) {
        this.homeState = homeState;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //writes variables to String for testing purposes
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Client ID: " + this.clientid);
        buffer.append("Client Name: " + firstName + " " + lastName);        
        buffer.append("User Status: " + userStatus);
        buffer.append("Address: " + address + ", "+city+", "+homeState+", "+zip);
        buffer.append("Phone: " + phone);
        buffer.append("Email: " + email);
        return buffer.toString();
    }
    
}
