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
    	<div class="row" style="margin: 2%;">
    		<div class="col s2">
    			<div class="card green-text text-darken-2" style="text-align: center;">
    				<div><a href="<c:url value='/ca/admin/user/form/create' />"><i class="medium material-icons">perm_identity</i></a></div>
    				<a href="<c:url value='/ca/admin/user/form/create' />">New User</a>
    			</div>
    		</div>
    		<div class="col s2">
    			<div class="card blue-text text-darken-2" style="text-align: center;">
    				<div><a href="<c:url value='/ca/admin/community/form/new' />"><i class="medium material-icons">loyalty</i></a></div>
    				<a href="<c:url value='/ca/admin/community/form/new' />">New Community</a>
    			</div>
    		</div>
    		<div class="col s2">
    			<div class="card red-text text-darken-2" style="text-align: center;">
    				<div><a href="<c:url value='/ca/admin/communities/get' />"><i class="medium material-icons">library_add</i></a></div>
    				<a href="<c:url value='/ca/admin/communities/get' />">New Unit</a>
    			</div>
    		</div>
    		<div class="col s2">
    			<div class="card orange-text text-darken-2" style="text-align: center;">
    				<div><a href="<c:url value='/ca/admin/discussion/i/form/new' />"><i class="medium material-icons">note_add</i></a></div>
    				<a href="<c:url value='/ca/admin/discussion/i/form/new' />">New Discussion</a>
    			</div>
    		</div>
    		
    		<div class="col s2">
    			<div class="card indigo-text text-darken-2" style="text-align: center;">
    				<div><a href="<c:url value='/ca/admin/cbt' />"><i class="medium material-icons">computer</i></a></div>
    				<a href="<c:url value='/ca/admin/cbt' />">CBT</a>
    			</div>
    		</div>
    		
    	</div>
    </div>
    
          


	


	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/materialize.min.js"></script>
	<script src="/js/admin.js"></script>
</body>
</html>