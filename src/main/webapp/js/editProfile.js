$(document).ready(
		function() 
		{
			$("#editProfile").click(
					function(event) {
						event.preventDefault();
						var mailid = $("#editMailId").val();
						var password = $("#editPassword").val();
						var company = $("#editCompany").val();
						var designation = $("#editDesignatopn").val();
						 

						$.ajax({
							type : "POST",
							contentType : "application/json; charset=utf-8",
							url : "whichcontact/editUserProfile?mailid=" + mailid
									+ "&password=" + password + "&company="
									+ company + "&designation=" + designation,
									 
								 
							data : {
								mailid : mailid,
								password : password,
								company : company,
								designation : designation,
								 
							},
							success : function(response) {
								data = response;
								handleEditResponse(data);
							},
							error : function(e) {
								alert("error while trying to save data"+e)
							}
						});
					});
		});

function handleEditResponse(data) {

	 
	 if (data.status == 200) {
		alert("you suceessfully edit your profile") 
        //top.location.href="Welcome.html";

	}
}

$(document).ready( function() {
    $(document).on('change', '.btn-file :file', function() {
                    var input = $(this),
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                    input.trigger('fileselect', [label]);
                    });

                    $('.btn-file :file').on('fileselect', function(event, label) {
                        
                        var input = $(this).parents('.input-group').find(':text'),
                            log = label;
                        
                        if( input.length ) {
                            input.val(log);
                        } else {
                            if( log ) alert(log);
                        }
        
                    });
                    function readURL(input) {
                        if (input.files && input.files[0]) {
                            var reader = new FileReader();
                            
                            reader.onload = function (e) {
                                $('#img-upload').attr('src', e.target.result);
                            }
                            
                            reader.readAsDataURL(input.files[0]);
                        }
                    }

                    $("#imgInp").change(function(){
                                    readURL(this);
                    });
                    
                    $("#saveimage").click(
        					function(event) {
        						event.preventDefault()
        						var fd = new FormData();
        						var files = $('#imgInp')[0].files[0];
        						fd.append('file',files);

        						
                    	$.ajax({
                    		type : "POST",
                    		enctype: 'multipart/form-data',
							url : "whichcontact/uploadImage",
							data:fd,
							cache : false,
						    processData: false,
							success : function(response) {
								data = response;
								handleImageResponse(data);
							},
							error : function(e) {
								alert("error while trying to save data"+e)
							}
						});
                    	
                    });
                    
    });	
function handleImageResponse(data) {

	 
	 if (data.status == 200) {
		alert("you suceessfully Save your Image") 
       //top.location.href="Welcome.html";

	}
}

function checkLogin(username) {
	
	var storedName = localStorage.getItem('name');
    var storedPw = localStorage.getItem('Password');
	
	if (storedName==username)
	{
		top.location.href="login.html";
	}
}


