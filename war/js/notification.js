function addMessage (message) {
	var spar = $("#new-message-box").clone();
	var par = spar.find(".row");
	par.find(".new-message").html(message);
	var pane = $("#message-pane");
	doToday(pane);
	pane.append(par);
	par.fadeIn();
}

function doToday(pane) {
	var header= pane.find(".msg-date").last();
	if(header.text()!="Today") {
		var nh = header.clone();
		nh.text("Today");
		pane.append(nh);
	}
}

$(document).ready(function() {
	$("#send-message").click(function(e) {
		e.preventDefault();
		var me = $(this);
		var form = me.closest("form");
		var eDiv = form.find(".textarea-editableDiv");
		var tArea = form.find(".materialize-textarea");
		var msg = tArea.val();
		if(msg) {
			$.ajax({
				url : form.prop("action"),
				data : form.serialize(),
				method : "POST",
				success : function() {
					addMessage(msg);
					eDiv.html("");
					tArea.val("");

				},
				error : function(xhr) {
					Materialize.toast(xhr.statusText, 4000)
				}
			});
		}
		
	});
	
	$(".delete-notification").click(function(e){
		var par = $(this).closest("li");
		var key = par.find(".notification-key").val();
		
		$.ajax({
			url :"/azure/notifications/delete",
			data : {
				"web-key" : key
			},
			method : "POST",
			complete : function() {
				par.remove();
			}
		});
	});
	$(".accept-friend-request").click(function(e){
		var me = $(this);
		var par = me.closest("li");
		var key = par.find(".notification-key").val();
		var butt = par.find(".delete-notification");
		$.ajax({
			url :"/azure/notifications/friend/request/accept",
			data : {
				"web-key" : key
			},
			method : "POST",
			complete : function() {
				me.text("Confirmed");
				me.prop("disabled",true);
				butt.prop("disabled",true);
			}
		});
	});
	
	$(".reply-message").click(function(e) {
		e.preventDefault();
		var par = $(this).closest("li");
		var key = par.find(".sender-key").val();
		window.location.assign("/azure/notifications/messages?web-key="+key);
	});
	
	
});