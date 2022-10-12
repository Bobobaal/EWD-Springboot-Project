<%@ page language="java" contentType="text/html; charset=UTF-8; encoding=UTF-8"
    pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>

<spring:message code="title" var="titel" />
<spring:message code="title.stadium" var="stadion" />
<spring:message code="title.ticketsAvailable" var="ticketsBeschikbaar" />
<spring:message code="title.buyTickets" var="ticketsKopenT" />

<spring:message code="label.email" var="email" />
<spring:message code="label.ticketsAmount" var="aantalTickets" />
<spring:message code="label.voetbalCode1" var="vc1" />
<spring:message code="label.voetbalCode2" var="vc2" />

<spring:message code="btn.buyTickets" var="ticketsKopen" />

<meta charset="ISO-8859-1">
<title>${titel} - ${ticketsKopenT}</title>
<link rel="stylesheet" href="/css/bulma.css" type="text/css" />
<link rel="stylesheet" href="/css/ownStyle.css" type="text/css" />
</head>
<body>
	<div class="box opmaakBox">
		<p class="title titleSpacing">${titel}</p>
		<p class="subtitle titleSpacing">${stadion} ${stadiumNaam}</p>
		<p class="subtitle titleSpacing">${matchInfo}</p>
		<p class="subtitle titleSpacing">${ticketsBeschikbaar} ${aantalTicketsOver}</p>
		
		<form:form method="post" action="/fifa/${id}" modelAttribute="ticketsBestellen">
			<div class="field">
	  			<label class="label">${email}</label>
	  			<div class="control has-icons-left has-icons-right">
	    			<form:input path="email" class="input" type="email" />
	    			<form:errors element="div" path="email" cssClass="help notification is-danger is-light errorMsgForm"/>
	    			<span class="icon is-small is-right">
	    				<form:errors path="email" element="ion-icon" name="alert" cssStyle="color:red"/>
	    			</span>
	    			<span class="icon is-small is-left">
	      				<ion-icon name="mail" class="blackIcon"></ion-icon>
	    			</span>
	  			</div>
			</div>
			
			<div class="field">
	  			<label class="label">${aantalTickets}</label>
	  			<div class="control has-icons-left has-icons-right">
	    			<form:input path="aantalTickets" class="input" type="number" value="${ticketsBestellen.aantalTickets}" />
	    			<form:errors element="div" path="aantalTickets" cssClass="help notification is-danger is-light errorMsgForm"/>
	    			<span class="icon is-small is-right">
	    				<form:errors path="aantalTickets" element="ion-icon" name="alert" cssStyle="color:red"/>
	    			</span>
	    			<span class="icon is-small is-left">
	    				<ion-icon name="ticket" class="blackIcon"></ion-icon>
	    			</span>
	  			</div>
			</div>
			
			<div class="field">
	  			<label class="label">${vc1}</label>
	  			<div class="control has-icons-left has-icons-right">
	    			<form:input path="voetbalCode1" class="input" type="number" value="${ticketsBestellen.voetbalCode1}" />
	    			<form:errors element="div" path="voetbalCode1" cssClass="help notification is-danger is-light errorMsgForm"/>
	    			<span class="icon is-small is-right">
	    				<form:errors path="voetbalCode1" element="ion-icon" name="alert" cssStyle="color:red"/>
	    			</span>
	    			<span class="icon is-small is-left">
	    				<ion-icon name="barcode" class="blackIcon"></ion-icon>
	    			</span>
	  			</div>
			</div>
			
			<div class="field">
	  			<label class="label">${vc2}</label>
	  			<div class="control has-icons-left has-icons-right">
	    			<form:input path="voetbalCode2" class="input" type="number" value="${ticketsBestellen.voetbalCode2}" />
	    			<form:errors element="div" path="voetbalCode2" cssClass="help notification is-danger is-light errorMsgForm"/>
	    			<span class="icon is-small is-left">
	    				<ion-icon name="barcode" class="blackIcon"></ion-icon>
	    			</span>
	  			</div>
			</div>
			<div class="buttons has-addons is-centered">
			    <button class="button is-primary is-responsive">${ticketsKopen}</button>
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</div>
		</form:form>
	</div>

	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>