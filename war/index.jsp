<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kareer Plus</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->

<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/dashboard.css">


</head>
<body class="home-body">

	<div class="container-fluid" id="main">
		<c:choose>
			<c:when test='${toSignUp}'>
				<%@ include file="/pages/partials/mobile-sign-up.html"%>
			</c:when>
			<c:otherwise>
				<div class="row hidden-sm hidden-md hidden-lg front-page-header">
					<div class="mobile-logo">Kareer Plus</div>
				</div>
				<div class="row hidden-sm hidden-md hidden-lg">
					<div class="login-div">
						<div>
							<form method="post">
								<div>
									<input type="text" placeholder="email"
										autofocus="autofocus" class="mobile-control" name="username" />
								</div>
								<div>
									<input type="password" placeholder="password"
										class="mobile-control" name="password" />
								</div>
								<div>
									<input type="button" value="login"
										class="mobile-control ca-btn-primary btn-sm login-button"
										style="margin-top: 3%; padding: 1%;" />
								</div>
							</form>
							<div style="text-align: center">
								<input type="button" value="Create New Account"
									class="mobile-new-account  btn-primary" id="new-account-button" />
							</div>
							<div style="text-align: center; font-size: 8pt; margin-top: 2%;">
								<a href="<c:url value='/password-recovery'/>"
									style="text-decoration: none; margin: 2%;">Forgot your
									password?</a>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="row hidden-xs front-page-header">
			<div class="col-sm-4 col-md-5 col-md-offset-1">
				<div class="front-page-logo">Kareer Plus</div>
			</div>
			<div class="col-sm-8 col-md-6" style="padding-top: 10px;">
				<form id="auth-form" method="post">
					<table>
						<tr>
							<td class="login-label">Email</td>
							<td class="login-label">Password</td>
							<td></td>
						</tr>
						<tr>
							<td><input type="text" id="username" name="username"
								class="login-input" autofocus="autofocus" /></td>
							<td><input type="password" id="password" name="password"
								class="login-input" /></td>
							<td><input type="button" value="Log in"
								class="ca-btn-primary login-button" /></td>
						</tr>
						<tr>
							<td class="login-text"><input type="checkbox" /> Keep me
								logged in</td>
							<td class="login-text"><a
								style="color: white; text-decoration: none;"
								href="<c:url value='/password-recovery'/>">Forgot your
									password?</a></td>
							<td></td>
					</table>

				</form>
			</div>
		</div>
		<c:choose>
			<c:when test="${toSignUp}">
				<div class="row hidden-xs" id="index-body">
					<div class="col-sm-8 col-md-6 col-lg-4 col-centered">
						<div id="sign-up-header" class="row"
							style="text-align: left; color: #983b59;">
							<span class="col-sm-12" style="font-size: 24pt">Sign Up</span>
						</div>
						<form method="post" id="register-user-form">
							<div class="row">
								<div class="form-group col-sm-6">
									<input type="text" class="form-control sign-up-input"
										id="first-name" placeholder="First Name" name="first-name" />
								</div>
								<div class="form-group col-sm-6">
									<input type="text" class="form-control sign-up-input"
										id="last-name" placeholder="Last Name" name="last-name" />
								</div>
							</div>
							<!-- 
							<div class="row">
								<div class="form-group col-sm-6">
									<input type="radio" name="username-mode" value="phone"
										id="use-phone" /> Use mobile number
								</div>
								<div class="form-group col-sm-6">
									<input type="radio" name="username-mode" value="email"
										id="use-email" /> Use email
								</div>
							</div> -->
							<div class="row">
								<div class="form-group col-sm-12">
									<input class="form-control sign-up-input" id="userid"
										name="userid" placeholder="Email"
										style="width: 100%;" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-12">
									<input type="password" class="form-control sign-up-input"
										name="password1" id="pass1" placeholder="Password" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-12">
									<input type="password" class="form-control sign-up-input"
										name="password2" id="pass2" placeholder="Re-enter Password" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-4">
									<input type="radio" name="gender" value="female" id="female" />
									Female
								</div>
								<div class="form-group col-sm-4">
									<input type="radio" name="gender" value="male" id="male" />
									Male
								</div>
								<div class="form-group col-sm-4"></div>
							</div>
							<input type="hidden" name="using-username" value="false" />
							<div class="row">
								<div class="col-sm-10 form-group" id="terms-div">
									By clicking Sign Up, you agree to our <a href="#">terms</a> and
									you have read our <a href="#">data policy</a>
								</div>
								<div class="col-sm-2"></div>
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<input type="button" class="ca-btn-primary btn-lg" id="sign-up"
										value="Sign Up" />
								</div>
								<div class="col-sm-8" id="reg-error-div"></div>
							</div>

						</form>

					</div>
				</div>
			</c:when>

			<c:when test="${empty editingUsername}">
				<div class="row hidden-xs" id="index-body">
					<div class="hidden-sm col-md-1"></div>
					<div class="col-sm-6 col-md-5">
						<div class="row" id="slogan-div" style="margin-right: 5%;">
							<span style="color: #983b59">Career development and Social
								network tailored to work together.</span>
						</div>
						<div class="row" id="slider-div" style="margin-right: 5%;">
							<div class="carousel slide hidden-xs" id="myCarousel"
								data-ride='carousel'
								style="padding: 0; margin: 0 auto; width: 55%; background-color: orange">


								<div class="carousel-inner" role="listbox" style="margin: 0">
									<div class="item active" style="margin: 0">
										<img alt="" src="/images/cbt-ready.jpg" class="slider-img">
									</div>

									<div class="item">
										<img alt="" src="/images/talent-ready.jpg" class="slider-img">
									</div>

									<div class="item">
										<img alt="" src="/images/career-ready.jpg" class="slider-img">
									</div>

									<div class="item">
										<img alt="" src="/images/personal-ready.jpg"
											class="slider-img">
									</div>

									<div class="item">
										<img alt="" src="/images/skills-ready.jpg" class="slider-img">
									</div>

									<div class="item">
										<img alt="" src="/images/brain-ready.jpg" class="slider-img">
									</div>
									<div class="item">
										<img alt="" src="/images/values-ready.jpg" class="slider-img">
									</div>

									<div class="item">
										<img alt="" src="/images/forum-ready.jpg" class="slider-img">
									</div>

								</div>

								<a class="left carousel-control" href="#myCarousel"
									role="button" data-slide="prev"> <span
									class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
									<span class="sr-only">Previous</span>
								</a> <a class="right carousel-control" href="#myCarousel"
									role="button" data-slide="next"> <span
									class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>

						</div>
						
					</div>
					<div class="col-sm-6 col-md-4">
						<div id="sign-up-header" class="row"
							style="text-align: left; color: #983b59;">
							<span class="col-sm-12" style="font-size: 24pt">Sign Up</span>
						</div>
						<form method="post" id="register-user-form">
							<div class="row">
								<div class="form-group col-sm-6">
									<input type="text" class="form-control sign-up-input"
										id="first-name" placeholder="First Name" name="first-name" />
								</div>
								<div class="form-group col-sm-6">
									<input type="text" class="form-control sign-up-input"
										id="last-name" placeholder="Last Name" name="last-name" />
								</div>
							</div>
							<!-- 
							<div class="row">

								<div class="form-group col-sm-6">
									<input type="radio" name="username-mode" value="phone"
										id="use-phone" checked="checked" /> Use mobile number
								</div>
								<div class="form-group col-sm-6">
									<input type="radio" name="username-mode" value="email"
										id="use-email" /> Use email
								</div>
							</div> -->
							<div class="row">
								<div class="form-group col-sm-12">
									<input class="form-control sign-up-input" id="userid"
										type="email" placeholder="Email" name="userid"
										style="width: 100%;" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-12">
									<input type="password" class="form-control sign-up-input"
										name="password1" id="pass1" placeholder="Password" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-12">
									<input type="password" class="form-control sign-up-input"
										name="password2" id="pass2" placeholder="Re-enter Password" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-4">
									<input type="radio" name="gender" value="female" id="female" />
									Female
								</div>
								<div class="form-group col-sm-4">
									<input type="radio" name="gender" value="male" id="male" />
									Male
								</div>
								<div class="form-group col-sm-4"></div>
							</div>
							<input type="hidden" name="using-username" value="false" />
							<div class="row">
								<div class="col-sm-10 form-group" id="terms-div">
									By clicking Sign Up, you agree to our <a href="#">terms</a> and
									you have read our <a href="#">data policy</a>
								</div>
								<div class="col-sm-2"></div>
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<input type="button" class="ca-btn-primary btn-lg" id="sign-up"
										value="Sign Up" />
								</div>
								<div class="col-sm-8" id="reg-error-div"></div>
							</div>

						</form>
					</div>
					<div class="hidden-sm col-md-2"></div>
				</div>
			</c:when>
			<c:when test="${editingUsername}">
				<div class="row hidden-xs" id="index-body">
					<div class="col-sm-8 col-md-6 col-lg-4 col-centered">
						<div id="sign-up-header" class="row"
							style="text-align: left; color: #983b59;">
							<span class="col-sm-12" style="font-size: 24pt">Sign Up</span>
						</div>
						<form method="post" id="register-user-form">
							<div class="row">
								<div class="form-group col-sm-6">
									<input type="text" class="form-control sign-up-input"
										id="first-name" placeholder="First Name" name="first-name"
										value="${registrationForm.firstName}" />
								</div>
								<div class="form-group col-sm-6">
									<input type="text" class="form-control sign-up-input"
										id="last-name" placeholder="Last Name" name="last-name"
										value="${registrationForm.lastName}" />
								</div>
							</div>
							<div class="row">
								<c:choose>
									<c:when test="${usingMobile}">
										<div class="form-group col-sm-6">
											<input type="radio" name="username-mode" value="phone"
												id="use-phone" checked="checked" /> Use mobile number
										</div>
										<div class="form-group col-sm-6">
											<input type="radio" name="username-mode" value="email"
												id="use-email" /> Use email
										</div>
									</c:when>
									<c:when test="${usingEmail}">
										<div class="form-group col-sm-6">
											<input type="radio" name="username-mode" value="phone"
												id="use-phone" /> Use mobile number
										</div>
										<div class="form-group col-sm-6">
											<input type="radio" name="username-mode" value="email"
												id="use-email" checked="checked" /> Use email
										</div>
									</c:when>
								</c:choose>

							</div>
							<div class="row">
								<div class="form-group col-sm-12">
									<c:choose>
										<c:when test="${usingMobile}">
											<input class="form-control sign-up-input" id="userid"
												type="tel" name="userid" placeholder="" style="width: 100%;"
												value="${registrationForm.username}" />
										</c:when>
										<c:otherwise>
											<input class="form-control sign-up-input" id="userid"
												type="email" name="userid" placeholder="Email"
												style="width: 100%;" value="${registrationForm.username}" />
										</c:otherwise>
									</c:choose>

								</div>
							</div>
							<script type="text/javascript">
								var x = document.getElementById("userid")
										.select();
							</script>
							<div class="row">
								<div class="form-group col-sm-12">
									<input type="password" class="form-control sign-up-input"
										name="password1" id="pass1" placeholder="Password" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-12">
									<input type="password" class="form-control sign-up-input"
										name="password2" id="pass2" placeholder="Re-enter Password" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-4">
									<input type="radio" name="gender" value="female" id="female" />
									Female
								</div>
								<div class="form-group col-sm-4">
									<input type="radio" name="gender" value="male" id="male" />
									Male
								</div>
								<div class="form-group col-sm-4"></div>
							</div>
							<input type="hidden" name="using-username" value="false" />
							<div class="row">
								<div class="col-sm-10 form-group" id="terms-div">
									By clicking Sign Up, you agree to our <a href="#">terms</a> and
									you have read our <a href="#">data policy</a>
								</div>
								<div class="col-sm-2"></div>
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<input type="button" class="ca-btn-primary btn-lg" id="sign-up"
										value="Sign Up" />
								</div>
								<div class="col-sm-8" id="reg-error-div"></div>
							</div>

						</form>

					</div>
				</div>
			</c:when>

		</c:choose>

	</div>

	<form action="/azure/fbook" method="post" id="login-form">
		<input id="name" name="name" type="hidden" /> <input id="picture"
			name="picture" type="hidden" /> <input id="cover" name="cover"
			type="hidden" /> <input id="access" name="access" type="hidden" />
	</form>

	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/validators.js"></script>
	<script type="text/javascript" src="/js/index.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>



</body>
</html>