
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import service.UserService;

public class UserServlet extends HttpServlet {

    private final int pageSize = 5;
    int page = 1;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService user = new UserService();
       
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception ex) {
            // not necessary to log exception since it is not important
        }
        
        try {
            List<User> userobj = user.getALL();
            request.setAttribute("user", userobj);
            double end = userobj.size();            
            request.setAttribute("end", end);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("DatabaseError", true);
        }
        request.setAttribute("page", page);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          UserService user = new UserService();
          String action = request.getParameter("action");
          String email = request.getParameter("email");
          String firstname = request.getParameter("firstName");
          String lastname = request.getParameter("lastName");
          String password = request.getParameter("password");
          String role = request.getParameter("systemRole");
          boolean active;
          String incomingEmail = "";
          String pageConvert = request.getParameter("page");
          int userRole = 0;
          
          /*if (pageConvert != null){
              page = Integer.parseInt(request.getParameter("page"));    
              
          }
         request.setAttribute("page", page);
         
         try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception ex) {
            // not necessary to log exception since it is not important
        } */
         
          
           System.out.println("code reaches here");
          //int roleNum = Integer.parseInt(role);
          active = request.getParameter("active") != null;
        System.out.println("code runs till here!!");
        
        // added a "," to the hidden values.
        // if the comma is found email was also passed and can
        // be used for deleting or updating a specific user
        
        if(action.contains(",")) {
            String [] str = action.split(",");
            action = str[0];
            
            if (str != null && str.length == 2) {
            incomingEmail = str[1];
            }
        }
        
        switch(action){
            case "adduser": 
               
                System.out.println("if statment holds true");
          {
              try {
                  user.insert(email, active, firstname, lastname, password,role);
              } catch (Exception ex) {
                  Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
            request.setAttribute("addSuccess", true);
            
            try {
            List<User> userobj = user.getALL();
            request.setAttribute("user", userobj);
            double end = userobj.size();            
            request.setAttribute("end", end);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("DatabaseError", true);
        }
        
            break;    
            
        case "edituser":
                User newUser = new User();
                newUser = user.edit(incomingEmail);               
                String editEmail= newUser.getEmail();
                String editFname = newUser.getFirstName() ;
                String editLname = newUser.getLastName();
                Role editRole = newUser.getRole();               
                request.setAttribute("editEmail", editEmail);
                request.setAttribute("editFname", editFname);
                request.setAttribute("editLname", editLname);
                
                
                request.setAttribute("editRole", editRole);
                
                
            try {
            List<User> userobj = user.getALL();
            request.setAttribute("user", userobj);
            double end = userobj.size();            
            request.setAttribute("end", end);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("DatabaseError", true);
        }
            break;            
        case "update":
                
                String webpageEmail = request.getParameter("editEmail");
                String webpageFname = request.getParameter("editFirstName");
                String webpageLname = request.getParameter("editLastName");
                String webpageRole = request.getParameter("editSystemRole");
                userRole = Integer.parseInt(webpageRole);
                String[] webpageUser = {webpageEmail, webpageFname, webpageLname, webpageRole};                                           
                User updateUser = new User();
          {
              try {
                  user.update(webpageEmail,webpageFname,webpageLname, userRole);
              } catch (Exception ex) {
                  Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
                
                try {
            List<User> userobj = user.getALL();
            request.setAttribute("user", userobj);
            double end = userobj.size();            
            request.setAttribute("end", end);
            
            if (!webpageEmail.equals("") || !webpageFname.equals("") || !webpageLname.equals("") || !webpageRole.equals("")) {        
            request.setAttribute("updateMessage", "Edit User Successful");
            }
            } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("DatabaseError", true);
            }
            break;        
        case "deleteuser": 
                System.out.println("code reaches here");
                String delEmail = request.getParameter("actionVal");
                 System.out.println(delEmail);
                //User deleted = new User();
               user.delete(delEmail);
               
               
               List<User> userobj = null;
         
                 
            break;
            
            case "cancel":
                
                User nUser = new User();
                newUser = user.edit(incomingEmail);               
                String cancelEmail= newUser.getEmail();
                String cancelFname = newUser.getFirstName() ;
                String cancelLname = newUser.getLastName();
                Role cancelRole = newUser.getRole();               
                request.setAttribute("editEmail", "");
                request.setAttribute("editFname", "");
                request.setAttribute("editLname", "");
                request.setAttribute("editRole", "");
                       
        }
        
        
       
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
        
        
    }

  
   

}
