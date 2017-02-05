<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Question</title>
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
			<div class="card-panel">
				<h6 style="font-weight: bold">${fn:length(editQuestionsPage.questions)}
						questions found</h6> <br /> <span><strong>Subject: </strong> <c:out
							value="${editQuestionsPage.subject}" /><strong
						style="margin-left: 2%">Year: </strong> <c:out
							value="${editQuestionsPage.year}" /><strong
						style="margin-left: 2%">Vendor: </strong> <c:out
							value="${editQuestionsPage.vendor}" /></span>

				<table class="bordered striped responsive-table">
					<thead>
						<tr>
							<th data-field="id">Question Body</th>
							<th data-field="name">No. Topics</th>
							<th data-field="price"></th>
							<th data-field="pric"></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="item" items="${editQuestionsPage.questions}">
							<tr>
								<td>${item.body}</td>
								
								<td>${item.noTopics}</td>
								<td><a
									href="<c:url value='/ca/admin/cbt/questions/delete?web-key=${item.webKey}' />">Delete</a></td>
								<td><a
									href="<c:url value='/ca/admin/cbt/questions/get?web-key=${item.webKey}' />">Edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</div>
		</div>
	</div>







	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/admin.js"></script>
</body>
</html>