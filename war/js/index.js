
$(document)
		.ready(
				function() {
					
					
					$("input").focus(function() {
						removeError();
					});
					$("#new-account-button").click(function() {
						window.location.assign("/sign-up");
					});

					$(".login-button").click(function() {
						var $form = $(this).closest('form');
						$form.prop('action', "/login");
						$form.submit();
					});

					

					var userid = $("#userid");
					$("#use-phone").click(function() {
						userid.val("");
						userid.prop("type","tel");
						userid.prop('placeholder', 'e.g +2347051212230');
						regInputOk(userid);
						

					});
					$("#use-email").click(function() {
						
						userid.val("");
						userid.prop("type","email");
						userid.prop('placeholder', 'E-Mail');
						regInputOk(userid);
					});

					$("#sign-up")
							.click(
									function() {

										var ok = validateRegistrationForm();

										if (ok) {
											var msgDiv = $("#reg-error-div");
											msgDiv
													.removeClass("alert alert-danger");
											msgDiv
													.addClass("alert alert-success");
											msgDiv.text("Please wait...");
											var myForm = $("#register-user-form");
											var jqxhr = $
													.post("/registeruser",
															myForm.serialize(),function() {},'text')
													.done(
															function(data) {
																window.location
																		.assign(data);
															})
													.error(
															function(jqXHR,
																	status,
																	errorThrown) {
																msgDiv
																		.removeClass("alert alert-success");
																msgDiv
																		.addClass("alert alert-danger");
																
																msgDiv
																		.html(jqXHR.responseText);
																
															});
										}
									});



				});

function validateFirstName($input) {

	if (required($input)) {
		regInputOk($input);
	} else {
		regInputBad($input, "Please enter your first name.");
		
		return false;
	}

	if (allLetter($input)) {
		regInputOk($input);

	} else {
		regInputBad($input, "Please enter a real name.");
		
		return false;
	}

	return true;

}

function validateLastName($input) {
	if (required($input)) {
		regInputOk($input);
	} else {
		regInputBad($input, "Please enter your last name.");
		
		return false;
	}

	if (allLetter($input)) {
		regInputOk($input);
	} else {
		regInputBad($input, "Please enter a real name.");
		
		return false;
	}

	return true;
}

function validateUserId($input) {
	var usingPhone = $("#use-phone").prop('checked');

	if (usingPhone) {

		if (required($input)) {
			regInputOk($input);
		} else {
			regInputBad($input, "Please enter your mobile number.");
			
			return false;
		}

		if (allNumeric($input)) {
			regInputOk($input);
		} else {
			regInputBad($input, "Your mobile number cannot contain alphabets.");
			
			return false;
		}

		if (lengthRange($input, 6)) {
			regInputOk($input);
		} else {
			regInputBad($input, "Your mobile number is not valid .");
			
			return false;
		}

	} else {
		if (required($input)) {
			regInputOk($input);
		} else {
			regInputBad($input, "Please enter your e-mail address.");
			
			return false;
		}

		if (validateEmail($input)) {
			regInputOk($input);
		} else {
			regInputBad($input, "Please enter a valid e-mail address.");
			
			return false;
		}

	}

	return true;

}

function validatePassword($input) {
	if (required($input)) {
		regInputOk($input);
	} else {
		regInputBad($input, "Please enter a password.");
		
		return false;
	}

	if (lengthRange($input, 7, 21)) {
		regInputOk($input);
	} else {
		regInputBad($input,
				"Your password should have between 7 to 21 characters.");
		
		return false;
	}

	if (checkPassword($input)) {
		regInputOk($input);
	} else {
		regInputBad($input,
				"Your password should contain at least a digit and a special character.");
		
		return false;
	}

	return true;
}

function validatePassword2($pass2) {

	if (required($pass2)) {
		regInputOk($pass2);
	} else {
		regInputBad($pass2, "Please re-enter your password.");
		
		return false;
	}
	return true;
}

function matchPasswords($input, $pass2) {
	var p1 = $input.val();
	var p2 = $pass2.val();

	if (p1 === p2) {
		regInputOk($input);
		regInputOk($pass2);
	} else {
		regInputBad($input, "Passwords do not match.");
		regInputBad($pass2, "Passwords do not match.");
		
		return false;
	}

	return true;
}

function validateRegistrationForm() {
	// validating first name
	var $input = $("#first-name");
	var ok = validateFirstName($input);
	if (!ok) {
		return false;
	}

	// validating last name
	$input = $("#last-name");
	ok = validateLastName($input);
	if (!ok) {
		return false;
	}

	var $usingUsername = $("#using-username").val();

	if ($usingUsername) {
		// do nothing for now. validation is on server
	} else {
		// are we using phone?
		$input = $("#userid");
		ok = validateUserId($input);
		if (!ok) {
			return false;
		}
	}

	// validating pass1
	$input = $("#pass1");
	ok = validatePassword($input);
	if (!ok) {
		return false;
	}

	// validating pass2
	$pass2 = $("#pass2");
	ok = validatePassword2($pass2);
	if (!ok) {
		return false;
	}

	ok = matchPasswords($input, $pass2);
	if (!ok) {
		return false;
	}

	var f = $("#female");
	var m = $("#male");

	if (f.prop("checked") | m.prop("checked")) {
		regInputOk(f);
		regInputOk(m);
	} else {
		regInputBad(f, "Please select your gender.");
		regInputBad(m, "Please select your gender.");
		return false;
	}

	return true;

}
