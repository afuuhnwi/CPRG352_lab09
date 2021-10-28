<%-- 
    Document   : users
    Created on : Oct 26, 2021, 11:18:17 AM
    Author     : Tyler
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JDBC Users</title>
    </head>
    <body>
        
        <div class="addUser">
            
            <h2>Add User</h2>    
            
            <form method="POST" action="users">  
                <input type="text" name="email" value="" placeholder="Email"><br>
                <input type="text" name="firstName" value="" placeholder="First Name"><br>
                <input type="text" name="lastName"  value="" placeholder="Last Name"><br>
                <input type="text" name="password" value="" placeholder="Password"><br>
                <select name="systemRole" id="systemRole">
                    <option value="" disabled selected>Select System Role</option>
                    <option value="systemAdmin">System Admin</option>
                    <option value="regularUser">Regular User</option>
                    <option value="companyAdmin">Company Admin</option>
                </select> <br>    
                <input type="submit" value="Save">
            </form>
            
         </div>    
        
        
         <div class="manageUser">
                
            <h2>Manage Users</h2>
            <table style="width: 50%">
               
               
                     <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    
                    <th>Last Name</th>
                   
                    <th>Role</th>
                    <th>Edit</th>
                    <th>Delete</th>
                     </tr>
                    <c:forEach var="user" items="${user}">
                        <tr>
                        <td><c:out value="${user.email}"></c:out></td>
                        <td><c:out value="${user.fname}"></c:out></td>
                        <td><c:out value="${user.lname}"></c:out></td>
                        <td><c:out value="${user.role}"></c:out></td>
                    </tr>
                    </c:forEach>
                    
                
                    
                
            </table>
                
                
         </div>
            
        
        
        
      
        <div class="editUser">
            
            <h2>Edit Users</h2>
            <form method="POST" action="users">
            <input type="text" name="editEmail" value=""><br>
            <input type="text" name="editFirstName" value=""><br>
            <input type="text" name="editLastName" value=""><br>
            <select name="editSystemRole" id="editSystemRole">
                <option value="" selected></option>
                <option value="systemAdmin">System Admin</option>
                <option value="regularUser">Regular User</option>
                <option value="companyAdmin">Company Admin</option>
            </select> <br>
                <input type="submit" value="Save"><br>
                <input type="submit" value="Cancel">
                
            </form>
        </div>
            
    
        
       
        
        
        
    </body>
</html>
