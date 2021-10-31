
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
import models.User;
import service.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService user = new UserService();
        try {
            List<User> userobj = user.getALL();
            request.setAttribute("user", userobj);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("DatabaseError", true);
        }
        
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
          
          
          //int roleNum = Integer.parseInt(role);
          active = request.getParameter("active") != null;
        System.out.println("code runs till here!!");
        
        // added a "," to the hidden values.
        // if the comma is found email was also passed and can
        // be used for deleting or updating a specific user
        
        if(action.contains(",")) {
            String [] str = action.split(",");
            action = str[0];
            incomingEmail = str[1];
        }
        
        switch(action){
            case "adduser": 
               
                System.out.println("if statment holds true");
            user.insert(email, active, firstname, lastname, password,role);
            request.setAttribute("addSuccess", true);
            
            try {
            List<User> userobj = user.getALL();
            request.setAttribute("user", userobj);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("DatabaseError", true);
        }
        
            break;  
            
        case "edituser":
                User newUser = new User();
                newUser = user.edit(incomingEmail);               
                String editEmail= newUser.getEmail();
                String editFname = newUser.getFname() ;
                String editLname = newUser.getLname();
                String editRole = newUser.getRole();               
                request.setAttribute("editEmail", editEmail);
                request.setAttribute("editFname", editFname);
                request.setAttribute("editLname", editLname);
                request.setAttribute("editRole", editRole);
                
            try {
                List<User> userobj = user.getALL();
                request.setAttribute("user", userobj);
            }catch (SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("EditUserError", true);
            }
            break;            
        case "update":
                
                String webpageEmail = request.getParameter("editEmail");
                String webpageFname = request.getParameter("editFirstName");
                String webpageLname = request.getParameter("editLastName");
                String webpageRole = request.getParameter("editSystemRole");
                String[] webpageUser = {webpageEmail, webpageFname, webpageLname, webpageRole};
                
                
                
                User updateUser = new User();
                updateUser = user.update(incomingEmail, webpageUser);
                
                try {
            List<User> userobj = user.getALL();
            request.setAttribute("user", userobj);
            } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("DatabaseError", true);
            }
                
            case "deleteuser":    
                
            break;
                 
                
        }
        //if(request.getAttribute("adduser")!= null){
             //System.out.println("if statment holds true");
            //user.insert(email, active, firstname, lastname, password,Integer.parseInt(role));
           // request.setAttribute("addSuccess", true);
           // getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
       // return;
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
        
        
    }

  
   

}
