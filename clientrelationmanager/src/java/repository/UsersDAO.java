/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import objects.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 *
 * @author Carter
 */
public class UsersDAO {
    private JdbcTemplate template;
    private String sql;
        
    public void setJdbcTemplate (JdbcTemplate template){
    this.template = template;
    }
     
    public int addUser (Users user){
        this.sql = "INSERT INTO users (Username,Password,User_Status) VALUES ('"
                + user.getUsername()+"," + user.getPassword() + "," + user.isUserStatus();
        return this.template.update(sql);
    }
    
    public int updateUser(Users user){
        this.sql = "UPDATE users SET Username = " + user.getUsername() + "," +
                "Password = " + user.getPassword() + "," + "User_Status = " +
                user.isUserStatus() + "WHERE UserID = " + user.getId();
        return this.template.update(sql);
    }
    
    public int deleteUser (int id){
        this.sql = "DELETE FROM users WHERE UserID = " + id +"";
        return this.template.update(sql);
    }
    
    public List<Users> getArtistsList(){
        return template.query("SELECT * FROM Artist",new RowMapper<Users>(){
            public Users mapRow(ResultSet rs,int row) throws SQLException{
                Users a = new Users();
                a.setId(rs.getInt("UserID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setUserStatus(rs.getBoolean("User_Status"));
                return a;
            }
        });
    }
    
    public Users getUsersById(int id){
        String sql = "SELECT UserID AS UserID, Username, Password FROM users WHERE UserID = ?";
        return template.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<Users>(Users.class));
    }
}
