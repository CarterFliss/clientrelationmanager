/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import objects.Users;
import objects.Roles;
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
public class RolesDAO {

    private JdbcTemplate template;
    private String sql;

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int addRole(Roles role) {
        this.sql = "INSERT INTO roles (Username,UserRole) VALUES (?,?)";
        Object[] values = {role.getUsername(), role.getUserRole()};
        return this.template.update(sql, values);
    }

    public int updateRole(Roles role) {
        this.sql = "UPDATE roles SET Username = ?, UserRole = ?,WHERE UserRoleID = ?";
        Object[] values = {role.getUsername(), role.getUserRole(), role.getUserRoleID()};
        return this.template.update(sql, values);
    }

    public int deleteRole(int id) {
        this.sql = "DELETE FROM roles WHERE UserRoleID = ?";
        Object[] values = {id};
        return this.template.update(sql, values);
    }

    public List<Roles> getRolesList() {
        return template.query("SELECT * FROM roles", new RowMapper<Roles>() {
            public Roles mapRow(ResultSet rs, int row) throws SQLException {
                Roles a = new Roles();
                a.setUserRoleID(rs.getInt("UserRoleID"));
                a.setUsername(rs.getString("Username"));
                a.setUserRole(rs.getString("UserRole"));
                return a;
            }
        });
    }

    public List<Roles> getRolesById(int id) {
        return template.query("SELECT * FROM roles", new RowMapper<Roles>() {
            public Roles mapRow(ResultSet rs, int row) throws SQLException {
                Roles a = new Roles();
                a.setUserRoleID(rs.getInt("UserRoleID"));
                a.setUsername(rs.getString("Username"));
                a.setUserRole(rs.getString("UserRole"));
                return a;
            }
        });
    }

    public List<Roles> getRolesByPage(int start, int total) {
        String sql = "SELECT roles.UserRoleID,users.Username,roles.UserRole FROM roles "
                + "INNER JOIN users AS users ON users.Username = roles.Username "
                + "ORDER BY users.Username "
                + "LIMIT " + (start - 1) + "," + total;
        return template.query(sql, new RowMapper<Roles>() {
            public Roles mapRow(ResultSet rs, int row) throws SQLException {
                Roles a = new Roles();
                a.setUserRoleID(rs.getInt(1));
                a.setUsername(rs.getString(2));
                a.setUserRole(rs.getString(3));
                return a;
            }
        });
    }

    public int getRolesCount() {
        String sql = "SELECT COUNT(UserRoleID) AS rowcount FROM roles";
        SqlRowSet rs = template.queryForRowSet(sql);

        if (rs.next()) {
            return rs.getInt("rowcount");
        }

        return 1;
    }

    public List<String> getUsersMap() {
        List<String> users = null;
        String sql = "SELECT Username FROM users";
        SqlRowSet srs = template.queryForRowSet(sql);
        while (srs.next()) {
            users.add(srs.getString("Username"));
        }
        return users;
    }
}
