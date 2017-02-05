<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Question Stats</title>
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
					<a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Account' />"
						class="collection-item">Account</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Agricultural Science' />"
						class="collection-item">Agricultural Science</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Biology' />"
						class="collection-item">Biology</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Chemistry' />"
						class="collection-item">Chemistry</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Commerce' />"
						class="collection-item">Commerce</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=C.R.K' />"
						class="collection-item">C.R.K</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Economics' />"
						class="collection-item">Economics</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=English' />"
						class="collection-item">English</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Government' />"
						class="collection-item">Government</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Geography' />"
						class="collection-item">Geography</a><a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=History' />"
						class="collection-item">History</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Mathematics' />"
						class="collection-item">Mathematics</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Literature' />"
						class="collection-item">Literature</a> <a
						href="<c:url value='/ca/admin/cbt/question/stats/get?subject=Physics' />"
						class="collection-item">Physics</a>
				</div>
			</div>
		</div>
	</div>

	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/admin.js"></script>
</body>
</html>