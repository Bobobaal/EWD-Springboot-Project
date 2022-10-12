<%@ page language="java" contentType="text/html; charset=UTF-8; encoding=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>

<spring:message code="title" var="titel" />
<spring:message code="title.login" var="login" />
<spring:message code="label.username" var="username" />
<spring:message code="label.password" var="password" />

<meta charset="UTF-8">
<title>${titel} - ${login}</title>
<link rel="stylesheet" href="/css/bulma.css" type="text/css" />
<link rel="stylesheet" href="/css/ownStyle.css" type="text/css" />
</head>
<body>

        <div class="box opmaakBox">
		<p class="title titleSpacing">${titel}</p>
		<p class="subtitle is-3 titleSpacing">${login}</p>
		
		<c:if test="${not empty logout}">
			<spring:message code="${logout}" var="loggedOut" />
			<p class="notification notifEdit is-success is-light">${loggedOut}</p>
		</c:if>
		
		<c:if test="${not empty error}">
			<spring:message code="${error}" var="errorMsg" />
			<p class="notification notifEdit is-danger is-light errorMsg">${errorMsg}</p>
		</c:if>
		
		<form method="post" action="/login">
			<div class="field">
	  			<label class="label">${username}</label>
	  			<div class="control has-icons-left">
	    			<input name="username" type="text" class="input">
	    			<span class="icon is-small is-left">
	      				<ion-icon name="person-circle" class="blackIcon"></ion-icon>
	    			</span>
	  			</div>
			</div>
			
			<div class="field">
	  			<label class="label">${password}</label>
	  			<div class="control has-icons-left">
	    			<input name="password" type="password" class="input">
	    			<span class="icon is-small is-left">
	    				<ion-icon name="key" class="blackIcon"></ion-icon>
	    			</span>
	  			</div>
			</div>
			<div class="field">
			    <button class="button is-primary is-responsive">${login}</button>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>