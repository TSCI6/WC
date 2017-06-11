  jQuery(document).ready(
     function() {

         $("#signupForm").click(
             function(event) {
                 event.preventDefault();
                 var username = $("#user").val();
                 var mailId = $("#useremail").val();
                 var password = $("#firstPassword").val();
                 var company = $("#userCompany").val();
                 var designation = $("#userDesignation").val();
                 if(username.length==null || username.length==0 || mailId.length==null|| mailId.length==0 || password.length==null||password.length==0||company.length==null||company.length==0||designation.length==null||designation.length==0){
                	 $('#error').html("<p> *All Fields are required </p>");
                 }
                 else{
                 $.ajax({
                     type: "POST",
                     contentType: "application/json; charset=utf-8",
                     url: "whichcontact/addUser?username=" + username +
                         "&mailId=" + mailId + "&password=" +
                         password + "&company=" + company +
                         "&designation=" + designation,
                     data: {
                         username: username,
                         mailId: mailId,
                         password: password,
                         company: company,
                         designation: designation
                     },
                     success: function(response) {
                         data = response;
                         handleRegistrationResponse(data);

                     },
                     error: function(e) {
                         alert("error while trying to save data")
                     }
                 });
                 }
             });

             

         function handleRegistrationResponse(data) {

             if (data.status == 400) {


                 $('#error').html("<p> User already exist </p>");
             } else if (data.status == 200) {

                 top.location.href = "login.html";

             }
         }

         $("#login").click(
             function(event) {
                 event.preventDefault();
                 var username = $("#nam").val();
                 var password = $("#pass").val();
                 if (username.length == null || username.length == 0 || password.length == null || password.length == 0) {

                     $('#response').html("<p> *Both fields are required.</p>");
                 } else {
                     $.ajax({
                         type: "POST",
                         contentType: "application/json; charset=utf-8",
                         url: "whichcontact/login?username=" + username +
                             "&password=" + password,
                         data: {
                             username: username,
                             password: password
                         },
                         success: function(response) {
                             data = response.status;
                             store(username, password);
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

         //Appending error message if the credtial is not match

         $('#response').html("<p> *Username & Password is incorrect.</p>");
     } else if (data == 200) {
         //redirect the dashboard page if th result is success 

         top.location.href = "TopCompanies.html";

     }
 }

 //storing input from login
 function store(username, password) {

     localStorage.setItem('name', username);
     localStorage.setItem('Password', password);



 }