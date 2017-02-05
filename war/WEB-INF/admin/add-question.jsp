<%@page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Question</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/dashboard.css">
<link type="text/css" rel="stylesheet" href="/style/waitMe.css">

</head>
<body>
	<%
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
	%>
	<div class="container">

		<div class="row">

			<div class="col-sm-12 col-md-offset-2 col-md-8">
				<h5 class="text-primary">New Question</h5>
				<c:choose>
					<c:when test="${not empty addQuestionError}">
						<div class="alert alert-danger">
							<c:out value="${addQuestionError}" />
						</div>
					</c:when>
					<c:when test="${not empty addQuestionSuccess}">
						<div class="alert alert-success">
							<c:out value="${addQuestionSuccess}" />
						</div>
					</c:when>
				</c:choose>

				<form
					action="<%=blobstoreService
					.createUploadUrl("/ca/admin/question/save")%>"
					method="post" enctype="multipart/form-data">
					<div class="form-group complex-form-section">
						<div class="row">
							<div class="col-sm-4">
								<label for="subject">Subject:</label> <select
									name="subject-name" id="subject" class="form-control">
									<option
										<c:if test='${subjectName eq "ACCOUNT"}'>selected</c:if>>ACCOUNT</option>
									<option
										<c:if test='${subjectName eq "AGRICULTURAL SCIENCE"}'>selected</c:if>>AGRICULTURAL
										SCIENCE</option>
									<option
										<c:if test='${subjectName eq "BIOLOGY"}'>selected</c:if>>BIOLOGY</option>
									<option
										<c:if test='${subjectName eq "CHEMISTRY"}'>selected</c:if>>CHEMISTRY</option>
									<option
										<c:if test='${subjectName eq "COMMERCE"}'>selected</c:if>>COMMERCE</option>
									<option <c:if test='${subjectName eq "C.R.K"}'>selected</c:if>>C.R.K</option>
									<option
										<c:if test='${subjectName eq "ECONOMICS"}'>selected</c:if>>ECONOMICS</option>
									<option
										<c:if test='${subjectName eq "ENGLISH"}'>selected</c:if>>ENGLISH</option>
									<option
										<c:if test='${subjectName eq "FINE ART"}'>selected</c:if>>FINE
										ART</option>
									<option
										<c:if test='${subjectName eq "GEOGRAPHY"}'>selected</c:if>>GEOGRAPHY</option>
									<option
										<c:if test='${subjectName eq "GOVERNMENT"}'>selected</c:if>>GOVERNMENT</option>
									<option
										<c:if test='${subjectName eq "HISTORY"}'>selected</c:if>>HISTORY</option>
									<option
										<c:if test='${subjectName eq "LITERATURE"}'>selected</c:if>>LITERATURE</option>
									<option
										<c:if test='${subjectName eq "MATHEMATICS"}'>selected</c:if>>MATHEMATICS</option>
									<option
										<c:if test='${subjectName eq "PHYSICS"}'>selected</c:if>>PHYSICS</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for="year">Year:</label> <select name="year" id="year"
									class="form-control">
									<option selected="selected">2016</option>
									<option>2015</option>
									<option>2014</option>
									<option>2013</option>
									<option>2012</option>
									<option>2011</option>
									<option>2010</option>
									<option>2009</option>
									<option>2008</option>
									<option>2007</option>
									<option>2006</option>
									<option>2005</option>
									<option>2004</option>
									<option>2003</option>
									<option>2002</option>
									<option>2001</option>
									<option>2000</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for="vendor">Vendor:</label> <select name="vendor"
									id="vendor" class="form-control">
									<option>UTME</option>
									<option>WAEC</option>
									<option>NECO</option>
								</select>
							</div>


						</div>
					</div>
					<div class="form-group complex-form-section">
						<div class="row" style="padding: 3%;">
							<div class="row">
								<div class="col-sm-12">
									<label for="question">Question Body:</label>
									<textarea rows="2" cols="" name="body" id="question"
										class="form-control tiny"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label for="alt-a">Alternative A:</label>
									<textarea id="alt-a" name="alt-a" class="form-control tiny"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label for="alt-b">Alternative B:</label>
									<textarea id="alt-b" name="alt-b" class="form-control tiny"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label for="alt-c">Alternative C:</label>
									<textarea id="alt-c" name="alt-c" class="form-control tiny"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label for="alt-d">Alternative D:</label>
									<textarea id="alt-d" name="alt-d" class="form-control tiny"></textarea>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-12">
									<label for="alt-e">Alternative E:</label>
									<textarea id="alt-e" name="alt-e" class="form-control tiny"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label for="table">Correct Alternative:</label><br>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="A"
											name="correct-alt">A</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="B"
											name="correct-alt">B</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="C"
											name="correct-alt">C</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="D"
											name="correct-alt">D</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="E"
											name="correct-alt">E</label>
									</div>
								</div>

							</div>
							<div class="row form-group">
								<div class="col-sm-6">
									<label for="picture-url">Picture Url:</label> <input
										type="file" id="picture-url" name="picture-url"
										class="form-control" />
								</div>
								<div class="col-sm-6">
									<label for="category-name">Category name:(For English
										only)</label> <select id="category-name" class="form-control"
										name="category-name">
										<option></option>
										<option>COMPREHENSION</option>
										<option>SYNONYMS</option>
										<option>ANTONYMS</option>
										<option>BEST EXPLANATION</option>
										<option>STRESS ON FIRST SYLLABLE</option>
										<option>SAME VOWEL SOUND AS OTHERS</option>
										<option>SAME VOWEL SOUND AS UNDERLINED</option>
										<option>SAME CONSONANT SOUND AS OTHERS</option>
										<option>SAME CONSONANT SOUND AS UNDERLINED</option>
										<option>DIFFERENT VOWEL SOUND FROM OTHERS</option>
										<option>DIFFERENT CONSONANT SOUND FROM OTHERS</option>
										<option>DIFFERENT STRESS PATTERN</option>
										<option>EMPHATIC STRESS</option>
										<option>FILL IN THE GAPS</option>
										<option>SAME STRESS PATTERN AS GIVEN WORD</option>
										<option>APPROPRIATE STRESS PATTERN</option>
									</select>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-12" >
									<ul id="passage-list" style="border: 1px solid gray; padding: 1%; display: none">
										<strong>Select a passage</strong><br/>
									</ul>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-sm-12">
									<label for="Topics">Topics (Hold down the Ctrl button
										to select multiple options.)</label> <select multiple id="topics"
										class="form-control" name="topics">
										<c:forEach var="item" items="${topics}">
											<option value="${item.webKey}">${item.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="row form-group">
								<div class="col-sm-12">
									<input type="submit" class="btn btn-primary btn-lg active"
										id="save" value="Save" />
								</div>
							</div>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/waitMe.js"></script>
	<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
	<script type="text/javascript">
		tinymce
				.init({
					selector : 'textarea',
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

			$("#category-name").on("change", function() {
				var x = $(this).val();
				if (x == "COMPREHENSION") {
					var y = $("#year").val();
					var z = $("#vendor").val();
					if (y) {
						$.ajax({
							url : "/ca/admin/cbt/english/passage/get",
							data : {
								"year" : y,
								"vendor" : z
							},
							dataType : "json",
							success : function(data) {
								for(i=0;i<data.length;i++) {
									$("#passage-list").append("<li class='radio' style='list-style: none; margin-bottom: 5px'><label><input type='radio' name='passage-key' value='"+data[i].key+"' >"+data[i].snippet+"</label></li>");
								}
								$("#passage-list").slideDown();
							},
							error : function(xhr) {
								alert(xhr.statusText);
							}
						});
					} else {
						alert("You need to select a year!");
					}
				}
			});

		});
	</script>
</body>