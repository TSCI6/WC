/*
 * 
 * 
 the script for SearchCONTACTS.HTML
 * 
 * 
 *
 * 
 *
 * 
 */

var NameOrCompany; // variable for polymorphism searchBy companyOrName
var Atoken;
var index = 0; 
/**
 * @param NameOrCompany
 * @returns
 */
function SearchByNameOrCompany(NameOrCompany) {
	var config = {
		'client_id' : '509482188994-irflm7vsgidsjq1joivmmr1ko7t7kucc.apps.googleusercontent.com',
		'scope' : 'https://www.googleapis.com/auth/contacts.readonly'
	};
	gapi.auth.authorize(config, function() {
		fetch(gapi.auth.getToken(), NameOrCompany);
	});
}
/**
 * @param token
 * @param NameOrCompany
 *            calls the respective fuction companyOrName
 */
function fetch(token, NameOrCompany) {
	Atoken = token.access_token;
	// viewContacts(token.access_token);
	if (NameOrCompany == 1)
		SearchByName();
	else if (NameOrCompany == 2)
		SearchByComp();

}

/**
 * @returns contacts matching the company
 */
function SearchByComp() {

	$("#2ndDegreecontact").empty();
	$("#status").empty();
	$("#1stDegreecontact").empty();
	var str = $("#query").val();
	$.ajax({
		url : 'whichcontact/searchCo',
		type : 'POST',
		data : {
			user : $("#query").val()
		},
		success : function(list) {
			data = list;
			handleUserData(data);
		},
		error : function(e) {
			alert("error while trying to authenticate")
		}
	});
}
/**
 * @returns contacts matching the name
 */
function SearchByName() {

	$("#2ndDegreecontact").empty();
	$("#status").empty();
	$("#1stDegreeContact").empty();

	var str = $("#query").val();
	$.ajax({
		url : 'whichcontact/searchName',
		type : 'POST',
		data : {
			user : $("#query").val()
		},
		success : function(list) {
			data = list;
			handleUserData(data);
		},
		error : function(e) {
			alert("error while trying to authenticate")
		}
	});
}
/**
 * @param data
 * @returns
 */
function printResponse(data) {
	console.log("done");
	if (data.Status == 200) {

		alert("save successfully")
	} else if (data.Status == 400) {
		alert("not save successfully")
	}
}
/**
 * @param data
 * @returns fetches the data from contacts table
 */

function handleUserData(data) {
	var noOfContact = data.length;
	if (noOfContact == 0) {
		// System.out.println("no contacts,Import Contacts First");
		console.log('No such contact ');
		$("#status").append('No such contact ')

	}
	var count = 0;
	var str = $("#email").val();

	for (var index = 0; index < data.length; index++) {
		var mutualPerson = {
			userid : ((data[index].userId) ? data[index].userId : 'N/A'),
			name : ((data[index].name) ? data[index].name : 'N/A'),
			gid : ((data[index].gid) ? data[index].gid : 'N/A'),
			email : ((data[index].email) ? data[index].email : 'N/A'),
			phone : ((data[index].phone) ? data[index].phone : 'N/A'),
			work : ((data[index].work) ? data[index].work : 'N/A'),
			mobile : ((data[index].mobile) ? data[index].mobile : 'N/A'),
			postal : ((data[index].postal) ? data[index].postal : 'N/A'),
			company : ((data[index].company) ? data[index].company : 'N/A'),
			designation : ((data[index].designation) ? data[index].designation : 'N/A'),
			url : 'https://www.google.com/m8/feeds/photos/media/' + str + '/'
					+ data[index].gid + '?access_token=' + Atoken,

		}
		
		count++;
		ShowMutualPersonDetails(mutualPerson, count);
	}
	function ShowMutualPersonDetails(mutualPerson, count) // ..for fetching
	// the
	// mutualContactsDetail
	// corresponding the
	// userId
	{
		$.ajax({
			url : 'whichcontact/findUser', // into searchController
			type : 'POST',
			data : {
				user : mutualPerson.userid
			},
			success : function(stuff) {
				display(mutualPerson, stuff, count);
			},
			error : function(e) {
				alert("error while trying to authenticate")
			}
		});
	}
	;

	function display(mutualPerson, stuff, count) {
		console.log(loggedInUserId);
		if(mutualPerson.userid==loggedInUserId)
				{	$("#1stDegreeContact").append(
				$('<figure><img  src=' + mutualPerson.url
						+ ' onerror="imgError(this);" ><figcaption  id="p['
						+ count + ']"onclick="viewdetail(this.id);"><h3>'
						+ mutualPerson.name + '</h3><p id="less">Email:'
						+ mutualPerson.email + '<br>Phone:'
						+ mutualPerson.phone + '<br>Locale:'
						+ mutualPerson.postal + '<br>Company:'
						+ mutualPerson.company + '</p><h5>Your Mutual:'
						+ stuff.name + '<br>Link:' + stuff.email
						+ '   </h5><p id="more">Work:' + mutualPerson.work
						+ '<br>Mobile:' + mutualPerson.mobile
						+ '<br>Designation:' + mutualPerson.designation
						+ '<br>Gid:' + mutualPerson.gid
						+ '<br></p> </figcaption></figure>'));
		}
		else
		{	$("#2ndDegreecontact").append(
		$('<figure><img  src=' + mutualPerson.url
				+ ' onerror="imgError(this);" ><figcaption  id="p['
				+ count + ']"onclick="viewdetail(this.id);"><h3>'
				+ mutualPerson.name + '</h3><p id="less">Email:'
				+ mutualPerson.email + '<br>Phone:'
				+ mutualPerson.phone + '<br>Locale:'
				+ mutualPerson.postal + '<br>Company:'
				+ mutualPerson.company + '</p><h5>Your Mutual:<a onclick="viewProfile('
				+stuff.id+')">'
				+ stuff.name + '</a><br>Link:' + stuff.email
				+ '   </h5><p id="more">Work:' + mutualPerson.work
				+ '<br>Mobile:' + mutualPerson.mobile
				+ '<br>Designation:' + mutualPerson.designation
				+ '<br>Gid:' + mutualPerson.gid
				+ '<br></p> </figcaption></figure>'));
}
	}

}
function imgError(image) {
	image.onerror = "";
	image.src = "Image/user.png";
	return true;
}
/**
 * @param id
 * @returns
 */
function viewdetail(id) { // toggling the details in grid
	var oldData, newData;
	oldData = document.getElementById(id).childNodes[1];
	newData = document.getElementById(id).childNodes[3];
	var clonedElement1 = oldData.cloneNode(true);
	var clonedElement2 = newData.cloneNode(true);

	newData.parentNode.replaceChild(clonedElement1, newData);
	oldData.parentNode.replaceChild(clonedElement2, oldData);

}
function viewProfile(userId) { // toggling the details in grid
	   localStorage.setItem('_userId', userId);
		console.log(userId);
		location.href="ViewProfile.html";



}