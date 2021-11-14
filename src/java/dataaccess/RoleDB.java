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
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import models.Role;
import models.User;


/**
 *
 * @author 794974
 */
public class RoleDB {
    
    public List<User> getAll() throws SQLException{
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        
        List<User> users = query.getResultList();
        return users;
    }
    
    public Role get(int roleId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
 
            Role role = em.find(Role.class, roleId);
            return role;
        
    }
   
    
    
   
    
    
    
}
