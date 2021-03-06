<%-- 
    Document   : viewclients
    Created on : Jan 26, 2017, 7:16:13 PM
    Author     : Carter
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ include file="theme/header.jsp" %>

<header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> Manage Clients</b></h5>
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
  <sec:authorize access="hasAnyRole('MANAGER','ADMIN')">   
  <a href="<c:url value="/clients/addclient" />"><button class="w3-btn w3-round w3-blue">New Client</button></a><br>
  </sec:authorize>
  <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">  
    <tr>
      <th>Client Name</th>
      <th>Client Status</th>
      <th>Client Address</th>
      <th>Client Phone</th>
      <th>Client Email</th>
      <th>Action</th>
    </tr>  

    <c:forEach var="client" items="${clients}">   
      <tr>  
        <td>${client.firstName} ${client.lastName}</td>
        <td>${client.userStatus}</td>
        <td>${client.address}, ${client.city}, ${client.homeState}, ${client.zip}</td>
        <td>${client.phone}</td>
        <td>${client.email}</td>
        <td>
          <a href="<c:url value="/clients/viewclient/${client.clientid}" />"><button class="w3-btn w3-round w3-blue">View</button></a>
          <sec:authorize access="hasAnyRole('MANAGER','ADMIN')">
          <a href="<c:url value="/clients/editclient/${client.clientid}" />"><button class="w3-btn w3-round w3-cyan">Edit</button></a>
          <a href="<c:url value="/clients/removeclient/${client.clientid}" />"><button class="w3-btn w3-round w3-teal" onclick="return confirm('Are you sure you want to delete this user?');">Delete</button></a>
          </sec:authorize>
        </td>  
      </tr>  
    </c:forEach>  
  </table> 

  <div class="w3-padding-8">
    <ul class="w3-pagination">
      <c:forEach begin="1" end="${pages}" varStatus="p">  
        <li><a class="<c:if test="${p.index eq page}">w3-indigo</c:if>" href="<c:url value="/clients/viewclients/${p.index}" />">${p.index}</a></li>
      </c:forEach>
    </ul>
  </div>
    
  </div>

<%@ include file="theme/footer.jsp" %>
