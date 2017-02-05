$(document).ready(function() {
	$(".textarea-editableDiv").focus(function() {
		var par = $(this).closest(".ca-textarea");
		var border = par.find(".ca-textarea-div-border");
		var label = par.find(".text-label");
		var textarea = par.find(".materialize-textarea");
		label.css("color", "#26a69a");
		border.css("border-bottom", "2px solid #26a69a");
		textarea.val($(this).html());
	});
	$(".textarea-editableDiv").blur(function() {
		var par = $(this).closest(".ca-textarea");
		var border = par.find(".ca-textarea-div-border");
		var textarea = par.find(".materialize-textarea");
		var label = par.find(".text-label");
		label.css("color", "gray");
		border.css("border-bottom", "1px solid gray");
		textarea.val($(this).html());
	});
	$('select').material_select();
	$('.datepicker').pickadate({
		selectMonths : true,
		selectYears : 15
	});
});