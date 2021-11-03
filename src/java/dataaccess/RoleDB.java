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
    
    public List<User> getAll(int offset, int count) throws SQLException{
        
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connect = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        int i = 0;
        
        String selectAll = "SELECT `email`, `active` ,`first_name`,`last_name`,`role_name` FROM user,role \n" +
                           "WHERE `role_id` = `role`;";
        try{
            ps = connect.prepareStatement(selectAll);
            rs = ps.executeQuery();
            while(rs.next()){
                i++;
                if (i <= offset) {
                    
                    
                     
                    continue;
                }                             
                if (i > offset + count) {
                    break;
                }
                
                User userobj = new User( rs.getString(1), rs.getBoolean(2) ,rs.getString(3), rs.getString(4), rs.getString(5) );
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
                Boolean active = rs.getBoolean(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                String password = rs.getString(5);
                String role = rs.getString(6);
                user = new User(email, active, fname, lname, password, role);
            }          
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
            
        }       
        return user;       
    }
    
    public User updateUser(String email, String[]webpageUser) throws Exception{
        
        String sql;
        User user = new User();
        user = get(email);
        String originalEmail = user.getEmail();
        
        
        if(!email.equals(webpageUser[0])) {
            
            User deleteUser = new User(user.getEmail() ,user.getActive() ,user.getFname(), user.getLname(), user.getRole());
            user.setEmail(webpageUser[0]); 
            insertNewUser(user);                     
            deleteUser(deleteUser);
            
        }  
        if(!webpageUser[1].equals(user.getFname()) ) {
            ConnectionPool cp = ConnectionPool.getInstance();
            Connection con = cp.getConnection();
                PreparedStatement ps = null;
                sql = "Update user SET first_name=? WHERE email=?";
                try {
                ps = con.prepareStatement(sql);
                ps.setString(1, webpageUser[1]);
                ps.setString(2, webpageUser[0]);
                ps.executeUpdate();
                }finally {
                DBUtil.closePreparedStatement(ps);
                cp.freeConnection(con);
                } 
            }
            if(!webpageUser[2].equals(user.getLname()) ) {
                ConnectionPool cp = ConnectionPool.getInstance();
                Connection con = cp.getConnection();
                PreparedStatement ps = null;
                sql = "Update user SET last_name=? WHERE email=?";
                try {
                ps = con.prepareStatement(sql);
                ps.setString(1, webpageUser[2]);
                ps.setString(2, webpageUser[0]);
                ps.executeUpdate();
                }finally {
                DBUtil.closePreparedStatement(ps);
                cp.freeConnection(con);
                } 
            }
            
            if (!webpageUser[3].equals("") ) {
                ConnectionPool cp = ConnectionPool.getInstance();
                Connection con = cp.getConnection();
                PreparedStatement ps = null;
                sql = "Update user SET role=? WHERE email=?";
                String convertRole = webpageUser[3];
                Integer role = Integer.parseInt(convertRole);                               
                try {
                ps = con.prepareStatement(sql);               
                ps.setInt(1, role);
                ps.setString(2, webpageUser[0]);
                ps.executeUpdate();
                
                } finally {
                DBUtil.closePreparedStatement(ps);
                cp.freeConnection(con);
                } 
            }
            
            
        
      
        return user;
        
    }
    public void deleteUser(User user){
        
        ConnectionPool cp = ConnectionPool.getInstance();
        PreparedStatement ps = null;
        Connection connect = cp.getConnection();
        
        
        
        
        
        String deleteQuery = "DELETE FROM user WHERE email= ?";
        
        try {
            ps = connect.prepareStatement(deleteQuery);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(connect);
           
                
        }
   
    }
    
    
}
