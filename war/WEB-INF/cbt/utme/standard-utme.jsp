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
							<span class="card-title">Standard UTME Examination</span><br>
							<small>Select a year and three subjects. English is
								compulsory</small>

							<form action="<c:url value='/azure/cbt/get' />">
								<input type="hidden" name="title" value="Standard UTME Examination" />
								<input type="hidden" name="vendor" value="UTME" />
								<div class="row">
									<div class="input-field col s6">
										<%@ include file="/pages/partials/year.html"%>
									</div>
									<div class="input-field col s6">
										<select multiple name="subject">
											<option value="" disabled selected>Select three subjects</option>
											<option value="account">Account</option>
											<option value="Biology">Biology</option>
											<option value="Chemistry">Chemistry</option>
											<option value="Commerce">Commerce</option>
											<option value="C.R.K">C.R.K</option>
											<option value="Economics">Economics</option>
											<option value="Government">Government</option>
											<option value="Geography">Geography</option>
											<option value="History">History</option>
											<option value="Mathematics">Mathematics</option>
											<option value="Physics">Physics</option>
										</select> <label>Subjects</label>
									</div>

									<div class="row">

										<div class="input-field col s12">
											<button type="submit" 
												class="ca-btn-primary ca-btn-lg launchCBT">Continue</button>
										</div>
									</div>
								</div>

							</form>
						</div>
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