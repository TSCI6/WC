/*AJAX call hitting CrunchBaseOrganizationController and return top funding Companies detail on UI*/


function viewFundingData() {

    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "whichcontact/crunchBasefundingResponse",
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


    $('#Response').DataTable({
        data: data,
        columns: [{
                'data': 'name'
            }, {
                'data': 'city_name'
            }, {
                'data': 'region_name'
            }, {
                'data': 'country_code'
            },

            {
                'data': 'money_raised_currency_code'
            }, {
                'data': 'money_raised'
            },

        ]

    });
}