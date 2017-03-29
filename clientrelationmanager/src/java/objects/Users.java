/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Carter
 * Users POJO
 */
public class Users implements Serializable{
    //initializing getters and setters
    private int userId;
    private String username;
    private String password;
    private String userrole;
    private int userStatus;
    
    //getters and setters

    /**
     * Gets the User account status
     * @return
     */
    public int getUserStatus() {
        return userStatus;
    }

    /**
     * Sets the User account status
     * @param userStatus
     */
    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * Gets the User's role
     * @return
     */
    public String getUserrole() {
        return userrole;
    }

    /**
     * Sets the User's role
     * @param userrole
     */
    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }
        
    /**
     * Gets the User ID
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the Username
     * @return
     */
    public String getUsername() {
        return username;
    }
      
    /**
     * Gets the Users password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the User ID
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the Username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
       
    /**
     * Sets the User's password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    //puts variables to String for testing purposes
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID: " + this.userId + ";");
        buffer.append("Username: " + username);
        buffer.append("Password: " + password);
        buffer.append("User Role:" + userrole);
        buffer.append("User Status: " + userStatus);
        return buffer.toString();
    }
    
}
