/*
 * 
 * 
 the script for SHOWCONTACTS.HTML
 * 
 * 
 */



//FUNCTION TO GET THE DATATABLE ON PAGE LOAD
$(window).on("load", function() {
    $("#status").empty();
    //$(".gridView").css("display", "hidden");
    document.getElementById("lol").style.display = "none";
    $("[id^='example']").css("display", "visible");
   /* var searchLocation = location.href;
	searchLocation = searchLocation + "changedSearchLocation";
	console.log('*********'+searchLocation+'**************');
	location.href=searchLocation;
	//jaikant's sir code to redirect to another location 
*/    
    viewContactsList();
});
var Ftoken;
var i = 0;

//FUNCTION TO GET THE CONTACTS IN GRID VIEW ----->>>hits the google api with key and gets the token 
function auth() {

    document.getElementById("contact").style.visibility = "visible"
    document.getElementById("lol").style.display = "block";
    $("[id^='example']").css("display", "none");
    var config = {
        'client_id': '509482188994-irflm7vsgidsjq1joivmmr1ko7t7kucc.apps.googleusercontent.com',
        'scope': 'https://www.googleapis.com/auth/contacts.readonly'
    };
    gapi.auth.authorize(config, function() {
        fetch(gapi.auth.getToken());
    });
}

//saves the token in var Ftoken 
function fetch(token) {
    Ftoken = token.access_token;
    viewContacts(token.access_token);
}

function NextPage() {
    $("#contact").empty();
    $("#status").empty();
    $("[id^='example']").css("display", "none");
    $(".gridView").css("display", "visible");

    viewContacts(Ftoken);
}
function PrevPage() {
    $("#contact").empty();
    $("#status").empty();
    $("[id^='example']").css("display", "none");
    $(".gridView").css("display", "visible");
    if(i>25)
    	{
    i=i-25;
    	}
    viewContacts(Ftoken);
}
//hits the contact controller and gets the list of objects
function viewContacts(Atoken) {
    $.ajax({
        type: "GET",

        url: "whichcontact/viewcontact",

        success: function(list) {
            data = list;
            handleUserData(data, Atoken);
        },
        error: function(e) {
            alert("error while retrieving Profile" + e)
        }
    });
}
//prints the data in contacts div in a grid format
function handleUserData(data, Atoken) {
    var noOfContact = data.length;
    if (noOfContact == 0) {
        // System.out.println("no contacts,Import Contacts First");
        console.log('No Contacts  ,  Import Contacts First');
        $("#status").empty()
        $("#status").append('<span style="color:red">No Contacts  ,  Import Contacts First</span>')
        document.getElementById("lol").style.display = "none";
    }
    var count = 0;
    do {
        count++;
        var str = $("#email").val();
        var mailid = str.trim();

        var name = ((data[i].name) ? data[i].name : 'N/A');

        var gid = data[i].gid;
        var email = ((data[i].email) ? data[i].email : 'N/A');
        var phone = ((data[i].phone) ? data[i].phone : 'N/A');
        var work = ((data[i].work) ? data[i].work : 'N/A');
        var mobile = ((data[i].mobile) ? data[i].mobile : 'N/A');
        var postal = ((data[i].postal) ? data[i].postal : 'N/A');
        var company = ((data[i].company) ? data[i].company : 'N/A');
        var designation = ((data[i].designation) ? data[i].designation : 'N/A');
        var url = 'https://www.google.com/m8/feeds/photos/media/' + mailid +
            '/' + gid + '?access_token=' + Atoken;
        $("#contact").append(
            $('<figure><img  src=' + url +
                ' onerror="imgError(this);" ><figcaption  id="p[' +
                count + ']"onclick="viewdetail(this.id);"><h3>' +
                name + '</h3><p id="less">Email:' + email +
                '<br>Phone:' + phone + '<br>Locale:' + postal +
                '<br>Company:' + company +
                '<br></p><p id="more">Work:' + work + '<br>Mobile:' +
                mobile + '<br>Designation:' + designation +
                '<br>Company:' + company +
                '<br></p> </figcaption></figure>'));

        i++;       //the main index on which the data is traversed
    } while (count < 18)

}

function imgError(image) {
    image.onerror = "";
    image.src = "Image/user.png";
    return true;
}
//function to toggle the details on image click
function viewdetail(id) {
    var oldData, newData;
    oldData = document.getElementById(id).childNodes[1];
    newData = document.getElementById(id).childNodes[2];
    var clonedElement1 = oldData.cloneNode(true);
    var clonedElement2 = newData.cloneNode(true);

    newData.parentNode.replaceChild(clonedElement1, newData);
    oldData.parentNode.replaceChild(clonedElement2, oldData);

}
//hits the contact controller and gets the list of objects for 
function viewContactsList() {
    $.ajax({
        type: "GET",

        url: "whichcontact/viewcontact",

        success: function(list) {
            data = list;
            handleUserDataList(data);
        },
        error: function(e) {
            alert("error while retrieving Profile" + e)
        }
    });
}
//prints the data in contacts div in a list format
function handleUserDataList(data) {
    var noOfContact = data.length;
    if (noOfContact == 0) {
        // System.out.println("no contacts,Import Contacts First");
    	$("#status").empty()
        $("#status").append('<span style="color:red">No Contacts  ,  Import Contacts First</span>')
        document.getElementById("lol").style.display = "none";//removes the prev page and next page button
        $("[id^='example']").css("display", "none");    //removes the datatable
    }
    $('#example').DataTable({
        data: data,
        columns: [{
                'data': 'name', // can be null or undefined
                "defaultContent": "<i>Not set</i>"
            }, {
                'data': 'phone', // can be null or undefined
                "defaultContent": "<i>Not set</i>"
            }, {
                'data': 'mobile', // can be null or undefined
                "defaultContent": "<i>Not set</i>"
            }, {
                'data': 'email', // can be null or undefined
                "render": function ( data, type, full, meta ) {
                    return '<a href="mailto:'+data+'">'+((data)?data:'NA')+'</a>';
                  },
                "defaultContent": "<i>Not set</i>"
            },

            {
                'data': 'company', // can be null or undefined
                "defaultContent": "<i>Not set</i>"
            }, {
                'data': 'designation', // can be null or undefined
                "defaultContent": "<i>Not set</i>"
            },

        ]
    });
}
//hides the grid contact div ....and brings the datatable in front
function listv() {
    $("#contact").empty();
    $("[id^='example']").css("display", "table-row-group");
    document.getElementById("lol").style.display = "none"; 

}