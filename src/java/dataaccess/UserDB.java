/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

/**
 *
 * @author 794974
 */
public class UserDB {
    
     public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
     
     public void deleteUser(User user){
        
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
       
        //String deleteQuery = "DELETE FROM user WHERE email= ?";
        
        try {
           trans.begin();
           em.remove(em.merge(user));
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }
        finally{
             em.close();
        }
   
    }
      public User get(String roleID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User user = em.find(User.class, roleID);
            return user;
        }finally{
            em.close();
        }
    }
     
     public User updateUser(User user) throws Exception{
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(user);
            trans.commit();
                    
        }catch(Exception ex){
            trans.rollback();
        }finally {
            em.close();
        }
            
            
        return user;
        
    }
    

}
