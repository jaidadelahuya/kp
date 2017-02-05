<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CBT Modules</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/style/materialize.min.css">
<link rel="stylesheet" href="/style/materialize-tags.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body class="blue lighten-5">

	<%@ include file="/pages/partials/admin-nav.html"%>
	<div class="container">
		<div class="row">
			<div class="col s12">
				<div class="collection">
					<a href="<c:url value='/ca/admin/p/subjects' />"
						class="collection-item">New Question</a> <a href="/ca/admin/cbt/i/questions/edit"
						class="collection-item">Edit Question</a> <a
						href="/ca/admin/cbt/i/subject/new" class="collection-item">New
						Subject</a> <a href="#!" class="collection-item">Edit Subject</a><a
						href="#!" class="collection-item">Edit Topic</a> <a href="/ca/admin/cbt/i/english/passage/new"
						class="collection-item">New English Passage</a>
						<a href="/ca/admin/cbt/1/question/stats"
						class="collection-item">Question Stats</a>
				</div>
			</div>
		</div>
	</div>







	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/admin.js"></script>
</body>
</html>