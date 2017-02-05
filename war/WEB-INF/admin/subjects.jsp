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
	<div class="container">
		<div class="row">
			<div class="col s12">
				<div class="collection">
					 <a
						href="/ca/admin/cbt/topic?subject=account" class="collection-item">Account</a>
						<a
						href="/ca/admin/cbt/topic?subject=agricultural%20science" class="collection-item">Agricultural science</a>
					<a href="/ca/admin/cbt/topic?subject=biology"
						class="collection-item">Biology</a> <a
						href="/ca/admin/cbt/topic?subject=chemistry"
						class="collection-item">Chemistry</a><a
						href="/ca/admin/cbt/topic?subject=commerce"
						class="collection-item">Commerce</a><a
						href="/ca/admin/cbt/topic?subject=crk" class="collection-item">C.R.K</a>
					<a href="/ca/admin/cbt/topic?subject=economics"
						class="collection-item">Economics</a><a
						href="/ca/admin/cbt/topic?subject=english" class="collection-item">English</a><a
						href="/ca/admin/cbt/topic?subject=government"
						class="collection-item">Government</a> <a
						href="/ca/admin/cbt/topic?subject=geography"
						class="collection-item">Geography</a><a
						href="/ca/admin/cbt/topic?subject=history" class="collection-item">History</a>
					<a href="/ca/admin/cbt/topic?subject=mathematics"
						class="collection-item">Mathematics</a><a href="/ca/admin/cbt/topic?subject=literature"
						class="collection-item">Literature</a><a
						href="/ca/admin/cbt/topic?subject=physics" class="collection-item">Physics</a>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/materialize-tags.min.js"></script>
	<script src="/js/admin.js"></script>

</body>
</html>