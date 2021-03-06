$(document)
		.ready(
				function() {

					document.getElementById("loadingProgressG").style.display = "none";

					var error_email = false;
					$("#email").focusout(function() {
						check_email();

					});

					function check_email() {
						var mail = $("#email").val();
						var validate_char = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
						if (mail.length == 0) {
							$("#authenicateEmail").html(
									"Email address cannot be empty");
							$("#authenicateEmail").show();
							error_email = true;
						} else if (mail.match(validate_char)) {

							$("#authenicateEmail").hide();
						} else {

							$("#authenicateEmail").html(
									"<p>Please enter valid email address <p>");
							$("#authenicateEmail").show();
							error_email = true;
						}

					}
				});
/*
 * while hitting forget password button Ajax call transfer the request to
 * UserController for sending password to their registered email-id
 */
$("#forgetPass")
		.click(
				function(event) {

					event.preventDefault();

					var useremail = $("#email").val();
					if (useremail.length == null || useremail.length == 0) {

						$("#authenicateEmail").html(
								"<p>*This Field is Required <p>");
					} else {
						document.getElementById("loadingProgressG").style.display = "inline-block";

						$.ajax({
							type : "PUT",
							contentType : "application/json; charset=utf-8",
							url : "whichcontact/forgetpassword?email="
									+ useremail,

							data : {

								email : useremail
							},
							success : function(response) {

								data = response;
								handleRegistrationResponse(data);

							},
							error : function(e) {
								alert("error while trying to save data")
							}
						});
					}
				});

function handleRegistrationResponse(data) {

	if (data.status == 400) {
		document.getElementById("loadingProgressG").style.display = "none";
		$('#emailResponse').html("<p>*Email Address Not Exists.</p>");
	} else if (data.status == 200) {

		document.getElementById("loadingProgressG").style.display = "none";

		spinnerCall();
	}
}
function spinnerCall() {

	$('#emailResponse').html(
			"<p>*Password sent to your Email address successfully..</p>");

}