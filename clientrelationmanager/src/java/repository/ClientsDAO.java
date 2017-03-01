/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *Clients Data Access Object
 */
package repository;

import objects.Clients;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//Users import is deprecated.  Logging import kept for testing reasons.
import objects.Users;
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
public class ClientsDAO {
    //initialize class-wide variables.  Only jdbctemplate and sql String.
    private JdbcTemplate template;
    private String sql;
    //getters and setters for jdbctemplate
    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    //methods for CRUD operations w/ MySQL database objects, utilizing Clients.java POJO
    public int addClient(Clients client) {
        this.sql = "INSERT INTO clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP,Phone,Email) VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] values = {client.getFirstName(), client.getLastName(), client.getUserStatus(), client.getAddress(), client.getCity(), client.getHomeState(), client.getZip(), client.getPhone(), client.getEmail()};
        return this.template.update(sql, values);
    }

    public int updateClient(Clients clients) {
        this.sql = "UPDATE clients SET First_Name = ?, Last_Name = ?, Status = ?,  Address = ?, City = ?, Home_State = ?, ZIP = ?, Phone = ?, Email = ? WHERE ClientID = ?";
        Object[] values = {clients.getFirstName(), clients.getLastName(), clients.getUserStatus(), clients.getAddress(), clients.getCity(), clients.getHomeState(), clients.getZip(), clients.getPhone(), clients.getEmail(), clients.getClientid()};
        return this.template.update(sql, values);
    }

    public int deleteClient(int id) {
        this.sql = "DELETE FROM clients WHERE ClientID = ?";
        Object[] values = {id};
        return this.template.update(sql, values);
    }
    //prints list of Clients for jsp pages
    public List<Clients> getClientsList() {
        return template.query("SELECT * FROM clients", new RowMapper<Clients>() {
            public Clients mapRow(ResultSet rs, int row) throws SQLException {
                Clients a = new Clients();
                a.setClientid(rs.getInt("ClientID"));
                a.setFirstName(rs.getString("First_Name"));
                a.setLastName(rs.getString("Last_Name"));
                a.setUserStatus(rs.getString("Status"));
                a.setAddress(rs.getString("Address"));
                a.setCity(rs.getString("City"));
                a.setHomeState(rs.getString("Home_State"));
                a.setZip(rs.getInt("ZIP"));
                a.setPhone(rs.getString("Phone"));
                a.setEmail(rs.getString("Email"));
                return a;
            }
        });
    }
    //for pulling a specific Clients object, for editing pages
    public Clients getClientByID (int id){
        this.sql = "SELECT ClientID,First_Name,Last_Name,Status,Address,City,Home_State,ZIP,Phone,Email FROM clients WHERE ClientID = ?";
        return this.template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Clients>(Clients.class));
    }

    public List<Clients> getClientsById(int id) {
        return template.query("SELECT * FROM clients WHERE ClientID = "+id, new RowMapper<Clients>() {
            public Clients mapRow(ResultSet rs, int row) throws SQLException {
                Clients a = new Clients();
                a.setClientid(rs.getInt("ClientID"));
                a.setFirstName(rs.getString("First_Name"));
                a.setLastName(rs.getString("Last_Name"));
                a.setUserStatus(rs.getString("Status"));
                a.setAddress(rs.getString("Address"));
                a.setCity(rs.getString("City"));
                a.setHomeState(rs.getString("Home_State"));
                a.setZip(rs.getInt("ZIP"));
                a.setPhone(rs.getString("Phone"));
                a.setEmail(rs.getString("Email"));
                return a;
            }
        });
    }
    //pagination method
    public List<Clients> getClientsByPage(int start, int total) {
        String sql = "SELECT * FROM clients LIMIT " + (start - 1) + "," + total;
        return template.query(sql, new RowMapper<Clients>() {
            public Clients mapRow(ResultSet rs, int row) throws SQLException {
                Clients a = new Clients();
                a.setClientid(rs.getInt("ClientID"));
                a.setFirstName(rs.getString("First_Name"));
                a.setLastName(rs.getString("Last_Name"));
                a.setUserStatus(rs.getString("Status"));
                a.setAddress(rs.getString("Address"));
                a.setCity(rs.getString("City"));
                a.setHomeState(rs.getString("Home_State"));
                a.setZip(rs.getInt("ZIP"));
                a.setPhone(rs.getString("Phone"));
                a.setEmail(rs.getString("Email"));
                return a;
            }
        });
    }
    //gets count of all Clients for pagination purposes
    public int getClientsCount() {
        String sql = "SELECT COUNT(ClientID) AS rowcount FROM clients";
        SqlRowSet rs = template.queryForRowSet(sql);

        if (rs.next()) {
            return rs.getInt("rowcount");
        }

        return 1;
    }

}
