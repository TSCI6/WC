/*
 * 
 * 
 the script for uploadCONTACTS.HTML
 * 
 * 
 *
 * @author TS001127
 *
 */


document.getElementById("loadingProgressG").style.display = "none";

function uploadGoogleContacts() {
	document.getElementById("loadingProgressG").style.display = "inline-block";
	var config = {
			'client_id' : '509482188994-irflm7vsgidsjq1joivmmr1ko7t7kucc.apps.googleusercontent.com',
			'scope' : 'https://www.googleapis.com/auth/contacts.readonly'
	};
	gapi.auth.authorize(config, function() {
		fetch(gapi.auth.getToken());
	});
}
function fetch(token) {
	$("#status").empty();
	$("#status").append('<span style="color:red">Please Log-in To Your Google Account</span>')
	var str = $("#email").val();
	var str1 = str.trim();

	$.ajax({
		url : "https://www.google.com/m8/feeds/contacts/" + str1
		+ "/full?access_token=" + token.access_token
		+ "&max-results=1000&alt=json",
		timeout: 10000,
		dataType : "jsonp",
		success : function(data) {
			console.log('done');
			add(data);
		},
		error : function(e) {
			$("#status").empty();
			$("#status").append('<span style="color:red">Login Error or TimeOut </span>')
			document.getElementById("loadingProgressG").style.display = "none";
		}
	});
}
/**
 * @param jsonObjects
 * @returns
 */
function add(jsonObjects) {		// function to fetch and save the contacts from
	// google api
	$.ajax({
		url : 'whichcontact/SaveContacts',
		type : 'POST',
		data : {
			user : JSON.stringify(jsonObjects)
		},
		success : function(response) {
			data = response.status;
			$("#status").empty();
			$("#status").append('<span style="color:green">Contacts Fetched Succesfully. Go To Show Contacts Or Search Contacts </span>')
			printResponse(data);
		},
		error : function(e) {
			$("#status").empty();
			$("#status").append('<span style="color:red">Please Log-in To Your Google Account</span>')
			alert("error while trying to authenticate")
		}
	});
}
function printResponse(data) {
	console.log("done");
	document.getElementById("loadingProgressG").style.display = "none";
	if (data == 200) {
		$("#status").empty();
		$("#status").append('<span style="color:green">Contacts Imported Succesfully. Go To   Show Contacts   Or   Search Contacts </span>')
	} else if (data == 400) {
		alert("not save successfully")
	}
}
function uploadCsv() {
	var formvalue = new FormData($('#csvReader')[0]);
	var csvpath=$("#csvpath").val();
	document.getElementById("loadingProgressG").style.display = "inline-block";
	console.log(formvalue);
	$.ajax({
		url : 'whichcontact/SaveCsv',
		type : 'POST',
		data : formvalue,
		processData : false,
		contentType : false,
		success : function(response) {
			data = response.status;
			document.getElementById("status").innerHTML = "CSV Fetched";
			printResponse(data);
		},
		error : function(e) {
			$("#status").empty();
			$("#status").append('<span style="color:green">IMPORT SUCCESFUL </span>')
		}
	});
}