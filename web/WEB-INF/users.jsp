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
            <header>
                <ul>

            <div class="navbar">
                <li><a class="active" href="#"><i class="fa fa-fw fa-home"></i> Home</a></li>
                <li><a href="#"><i class="fa fa-fw fa-search"></i> Search</a></li>
                <li><a href="#"><i class="fa fa-fw fa-envelope"></i> Contact</a></li>
                <li><a href="#"><i class="fa fa-fw fa-user"></i> Login</a></li>
            </div>
                    </ul>
            </header>


            <div class="addUser">
                <fieldset>

                    <legend><h2>Add User</h2> </legend>   

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

                    </fieldset>

             </div>    


             <div class="manageUser">
                 <fieldset>

                     <legend><h2>Manage Users</h2></legend>
                <table style="width: 55%" cellpadding="2" cellwidth="7%" border="1">


                         <tr>
                        <th>Email</th>
                        <th>Active</th>
                        <th>First Name</th>                   
                        <th>Last Name</th>                  
                        <th>Role</th>
                         <div class ="spacing">
                        <th>Edit</th>
                        <th>Delete</th>
                        </div>
                         </tr>
                        <c:forEach var="user" items="${user}">
                            <tr>
                            <td><c:out value="${user.email}"></c:out></td>
                            <td><c:out value="${user.active}"></c:out></td>
                            <td><c:out value="${user.fname}"></c:out></td>
                            <td><c:out value="${user.lname}"></c:out></td>
                            <td><c:out value="${user.role}"></c:out></td>
                            <form method="POST" action ="users">
                                <td><button class="edt"><i class="fa fa-edit"></i></button></td>
                                <input type="hidden" name="action" value="edituser,${user.email}">
                            </form>
                            <form method="POST" action ="users">
                                <td><button class="del"><i class="fa fa-trash"></i></button></td>
                                <input type="hidden" name="action" value="deleteuser,${user.email}">
                                <input type="hidden" name="actionVal" value="${user.email}">
                            </form>                           
                        </tr>
                        </c:forEach>

                </table>
                   </fieldset> 
                 <div class="paging">
                     <h4>page ${page} </h4>
                     <c:if test="${page > 1}">
                     <a href="users?page=${page - 1}">&lt;Back </a>
                     </c:if>
                     
                     <c:if test="${page < end}">
                     <a href="users?page=${page + 1}"> Forward&gt;</a>
                     </c:if>
                 </div>
                     
             </div>

            <div class="editUser">

                <fieldset>

                    <legend> <h2>Edit Users</h2> </legend>
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
                    ${updateMessage}
                    <input type="submit" class="button" value="Cancel">
                    <input type="hidden"  value="action" value="cancel">

                </form>
                    </fieldset>
            </div>

                 <footer class="footer">
                            &reg Authors<br>
                     -Tyler Amstrong<br>
                     -Dwayne Justin<br>
                     -Suh Afuuhnwi<br>

                        &copy 2021 SAT
                    </footer>  

        </body>

    </html>
