<%-- 
    Document   : viewclient
    Created on : Feb 9, 2017, 7:50:48 PM
    Author     : Carter
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ include file="theme/header.jsp" %>

<header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> View Client Profile</b></h5>
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
<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">  
    <tr>
        <th><strong>Client Name:  </strong></th>
        <td><c:forEach var="u" items="${Client}">${u.firstName} ${u.lastName}</c:forEach></td>
    </tr>
    <tr>
        <th><strong>Client Status:  </strong></th>
    <c:forEach var="u" items="${Client}"><td>${u.userStatus}</td></c:forEach>
    </tr>
    <tr>
        <th><strong>Address:  </strong></th>
        <c:forEach var="u" items="${Client}"><td>${u.address}, ${u.city}, ${u.homeState}, ${u.zip}</td></c:forEach>
    </tr>
    <tr>
        <th><strong>Phone:  </strong></th>
        <c:forEach var="u" items="${Client}"><td>${u.phone}</td></c:forEach>
    </tr>
    <tr>
        <th><strong>Email:  </strong></th>
        <c:forEach var="u" items="${Client}"><td>${u.phone}</td></c:forEach>
    </tr>
</table><br><br>

<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">  
    <tr>
      <th>Date</th>
      <th>Client Name</th>
      <th>Username</th>
      <th>Event</th>
      <th>Action</th>
    </tr>  

    <c:forEach var="e" items="${Events}">   
      <tr>  
        <td>${e.date}</td>
        <td>${e.clientFirstName} ${e.clientLastName}</td>
        <td>${e.username}</td>
        <td>${e.interaction}</td>
        <td>
          <a href="<c:url value="/eventlog/editevent/${e.eventid}" />"><button class="w3-btn w3-round w3-red">Edit</button></a>
          <sec:authorize access="hasRole('ADMIN')">
          <a href="<c:url value="/eventlog/removeevent/${e.eventid}" />"><button class="w3-btn w3-round w3-green">Delete</button></a>
          </sec:authorize>
        </td>  
      </tr>  
    </c:forEach>  
  </table> 