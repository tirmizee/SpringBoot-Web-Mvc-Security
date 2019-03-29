var ManageRoleModule = function(){
	
	var Search = {};
	var DataTable = {};
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-setting').addClass('active');
	}
	
	var handleDataTable = function() {
		
		var btnEdit = '<button data-btn-name="btnEdit" type="button" class="btn btn-success" data-toggle="tooltip" title="Edit Member !"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>';
		var btnView = '<button data-btn-name="btnView" type="button" class="btn btn-info" data-toggle="tooltip" title="View Member !"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></button>';
		var btnDelete = '<button data-btn-name="btnDelete" type="button" class="btn btn-danger" data-toggle="tooltip" title="Delete Member !"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>';
		
		DataTable = $('#TBRole').DataTable({
			processing   : true,
			serverSide   : true,
			searching    : false,
			responsive   : false,
			scrollX      : true,
			select       : true,
			deferRender  : true,
			ajax: {
				url: 'api/role/find/page',
				type: "POST",
				contentType: 'application/json',
				headers: {
	                'X-CSRF-TOKEN' : AjaxManager.CsrfToken 
	            },
				data : function (d) {
					d.search = Search;
					return JSON.stringify(d);
				},error : function (xhr, error, thrown) {
					window.location.replace("login");
	            }
			},
			columns: [
				{ data : null          ,title : "Order" },
				{ data : "roleId"      ,title : "Role Id"},
				{ data : "roleCode"    ,title : "Role Code"},
				{ data : "roleName"    ,title : "Role Name"},
				{ data : "roleDesc"    ,title : "Role Description"},
				{ data : "CreateDate"  ,title : "Create Date"},
				{ data : null          ,title : "Action" }
			],
			columnDefs: [
				{
					targets   : 0,
					width     : "5%",
					orderable : false,
					render    : function (data, type, row, meta) {
						return meta.settings._iDisplayStart + meta.row + 1;
					}
				},
				{
					targets : 1,
					visible : false
				},
				{
					targets   : 6,
					orderable : false,
					width     : "10%",
					className : "text-center",
					render    : function (data, type, row, meta) {
						return btnDelete + ' ' + btnEdit + ' ' + btnView;
					}
				}
			],
			select: {
		   		style: 'single'
		    },
			colReorder : {
		        fixedColumnsLeft: 1
		    }
		}).on('click', 'button[data-btn-name="btnEdit"]', function (event) {
			$('#ModalEditRole').modal({
			    backdrop: 'static',
			    keyboard: false
			});
		});
	}
	
	var handleButtonSearch = function(){
		$('#BtnSearch').on('click', function(){
			Search.roleCode = $('#FormSearchRole input[name="roleCode"]').val();
			Search.roleName = $('#FormSearchRole input[name="roleName"]').val();
			DataTable.ajax.reload();
		});
	}
	
	var handleButtonClear = function(){
		$('#BtnClear').on('click', function(){
			$('#FormSearchRole')[0].reset();
		});
	}
	
	return {
		init : function(){
			activeMenu();
			handleDataTable();
			handleButtonSearch();
			handleButtonClear();
		}
	};
	
}();