<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Career Explora Admin Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/style/materialize.min.css">
<link rel="stylesheet" href="/style/materialize-tags.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body class="blue lighten-5">

	<%@ include file="/pages/partials/admin-nav.html"%>
	<div class="container"
		style="width: 60%; border: 1px solid #3b5998; padding: 3%; margin-top: 4%; background-color: white">
		<h4 style="text-align: center;">New Subject</h4>
		<c:choose>
			<c:when test="${ not empty formError}">
				<h5 style="color: white; background-color: red; padding: 2%;">
					<c:out value="${formError}" />
				</h5>
			</c:when>
			<c:when test="${ not empty formSuccess}">
				<h5 style="color: white; background-color: green; padding: 2%;">
					<c:out value="${formSuccess}" />
				</h5>
			</c:when>
		</c:choose>
		<form action="<c:url value='/ca/admin/cbt/subject/save' />"
			method="post">
			<div class="row">
				<div class="input-field col s12">
					<%@ include file="/pages/partials/subjects.html"%>
				</div>
			</div>
			<div class="row">
				
					<div class="row">
						<div class="input-field col s12">
							<textarea id="textarea1" class="materialize-textarea" name="topics"></textarea>
							<label for="textarea1">Topics (separate topics with ;)</label>
						</div>
					</div>
				
			</div>
			<br /> <br />
			<div class="row">
				<div class="col s6">
					<button class="btn waves-effect waves-light" type="submit"
						name="action">
						Submit <i class="material-icons right">send</i>
					</button>
				</div>
			</div>
		</form>


	</div>







	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/materialize-tags.min.js"></script>
	<script src="/js/admin.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();
		});
	</script>
</body>
</html>