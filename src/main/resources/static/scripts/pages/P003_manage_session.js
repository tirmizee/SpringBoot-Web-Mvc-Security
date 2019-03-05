var ManageSessionModule = function(){
	
	var Search = {};
	var DataTable = {};
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-setting').addClass('active');
	}
	
	var handleDataTable = function() {
		
		var btnDelete = '<button data-btn-name="btnDelete" type="button" class="btn btn-danger" data-toggle="tooltip" title="Delete Member !"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>';
		
		DataTable = $('#TBSession').DataTable({
			processing   : true,
			responsive   : false,
			scrollX      : true,
			select       : true,
			deferRender  : true,
			data         : [],
			columns: [
				{ data : null          ,title : "Action" },
				{ data : "sessionId"   ,title : "Session"},
				{ data : "username"    ,title : "User Name"},
				{ data : "firstName"   ,title : "Full Name"},
				{ data : "roleName"    ,title : "Role Name"},
				{ data : "accessIp"    ,title : "Access IP"},
				{ data : null          ,title : "Status"}
			],
			columnDefs: [
				{
					targets   : 0,
					orderable : false,
					className : "text-center",
					render    : function (data, type, row, meta) {
						return btnDelete;
					}
				},
				{
					targets   :3,
					render    : function (data, type, row, meta) {
						return row.firstName + ' ' + row.lastName;
					}
				},
				{
					targets   : 6,
					orderable : false,
					className : "text-center",
					render    : function (data, type, row, meta) {
						return '<label class="text-success">Active</label>';
					}
				}
			],
			select: {
		   		style: 'single'
		    },
			colReorder : {
		        fixedColumnsLeft: 1
		    }
		});
	}
	
	var loadData = function() {
		AjaxManager.PostData(null ,"api/session/alluserlogged",
			function(response){
				if (response) {
					$('#SPCountUsers').text(response.length);
					DataTable.rows.add(response);
					DataTable.draw();
				}
			},
			function(jqXHR, textStatus, errorThrown){
				
			});
	}
	
	return {
		init : function(){
			activeMenu();
			handleDataTable();
			loadData();
		}
	};
	
}();