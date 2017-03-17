<%-- 
    Document   : edituser
    Created on : Jan 26, 2017, 7:20:08 PM
    Author     : Carter
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ include file="theme/header.jsp" %>

<header class="w3-container" style="padding-top:22px">
  <h5><b><i class="fa fa-dashboard"></i> Edit User</b></h5>
</header>

<div class="w3-row-padding w3-half w3-margin-bottom">

  <div class="w3-card-4">
    <div class="w3-container w3-blue">
      <h2>User Information</h2>
    </div>

    <form:form method="POST" action="/clientrelationmanager/users/editsave" cssClass="w3-container" commandName="users">
      <form:hidden path="userId"  />
      <div class="w3-padding-8">
        <label><b>Username</b></label>
        <form:input path="username" cssClass="w3-input w3-border"  />
        <form:errors path="username" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Password</b></label>
        <form:password path="password" cssClass="w3-input w3-border"  />
        <form:errors path="password" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>User Status</b></label>
        <form:select path="userStatus" cssClass="w3-input w3-border"  >
            <form:option value="1">Active</form:option>
            <form:option value="0">Inactive</form:option>
        </form:select>
        <form:errors path="userStatus" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
      <form:select path="userrole" cssClass="w3-input w3-border"  >
            <form:option value="ROLE_ADMIN">Administrator</form:option>
            <form:option value="ROLE_MANAGER">Manager</form:option>
            <form:option value="ROLE_USER">User</form:option>
      </form:select>
      <form:errors path="userrole" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      
      <div class="w3-padding-8">
        <button type="submit" class="w3-btn w3-padding w3-blue" style="width:120px">Save</button>
      </div>
    </form:form>
    
  </div>
</div>

<%@ include file="theme/footer.jsp" %>