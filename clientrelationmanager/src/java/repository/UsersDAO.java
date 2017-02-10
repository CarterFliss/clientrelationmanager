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

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
       
    public int addUser (Users user){
        this.sql = "INSERT INTO users (Username,Password,UserRole,User_Status) VALUES (?,SHA(?),?,?)";
        Object[] values = {user.getUsername(),user.getPassword(),user.getUserrole(),user.isUserStatus()};
        return this.template.update(sql, values);
    }
    
    public int updateUser(Users user){
        this.sql = "UPDATE users SET Username = ?, Password = SHA(?), UserRole = ?, User_Status = ? WHERE UserID = ?";
        Object[] values = {user.getUsername(),user.getPassword(),user.getUserrole(), user.isUserStatus(),user.getId()};
        return this.template.update(sql, values);
    }
    
    public int deleteUser (int id){
        this.sql = "DELETE FROM users WHERE UserID = ?";
        Object[] values = {id};
        return this.template.update(sql, values);
    }
          
    public List<Users> getUsersList(){
        return template.query("SELECT UserID,Username,UserRole,User_Status FROM users",new RowMapper<Users>(){
            public Users mapRow(ResultSet rs,int row) throws SQLException{
                Users a = new Users();
                a.setId(rs.getInt("UserID"));
                a.setUsername(rs.getString("Username"));
                a.setUserrole(rs.getString("UserRole"));
                a.setUserStatus(rs.getBoolean("User_Status"));                           
                return a;
            }
        });
    }
    
    public List<Users> getUsersById(int id){
        return template.query("SELECT UserID,Username,UserRole, User_Status FROM users WHERE UserID="+id,new RowMapper<Users>(){
            public Users mapRow(ResultSet rs,int row) throws SQLException{
                Users a = new Users();
                a.setId(rs.getInt("UserID"));
                a.setUsername(rs.getString("Username"));
                a.setUserrole(rs.getString("UserRole"));
                a.setUserStatus(rs.getBoolean("User_Status"));
                return a;
            }
        });
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
