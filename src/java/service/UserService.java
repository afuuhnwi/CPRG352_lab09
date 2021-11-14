/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Role;
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
    
    public void insert(String email,boolean active ,String firstname,String lastname,String password,String role) throws Exception{
        User user = new User(email,active,firstname,lastname,password,role);
        UserDB userDb = new UserDB();
        userDb.insert(user);
        
    }
    
    public User edit(String email) {
        UserDB editUser = new UserDB();
        User user = new User();
        try {      
            user = editUser.get(email);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
        
    }
    
    public void update(String email,String firstname,String lastname,int roleid) throws Exception {
        RoleDB roledb = new RoleDB();
        UserDB userdb = new UserDB();
        User get =  userdb.get(email);
        
        //User role = userdb.get(roleid);
      
        get.setFirstName(firstname);
        get.setLastName(lastname);
        
        //user.setRole(role);
        
        Role update = roledb.get(roleid);
        get.setRole(update);
        userdb.updateUser(get);
                
    }
    
    public void delete(String email){
        UserDB deletUser = new UserDB();
        User user = new User(email);
        deletUser.deleteUser(user);
    }
    
    
}
