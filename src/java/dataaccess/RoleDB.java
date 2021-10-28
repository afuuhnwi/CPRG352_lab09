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
import models.User;

/**
 *
 * @author 794974
 */
public class RoleDB {
    
    /*
    
     String sql = "SELECT * FROM note WHERE owner=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, owner);
            rs = ps.executeQuery();
            while (rs.next()) {
                int noteId = rs.getInt(1);
                String title = rs.getString(2);
                String contents = rs.getString(3);
                Note note = new Note(noteId, title, contents, owner);
                notes.add(note);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return notes;
*/
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
}
