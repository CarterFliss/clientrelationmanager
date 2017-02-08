<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sec:authorize access="isAnonymous()">
    <c:redirect url="/login"/>
</sec:authorize>

<sec:authorize access="hasAnyRole('USER','MANAGER','ADMIN')">
    <c:redirect url="/home"/>
</sec:authorize>

