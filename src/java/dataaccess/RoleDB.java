/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author 794974
 */
public class RoleDB {
    
    public List<User> getAll() throws SQLException{
        
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connect = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String selectAll = "SELECT `email`,`first_name`,`last_name`,`role_name` FROM user,role \n" +
                           "WHERE `role_id` = `role`;";
        try{
            ps = connect.prepareStatement(selectAll);
            rs = ps.executeQuery();
            while(rs.next()){
                String email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String roleName = rs.getString(4);
                
                User userobj = new User( email,firstName, lastName, roleName);
                
                users.add(userobj);
                
                        
            }
        }
        finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(connect);
            
        }
        return users;
        
    }
    
    public void insertNewUser(User user){
        
            ConnectionPool cp = ConnectionPool.getInstance();
            Connection connect = cp.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            String insertQuery =
                    "INSERT INTO user "
                    + "(email,active,first_name,last_name,password,role)"
                    + "VALUES"
                    + "(?,?,?,?,?,?)";
            
            try {
            
            ps = connect.prepareStatement(insertQuery);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.getActive());
            ps.setString(3, user.getFname());
            ps.setString(4, user.getLname());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(connect);
            }    
    }
    
    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT * FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()) {
                Boolean status = rs.getBoolean(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                String password = rs.getString(5);
                String role = rs.getString(6);
                user = new User(email, status, fname, lname, password, role);
            }          
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
            
        }       
        return user;       
    }
    //comment
    
    
}
