/*while clicking the SignUpform this AJAX call transfer the request to the userController for register the new User*/
jQuery(document)
 .ready(
  function() {

   $("#signupForm")
    .click(
     function(event) {
      event.preventDefault();
      var username = $("#user").val();
      var mailId = $("#useremail").val();
      var password = $("#firstPassword").val();
      var company = $("#userCompany").val();
      var designation = $("#userDesignation").val();
      if (username.length == null || username.length == 0 || mailId.length == null || mailId.length == 0 || password.length == null || password.length == 0 || company.length == null || company.length == 0 || designation.length == null || designation.length == 0) {
       $('#error')
        .html(
         "<p> *All Fields are required </p>");
      } else {
       $
        .ajax({
         type: "POST",
         contentType: "application/json; charset=utf-8",
         url: "whichcontact/addUser?username=" + username + "&mailId=" + mailId + "&password=" + password + "&company=" + company + "&designation=" + designation,
         data: {
          username: username,
          mailId: mailId,
          password: password,
          company: company,
          designation: designation
         },
         success: function(
          response) {
          data = response;
          handleRegistrationResponse(data);

         },
         error: function(e) {
        	    $('#response-modal').modal('toggle');
        	    $("#message_success").empty();
        	    $("#message_success").append(e.message );
        	   }

        });
      }
     });
/*Handle the data Response by checking its status */
   function handleRegistrationResponse(data) {

    if (data.status == 400) {

     $('#error').html("<p> User already exist </p>");
    } else if (data.status == 200) {
    	  $('#response-modal').modal('toggle');
    	    $("#header_success").empty();
    	    $("#header_success").append("Response");
    	    $("#message_success").empty();
    	    $("#message_success").append("Congratulation you are Successfully Registered.");
    	    setTimeout(function(){
    	        $("#response-modal").modal('hide');
    	        top.location.href = "login.html";
    	    }, 2000);
    	    };
    	   }
 /*  while user click the login button this Ajax call transfer the request to the userController for check whether the user is valid or not*/
   $("#login")
    .click(
     function(event) {
      event.preventDefault();
      var username = $("#nam").val();
      var password = $("#pass").val();
      if (username.length == null || username.length == 0 || password.length == null || password.length == 0) {

       $('#response')
        .html(
         "<p> *Username and Password Required.</p>");
      } else {
       $
        .ajax({
         type: "POST",
         contentType: "application/json; charset=utf-8",
         url: "whichcontact/login?username=" + username + "&password=" + password,
         data: {
          username: username,
          password: password
         },
         success: function(
          response) {
          data = response.status;
          store(username,
           password);
          printResponse(data);
         },
         error: function(e) {
          alert("error while trying to authenticate")
         }
        });
      }
     });
  });

function printResponse(data) {

 if (data == 401) {

 /* Appending error message if the crendtial is not match
*/
  $('#response').html("<p> *Username or Password is incorrect.</p>");
 } else if (data == 200) {
	 
  /*redirect the dashboard page if th result is success 
*/
  top.location.href = "TopCompanies.html";

 }
}

/*storing input from While user login 
*/
function store(username, password) {

 localStorage.setItem('name', username);
 localStorage.setItem('Password', password);

}


