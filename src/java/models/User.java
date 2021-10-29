/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.io.Serializable;

/**
 *
 * @author djtsa
 */
public class User implements Serializable{
    private String email;
    private Boolean active;
    private String fname;
    private String lname;
    private String password;
    private String role;

    public User(String email,String fname, String lname, String role) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
    }
    
    
    
    public User() {
        this.email = "";
        this.active = false;
        this.fname = "";
        this.lname = ""; 
        this.password = "";
        this.role = "";
    }

    public User(String email, Boolean active, String fname, String lname, String password, String role) {
        this.email = email;
        this.active = active;
        this.fname  = fname;
        this.lname = lname;
        this.password = password;
        this.role = role;
    }

    public User(int i, String email, String atrue, String firstname, String lastname, String password, int role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(int i, String email, boolean active, String firstname, String lastname, String password, int role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
