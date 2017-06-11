 
function fetch() {
     $.ajax({
         url: 'whichcontact/saveOrganizationData',
         type: 'POST',
         success: function(response) {
             data = response.status;
             console.log(data);
             printResponse(data);
         },

     });
 }

 function printResponse(data) {
     console.log("done");
     if (data == 200) {

         alert("save successfully")
     } else if (data == 400) {
         alert("not save successfully")
     }
 }