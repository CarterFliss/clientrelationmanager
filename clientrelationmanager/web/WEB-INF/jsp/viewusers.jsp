<%-- 
    Document   : viewusers
    Created on : Jan 26, 2017, 7:16:58 PM
    Author     : Carter
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ include file="theme/header.jsp" %>

<header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> Manage Users</b></h5>
  </header>

  <div class="w3-row-padding w3-margin-bottom">
    
  <c:if test="${not empty message}">
    <c:choose>
      <c:when test="${message.type eq 'INFO'}">
        <div class="w3-panel w3-border w3-pale-yellow w3-border-yellow"><p>${message.message}</p></div>
      </c:when>
      <c:when test="${message.type eq 'ERROR'}">
        <div class="w3-panel w3-border w3-pale-red w3-border-red"><p>${message.message}</p></div>
      </c:when>
    </c:choose>
   </c:if>
    
  <a href="<c:url value="/users/adduser" />"><button class="w3-btn w3-round w3-blue">New User</button></a><br>
  
  <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">  
    <tr>
      <th>Username</th>
      <th>User Role</th>
      <th>User Status</th>
      <th>Action</th>
    </tr>  

    <c:forEach var="u" items="${users}">   
      <tr>  
        <td>${u.username}</td>
        <td><c:choose>
            <c:when test="${u.userrole == 'ROLE_ADMIN'}">Admin</c:when>
            <c:when test="${u.userrole == 'ROLE_MANAGER'}">Manager</c:when>
            <c:when test="${u.userrole == 'ROLE_USER'}">User</c:when>
            </c:choose></td>
        <td><c:choose>
                <c:when test="${u.userStatus == 1}">Active</c:when>
                <c:when test="${u.userStatus == 0}">Inactive</c:when>
            </c:choose></td>
        <td>
          <a href="<c:url value="/users/viewuser/${u.userId}" />"><button class="w3-btn w3-round w3-blue">View</button></a>
          <a href="<c:url value="/users/edituser/${u.userId}" />"><button class="w3-btn w3-round w3-red">Edit</button></a>
          <a href="<c:url value="/users/removeuser/${u.userId}" />"><button class="w3-btn w3-round w3-green">Delete</button></a>
        </td>  
      </tr>  
    </c:forEach>  
  </table> 

  <div class="w3-padding-8">
    <ul class="w3-pagination">
      <c:forEach begin="1" end="${pages}" varStatus="p">  
        <li><a class="<c:if test="${p.index eq page}">w3-green</c:if>" href="<c:url value="/users/viewusers/${p.index}" />">${p.index}</a></li>
      </c:forEach>
    </ul>
  </div>
    
  </div>

<%@ include file="theme/footer.jsp" %>
