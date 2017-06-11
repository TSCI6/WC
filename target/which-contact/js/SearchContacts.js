var NameOrCompany;
var Atoken;
var i = 0;
function auth(NameOrCompany) {
	var config = {
			'client_id' : '509482188994-irflm7vsgidsjq1joivmmr1ko7t7kucc.apps.googleusercontent.com',
			'scope' : 'https://www.googleapis.com/auth/contacts.readonly'
	};
	gapi.auth.authorize(config, function() {
		fetch(gapi.auth.getToken(),NameOrCompany);
	});
}
function fetch(token,NameOrCompany) {

	Atoken = token.access_token;
	//  viewContacts(token.access_token);
	if(NameOrCompany==1)
		SearchByName();
	else if(NameOrCompany==2)
		SearchByComp();

}

function SearchByComp() {

	$("#contact").empty();
	$("#status").empty();


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
function SearchByName() {


	$("#contact").empty();
	$("#status").empty();


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
function printResponse(data) {
	console.log("done");
	if (data.Status == 200) {

		alert("save successfully")
	} else if (data.Status == 400) {
		alert("not save successfully")
	}
}
function handleUserData(data) {
	var noOfContact=data.length;
	if (noOfContact==0)
	{
		//System.out.println("no contacts,Import Contacts First");
		console.log('No such contact ');
		$("#status").append('No such contact ')

	}
	var count=0;
	var str= $("#email").val();
	for(var i=0;i<data.length;i++)
	{
		var mutualPerson = {
			    userid:((data[i].userId)?data[i].userId:'N/A'),
				name :((data[i].name)?data[i].name:'N/A'),
				gid :((data[i].gid)?data[i].gid:'N/A'),
				email: ((data[i].email)?data[i].email:'N/A'),
				phone :((data[i].phone)?data[i].phone:'N/A'),
				work :((data[i].work)?data[i].work:'N/A'),
				mobile:((data[i].mobile)?data[i].mobile:'N/A'),
				postal :((data[i].postal)?data[i].postal:'N/A'),
				company :((data[i].company)?data[i].company:'N/A'),
				designation :((data[i].designation)?data[i].designation:'N/A'),
				url : 'https://www.google.com/m8/feeds/photos/media/' + str+ '/' + data[i].gid + '?access_token=' + Atoken,

		}
//		var url = 'https://www.google.com/m8/feeds/photos/media/' + person.str
//		+ '/' +  person.gid + '?access_token=' + Atoken;
		
		count++;
		ShowMutualPersonDetails(mutualPerson,count);
}
function ShowMutualPersonDetails(mutualPerson,count) 
  {
	$.ajax({
	url : 'whichcontact/findUser',   //into searchController
	type : 'POST',
	data : {
		user :mutualPerson.userid
	},
	success : function(stuff) {
		display(mutualPerson,stuff,count);
	},
	error : function(e) {
		alert("error while trying to authenticate")
	}
});
	};

function display(mutualPerson,stuff,count){
	$("#contact")
	.append(
			$('<figure><img  src='
					+ mutualPerson.url
					+ ' onerror="imgError(this);" ><figcaption  id="p['
					+count
					+ ']"onclick="viewdetail(this.id);"><h3>'
					+mutualPerson.name
					+'</h3><p id="less">Email:'
					+mutualPerson.email
					+'<br>Phone:'
					+mutualPerson.phone
					+'<br>Locale:'
					+mutualPerson.postal
					+'<br>Company:'
					+mutualPerson.company
					+'</p><h5>Your Mutual:'
					+stuff.name
					+'<br>Link:'
					+stuff.email
					+'   </h5><p id="more">Work:'
					+mutualPerson.work
					+'<br>Mobile:'
					+mutualPerson.mobile
					+'<br>Designation:'
					+mutualPerson.designation
					+'<br>Gid:'
					+mutualPerson.gid
					+'<br></p> </figcaption></figure>'));






}

}
function imgError(image) {
	image.onerror = "";
	image.src = "Image/user.png";
	return true;
}
function viewdetail(id){
	var  oldData, newData;
	oldData=document.getElementById(id).childNodes[1];
	newData=document.getElementById(id).childNodes[3];
	var clonedElement1 = oldData.cloneNode(true);
	var clonedElement2 = newData.cloneNode(true);

	newData.parentNode.replaceChild(clonedElement1, newData);
	oldData.parentNode.replaceChild(clonedElement2, oldData);


}