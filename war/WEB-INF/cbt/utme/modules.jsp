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
							<span class="card-title">UTME Exam modes</span><br> <small style="color: #983b59">Select
								an examination mode</small>

							<form action="<c:url value='/azure/cbt/utme/mode/select' />">
								
								<p style="margin: 2%;">
									<input checked="checked" name="mode" type="radio" id="test2" value="standard-subject" /> <label
										style="height: auto" for="test2"><strong
										style="color: black; font-weight: bold">Standard UTME
											subject test.</strong> <br>This mode simulates a UTME subject
										test. You select a subject and a year. The test duration is
										UTME default.</label>
								</p>
								<p style="margin: 2%;">
									<input class="with-gap" name="mode" type="radio" id="test3" value="custom-subject-1" />
									<label style="height: auto" for="test3"><strong
										style="color: black; font-weight: bold">Custom UTME
											subject test I</strong> <br>You select a subject, set the number
										of questions you want to attempt and the time you will like to
										complete the test.</label>
								</p>
								<p style="margin: 2%;">
									<input class="with-gap" name="mode" type="radio" id="test4" value="custom-subject-2" />
									<label style="height: auto" for="test4"><strong
										style="color: black; font-weight: bold">Custom UTME
											subject test II</strong><br> You select a subject, choose he
										topics you want the questions to be pulled from, and the time
										you will like to complete the test.</label>
								</p>
								<p style="margin: 2%;">
									<input name="mode" type="radio" id="test1" value="standard-utme"  disabled="disabled" /> <label
										style="height: auto" for="test1"><strong
										style="color: black; font-weight: bold">Standard UTME
											Examination</strong><br /> This mode simulates atypical UTME
										examination. English is compulsory and you have to select
										three other subjects and a year. The duration for the
										examination is 4hrs 30 minutes.</label>
								</p>
								<p style="margin: 2%;">
									<button id="cbt-back-1" class="ca-btn-primary ca-btn-lg">Back</button> <button type="submit"
										class="ca-btn-primary ca-btn-lg">Next</button>
								</p>

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
	<script type="text/javascript">
		$(document).ready(function(e) {
			
			$("#cbt-back-1").click(function(e) {
				e.preventDefault();
				window.location.assign("/azure/p/cbt");
			});
		});
	</script>
</body>
</html>