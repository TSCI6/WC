function viewUser() {
     $.ajax({
         type: "GET",
         contentType: "application/json; charset=utf-8",
         url: "whichcontact/viewUser",


         success: function(response) {
             data = response;
             handleUserData(data);
         },
         error: function(e) {
             alert("error while trying Compleate Profile" + e)
         }
     });
 }

 function handleUserData(data) {

     var mailid = $("#editMailId").val(data[0].email);
     var password = $("#editPassword").val(data[0].password);
     var password = $("#editConfirm").val(data[0].password);
     var company = $("#editCompany").val(data[0].company);
     var designation = $("#editDesignatopn").val(data[0].designation);
     var image = $("#Imginp").val(data[0].image);
 }