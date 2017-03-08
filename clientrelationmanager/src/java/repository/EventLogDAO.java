/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import objects.Clients;
import objects.Users;
import objects.EventLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//Supplier import is deprecated.  Logging imports kept for testing reasons.
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author Carter
 * Event Log Data Access Object
 */
public class EventLogDAO {
    //initializing class-wide variables
    private JdbcTemplate template;
    private String sql;
    
    private static final Logger logger = Logger.getLogger(EventLogDAO.class.getName());
    //getters and setters for jdbc template
    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    //methods for CRUD operations w/ MySQL database
    public int addEvent(EventLog events) {
        this.sql = "INSERT INTO interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (?,?,?,?,?,?,?)";
        Object[] values = {events.getClientid(),events.getClientFirstName(), events.getClientLastName(), events.getUserid(),events.getUsername(), events.getInteraction(), events.getDate()};
        return this.template.update(sql, values);
    }

    public int updateEvent(EventLog eventlog) {
        this.sql = "UPDATE interactions SET ClientID = ?, First_Name = ?,Last_Name = ?,UserID = ?,Username = ?,Interaction_Type = ?,Interaction_Date = ? WHERE EventID = ?";
        Object[] values = {eventlog.getClientid(),eventlog.getClientFirstName(), eventlog.getClientLastName(), eventlog.getUserid(),eventlog.getUsername(), eventlog.getInteraction(), eventlog.getDate(), eventlog.getEventid()};
        logger.info(eventlog.getEventid()+"");
        return this.template.update(sql, values);
    }

    public int deleteEvent(int id) {
        this.sql = "DELETE FROM interactions WHERE EventID = ?";
        Object[] values = {id};
        return this.template.update(sql, values);
    }
    //prints list of EventLogs for jsp pages
    public List<EventLog> getEventsList() {
        return template.query("SELECT EventID,ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date FROM interactions", new RowMapper<EventLog>() {
            public EventLog mapRow(ResultSet rs, int row) throws SQLException {
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
    //for pulling a specific EventLog object, for editing pages and providing
    //interaction history w/ a User or Client
    public EventLog getEventsById(int id) {
        String sql = "SELECT EventID,First_Name, Last_Name, Username, Interaction_Type, Interaction_Date FROM interactions WHERE EventID = ?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<EventLog>(EventLog.class));
    }

    public List<EventLog> getEventsByClientID(int id) {
        return template.query("SELECT EventID,ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date FROM interactions WHERE ClientID = "+id, new RowMapper<EventLog>() {
            public EventLog mapRow(ResultSet rs, int row) throws SQLException {
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

    public List<EventLog> getEventsByUserID(int id) {
        return template.query("SELECT EventID,ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date FROM interactions WHERE UserID = "+id, new RowMapper<EventLog>() {
            public EventLog mapRow(ResultSet rs, int row) throws SQLException {
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
    //pagination method
    public List<EventLog> getEventsByPage(int start, int total) {
        String sql = "SELECT interactions.EventID,clients.ClientID,clients.First_Name,"
                + "clients.Last_Name,users.UserID,users.Username,interactions.Interaction_Type,"
                + "interactions.Interaction_Date FROM interactions "
                + "INNER JOIN clients AS clients ON clients.ClientID = interactions.ClientID "
                + "INNER JOIN users AS users ON users.UserID = interactions.UserID "
                + "ORDER BY interactions.Interaction_Date "
                + "LIMIT " + (start - 1) + "," + total;
        return template.query(sql, new RowMapper<EventLog>() {
            public EventLog mapRow(ResultSet rs, int row) throws SQLException {
                EventLog a = new EventLog();
                a.setEventid(rs.getInt("EventID"));

                Clients client = new Clients();
                client.setClientid(rs.getInt("ClientID"));
                client.setFirstName(rs.getString("First_Name"));
                client.setLastName(rs.getString("Last_Name"));

                a.setClient(client);

                Users user = new Users();
                user.setUserId(rs.getInt("UserID"));
                user.setUsername(rs.getString("Username"));

                a.setUser(user);

                a.setInteraction(rs.getString("Interaction_Type"));
                a.setDate(rs.getString("Interaction_Date"));
                return a;
                
            }
        });
    }
    //gets count of all Clients for pagination purposes
    public int getEventsCount() {
        String sql = "SELECT COUNT(EventID) AS rowcount FROM interactions";
        SqlRowSet rs = template.queryForRowSet(sql);

        if (rs.next()) {
            return rs.getInt("rowcount");
        }

        return 1;
    }
    //maps Clients for pagination purposes
    public Map<Integer, String> getClientsMap() {
        Map<Integer, String> clients = new LinkedHashMap<Integer, String>();
        String sql = "SELECT ClientID,First_Name,Last_Name FROM clients";

        SqlRowSet srs = template.queryForRowSet(sql);

        while (srs.next()) {
            clients.put(srs.getInt("ClientID"), srs.getString("First_Name") + " " + srs.getString("Last_Name"));
        }
        return clients;
    }
    //maps Users for pagination purposes
    public Map<Integer, String> getUsersMap() {
        Map<Integer, String> users = new LinkedHashMap<Integer, String>();
        String sql = "SELECT UserID,Username FROM users";

        SqlRowSet srs = template.queryForRowSet(sql);

        while (srs.next()) {
            users.put(srs.getInt(1), srs.getString(2));
        }
        return users;
    }
}
