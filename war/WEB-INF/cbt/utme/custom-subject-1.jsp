<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CBT</title>
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
.category-header {
	text-align: center;
	color: white;
	text-shadow: blue;
}

.error-div {
	font-size: 9pt;
	color: red
}
</style>
</head>
<body style="background-color: #f1f3f7">
	<%@ include file="/WEB-INF/main-nav.html"%>
	<div class="center-div">

		<div id="home-center-div" class="center-main-content">
			<div class="row">
				<div class="col s12 m12">
					<div class="card">
						<div class="card-content">
							<span class="card-title">Custom UTME Subject Test I</span><br>
							<small>Complete the form to take a test. All fields are
								required.</small>

							<form action="<c:url value='/azure/cbt/get' />">
								<input type="hidden" name="title" value="Custom UTME Subject Test I" />
								<input type="hidden" name="vendor" value="UTME" />
								<div class="row">
									<div class="input-field col s6">
										<%@ include file="/pages/partials/subjects.html"%>
									</div>
									<div class="input-field col s6">
										<input id="time" name="time" type="number" class="validate"
											required="required"> <label
											data-error="Time should be in minutes" data-success="right"
											for="time">How long should this test take (mins)?</label>
									</div>
								</div>


								<div class="row">
									<div class="input-field col s6">
										<input name="question" id="questions" type="number" min="10"
											max="100" required="required" class="validate"> <label
											for="questions"
											data-error="Min. number = 10 and Max. number = 100"
											data-success="right">How many questions do you want
											to attempt?</label>
									</div>
								</div>

								<div class="row">

									<div class="input-field col s12">
										<button type="submit" 
											class="ca-btn-primary ca-btn-lg launchCBT">Continue</button>
									</div>
								</div>
							</form>
						</div>
					</div>



				</div>
			</div>
		</div>


		<script src="/js/jquery-1.11.2.min.js"></script>
		<script src="/js/materialize.min.js"></script>
		<script src="/js/jquery.webui-popover.js"></script>
		<script type="text/javascript" src="/js/main.js"></script>
		<script type="text/javascript" src="/js/cbt1.js"></script>
</body>
</html>