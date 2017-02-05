$(document).ready(
		function() {

		/*	$(window).scroll(
					function() {
						if ($(window).scrollTop() + $(window).height() == $(
								document).height()) {
							alert("bottom!");
							// getData();
						}
					});*/

			

			$(".message").click(function() {
				var par = $(this).closest(".card");
				var img = par.find(".person-image").prop("src");
				var name = par.find(".person-name").text();
				var webKey = par.find(".person-webkey").val();
				$("#send-message").text("Send Message");
				$("#send-message").css("color", "black");

				$("#message-area").val("");
				$("#message-area").next().removeClass("active");
				$("#recipient-key").val(webKey);
				$("#recipient-image").prop("src", img);
				$("#recipient-name").text(name);
				$("#message-modal").openModal();
			});

			$(".people-tab").click(function() {
				var ref = "/azure/people/get?category=" + $(this).prop("id");
				$.ajax({
					url : ref,
					dataType : "json",
					success : function(data) {
						console.log(data);
					}
				});
			});

			$(".follow").click(
					function(e) {
						e.preventDefault();
						var webKey = $(this).closest(".card-action").find(
								".person-webkey").val();
						var x = $(this).closest(".card").find(
						".person-name").text();
						var me = $(this);
						$.ajax({
							url : "/azure/people/follow/add",
							data : {
								"web-key" : webKey
							},
							success : function(data) {
								me.fadeOut();
								Materialize.toast('You are now following '+x, 4000);
							}
						});
					});

			$(".friend-request").click(
					function(e) {
						e.preventDefault();
						var webKey = $(this).closest(".card-action").find(
								".person-webkey").val();
						var me = $(this);
						$.ajax({
							url : "/azure/notifications/friend/request",
							method : "POST",
							data : {
								"web-key" : webKey
							},
							success : function(data) {
								me.fadeOut();
								Materialize.toast('Your friend request has been sent!', 4000);
							}
						});

					});
		});