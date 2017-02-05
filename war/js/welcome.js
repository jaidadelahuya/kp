function clonePost(aImg, aName, pImg, pText, likes, comments, date, source,
		webkey, liked) {
	var clone = $("#template").find(".temp-panel").clone(true);
	if(liked) {
		alert("liked");
		clone.find(".like").text("thumb_down");
	}else {
		clone.find(".like").text("thumb_up");
	}
	clone.find(".a-img").prop("src", aImg);
	clone.find(".a-name").text(aName);
	clone.find(".no-likes").text(likes);
	clone.find(".no-comments").text(comments);
	clone.find(".date").text(date);
	clone.find(".h-input").val(webkey);
	clone.find(".more").prop("href",
			"/azure/post/single/get?bean=welcome&webkey=" + webkey);
	if (source) {
		clone.find(".source").text("Source");
		clone.find(".source").prop("href", source);
	}
	clone.find(".card-content").text(pText);
	if (pImg) {
		clone.find(".materialboxed").prop("src", pImg);
	}
	return clone;
}

function getMoreDiscussions() {
	$.ajax({
		url : "/azure/post/get",
		dataType : "json",
		success : function(data) {
		
			for (i = 0; i < data.length; i++) {
				var val = data[i];
				console.log(val);
				var clone = clonePost(val.authorImage, val.author,
						val.pictureUrl, val.snippet, val.likes, val.comments,
						val.postDate, val.link, val.webkey, val.liked);
				
				if ($("#s-col").innerHeight() < $("#f-col").innerHeight()) {
					$("#s-col").append(clone);
				} else {
					$("#f-col").append(clone);
				}
			}
			if(data.length < 10) {
				$("#more").hide();
			}
		},
		error : function(xhr) {
			//console.log(xhr);
		},
		complete : function() {
			//console.log("done");

		}
	});
}

function getUploadUrl(url, form) {
	$.ajax({
		url : "/getuploadurl",
		data : {
			"url" : url
		},
		success : function(data) {
			form.prop("action", data);
		},
		error : function() {
			alert("An error has occurred. Check your internet connection");
		}
	});
}

$(document)
		.ready(
				function() {
					
					$("#more").click(function(e) {
						console.log("click");
						getMoreDiscussions();
					});
					
					

					/*$(window).scroll(function() {
						

						
						if (window.scrollHeight - window.scrollTop === window.clientHeight) {
							getMoreDiscussions();

						}
					});*/

					$("#submit-post").click(
							function(e) {
								e.preventDefault();
								var x = $("#textarea11").val();
								var z = $("#new-img").prop("src");
								var y = $("#tags").val();
								if($("#public").is(":checked")) {
									var a = "true";
								}

								$.ajax({
									url : "/azure/post/save",
									data : {
										"post" : x,
										"tags" : y,
										"public" : a
									},
									method : "POST",
									success : function(data) {
										var clone = $("#template").find(
												".temp-panel").clone(true);
										clone.find(".card-content").text(x);
										if (z) {
											clone.find(".materialboxed").prop(
													"src", z);
										}
										$("#f-col").prepend(clone);
										$('#modal1').closeModal();
									},
									error : function(xhr) {
										console.log("error");
									}
								});
							});

					$("#img-input").change(function() {
						$(this).closest("form").submit();
					});

					$("#img-input-form")
							.submit(
									function(e) {
										e.preventDefault();
										var x = $(this).prop("action");
										$
												.ajax({
													url : x,
													data : new FormData(this),
													method : "POST",
													processData : false,
													contentType : false,
													success : function(data) {
														console.log(data);
														$("#new-img").prop(
																"src", data);
														getUploadUrl(
																"/azure/post/image/new",
																$("#img-input-form"));
														$("#remove-image")
																.show();
													},
													error : function() {
														alert("We could not upload your image.");
													}
												});
									});

					$("#remove-image")
							.click(
									function() {
										$("#new-img").prop("src", "");
										getUploadUrl("/azure/post/image/new",
												$("#img-input-form"));
										var img = $('<img id="new-img" class="responsive-img">');
										$(this).closest(".card-image").find(
												"img").replaceWith(img);
										$(this).hide();
									});

					$(".button-collapse").sideNav();
					$('.materialboxed').materialbox();
					$('.modal-trigger')
							.leanModal(
									{
										ready : function() {
											var url = getUploadUrl(
													"/azure/post/image/new",
													$("#img-input-form"));

										},
										complete : function() {
											var img = $('<img id="new-img" class="responsive-img">');
											$("#new-img").replaceWith(img);
											$("#textarea11").val("");
											$("#remove-image").hide();
										}
									});
				});