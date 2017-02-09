/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
import objects.Roles;

/**
 *
 * @author Carter
 */
public class Users implements Serializable{
    private int id;
    private String username;
    private String password;
    private boolean userStatus;
    Roles role = new Roles();
    List<Roles> roles;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUserStatus() {
        return userStatus;
    }
    
    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
   
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID: " + this.id + ";");
        buffer.append("Username: " + username);
        buffer.append("Password: " + password);
        buffer.append("User Status: " + userStatus);
        return buffer.toString();
    }
    
}
