<%@ page language="java" contentType="text/html; charset=UTF-8; encoding=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>

<spring:message code="title" var="titel" />
<spring:message code="title.selectStadium" var="selectStadium" />

<spring:message code="label.stadiums" var="labelStadions" />

<spring:message code="btn.select.stadium" var="selecteerKnop" />

<meta charset="UTF-8">
<title>${titel} - ${selectStadium}</title>
<link rel="stylesheet" href="/css/bulma.css" type="text/css" />
<link rel="stylesheet" href="/css/ownStyle.css" type="text/css" />
</head>
<body>
	<nav class="navbar">
		<div class="navbar-menu">
			<div class="navbar-end">
				<div class="navbar-item">
					<form action='/logout' method='post'>
						<button class="button is-primary is-responsive">Uitloggen</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
	</nav>
	<div class="box opmaakBox">
		<c:if test="${not empty param.uitverkocht && param.uitverkocht == true}">
			<p class="notification notifEdit is-danger is-light errorMsg">${uitverkochtMessage}</p>
		</c:if>
		<c:if test="${not empty param.stadionBestaatNiet && param.stadionBestaatNiet == true}">
			<p class="notification notifEdit is-danger is-light errorMsg">${nietBestaandStadionMessage}</p>
		</c:if>
		<c:if test="${not empty param.tickets}">
			<p class="notification notifEdit is-success is-light">${aantalGekochteTicketsMessage}</p>
		</c:if>
		<h1 class="title">${titel}</h1>
		
		<form:form method="post" action="/fifa" modelAttribute="stadium">		
			<div class="field">
				<label class="label">${labelStadions}</label>
			  	<div class="control has-icons-left">
			   		<div class="select">
			   			<form:select multiple="false" path="naam">
							<%-- <form:options items="${stadiumList}" /> --%>
							<c:forEach items="${stadiumList}" var="stadium">
								<form:option value="${stadium.naam}" label="${stadium.naam}" />
							</c:forEach>
						</form:select>
			   		</div>
			   		<span class="icon is-left">
			   			<ion-icon name="football" style="color:black"></ion-icon>
			   		</span>
			  	</div>
			</div>
			<div class="field">
		  		<div class="control">
		    		<button class="button is-primary">${selecteerKnop}</button>
		    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		  		</div>
			</div>
		</form:form>
	</div>
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>