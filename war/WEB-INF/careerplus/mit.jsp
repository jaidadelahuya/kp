<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Multiple Intelligence Test</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/dashboard.css">
<style type="text/css">
.cont-button {
	position: relative;
	top: 22%;
}
</style>
<body style="padding-top: 0px; height: 100%;" id="body" class="test-grad">
	<div class="container-fluid topmost-container" id="div-1"
		style="padding-right: 0px;">
		<div class="row">
			<div class="test-board-header">
				<div class="hidden-xs col-sm-2" style="height: 4.5em">
					<img class="img-circle img-responsive profile-img to-pointer" />
				</div>

				<div class="col-xs-12 col-sm-8 heading-div" style="height: 4.5em">
					<h2 class="heading">Multiple Intelligence Test</h2>
				</div>
				<div class="hidden-xs col-sm-2 col-md-2" id="save-div"
					style="height: 4.5em; padding-bottom: 1%;"></div>
			</div>
		</div>


		<div id="cc-div-content" class="row">
			<div class="row test-board-question">
				<div id="div-q" class="col-sm-12"></div>
			</div>
			<div class="row test-board-alternative">
				<div class="col-sm-12">
					<input type="radio" name="opt" class="opt" id="opt-4" value="4" />
					<span id="opt-a" class="test-board-option">Mostly agree</span>
				</div>
			</div>
			<div class="row test-board-alternative">
				<div class="col-sm-12">
					<input type="radio" name="opt" class="opt" id="opt-3" value="3" />
					<span id="opt-b" class="test-board-option">Slightly agree</span>
				</div>
			</div>
			<div class="row test-board-alternative">
				<div class="col-sm-12">
					<input type="radio" name="opt" class="opt" id="opt-2" value="2" />
					<span id="opt-c" class="test-board-option">Slightly disagree</span>
				</div>
			</div>
			<div class="row test-board-alternative">
				<div class="col-sm-12">
					<input type="radio" name="opt" class="opt" id="opt-1" value="1" />
					<span id="opt-d" class="test-board-option">Mostly disagree</span>
				</div>
			</div>

			<div class="row test-board-button-group">
				<div class="col-sm-12">
					<div class="btn-group-xs test-btn-grp" role="group"
						id="test-board-btn-grp"></div>
				</div>
			</div>

		</div>


		<div class="row test-board-footer">
			<div class="col-sm-12">
				<button id="back" class="btn ca-btn-primary  test-board-button">Back</button>
				<button id="next" class="btn ca-btn-primary  test-board-button">Next</button>
				<button id="finish"
					class="btn ca-btn-primary pull-right  test-board-button">Finish</button>
			</div>
		</div>
	</div>
	
	<!-- Multiple Intelligence description modal -->
	<div id="multiple-intelligence-dialog" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #3b5998;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel" style="color: white;">Multilple
						Intelligence Type</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h2 id="intelligence-type-header"></h2>
							<hr></hr>
							<h4>Description</h4>
							<p id="intelligence-type-description"></p>
							<h4>Possible careers</h4>
							<p id="intelligence-type-typical-roles"></p>
							<h4>Activities you excel at</h4>
							<p id="intelligence-type-related-activities"></p>
							<h4>Your preferred learning style</h4>
							<p id="intelligence-type-preferred-learning-style"></p>
							

						</div>
					</div>
				</div>
				<div class="modal-footer" style="background-color: #3b5998;">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				</div>
			</div>
		</div>
	</div>
	
	<!-- End test modal -->
	<div id="end-test-dialog" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #3b5998;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel" style="color: white;">End
						Test confirmation</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h3 style="color: red; font-family: tahoma">END TEST?</h3>
							<p>
								<small>click the submit button to end the test.</small>
							</p>
						</div>
					</div>
				</div>
				<div class="modal-footer" style="background-color: #3b5998;">
					<button class="btn ca-btn-primary btn-sm submit-test">Submit</button>
					<button type="button" class="btn btn-default btn-sm"
						data-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>
	
	<!-- save successful modal -->
	<div id="save-success-dialog" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #3b5998;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel" style="color: white;">Result Saved</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h3 style="color: #983b59; font-family: tahoma">Save Sucessful</h3>
							<p><small>Click close window to go back to welcome page</small></p>
						</div>
					</div>
				</div>
				<div class="modal-footer" style="background-color: #3b5998;">
					<button class="btn ca-btn-primary btn-sm close-window">Close window</button>
					<button type="button" class="btn btn-default btn-sm"
						data-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>
	
	
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/canvasjs.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/index.js"></script>
	<script type="text/javascript" src="/js/career-explora-tests.js"></script>
	<script type="text/javascript" src="/js/methods.js"></script>
	<script type="text/javascript" src="/js/mit.js"></script>
</body>
</html>