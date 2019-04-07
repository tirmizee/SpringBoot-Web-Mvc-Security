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
					[AjaxManager.CsrfHeader] : AjaxManager.CsrfToken 
	            },
				data : function (d) {
					d.search = Search;
					return JSON.stringify(d);
				},error : function (xhr, error, thrown) {
					window.location.replace("/login");
	            }
			},
			columns: [
				{ data : null           		  ,title : "Action" },
				{ data : null                     ,title : "Order" },
				{ data : "userId"                 ,title : "User ID" },
				{ data : "username"               ,title : "Username" },
				{ data : "firstName"              ,title : "Full Name" },
				{ data : "lastName"               ,title : "Last Name" },
				{ data : "roleName"               ,title : "Role Name" },
				{ data : "email"                  ,title : "Email" },
				{ data : "enabled"     		      ,title : "Status Enable" },
				{ data : "accountnonexpired"      ,title : "Status Account" },
				{ data : "accountnonlocked"       ,title : "Status Locked" },
				{ data : "credentialsnonexpired"  ,title : "Status Password" },
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
					targets   : 4,
					render    : function (data, type, row, meta) {
						return row.firstName + ' ' + row.lastName;
					}
				},
				{
					targets   : 5,
					visible   : false
				},
				{
					targets   : 8,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var enable = '<a data-btn-name="enable" href=""><span class="label label-success raduis">enable</span></a>';
						var disable = '<a data-btn-name="enable" href=""><span class="label label-danger raduis">disable</span></a>';
						return data ? enable : disable;
					}
				},
				{
					targets   : 9,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="accountExpired" href=""><span class="label label-success raduis">normal</span></a>';
						var expired = '<a data-btn-name="accountExpired" href=""><span class="label label-danger raduis">expired</span></a>';
						return data ? normal : expired;
					}
				},
				{
					targets   : 10,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="locked" href=""><span class="label label-success raduis">normal</span></a>';
						var locked = '<a data-btn-name="locked" href=""><span class="label label-danger raduis">locked</span></a>';
						return data ? normal : locked;
					}
				},
				{
					targets   : 11,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="passwordExpired" href=""><span class="label label-success raduis">normal</span></a>';
						var expired = '<a data-btn-name="passwordExpired" href=""><span class="label label-danger raduis">expired</span></a>';
						return data ? normal : expired;
					}
				},
				{
					targets   : 12,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="firstlogin" href=""><span class="label label-success raduis">active</span></a>';
						var expired = '<a data-btn-name="firstlogin" href=""><span class="label label-warning raduis">inactive</span></a>';
						return !data ? normal : expired;
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
			confirmUpdateStatusEnable(data);
			
		}).on('click', 'a[data-btn-name="locked"]', function (event) {
			event.preventDefault();
			
			var data = DataTable.row($(this).parents('tr')).data();
			confirmUpdateStatusLocked(data);
			
		}).on('click', 'a[data-btn-name="passwordExpired"]', function (event) {
			event.preventDefault();
			
			var data = DataTable.row($(this).parents('tr')).data();
			confirmUpdateStatusPasswordExpired(data);
			
		}).on('click', 'a[data-btn-name="firstlogin"]', function (event) {
			event.preventDefault();
			
			var data = DataTable.row($(this).parents('tr')).data();
			confirmUpdateStatusLogin(data);
		}).on('click', 'a[data-btn-name="accountExpired"]', function (event) {
			event.preventDefault();
			
			var data = DataTable.row($(this).parents('tr')).data();
			confirmUpdateStatusAccount(data);
		}).on('click', 'button[data-btn-name="btnEdit"]', function (event) {
			
			var data = DataTable.row($(this).parents('tr')).data();
			showModalEdit(data);
			
		}).on('click', 'button[data-btn-name="btnView"]', function (event) {
			
			var data = DataTable.row($(this).parents('tr')).data();
			alert(JSON.stringify(data,null,5));
			
		}).on('click', 'button[data-btn-name="btnDelete"]', function (event) {
			
			var data = DataTable.row($(this).parents('tr')).data();
			alert(JSON.stringify(data,null,5));
			
		});
		
	}
	
	var showModalEdit = function(data){
		$('#ModalEditUser').modal({
		    backdrop: 'static',
		    keyboard: false
		});
		AjaxManager.GetData(null, 'api/user/get/' + data.userId,
			function(response){
				$('#FormEditUser input[name="userId"]').val(response.userId);
				$('#FormEditUser input[name="username"]').val(response.username);
				$('#FormEditUser input[name="firstName"]').val(response.firstName);
				$('#FormEditUser input[name="lastName"]').val(response.lastName);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}
	
	var confirmUpdateStatusAccount = function(data){
		$.confirm({
		    title: 'Confirm!',
		    icon: 'fa fa-warning',
		    type: 'blue',
		    content: 'Simple confirm!',
		    buttons: {
		        confirm: {
		        	btnClass : 'btn-blue',
		        	action : function() {
		        		updateStatusAccount(data);
		            }
		        },
		        cancel: function () {}
		    }
		});
	}
	
	var confirmUpdateStatusEnable = function(data){
		$.confirm({
		    title: 'Confirm!',
		    icon: 'fa fa-warning',
		    type: 'blue',
		    content: 'Simple confirm!',
		    buttons: {
		        confirm: {
		        	btnClass : 'btn-blue',
		        	action : function() {
		        		updateStatusEnable(data);
		            }
		        },
		        cancel: function () {}
		    }
		});
	}
	
	var confirmUpdateStatusLocked = function(data){
		$.confirm({
		    title : 'Confirm!',
		    icon : 'fa fa-warning',
		    type : 'blue',
		    content : 'Simple confirm!',
		    buttons : {
		        confirm : {
		        	btnClass : 'btn-blue',
		        	action : function() {
		        		updateStatusLocked(data);
		            }
		        },
		        cancel: function () {}
		    }
		});
	}
	
	var confirmUpdateStatusPasswordExpired = function(data){
		$.confirm({
		    title : 'Confirm!',
		    icon : 'fa fa-warning',
		    type : 'blue',
		    content : 'Simple confirm!',
		    buttons : {
		        confirm : {
		        	btnClass : 'btn-blue',
		        	action : function() {
		        		updateStatusPasswordExpired(data);
		            }
		        },
		        cancel : function () {}
		    }
		});
	}
	
	var confirmUpdateStatusLogin = function(data){
		$.confirm({
		    title : 'Confirm!',
		    icon : 'fa fa-warning',
		    type : 'blue',
		    content : 'Simple confirm!',
		    buttons : {
		        confirm : {
		        	btnClass : 'btn-blue',
		        	action : function() {
		        		updateStatusLogin(data);
		            }
		        },
		        cancel: function () {}
		    }
		});
	}
	
	var updateStatusEnable = function(data){
		var request = {
			username : data.username,
			status : !data.enabled
		};
		AjaxManager.PostData(request, 'api/user/update/enabled',
			function(response){
				$.confirm({
				    title: 'Meaages Alert!',
				    content: response.messageName,
				    type: 'green',
				    typeAnimated: true,
				    buttons: {
				        close: function () {	        				        	
				        
				        }
				    }
				});
				DataTable.ajax.reload(false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}
	
	var updateStatusLocked = function(data){
		var request = {
			username : data.username,
			status : !data.accountnonlocked
		};
		AjaxManager.PostData(request, 'api/user/update/accountnonlocked',
			function(response){
				$.confirm({
				    title: 'Meaages Alert!',
				    content: response.messageName,
				    type: 'green',
				    typeAnimated: true,
				    buttons: {
				        close: function () {	        				        	
				        
				        }
				    }
				});
				DataTable.ajax.reload(false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}

	var updateStatusPasswordExpired = function(data){
		var request = {
			username : data.username,
			status : !data.credentialsnonexpired
		};
		AjaxManager.PostData(request, 'api/user/update/passwordexpired',
			function(response){
				$.confirm({
				    title: 'Meaages Alert!',
				    content: response.messageName,
				    type: 'green',
				    typeAnimated: true,
				    buttons: {
				        close: function () {	        				        	
				        
				        }
				    }
				});
				DataTable.ajax.reload(false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}

	var updateStatusLogin = function(data){
		var request = {
			username : data.username,
			status : !data.firstLogin
		};
		AjaxManager.PostData(request, 'api/user/update/firstlogin',
			function(response){
				$.confirm({
				    title : 'Meaages Alert!',
				    content : response.messageName,
				    type: 'green',
				    typeAnimated : true,
				    buttons: {
				        close: function () {	        				        	
				        
				        }
				    }
				});
				DataTable.ajax.reload(false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}
	
	var updateStatusAccount = function(data){
		var request = {
			username : data.username,
			status : !data.accountnonexpired
		};
		AjaxManager.PostData(request, 'api/user/update/accountnonexpired',
			function(response){
				$.confirm({
				    title : 'Meaages Alert!',
				    content : response.messageName,
				    type: 'green',
				    typeAnimated : true,
				    buttons: {
				        close: function () {	        				        	
				        
				        }
				    }
				});
				DataTable.ajax.reload(false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
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
			    	[AjaxManager.CsrfHeader] : AjaxManager.CsrfToken 
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