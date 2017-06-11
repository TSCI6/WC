$(document).ready(function() {
    var error_fname = false;
    var error_email = false;
    var error_password = false;
    var error_crfmpassword = false;
    var error_company = false;
    var error_designation = false;

    $("#user").focusout(function() {
        check_username();
    });

    $("#useremail").focusout(function() {
        check_email();
    });

    $("#firstPassword").focusout(function() {
        check_password();
    });

    $("#repeatpasword").focusout(function() {
        check_cnfrmpassword();
    });

    $("#userCompany").focusout(function() {
        check_company();
    });

    $("#userDesignation").focusout(function() {
        check_designation();
    });

    function check_username() {
        var username_length = $("#user").val();
        var letters = /^[A-Za-z]+$/;
        if (username_length.length == 0) {
            $("#validuser").html("Username cannot be empty");
            $("#validuser").show();
            error_password = true;
        } else if (username_length.match(letters)) {
            if (username_length.length <= 3) {

                $("#validuser").html("<p>username must be conatin 3 character <p>");
                $("#validuser").show();
                error_fname = true;
            } else {
                $("#validuser").hide();
            }
        } else {
            $("#validuser").html("Enter valid name");
            $("#validuser").show();
        }
    }

    function check_email() {

        var mail = $("#useremail").val();
        var validate_char = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
        if (mail.length == 0) {
            $("#validMail").html("Email address cannot be empty");
            $("#validMail").show();
            error_password = true;
        } else if (mail.match(validate_char)) {

            $("#validMail").hide();
        } else {

            $("#validMail").html("<p>Please enter valid email address <p>");
            $("#validMail").show();
            error_email = true;
        }

    }

    function check_password() {
        var password_length = $("#firstPassword").val();

        if (password_length.length == 0) {
            $("#userpass").html("Password cannot be empty");
            $("#userpass").show();
            error_password = true;
        } else if (password_length < 5) {
            $("#userpass").html("Password must be greater than 5");
            $("#userpass").show();
            error_password = true;
        } else {
            $("#userpass").hide();
        }
    }

    function check_cnfrmpassword() {
        var password_length = $("#firstPassword").val();
        var password = $("#repeatpasword").val();
        if (password != password_length) {
            $("#cnfrmpass").html("Password must be same as above");
            $("#cnfrmpass").show();
            error_password = true;
        } else {
            $("#cnfrmpass").hide();
        }
    }

    function check_company() {
        var company_length = $("#userCompany").val();

        if (company_length.length == 0) {
            $("#comp").html("CompanyName cannot be empty");
            $("#comp").show();
            error_password = true;
        } else if (isNaN(company_length)) {
            if (company_length.length <= 1) {

                $("#comp").html("<p>CompanyName must be contain atleast 2 character <p>");
                $("#comp").show();
                error_fname = true;
            } else {
                $("#comp").hide();
            }
        } else {
            $("#comp").html("Enter valid CompanyName");
            $("#comp").show();
        }
    }

    function check_designation() {
        var designation_length = $("#userDesignation").val();
        var letters = /^[A-Za-z]+$/;

        if (designation_length.length == 0) {
            $("#pos").html("Designation cannot be empty");
            $("#pos").show();
            error_password = true;
        } else if (designation_length.match(letters)) {
            if (designation_length.length <= 3) {

                $("#pos").html("<p>Designation must be conatin 3 character <p>");
                $("#pos").show();
                error_fname = true;
            } else {
                $("#pos").hide();
            }
        } else {
            $("#pos").html("Enter valid Designation");
            $("#pos").show();
        }
    }




    /* LOGIN VALIDATION */

    $("form").submit(function(e) {
        e.preventDefault();

    });

    /*var error_user=false;
	var error_password=false;
	
	function check_user(){
		var username_length=$("#nam").val();
		
		if(username_length.length==0)
		{
		$("#response").html("Username cannot be empty");
		$("#response").show();
		error_password=true;
		}
		else
		{
			$("#response").hide();

		}
	}
		
		function check_password(){
			var password_length=$("#pass").val().length;
			if(password_length==0)
				{
				$("#response").html("Password cannot be empty");
				$("#response").show();
				error_password=true;
				}
			
			else
			{
				$("#response").hide();

			}
		}
  
*/
});