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
				<div class="card-panel">
					<table class="bordered striped responsive-table">
						<thead>
							<tr>
								<th data-field="id">Vendor</th>
								<th data-field="name">Subject</th>
								<th data-field="price">Year</th>
								<th data-field="questions">No. Questions</th>
							</tr>
						</thead>

						<tbody>
							<c:set var="total" scope="session" value="${0}" />
							<c:forEach var="item" items="${questionStats}">
								<tr>
									<td>${item.vendor}</td>
									<td>${item.subject}</td>
									<td><a
										href="/ca/admin/cbt/s/questions/edit?vendor=${item.vendor}&year=${item.year}&subject=${item.subject}">${item.year}</a></td>
									<td>${item.noQ}</td>
									<c:set var="total" scope="session" value="${total+item.noQ}" />
								</tr>
							</c:forEach>
							<tr>
								<td colspan="3" style="text-align: right;"><strong>Total</strong></td>
								<td><c:out value="${total}" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/admin.js"></script>
</body>
</html>