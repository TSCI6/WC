/*
 * 
 * 
 the script for IndianCo.HTML
 * 
 * 
 *
 * @author TS001127
 *
 */

// mapped to glassDoorResponseController.java--->glassdoorService.java
$(window).on("load", function() {
	$.ajax({
		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "whichcontact/viewIndianCoData",
		success : function(response) {
			data = response;
			handleUserData(data);
		},
		error : function(e) {
			alert("error while trying Compleate Profile" + e)
		}
	});
});
function handleUserData(data) {

	$('#indCompanyTable').DataTable({
		data : data,
		columns : [ {
			'data' : 'state',
			"defaultContent" : "<i>Not set</i>"
		}, {
			'data' : 'investment',
			"defaultContent" : "<i>Not set</i>"
		}, {
			'data' : 'name',
			"defaultContent" : "<i>Not set</i>"
		}, {
			'data' : 'cin',
			"render" : function(data, type, full, meta) {
				return '<a href="http://www.mca.gov.in/mcafoportal/viewCompanyMasterData.do" target="_blank">'
						+ ((data) ? data : 'NA') + '</a>';
			},
			"defaultContent" : "<i>Not set</i>"
		}, {
			'data' : 'registeredaddress',
			"defaultContent" : "<i>Not set</i>"
		}, {
			'data' : 'lastrevenue',
			"defaultContent" : "<i>Not set</i>"
		},

		]
	});
}
