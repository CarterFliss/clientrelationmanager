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
 */
public class Users implements Serializable{
    private int userId;
    private String username;
    private String password;
    private String userrole;
    private int userStatus;

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }
        
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
      
    public String getPassword() {
        return password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
       
    public void setPassword(String password) {
        this.password = password;
    }
    
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
