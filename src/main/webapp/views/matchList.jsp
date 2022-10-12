<%@ page language="java" contentType="text/html; charset=UTF-8; encoding=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

<spring:message code="date.format.pattern" var="dateFormatPattern" />
<spring:message code="time.format.pattern" var="timeFormatPattern" />

<spring:message code="title" var="titel" />
<spring:message code="title.stadium" var="stadion" />
<spring:message code="title.selectMatch" var="matchSelect" />

<spring:message code="th.number.full" var="nummerVolledig" />
<spring:message code="th.number.abbr" var="nummerKort" />
<spring:message code="th.match" var="wedstrijd" />
<spring:message code="th.date" var="datum" />
<spring:message code="th.timeKickOff" var="aftrap" />
<spring:message code="th.tickets" var="tickets" />

<meta charset="UTF-8">
<title>${titel} - ${matchSelect}</title>
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
		<spring:url value="/fifa/" var="fifaUrl" />
	
		<p class="title titleSpacing">${titel}</p>
		<p class="subtitle titleSpacing">${stadion}: ${stadiumNaam}</p>
		
		<table class="table is-bordered is-hoverable is-striped">
			<thead>
				<tr>
					<th><abbr title="${nummerVolledig}">${nummerKort}</abbr></th>
					<th><ion-icon name="flag"></ion-icon> ${wedstrijd}</th>
					<th><ion-icon name="calendar"></ion-icon> ${datum}</th>
					<th><ion-icon name="time"></ion-icon> ${aftrap}</th>
					<th><ion-icon name="ticket"></ion-icon> ${tickets}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${matchLijst}" var="match">
					<spring:message code="country.${match.wedstrijd.landen[0]}" var="country1" />
					<spring:message code="country.${match.wedstrijd.landen[1]}" var="country2" />
					<tr>
						<td><a href="${fifaUrl}${match.wedstrijd.wId}">${match.wedstrijd.wId}</a></td>
						<td>${country1} - ${country2}</td>
						<td><fmt:formatDate value="${match.wedstrijd.dagEnUur.time}" pattern="${dateFormatPattern}"/></td>
						<td><fmt:formatDate value="${match.wedstrijd.dagEnUur.time}" pattern="${timeFormatPattern}"/></td>
						<td>${match.tickets}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>