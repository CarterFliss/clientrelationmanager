<%-- 
    Document   : home
    Created on : Feb 7, 2017, 6:20:13 PM
    Author     : Carter
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ include file="theme/header.jsp" %>

<header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i>Home</b></h5>
  </header>

<div class="w3-row-padding w3-margin-bottom">
    <sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')">
        <a href="<c:url value="/clients/viewclients/" />"><button class="w3-btn w3-round w3-blue">View Clients</button></a>
        <a href="<c:url value="/eventlog/vieweventlog/" />"><button class="w3-btn w3-round w3-blue">View Event Log</button></a>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <a href="<c:url value="/users/viewusers/" />"><button class="w3-btn w3-round w3-blue">View Users</button></a>
    </sec:authorize>
        
    <a href="#" onclick="logoutFormSubmit();" class="w3-padding"><i class="fa fa-sign-out fa-fw"></i>  Logout</a><br><br>
      
      <form action="<c:url value="/j_spring_security_logout" />" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
</div>
