/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.RoleDB;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author 794974
 */
public class UserService {
    
    public List<User> getALL(int page, int pageSize) throws SQLException{
    RoleDB role = new RoleDB();
    List<User> user = role.getAll((page - 1) * pageSize, pageSize);
    return user;
      }
    
    public void insert(String email,boolean active ,String firstname,String lastname,String password,String role ){
        User user = new User(email,active,firstname,lastname,password,role);
        RoleDB insertRole = new RoleDB();
        insertRole.insertNewUser(user);
        
    }
    public User edit(String email) {
        RoleDB editUser = new RoleDB();
        User user = new User();
        try {      
            user = editUser.get(email);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    public User update(String email, String[]webpageUser) {
        RoleDB updateUser = new RoleDB();
        User user = new User();
        
        try {
            user= updateUser.updateUser(email, webpageUser);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
        
    }
    
    public void delete(String email){
        RoleDB deletUser = new RoleDB();
        User user = new User(email);
        deletUser.deleteUser(user);
    }
    
    
}
