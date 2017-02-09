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
public class Roles implements Serializable {
    private String username;
    private String userRole;
    private int userID;
    
    private Users user;
    private List<String> users;

    public Users getUser() {
        return user;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
    
    public void setUserRoleID(int userRoleID) {
        this.userID = userRoleID;
    }

    public int getUserRoleID() {
        return userID;
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
        buffer.append("User Role ID: " + userID);
        buffer.append("Username: " + username);
        buffer.append("User Role: " + userRole);
        return buffer.toString();
    }
    
}
