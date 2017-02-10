<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${userProfile.firstName}" /> <c:out
		value="${userProfile.lastName}" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/style/materialize.min.css">
<link rel="stylesheet" href="/style/materialize-tags.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="/style/jquery.webui-popover.css">
<link rel="stylesheet" href="/style/dashboard.css">
<link rel="stylesheet" href="/style/main.css">
<link rel="stylesheet" href="/style/media-queries.css">
<style type="text/css">
.report {
	margin-top: 7px;
	font-family: calibri;
	font-size: 9pt;
	color: #519be9;
	cursor: pointer
}
</style>
</head>
<body style="background-color: #f1f3f7">

	<%@ include file="/WEB-INF/main-nav.html"%>
	<%@ include file="/WEB-INF/profile/home.html"%>
	<%@ include file="/pages/partials/send-message-modal.html"%>
	<c:choose>
		<c:when test="${userProfile.currentUser}">
			<div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
				<input type="hidden" value="" class="person-image">
				<input type="hidden" value="" class="person-name">
				<input type="hidden" value="" class="person-webkey">
				<a href="<c:url value="/azure/profile?edit=true" />"
					class="btn-floating btn-large red"> <i
					class="large material-icons">mode_edit</i>
				</a>
			</div>
		</c:when>
		<c:otherwise>
			<div class="fixed-action-btn fab" style="bottom: 45px; right: 24px;">
				<input type="hidden" class="person-image"
					value="${userProfile.picture}">
				<input type="hidden" class="person-webkey"
					value="${userProfile.webKey}">
				<input type="hidden" class="person-name"
					value="${userProfile.firstName} ${userProfile.lastName}">

				<c:choose>
					<c:when test='${userProfile.friend}'>
						<a class="btn-floating btn-large blue message"> <i
							class="material-icons">message</i>
						</a>
					</c:when>
					<c:when test='${userProfile.follow}'>
						<a class="btn-floating btn-large red"> <i
							class="material-icons">&#xE5D4;</i>
						</a>

						<ul>

							<li><a class="btn-floating green friend-request"><i
									class="material-icons">add</i></a></li>
							<li><a class="btn-floating blue message"><i
									class="material-icons">message</i></a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<a class="btn-floating btn-large red"> <i
							class="material-icons">&#xE5D4;</i>
						</a>

						<ul>
							<li><a class="btn-floating purple darken-1 follow"><i
									class="material-icons">directions_walk</i></a></li>
							<li><a class="btn-floating green friend-request"><i
									class="material-icons">add</i></a></li>
							<li><a class="btn-floating blue message"><i
									class="material-icons">message</i></a></li>
						</ul>
					</c:otherwise>
				</c:choose>


			</div>
		</c:otherwise>
	</c:choose>


	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/jquery.webui-popover.js"></script>
	<script type="text/javascript" src="/js/main.js"></script>
	<script type="text/javascript" src="/js/send-message.js"></script>
	<script type="text/javascript" src="/js/my-profile.js"></script>

</body>
</html>