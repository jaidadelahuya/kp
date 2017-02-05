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
		<form action="/ca/admin/cbt/s/questions/edit">
			<div class="card">
				<div class="card-content">
				<span class="card-title">Edit Question</span>
					<div class="row">
						<div class="input-field col s4">
							<select name="vendor">
								<option value="UTME">UTME</option>
							</select> <label>Vendor</label>
						</div>
						<div class="input-field col s4">
							<%@ include file="/pages/partials/year.html"%>
						</div>
						<div class="input-field col s4">
							<%@ include file="/pages/partials/subjects.html"%>
						</div>
					</div>
					<div class="row">
						<div class="col s4">
							<button type="submit" class="waves-effect waves-light btn">Continue</button>
						</div>
					</div>
					
				</div>
			</div>
		</form>
	</div>







	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/admin.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();
		});
	</script>
</body>
</html>