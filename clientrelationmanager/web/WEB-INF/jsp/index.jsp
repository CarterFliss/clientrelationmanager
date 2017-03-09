<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sec:authorize access="isAnonymous()">
    <c:redirect url="/login"/>
</sec:authorize>

<sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')">

    <%@ include file="theme/header.jsp" %>
    <header class="w3-container" style="padding-top:22px">
        <h5><b><i class="fa fa-dashboard"></i>Home</b></h5>
    </header>

    <div class="w3-row-padding w3-margin-bottom">
        <sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')">
            <a href="<c:url value="/clients/viewclients/" />"><button class="w3-btn w3-round w3-teal">View Clients</button></a>
            <a href="<c:url value="/eventlog/vieweventlog/" />"><button class="w3-btn w3-round w3-cyan">View Event Log</button></a>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/users/viewusers/" />"><button class="w3-btn w3-round w3-blue">View Users</button></a>
        </sec:authorize>

        <a href="#" onclick="logoutFormSubmit();" class="w3-padding"><i class="fa fa-sign-out fa-fw"></i>  Logout</a><br><br>

        <form action="<c:url value="/j_spring_security_logout" />" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        
        <div class="w3-card w3-indigo w3-half w3-row-padding">
            <ul>
                <li><sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')"><a href="<c:url value="/clients/viewclients/" />"></sec:authorize>${clientscount} Clients</a></li>
                <li><sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')"><a href="<c:url value="/eventlog/vieweventlog/" />"></sec:authorize>${EventCount} Events</a></li>
                <sec:authorize access="hasRole('ADMIN')"><li><a href="<c:url value="/users/viewusers/" />">${userscount} Users</a></li></sec:authorize>
            </ul>
        </div>
        
        <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white w3-half">  
    <tr>
      <th>Date</th>
      <th>Client Name</th>
      <th>Username</th>
      <th>Event</th>
    </tr>  

    <c:forEach var="eventlogs" items="${eventlog}">   
      <tr>  
        <td>${eventlogs.date}</td>
        <td><sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')"><a href="<c:url value="/clients/viewclient/${eventlogs.clientid}" />">${eventlogs.clientFirstName} ${eventlogs.clientLastName}</a></sec:authorize></td>
        <td><sec:authorize access="hasRole('ADMIN')"><a href="<c:url value="/users/viewuser/${eventlogs.userid}" />"></sec:authorize>${eventlogs.username}</a></td>
        <td>${eventlogs.interaction}</td>
      </tr>  
    </c:forEach>  
  </table> 

  <div class="w3-padding-8">
    <ul class="w3-pagination">
      <c:forEach begin="1" end="${pages}" varStatus="p">  
        <li><a class="<c:if test="${p.index eq page}">w3-indigo</c:if>" href="<c:url value="/eventlog/vieweventlog/${p.index}" />">${p.index}</a></li>
      </c:forEach>
    </ul>
  </div>


        <%@ include file="theme/footer.jsp" %>
    </sec:authorize>
    