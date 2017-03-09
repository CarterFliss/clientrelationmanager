<%-- 
    Document   : editevent
    Created on : Jan 26, 2017, 7:19:46 PM
    Author     : Carter
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ include file="theme/header.jsp" %>

<header class="w3-container" style="padding-top:22px">
  <h5><b><i class="fa fa-dashboard"></i> Edit Event</b></h5>
</header>

<div class="w3-row-padding w3-half w3-margin-bottom">

  <div class="w3-card-4">
    <div class="w3-container w3-blue">
      <h2>Event Information</h2>
    </div>

    <form:form method="POST" action="/clientrelationmanager/eventlog/editsave" cssClass="w3-container" commandName="eventlog">
      <form:hidden path="eventid"  />
      <div class="w3-padding-8">
          <label><b>Client Name</b></label>
        <form:select path="clientid" cssClass="w3-input w3-border"  >
        <form:option value="-1"> Select Client</form:option>
        <form:options items="${eventlog.clients}"></form:options>
        </form:select>
        <form:errors path="clientid" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Client First Name</b></label>
        <form:input path="clientFirstName" cssClass="w3-input w3-border"  />
        <form:errors path="clientFirstName" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Client Last Name</b></label>
        <form:input path="clientLastName" cssClass="w3-input w3-border"  />
        <form:errors path="clientLastName" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
          <label><b>Username</b></label>
        <form:select path="userid" cssClass="w3-input w3-border"  >
        <form:option value="-1"> Select user</form:option>
        <form:options items="${eventlog.users}"></form:options>
        </form:select>
        <form:errors path="userid" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Username</b></label>
        <form:input path="username" cssClass="w3-input w3-border"  />            
        <form:errors path="username" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Event Description</b></label>
        <form:textarea path="interaction" cssClass="w3-input w3-border"  />
        <form:errors path="interaction" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      <div class="w3-padding-8">
        <label><b>Date</b></label>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
                <link rel="stylesheet" href="/resources/demos/style.css">
                <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
                <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
                <script>
                    $(function () {
                        $("#date").datepicker();
                    });
                </script>
        <form:input path="date" cssClass="w3-input w3-border"  />
        <form:errors path="date" cssClass="w3-red w3-padding-8 w3-panel" cssStyle="display: block; width: 100%; font-weight:bold;" />
      </div>
      
      <div class="w3-padding-8">
        <button type="submit" class="w3-btn w3-padding w3-blue" style="width:120px">Save</button>
      </div>
    </form:form>
    
  </div>
</div>

<%@ include file="theme/footer.jsp" %>