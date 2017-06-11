/*/AJAX call hitting the GlassDoorResposeController and show the company detail based on user Profile/ */
function viewData() {

    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "whichcontact/viewEmployeeData",
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


    $('#pagination').DataTable({
        data: data,
        columns: [{
                'data': 'id'
            },
            {
                'data': 'name'
            },
            {
                'data': 'website'
            },
            {
                'data': 'logo',
                "render": appendImageIcon
            }
        ]

    
    });
}

  

function appendImageIcon(data) {
    return "<div style='height:90px;width:90px;overflow:hidden;'><img src=" + data + "  style='height:100%;width:100%;'>";
}