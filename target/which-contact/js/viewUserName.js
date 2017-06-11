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
		},
		error : function(e) {
			alert("error while trying Compleate Profile"+e)
		}
	});
 }
