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

    /**
     * Gets ClientID
     * @return
     */
    public int getClientid() {
        return clientid;
    }

    /**
     * Gets client's first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets client's last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets client's status (active, prospective, former)
     * @return
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * Gets client's address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets client's city
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets client's home state (ex. AR)
     * @return
     */
    public String getHomeState() {
        return homeState;
    }

    /**
     * Gets client's ZIP code
     * @return
     */
    public int getZip() {
        return zip;
    }

    /**
     * Gets client's phone number
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets client's email address
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets ClientID
     * @param clientid
     */
    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    /**
     * Sets client's first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets client's last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets client's status
     * @param userStatus
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * Sets client's address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets client's city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets client's home state
     * @param homeState
     */
    public void setHomeState(String homeState) {
        this.homeState = homeState;
    }

    /**
     * Sets client's ZIP code
     * @param zip
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Sets client's phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets client's email address
     * @param email
     */
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
