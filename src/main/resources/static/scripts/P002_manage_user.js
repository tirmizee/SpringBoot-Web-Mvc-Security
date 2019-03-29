var ManageUserModule = function(){
	
	var Search = {};
	var DataTable = {};
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-setting').addClass('active');
	}
	
	var handleDataTable = function() {
		
		var btnEdit = '<button data-btn-name="btnEdit" type="button" class="btn btn-success" data-toggle="tooltip" title="Edit Member !"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>';
		var btnView = '<button data-btn-name="btnView" type="button" class="btn btn-info" data-toggle="tooltip" title="View Member !"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></button>';
		var btnDelete = '<button data-btn-name="btnDelete" type="button" class="btn btn-danger" data-toggle="tooltip" title="Delete Member !"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>';
		
		DataTable = $('#TBUser').DataTable({
			processing   : true,
			serverSide   : true,
			responsive   : false,
			searching    : false,
			scrollX      : true,
			select       : true,
			deferRender  : true,
			pagingType   : "full_numbers",
			ajax: {
				url: 'api/user/page',
				type: "POST",
				contentType: 'application/json',
				headers: {
	                'X-CSRF-TOKEN' : AjaxManager.CsrfToken 
	            },
				data : function (d) {
					d.search = Search;
					return JSON.stringify(d);
				},error : function (xhr, error, thrown) {
					window.location.replace("tirmizee/login");
	            }
			},
			columns: [
				{ data: null           		      ,title : "Action" },
				{ data : null                     ,title : "Order" },
				{ data : "username"               ,title : "Ussername"},
				{ data : "firstName"              ,title : "First Name"},
				{ data : "lastName"               ,title : "Last Name"},
				{ data : "roleName"               ,title : "Role Name"},
				{ data : "email"                  ,title : "Email"},
				{ data : "enabled"     		      ,title : "Status Enable"},
				{ data : "accountnonlocked"       ,title : "Status Locked"},
				{ data : "credentialsnonexpired"  ,title : "Status Password"},
				{ data : "firstLogin"             ,title : "Status First Login"}
			],
			columnDefs: [
				{
					targets   : 0,
					orderable : false,
					className : "text-center",
					render    : function (data, type, row, meta) {
						return btnDelete + ' ' + btnEdit + ' ' + btnView;
					}
				},
				{
					targets   : 1,
					width     : "10%",
					orderable : false,
					render    : function (data, type, row, meta) {
						return meta.settings._iDisplayStart + meta.row + 1;
					}
				},
				{
					targets   : 7,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var actice = '<a data-btn-name="enable" href=""><span class="label label-success raduis">active</span></a>';
						var inActive = '<a data-btn-name="enable" href=""><span class="label label-danger raduis">inactive</span></a>';
						return data ? actice : inActive;
					}
				},
				{
					targets   : 8,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="locked" href=""><span class="label label-success raduis">normal</span></a>';
						var locked = '<a data-btn-name="locked" href=""><span class="label label-danger raduis">locked</span></a>';
						return data ? normal : locked;
					}
				},
				{
					targets   : 9,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="expired" href=""><span class="label label-success raduis">normal</span></a>';
						var expired = '<a data-btn-name="expired" href=""><span class="label label-danger raduis">expired</span></a>';
						return data ? normal : expired;
					}
				},
				{
					targets   : 10,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="firstlogin" href=""><span class="label label-success raduis">active</span></a>';
						var expired = '<a data-btn-name="firstlogin" href=""><span class="label label-warning raduis">inactive</span></a>';
						return data ? normal : expired;
					}
				}
			],
			select: {
		   		style: 'single'
		    },
			colReorder : {
		        fixedColumnsLeft: 2
		    }
		}).on('click', 'a[data-btn-name="enable"]', function (event) {
			event.preventDefault();

			var data = DataTable.row($(this).parents('tr')).data();
			updateStatusEnable(data);
		}).on('click', 'a[data-btn-name="locked"]', function (event) {
			event.preventDefault();
			
			var data = DataTable.row($(this).parents('tr')).data();
			updateStatusLocked(data);
		}).on('click', 'a[data-btn-name="expired"]', function (event) {
			event.preventDefault();
			
			var data = DataTable.row($(this).parents('tr')).data();
			updateStatusExpired(data);
		}).on('click', 'a[data-btn-name="firstlogin"]', function (event) {
			event.preventDefault();
			
			var data = DataTable.row($(this).parents('tr')).data();
			updateStatusLogin(data);
		});
		
	}
	
	var updateStatusEnable = function(data){
		
	}
	
	var updateStatusLocked = function(data){
		
	}

	var updateStatusExpired = function(data){
		
	}

	var updateStatusLogin = function(data){
	
	}
	
	var handleButtonSearch = function(){
		$('#BtnSearch').on('click', function(){
			Search.username = $('input[name="username"]').val();
			Search.firstName = $('input[name="firstName"]').val();
			Search.lastName = $('input[name="lastName"]').val();
			Search.email = $('input[name="email"]').val();
			Search.roleId = parseInt($('#SLRole').val());
			DataTable.ajax.reload();
		});
	}
	
	var handleSelect2Role = function(){
		$('#SLRole').select2({
			placeholder: 'Role',
			ajax: {
			    url : 'api/role/page',
			    delay : 250,
			    type : 'POST',
			    contentType : 'application/json',
			    headers : {
	                'X-CSRF-TOKEN' : AjaxManager.CsrfToken 
	            },
	            data : function (params) {
	            	params.page = params.page || 0;
	            	params.size = 5;
	                return JSON.stringify(params);
	            },
			    processResults : function (data , params) {
			    	return {
		                results : $.map(data.content, function (item) { 
		                    return {
		                    	id     : item.roleId,
		                    	code   : item.roleCode,
		                    	text   : item.roleName
		                    }
		                }),
	                    pagination: {
	                        more : !data.last
	                    }
		            };
			    }
			  },
			  cache : true,
			  templateResult : function (data) {
				  
				  var $template = $('<div></div>');
				  var $body_line1 = $('<span>' + data.text + '</span>');  
				  var $body_line2 = $('<small><b>code : </b>' + data.code + '</small>');  
				  
				  $template.append($body_line1);
				  $template.append('<br>');
				  $template.append($body_line2);
				  
                  return $template;
			  } 
		});
	}
	
	return {
		init : function(){
			activeMenu();
			handleDataTable();
			handleButtonSearch();
			handleSelect2Role();
		}
	};
	
}();