/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.RoleDB;
import java.sql.SQLException;
import java.util.List;
import models.User;

/**
 *
 * @author 794974
 */
public class UserService {
    
    public List<User> getALL() throws SQLException{
    RoleDB role = new RoleDB();
    List<User> user = role.getAll();
    return user;
      }
    
    public void insert(String email,boolean active ,String firstname,String lastname,String password,String role ){
        User user = new User(email,active,firstname,lastname,password,role);
        RoleDB insertRole = new RoleDB();
        insertRole.insertNewUser(user);
        
    }
}
