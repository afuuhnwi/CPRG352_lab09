/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author djtsa
 */
public class DBUtil {
        public static final EntityManagerFactory emf = 
                Persistence.createEntityManagerFactory("usersPU");
        
        public static EntityManagerFactory getEmFactory(){
            return emf;
        }
  
}
