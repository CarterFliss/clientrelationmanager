<%-- 
    Document   : editclient
    Created on : Jan 26, 2017, 7:19:32 PM
    Author     : Carter
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ include file="theme/header.jsp" %>

<header class="w3-container" style="padding-top:22px">
  <h5><b><i class="fa fa-dashboard"></i> Edit Client</b></h5>
</header>

<div class="w3-row-padding w3-half w3-margin-bottom">

  <div class="w3-card-4">
    <div class="w3-container w3-blue">
      <h2>Client Information</h2>
    </div>

    <form:form method="POST" action="/clientrelationmanager/clients/editsave" cssClass="w3-container" commandName="clients">
      <form:hidden path="clientid"  />
      <div class="w3-padding-8">
        <label><b>First Name</b></label>
        <form:input path="firstName" cssClass="w3-input w3-border"  />
        <form:errors path="firstName" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Last Name</b></label>
        <form:input path="lastName" cssClass="w3-input w3-border"  />
        <form:errors path="lastName" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Client Status</b></label>
        <form:select path="userStatus" cssClass="w3-input w3-border"  >
            <form:option value="Prospective">Prospective</form:option>
            <form:option value="Active">Active</form:option>
            <form:option value="Former">Former</form:option>
        </form:select>
        <form:errors path="userStatus" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Address</b></label>
        <form:input path="address" cssClass="w3-input w3-border"  />
        <form:errors path="address" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>City</b></label>
        <form:input path="city" cssClass="w3-input w3-border"  />
        <form:errors path="city" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
      <label><b>State</b></label>
        <form:input path="homeState" cssClass="w3-input w3-border"  />
        <form:errors path="homeState" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
      <label><b>ZIP Code</b></label>
        <form:input path="zip" cssClass="w3-input w3-border"  />
        <form:errors path="zip" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
      <label><b>Phone Number</b></label>
        <form:input path="phone" cssClass="w3-input w3-border"  />
        <form:errors path="phone" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <label><b>Email Address</b></label>
        <form:input path="email" cssClass="w3-input w3-border"  />
        <form:errors path="email" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      
      <div class="w3-padding-8">
        <button type="submit" class="w3-btn w3-padding w3-blue" style="width:120px">Save</button>
      </div>
    </form:form>
    
  </div>
</div>

<%@ include file="theme/footer.jsp" %>