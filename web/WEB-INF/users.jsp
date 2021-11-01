<%-- 
    Document   : users
    Created on : Oct 26, 2021, 11:18:17 AM
    
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="style/style.css">
         <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" crossorigin="anonymous">
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
                <input type="password" name="password" value="" placeholder="Password"><br>
                <input type="checkbox" name="active" value=""> Active<br> 
                <select name="systemRole" id="systemRole">
                    <option value="" disabled selected>Select System Role</option>
                    <option value="1">System Admin</option>
                    <option value="2">Regular User</option>
                    <option value="3">Company Admin</option>
                </select> <br> <br>   
                <input type="submit" class="button" value="Save" >
                <input type="hidden" name="action" value="adduser">
            </form>
            <c:if test="addSuccess">
                <p>New user successfully added!!</p>
            </c:if>
       
         </div>    
        
         
         <div class="manageUser">
                
            <h2>Manage Users</h2>
            <table style="width: 50%">
               
               
                     <tr>
                    <th>Email</th>
                    <th>Active</th>
                    <th>First Name</th>                   
                    <th>Last Name</th>                  
                    <th>Role</th>
                    <th>Edit</th>
                    <th>Delete</th>
                     </tr>
                    <c:forEach var="user" items="${user}">
                        <tr>
                        <td><c:out value="${user.email}"></c:out></td>
                        <td><c:out value="${user.active}"></c:out></td>
                        <td><c:out value="${user.fname}"></c:out></td>
                        <td><c:out value="${user.lname}"></c:out></td>
                        <td><c:out value="${user.role}"></c:out></td>
                        <form method="POST" action ="users">
                            <td><button class="btn"><i class="fa fa-edit"></i></button></td>
                            <input type="hidden" name="action" value="edituser,${user.email}">
                        </form>
                        <form method="POST" action ="users">
                            <td><button class="btn"><i class="fa fa-trash"></i></button></td>
                            <input type="hidden" name="action" value="deleteuser,${user.email}">
                            <input type="hidden" name="actionVal" value="${user.email}">
                        </form>
                        
                    </tr>
                    </c:forEach>
                   
            </table>
                
                
         </div>
        
        <div class="editUser">
            
            <h2>Edit Users</h2>
            <form method="POST" action="users">
            <input type="text" name="editEmail" value="${editEmail}" placeholder="Email"><br>
            <input type="text" name="editFirstName" value="${editFname}" placeholder="First Name"><br>
            <input type="text" name="editLastName" value="${editLname}" placeholder="Last Name"><br>
            <select name="editSystemRole" id="editSystemRole">
                <option value="" selected>${editRole}</option>
                <option value="1">System Admin</option>
                <option value="2">Regular User</option>
                <option value="3">Company Admin</option>
            </select> <br>
                <input type="submit" class ="button" value="Save">
                <input type="hidden" name="action" value="update,${editEmail}"><br>
                <br>
                <input type="submit" class="button" value="Cancel">
                <input type="hidden"  value="action" value="cancel">
                
            </form>
        </div>
            
    
        
    </body>
</html>
