/*AJAX call hitting UserController for showing the Current login user-name on the top of header of the UI*/

function viewUserName(){
	 $.ajax({
		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "whichcontact/viewUser",
		success : function(response) {
			data = response;
			$("#username").html(response[0].name); 
			$("#email").val(response[0].email);
			loggedInUserId=response[0].id;
			console.log(loggedInUserId);

		},
		error : function(e) {
			console.log("error while fetching username");
		}
	});
 }
