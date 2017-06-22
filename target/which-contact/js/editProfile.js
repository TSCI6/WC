$(document).ready(function() {
	$("#editProfile").click(function() {
		var formvalue = new FormData($('#updateForm')[0]);

		$.ajax({
			type : "POST",
			url : "whichcontact/editUserProfile",
			data : formvalue,
			processData : false,
			contentType : false,
			success : function(response) {
				data = response;
				handleEditResponse(data);
			},
			error : function(e) {
				alert("error while trying to save data" + e)
			}
		});
	});
});

function handleEditResponse(data) {

	if (data.status == 200) {
		alert("you suceessfully update your profile")
		// top.location.href="Welcome.html";

	}
}

$(document).ready(
		function() {
			$(document).on(
					'change',
					'.btn-file :file',
					function() {
						var input = $(this), label = input.val().replace(/\\/g,
								'/').replace(/.*\//, '');
						input.trigger('fileselect', [ label ]);
					});

			$('.btn-file :file').on(
					'fileselect',
					function(event, label) {

						var input = $(this).parents('.input-group').find(
								':text'), log = label;

						if (input.length) {
							input.val(log);
						} else {
							if (log)
								alert(log);
						}

					});
			function readURL(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('#img-upload').attr('src', e.target.result);
					}

					reader.readAsDataURL(input.files[0]);
				}
			}

			$("#imgInp").change(function() {
				readURL(this);
			});

		});

function checkLogin(username) {

	var storedName = localStorage.getItem('name');
	var storedPw = localStorage.getItem('Password');

	if (storedName == username) {
		top.location.href = "login.html";
	}
}
