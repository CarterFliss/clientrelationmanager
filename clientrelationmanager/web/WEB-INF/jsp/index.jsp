<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sec:authorize access="isAnonymous()">
    <c:redirect url="/login"/>
</sec:authorize>

<sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')">
  <c:url value="/j_spring_security_logout" var="logoutUrl" />
  

  <%@ include file="theme/header.jsp" %>

  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> Dashboard</b></h5>
  </header>

  <div class="w3-row-padding w3-margin-bottom">

  </div>
  
  <%@ include file="theme/footer.jsp" %>
</sec:authorize>

