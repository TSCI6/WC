function inviteContact() {
       $.ajax({
              type : "POST",
              contentType : "application/json; charset=utf-8",
              url : "whichcontact/commonEmail",
              success : function(response) {
                     data = response.status;
                     handleInviteResponse(data);
              }
               
              }
       });

       function handleInviteResponse(data) {
    	   
    	   if(data ==200){
    		   alert("save successfully")
    		   }
             
       }
}

function sendInvitation() {
       $.ajax({
              type : "PUT",
              contentType :"application/json; charset=utf-8",
              url : "whichcontact/sendInvitation",
              success : function(response) {
                     data = response.status;
                     handleEmailResponse(data);
              }
              
       });

       function  handleEmailResponse(data) {

              if (data == 200) {
                     alert("send mail successfully")
              } else {
                     alert("error while sending")
              }

       }
}

