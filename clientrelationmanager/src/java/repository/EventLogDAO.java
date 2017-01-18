/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import objects.EventLog;
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
public class EventLogDAO {
    private JdbcTemplate template;
    private String sql;
        
    public void setJdbcTemplate (JdbcTemplate template){
    this.template = template;
    }
     
    public int addEvent (EventLog events){
        this.sql = "INSERT INTO interactions (EventID,ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (?,?,?,?,?,?,?,?)";
        Object[] values = {events.getEventid(),events.getClientid(),events.getClientFirstName(),events.getClientLastName(),events.getUserid(),events.getUsername(),events.getInteraction(),events.getDate()};
        return this.template.update(sql, values);
    }
    
    public int updateEvent(EventLog events){
        this.sql = "UPDATE interactions SET ClientID = ?, First_Name = ?, Last_Name = ?, UserID = ?, Username = ?, Interaction_Type = ?, Interaction_Date = ? WHERE EventID = ?";
        Object[] values = {events.getClientid(),events.getClientFirstName(),events.getClientLastName(),events.getUserid(),events.getUsername(),events.getInteraction(),events.getDate(),events.getEventid()};
        return this.template.update(sql, values);
    }
    
    public int deleteEvent (int id){
        this.sql = "DELETE FROM interactions WHERE EventID = ?";
        Object[] values = {id};
        return this.template.update(sql, values);
    }
    
    public List<EventLog> getEventsList(){
        return template.query("SELECT * FROM interactions",new RowMapper<EventLog>(){
            public EventLog mapRow(ResultSet rs,int row) throws SQLException{
                EventLog a = new EventLog();
                a.setEventid(rs.getInt("EventID"));
                a.setClientid(rs.getInt("ClientID"));
                a.setClientFirstName(rs.getString("First_Name"));
                a.setClientLastName(rs.getString("Last_Name"));
                a.setUserid(rs.getInt("UserID"));
                a.setUsername(rs.getString("Username"));
                a.setInteraction(rs.getString("Interaction_Type"));
                a.setDate(rs.getString("Interaction_Date"));
                return a;
            }
        });
    }
    
    public EventLog getEventsById(int id){
        String sql = "SELECT EventID AS EventID, ClientID, First_Name, Last_Name, UserID, Username, Interaction_Type, Interaction_Date FROM interactions WHERE EventID = ?";
        return template.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<EventLog>(EventLog.class));
    }
    
    public List<EventLog> getEventsByPage(int start, int total){
        String sql = "SELECT * FROM users LIMIT " + (start - 1) + "," + total;
        return template.query(sql,new RowMapper<EventLog>(){
            public EventLog mapRow(ResultSet rs,int row) throws SQLException{
                EventLog a = new EventLog();
                a.setEventid(rs.getInt("EventID"));
                a.setClientid(rs.getInt("ClientID"));
                a.setClientFirstName(rs.getString("First_Name"));
                a.setClientLastName(rs.getString("Last_Name"));
                a.setUserid(rs.getInt("UserID"));
                a.setUsername(rs.getString("Username"));
                a.setInteraction(rs.getString("Interaction_Type"));
                a.setDate(rs.getString("Interaction_Date"));
                return a;
            }
        });
    }
    
    public int getEventsCount() {
        String sql = "SELECT COUNT(EventID) AS rowcount FROM interactions";
        SqlRowSet rs = template.queryForRowSet(sql);
        
        if (rs.next()) {
            return rs.getInt("rowcount");
        }
        
        return 1;
    }
}
