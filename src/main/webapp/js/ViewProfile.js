/*viewUser function made Ajax call and transfer the request to userController for getting currently login user detail on the editprofile page */


//hits the contact controller and gets the list of objects
function viewContactsList(profile_id) {
	$.ajax({
		type : "POST",

		url : "whichcontact/viewContactsForProfile",
		data : {
			userId :profile_id
		},
		success : function(list) {
			data = list;
			handleUserDataList(data);
		},
		error : function(e) {
			alert("error while retrieving Profile" + e)
		}
	});
}
function viewUserProfile() {
	var userId=localStorage.getItem("_userId");
	console.log("tatatatatatata"+userId);
	viewUser(userId);
	viewContactsList(userId);
}
function viewUser(user_id) {
     $.ajax({
         type: "POST",
         url: "whichcontact/viewProfileByID",
     	data : {
			userId :user_id
		},
         success: function(response) {
             data = response;
             handleUserData(data);
             
         },
         error: function(e) {
             alert("error while trying to Complete Profile" + e)
         }
     });
 }

 function handleUserData(data) {
	 var name=$("#profile_name").append(data.name);
     var mailid = $("#profile_email").append(data.email);
     var company = $("#profile_company").append(data.company);
     var designation = $("#profile_designation").append(data.designation);
 }
 function handleUserDataList(data)
 {
	 
		$('#contactsTable').DataTable(
				{
					data : data,
					columns : [
							{
								'data' : 'name',
								"render" : function(data, type, full, meta) {
									return ((data) ? data : 'NA');
								},
								"defaultContent" : "<i>Not set</i>"
							},
							{
								'data' : 'phone',
								"defaultContent" : "<i>Not set</i>"
							},
							{
								'data' : 'mobile',
								"defaultContent" : "<i>Not set</i>"
							},
							{
								'data' : 'email',
								"render" : function(data, type, full, meta) {
									return '<a href="mailto:' + data + '">'
											+ ((data) ? data : 'NA') + '</a>';
								},
								"defaultContent" : "<i>Not set</i>"
							},

							{
								'data' : 'company',
								"defaultContent" : "<i>Not set</i>"
							}, {
								'data' : 'designation',
								"defaultContent" : "<i>Not set</i>"
							},

					]
				});
 }