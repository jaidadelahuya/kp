$(document).ready(function () {
	$(".launchCBT").click(function(e) {
		e.preventDefault();
		var form = $(this).closest("form");
		var x = form.attr("action");
		$.ajax({
			url : x,
			dataType : "json",
			data : form.serialize(),
			beforeSend : function() {
				Materialize.toast('If you do not have pop up enabled, the test page will load shortly', 7000);
			},
			success : function (data) {
				
				var wind = window.open("/azure/cbt/test/board", data.title,
				"toolbar=yes, scrollbars=yes, fullscreen=1");
				if(wind) {
					wind.data = data;
				}else {
					alert("You have to disable pop up blocker to open the test page.");
				}
				
			}, 
			error : function(xhr) {
				alert(xhr.statusText);
			}
		});
	});
});