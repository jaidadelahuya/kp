$(document).ready(function() {

	$("#send-message").click(function(e) {
		e.preventDefault();
		var me = $(this);
		var form = me.closest("form");
		$.ajax({
			url : form.prop("action"),
			data : form.serialize(),
			method : "POST",
			beforeSend : function() {
				me.prop("disabled", true);
				me.css("color", "orange");
				me.text("Sending...");
			},
			success : function() {
				me.text("Message Sent");
				me.css("color", "green");
				setTimeout(function() {
					me.prop("disabled", false);
					$("#message-modal").closeModal();
				}, 2000);

			},
			error : function(xhr) {
				$("#send-msg-error").text(xhr.statusText);
				me.text("Send Message");
				me.css("color", "black");
			}
		});
	});
});