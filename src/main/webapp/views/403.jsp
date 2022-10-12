<%@ page language="java" contentType="text/html; charset=UTF-8; encoding=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>

<spring:message code="title" var="titel" />
<spring:message code="title.accessDenied" var="toegangGeweigerd" />

<meta charset="UTF-8">
<title>${titel} - ${toegangGeweigerd}</title>
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
	<h1 class="title is-1 denied">403: ${toegangGeweigerd}</h1>
</body>
</html>