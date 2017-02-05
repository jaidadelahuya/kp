<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New English Passage</title>
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
				<div class="card">
					<form
						action="<c:url value='/ca/admin/cbt/english/passage/create' />"
						method="post">
						<div class="card-content">
							<span class="card-title">New English Passage</span>
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
							<div class="row">
								<div class="input-field col s6">
									<select class="validate" required="required" name="vendor">
										<option value="" disabled selected>Select a vendor</option>
										<option value="UTME">UTME</option>

									</select> <label data-error="required" data-success="right">Vendor</label>
								</div>
								<div class="input-field col s6">
									<%@ include file="/pages/partials/year.html"%>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<input id="topic" type="text" name="topic"> <label
										for="topic">Topic</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<textarea class="tiny" id="passage" name="passage"
										style="margin-top: 4%"></textarea>

								</div>
							</div>
						</div>
						<div class="card-action">
							<button type="submit" class="waves-effect waves-light btn">Continue</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>







	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/admin.js"></script>
	<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
	<script type="text/javascript">
		tinymce
				.init({
					selector : 'textarea',
					height : 300,
					theme : 'modern',
					plugins : [
							'advlist autolink lists link image charmap print preview hr anchor pagebreak',
							'searchreplace wordcount visualblocks visualchars code fullscreen',
							'insertdatetime media nonbreaking save table contextmenu directionality',
							'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc' ],
					toolbar1 : 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
					toolbar2 : 'print preview media | forecolor backcolor emoticons | codesample',
					image_advtab : true
					
					
				});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();
			
		});
	</script>

</body>
</html>