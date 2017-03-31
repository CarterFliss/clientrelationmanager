/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *Users Data Access Object
 */
package repository;

import objects.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//logging imports kept for testing purposes
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
    //initializing class-wide variables
    private JdbcTemplate template;
    private String sql;
    //getters and setters for jdbctemplate

    /**
     * Gets the JDBCTemplate
     * @return
     */
    public JdbcTemplate getTemplate() {
        return template;
    }

    /**
     * Sets the JDBCTemplate
     * @param template
     */
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    //methods for CRUD operations w/ MySQL database   

    /**
     * Adds a user to the database
     * @param user
     * @return
     */
    public int addUser (Users user){
        this.sql = "INSERT INTO users (Username,Password,UserRole,User_Status) VALUES (?,SHA(?),?,?)";
        Object[] values = {user.getUsername(),user.getPassword(),user.getUserrole(),user.getUserStatus()};
        return this.template.update(sql, values);
    }
    
    /**
     * Updates a user in the database
     * @param users
     * @return
     */
    public int updateUser(Users users){
        this.sql = "UPDATE users SET Username = ?, Password = SHA(?), UserRole = ?, User_Status = ? WHERE UserID = ?";
        Object[] values = {users.getUsername(),users.getPassword(),users.getUserrole(), users.getUserStatus(),users.getUserId()};
        return this.template.update(sql, values);
    }
    
    /**
     * Deletes a user from the database
     * @param id
     * @return
     */
    public int deleteUser (int id){
        this.sql = "DELETE FROM users WHERE UserID = ?";
        Object[] values = {id};
        return this.template.update(sql, values);
    }
    //for pulling a specific Users object, for editing pages

    /**
     * Gets a user by their User ID
     * @param id
     * @return
     */
    public Users getUserById(int id){
        this.sql = "SELECT UserID,Username,Password,UserRole,User_Status FROM users WHERE UserID = ?";
        return this.template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Users>(Users.class));
    }
    //prints list of Users for jsp pages      

    /**
     * Gets a list of Users
     * @return
     */
    public List<Users> getUsersList(){
        return template.query("SELECT UserID,Username,UserRole,User_Status FROM users",new RowMapper<Users>(){
            public Users mapRow(ResultSet rs,int row) throws SQLException{
                Users a = new Users();
                a.setUserId(rs.getInt("UserID"));
                a.setUsername(rs.getString("Username"));
                a.setUserrole(rs.getString("UserRole"));
                a.setUserStatus(rs.getInt("User_Status"));                           
                return a;
            }
        });
    }
    //for pulling a specific Users object, for editing pages

    /**
     * Gets a list of Users based on a specific User ID, mainly for specific
     *  User and Client profiles
     * @param id
     * @return
     */
    public List<Users> getUsersById(int id){
        return template.query("SELECT UserID,Username,UserRole, User_Status FROM users WHERE UserID="+id,new RowMapper<Users>(){
            public Users mapRow(ResultSet rs,int row) throws SQLException{
                Users a = new Users();
                a.setUserId(rs.getInt("UserID"));
                a.setUsername(rs.getString("Username"));
                a.setUserrole(rs.getString("UserRole"));
                a.setUserStatus(rs.getInt("User_Status"));
                return a;
            }
        });
    }
    //pagination method

    /**
     * Gets a list of Users based on a set number, 25, mainly for pagination
     * @param start
     * @param total
     * @return
     */
    public List<Users> getUsersByPage(int start, int total){
        String sql = "SELECT * FROM users LIMIT " + (start - 1) + "," + total;
        return template.query(sql,new RowMapper<Users>(){
            public Users mapRow(ResultSet rs,int row) throws SQLException{
                Users a = new Users();
                a.setUserId(rs.getInt(1));
                a.setUsername(rs.getString(2));
                a.setUserrole(rs.getString(4));
                a.setUserStatus(rs.getInt(5));
                return a;
            }
        });
    }
    //gets count of all Clients for pagination purposes

    /**
     * Gets a count of all users, mainly for pagination
     * @return
     */
    public int getUsersCount() {
        String sql = "SELECT COUNT(UserID) AS rowcount FROM users";
        SqlRowSet rs = template.queryForRowSet(sql);
        
        if (rs.next()) {
            return rs.getInt("rowcount");
        }
        
        return 1;
    }
}
