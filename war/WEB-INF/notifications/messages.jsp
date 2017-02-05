
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notifications</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/style/materialize.min.css">
<link rel="stylesheet" href="/style/materialize-tags.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="/style/jquery.webui-popover.css">
<link rel="stylesheet" href="/style/main.css">
<link rel="stylesheet" href="/style/media-queries.css">
<style type="text/css">
.sender-msg {
	min-width: 10%;
	max-width: 80%;
	display: inline-block;
}

.sender-msg div {
	background-color: #e3f2fd;
	margin: 0px !important;
	margin-bottom: 8px
}

.recipient-msg {
	min-width: 10%;
	max-width: 80%;
	float: right;
	display: inline-block;
	text-align: right;
}

.recipient-msg div {
	background-color: #ede7f6;
	margin: 0px !important;
	margin-bottom: 8px;
}
</style>
</head>
<body style="background-color: #f1f3f7">
	<%@ include file="/WEB-INF/main-nav.html"%>
	<%@ include file="/WEB-INF/notifications/messages.html"%>

	<div id="new-message-box" style="display: none">
		<div class="row">
			<div class="col s12">
				<div class="recipient-msg">
					<div class="card-panel">
						<div class="new-message"></div>
					</div>
					<span style="color: gray; font-family: calibri; font-size: 10pt">Now</span>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/jquery.webui-popover.js"></script>
	<script type="text/javascript" src="/js/main.js"></script>
	<script type="text/javascript" src="/js/notification.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			<c:if test="${empty messagePage.messages}">
			var x = '${messagePage.sender.name}';
			var $toastContent = $('<span>No conversation yet between you and '+x+' </span>');
			Materialize.toast($toastContent, 5000);
			</c:if>
		});
	</script>


</body>
</html>