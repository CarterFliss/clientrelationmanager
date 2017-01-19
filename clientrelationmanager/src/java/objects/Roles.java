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
 */
public class Roles implements Serializable {
    private String username;
    private String userRole;
    private int userRoleID;

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }

    public int getUserRoleID() {
        return userRoleID;
    }   
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public String getUserRole() {
        return userRole;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("User Role ID: " + userRoleID);
        buffer.append("Username: " + username);
        buffer.append("User Role: " + userRole);
        return buffer.toString();
    }
    
}
