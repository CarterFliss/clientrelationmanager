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
import org.springframework.jdbc.support.rowset.SqlRowSet;


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
        this.sql = "INSERT INTO users (Username,Password,User_Status) VALUES (?,?,?)";
        Object[] values = {user.getUsername(),user.getPassword(),user.isUserStatus()};
        return this.template.update(sql, values);
    }
    
    public int updateUser(Users user){
        this.sql = "UPDATE users SET Username = ?, Password = ?,User_Status = ? WHERE UserID = ?";
        Object[] values = {user.getUsername(),user.getPassword(),user.isUserStatus(),user.getId()};
        return this.template.update(sql, values);
    }
    
    public int deleteUser (int id){
        this.sql = "DELETE FROM users WHERE UserID = ?";
        Object[] values = {id};
        return this.template.update(sql, values);
    }
    
    public List<Users> getUsersList(){
        return template.query("SELECT * FROM users",new RowMapper<Users>(){
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
    
    public List<Users> getUsersByPage(int start, int total){
        String sql = "SELECT * FROM users LIMIT " + (start - 1) + "," + total;
        return template.query(sql,new RowMapper<Users>(){
            public Users mapRow(ResultSet rs,int row) throws SQLException{
                Users a = new Users();
                a.setId(rs.getInt(1));
                a.setUsername(rs.getString(2));
                return a;
            }
        });
    }
    
    public int getUsersCount() {
        String sql = "SELECT COUNT(UserID) AS rowcount FROM users";
        SqlRowSet rs = template.queryForRowSet(sql);
        
        if (rs.next()) {
            return rs.getInt("rowcount");
        }
        
        return 1;
    }
}
