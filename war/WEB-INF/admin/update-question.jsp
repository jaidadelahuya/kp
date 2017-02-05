<%@page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="cg" %>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Question</title>
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
				<h5 class="text-primary">Update Question</h5>
				<c:choose>
					<c:when test="${not empty formError}">
						<div class="alert alert-danger">
							<c:out value="${formError}" />
						</div>
					</c:when>
					<c:when test="${not empty formSuccess}">
						<div class="alert alert-success">
							<c:out value="${formSuccess}" /> <a href="/ca/admin/cbt/s/questions/edit?vendor=${editQuestionsPage.vendor}&year=${editQuestionsPage.year}&subject=${editQuestionsPage.subject}">Back</a>
						</div>
					</c:when>
				</c:choose>

				<form
					action="<%=blobstoreService
					.createUploadUrl("/ca/admin/cbt/questions/update")%>"
					method="post" enctype="multipart/form-data">
					<div class="form-group complex-form-section">
						<div class="row">
							<div class="col-sm-4">
								<label for="subject">Subject:</label> <select
									name="subject-name" id="subject" class="form-control">
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "ACCOUNT"}'>selected</c:if>>ACCOUNT</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "AGRICULTURAL SCIENCE"}'>selected</c:if>>AGRICULTURAL
										SCIENCE</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "BIOLOGY"}'>selected</c:if>>BIOLOGY</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "CHEMISTRY"}'>selected</c:if>>CHEMISTRY</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "COMMERCE"}'>selected</c:if>>COMMERCE</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "C.R.K"}'>selected</c:if>>C.R.K</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "ECONOMICS"}'>selected</c:if>>ECONOMICS</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "ENGLISH"}'>selected</c:if>>ENGLISH</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "FINE ART"}'>selected</c:if>>FINE
										ART</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "GEOGRAPHY"}'>selected</c:if>>GEOGRAPHY</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "GOVERNMENT"}'>selected</c:if>>GOVERNMENT</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "HISTORY"}'>selected</c:if>>HISTORY</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "LITERATURE"}'>selected</c:if>>LITERATURE</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "MATHEMATICS"}'>selected</c:if>>MATHEMATICS</option>
									<option
										<c:if test='${fn:toUpperCase(editQuestionsPage.subject) eq "PHYSICS"}'>selected</c:if>>PHYSICS</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for="year">Year:</label> <select name="year" id="year"
									class="form-control">
									<option
										<c:if test='${editQuestionsPage.year eq "2016"}'>selected</c:if>>2016</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2015"}'>selected</c:if>>2015</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2014"}'>selected</c:if>>2014</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2013"}'>selected</c:if>>2013</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2012"}'>selected</c:if>>2012</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2011"}'>selected</c:if>>2011</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2010"}'>selected</c:if>>2010</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2009"}'>selected</c:if>>2009</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2008"}'>selected</c:if>>2008</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2006"}'>selected</c:if>>2007</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2007"}'>selected</c:if>>2006</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2005"}'>selected</c:if>>2005</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2004"}'>selected</c:if>>2004</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2003"}'>selected</c:if>>2003</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2002"}'>selected</c:if>>2002</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2001"}'>selected</c:if>>2001</option>
									<option
										<c:if test='${editQuestionsPage.year eq "2000"}'>selected</c:if>>2000</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for="vendor">Vendor:</label> <select name="vendor"
									id="vendor" class="form-control">
									<option
										<c:if test='${editQuestionsPage.vendor eq "UTME"}'>selected</c:if>>UTME</option>
									<option
										<c:if test='${editQuestionsPage.vendor eq "WAEC"}'>selected</c:if>>WAEC</option>
									<option
										<c:if test='${editQuestionsPage.vendor eq "NECO"}'>selected</c:if>>NECO</option>
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
										class="form-control tiny">${editQuestionsPage.currentQuestion.body}</textarea>
								</div>
							</div>
							<c:forEach var="item"
								items="${editQuestionsPage.currentQuestion.alternatives}"
								varStatus="i">
								<c:choose>
									<c:when test="${i.index eq 0}">
										<div class="row">
											<div class="col-sm-12">
												<label for="alt-a">Alternative A:</label>
												<textarea id="alt-a" name="alt-a" class="form-control tiny">${item}</textarea>
											</div>
										</div>
									</c:when>
									<c:when test="${i.index eq 1}">
										<div class="row">
											<div class="col-sm-12">
												<label for="alt-b">Alternative B:</label>
												<textarea id="alt-b" name="alt-b" class="form-control tiny">${item}</textarea>
											</div>
										</div>
									</c:when>
									<c:when test="${i.index eq 2}">
										<div class="row">
											<div class="col-sm-12">
												<label for="alt-c">Alternative C:</label>
												<textarea id="alt-c" name="alt-c" class="form-control tiny">${item}</textarea>
											</div>
										</div>
									</c:when>
									<c:when test="${i.index eq 3}">
										<div class="row">
											<div class="col-sm-12">
												<label for="alt-d">Alternative D:</label>
												<textarea id="alt-d" name="alt-d" class="form-control tiny">${item}</textarea>
											</div>
										</div>
									</c:when>
									<c:when test="${i.index eq 4}">
										<div class="row">
											<div class="col-sm-12">
												<label for="alt-e">Alternative E:</label>
												<textarea id="alt-e" name="alt-e" class="form-control tiny">${item}</textarea>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
							<div class="row">
								<div class="col-sm-12">
									<label for="table">Correct Alternative:</label><br>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="A"
											<c:if test='${editQuestionsPage.currentQuestion.correctAlternative eq "A"}'>checked</c:if>
											name="correct-alt">A</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="B"
											<c:if test='${editQuestionsPage.currentQuestion.correctAlternative eq "B"}'>checked</c:if>
											name="correct-alt">B</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="C"
											<c:if test='${editQuestionsPage.currentQuestion.correctAlternative eq "C"}'>checked</c:if>
											name="correct-alt">C</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="D"
											<c:if test='${editQuestionsPage.currentQuestion.correctAlternative eq "D"}'>checked</c:if>
											name="correct-alt">D</label>
									</div>
									<div class="col-sm-2">
										<label class="opts"><input type="radio" value="E"
											<c:if test='${editQuestionsPage.currentQuestion.correctAlternative eq "E"}'>checked</c:if>
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
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "COMPREHENSION"}'>selected</c:if>>COMPREHENSION</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "SYNONYMS"}'>selected</c:if>>SYNONYMS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "ANTONYMS"}'>selected</c:if>>ANTONYMS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "BEST EXPLANATION"}'>selected</c:if>>BEST
											EXPLANATION</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "STRESS ON FIRST SYLLABLE"}'>selected</c:if>>STRESS
											ON FIRST SYLLABLE</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "SAME VOWEL SOUND AS OTHERS"}'>selected</c:if>>SAME
											VOWEL SOUND AS OTHERS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "SAME VOWEL SOUND AS UNDERLINED"}'>selected</c:if>>SAME
											VOWEL SOUND AS UNDERLINED</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "SAME CONSONANT SOUND AS OTHERS"}'>selected</c:if>>SAME
											CONSONANT SOUND AS OTHERS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "SAME CONSONANT SOUND AS UNDERLINED"}'>selected</c:if>>SAME
											CONSONANT SOUND AS UNDERLINED</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "DIFFERENT VOWEL SOUND FROM OTHERS"}'>selected</c:if>>DIFFERENT
											VOWEL SOUND FROM OTHERS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "DIFFERENT CONSONANT SOUND FROM OTHERS"}'>selected</c:if>>DIFFERENT
											CONSONANT SOUND FROM OTHERS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "DIFFERENT STRESS PATTERN"}'>selected</c:if>>DIFFERENT
											STRESS PATTERN</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "EMPHATIC STRESS"}'>selected</c:if>>EMPHATIC
											STRESS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "FILL IN THE GAPS"}'>selected</c:if>>FILL
											IN THE GAPS</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "SAME STRESS PATTERN AS GIVEN WORD"}'>selected</c:if>>SAME
											STRESS PATTERN AS GIVEN WORD</option>
										<option
											<c:if test='${editQuestionsPage.currentQuestion.categoryName eq "APPROPRIATE STRESS PATTERN"}'>selected</c:if>>APPROPRIATE
											STRESS PATTERN</option>
									</select>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-12">
									<ul id="passage-list"
										style="border: 1px solid gray; padding: 1%; display: none">
										<strong>Select a passage</strong>
										<br />
									</ul>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-sm-12">
									<label for="Topics">Topics (Hold down the Ctrl button
										to select multiple options.)</label> <select multiple id="topics"
										class="form-control" name="topics">
										<c:forEach var="item" items="${editQuestionsPage.topics}">
											
											<option <c:if test='${cg:containsTopic(item,editQuestionsPage.currentQuestion)}'>selected</c:if> value="${item.webKey}">${item.name}</option>
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
		$(document)
				.ready(
						function() {

							$("#category-name")
									.on(
											"change",
											function() {
												var x = $(this).val();
												if (x == "COMPREHENSION") {
													var y = $("#year").val();
													var z = $("#vendor").val();
													if (y) {
														$
																.ajax({
																	url : "/ca/admin/cbt/english/passage/get",
																	data : {
																		"year" : y,
																		"vendor" : z
																	},
																	dataType : "json",
																	success : function(
																			data) {
																		for (i = 0; i < data.length; i++) {
																			$(
																					"#passage-list")
																					.append(
																							"<li class='radio' style='list-style: none; margin-bottom: 5px'><label><input type='radio' name='passage-key' value='"+data[i].key+"' >"
																									+ data[i].snippet
																									+ "</label></li>");
																		}
																		$(
																				"#passage-list")
																				.slideDown();
																	},
																	error : function(
																			xhr) {
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