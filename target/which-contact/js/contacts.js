/*
 * 
 * 
 the script for SHOWCONTACTS.HTML
 * 
 * 
 *
 * @author TS001127
 *
 * 
 */
//contact div to display the contacts gridwise
//contactsTable table to display the contacts listwise
//show and hide these elements
//FUNCTION TO GET THE DATATABLE ON PAGE LOAD
$(window).on("load", function() {
	$("#status").empty();
	document.getElementById("pageButtons").style.display = "none";
	$("[id^='contactsTable']").css("display", "visible");
	viewContactsList();

});
var Ftoken;
var index = 0; 
// FUNCTION TO GET THE CONTACTS IN GRID VIEW ----->>>google api authorisation
function auth() {
	document.getElementById("contact").style.visibility = "visible"
	document.getElementById("pageButtons").style.display = "block";
	$("[id^='contactsTable']").css("display", "none");
	var config = {
		'client_id' : '509482188994-irflm7vsgidsjq1joivmmr1ko7t7kucc.apps.googleusercontent.com',
		'scope' : 'https://www.googleapis.com/auth/contacts.readonly'
	};
	gapi.auth.authorize(config, function() {
		fetch(gapi.auth.getToken());
	});
}

// saves the token in var Ftoken
function fetch(token) {
	Ftoken = token.access_token;
	viewContacts(token.access_token);
}

function NextPage() {
	$("#contact").empty();
	$("#status").empty();
	$("[id^='contactsTable']").css("display", "none");
	$(".gridView").css("display", "visible");
	viewContacts(Ftoken);
}
function PrevPage() {
	$("#contact").empty();
	$("#status").empty();
	$("[id^='contactsTable']").css("display", "none");
	$(".gridView").css("display", "visible");
	if (index> 25) {
		index = index - 25;
	}
	viewContacts(Ftoken);
}
// hits the contact controller and gets the list of objects
function viewContacts(Atoken) {
	$.ajax({
		type : "GET",

		url : "whichcontact/viewcontact",

		success : function(list) {
			data = list;
			handleUserData(data, Atoken);
		},
		error : function(e) {
			alert("error while retrieving Profile" + e)
		}
	});
}
// prints the data in contacts div in a grid format
function handleUserData(data, Atoken) {
	var noOfContact = data.length;
	if (noOfContact == 0) {
		console.log('No Contacts  ,  Import Contacts First');
		$("#status").empty()
		$("#status")
				.append(
						'<span style="color:red">No Contacts  ,  Import Contacts First</span>')
		document.getElementById("pageButtons").style.display = "none";
	}
	var count = 0;
	do {
		count++;
		var str = $("#email").val();
		var mailid = str.trim();

		var name = ((data[index].name) ? data[index].name : 'N/A');

		var gid = data[index].gid;
		var email = ((data[index].email) ? data[index].email : 'N/A');
		var phone = ((data[index].phone) ? data[index].phone : 'N/A');
		var work = ((data[index].work) ? data[index].work : 'N/A');
		var mobile = ((data[index].mobile) ? data[index].mobile : 'N/A');
		var postal = ((data[index].postal) ? data[index].postal : 'N/A');
		var company = ((data[index].company) ? data[index].company : 'N/A');
		var designation = ((data[index].designation) ? data[index].designation : 'N/A');
		var url = 'https://www.google.com/m8/feeds/photos/media/' + mailid
				+ '/' + gid + '?access_token=' + Atoken;
		$("#contact").append(
				$('<figure><img  src=' + url
						+ ' onerror="imgError(this);" ><figcaption  id="p['
						+ count + ']"onclick="viewdetail(this.id);"><h3>'
						+ name + '</h3><p id="less">Email:' + email
						+ '<br>Phone:' + phone + '<br>Locale:' + postal
						+ '<br>Company:' + company
						+ '<br></p><p id="more">Work:' + work + '<br>Mobile:'
						+ mobile + '<br>Designation:' + designation
						+ '<br>Company:' + company
						+ '<br></p> </figcaption></figure>'));

		index++; // the main index on which the data is traversed
	} while (count < 18)

}

function imgError(image) {
	image.onerror = "";
	image.src = "Image/user.png";
	return true;
}
// function to toggle the details on image click
function viewdetail(id) {
	var oldData, newData;
	oldData = document.getElementById(id).childNodes[1];
	newData = document.getElementById(id).childNodes[2];
	var clonedElement1 = oldData.cloneNode(true);
	var clonedElement2 = newData.cloneNode(true);
	newData.parentNode.replaceChild(clonedElement1, newData);
	oldData.parentNode.replaceChild(clonedElement2, oldData);

}
// hits the contact controller and gets the list of objects
function viewContactsList() {
	$.ajax({
		type : "GET",

		url : "whichcontact/viewcontact",

		success : function(list) {
			data = list;
			handleUserDataList(data);
		},
		error : function(e) {
			alert("error while retrieving Profile" + e)
		}
	});
}
// prints the data in contacts div in a list format
function handleUserDataList(data) {
	var noOfContact = data.length;
	if (noOfContact == 0) {
		$("#status").empty()
		$("#status")
				.append(
						'<span style="color:red">No Contacts  ,  Import Contacts First</span>')
		document.getElementById("pageButtons").style.display = "none";
		$("[id^='contactsTable']").css("display", "none");
	}
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
// hides the grid contact div ....and brings the datatable in front
function listv() {
	$("#contact").empty();
	$("[id^='contactsTable']").css("display", "table-row-group");
	document.getElementById("pageButtons").style.display = "none";

}