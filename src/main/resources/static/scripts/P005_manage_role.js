var ManageRoleModule = function(){
	
	var SearchRole = {};
	var SearchPermission = {};
	var SelectedRoleId = null;
	
	var TableRole = {};
	var TableEditPermission = {};
	var TableSearchPermission = {};
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-setting').addClass('active');
	}
	
	var handleTableRole = function() {
		
		var btnEdit = '<button data-btn-name="btnEdit" type="button" class="btn btn-success" data-toggle="tooltip" title="Edit Member !"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>';
		var btnView = '<button data-btn-name="btnView" type="button" class="btn btn-info" data-toggle="tooltip" title="View Member !"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></button>';
		var btnDelete = '<button data-btn-name="btnDelete" type="button" class="btn btn-danger" data-toggle="tooltip" title="Delete Member !"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>';
		
		TableRole = $('#TBRole').DataTable({
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
					[AjaxManager.CsrfHeader] : AjaxManager.CsrfToken 
	            },
				data : function (d) {
					d.search = SearchRole;
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
			
			var data = TableRole.row($(this).parents('tr')).data();
			
			SelectedRoleId = data.roleId;
			
			$('#ModalEditRole').modal({
			    backdrop: 'static',
			    keyboard: false
			});
			
			AjaxManager.GetData( null, 'api/role/get/' + data.roleId,
				function(response){
					$('#FormEditRole input[name="roleCode"]').val(response.roleCode);
					$('#FormEditRole input[name="roleName"]').val(response.roleName);
					TableEditPermission.ajax.url( 'api/permission/' + data.roleId).load();
				},
				function(jqXHR, textStatus, errorThrown){
					$.alert('Error!');
				}
			);
			
		});
	}
	
	var handleTableEditPermission = function(){
		TableEditPermission = $('#TBEditPermission').DataTable({
			processing   : true,
			responsive   : false,
			searching    : true,
			scrollX      : true,
			deferRender  : false,
			data: [],
			columns: [
				{ data : null           ,title : "Order" },
				{ data : "perId"        ,title : "" },
				{ data : "perCode"      ,title : "Permission Code"},
				{ data : "perName"      ,title : "Permission Name"},
				{ data : "hasPercode"   ,title : "Status"}
			],
			columnDefs: [
				{
					targets   : 0,
					width     : "10%",
					searchable: false,
					orderable : false
				},
				{
					targets : 1,
					visible : false
				},
				{
					targets : 2,
					width : "10%"
				},
				{
					targets   : 4,
					width     : "10%",
					className : "text-center",
					orderDataType : "dom-text",
					render : function (data, type, row, meta) {
						var checked = (data ? 'checked' : '');
						return '<input type="checkbox" ' + checked + ' data-toggle="toggle" data-style="ios" data-onstyle="success" data-offstyle="danger" data-size="mini" >';
					}
				}
			],
			fnDrawCallback : function() {
	            $('input[type="checkbox"]').bootstrapToggle();
	        },
	        lengthMenu : [[5,10, 25, 50, -1], [5,10, 25, 50, "All"]]
	        
		}).on( 'draw.dt', function () {
			var PageInfo = TableEditPermission.page.info();
			TableEditPermission.column(0, { page: 'current' }).nodes().each( function (cell, i) {
	        	cell.innerHTML = i + 1 + PageInfo.start;
	        });
	    });
	}
	
	var handleTableSearchPermission = function(){
		TableSearchPermission = $('#TBSearchPermission').DataTable({
			serverSide   : true,
			searching    : false,
			responsive   : false,
			scrollX      : true,
			select       : true,
			deferRender  : true,
			ajax: {
				url: 'api/permission/page',
				type: "POST",
				contentType: 'application/json',
				headers: {
					[AjaxManager.CsrfHeader] : AjaxManager.CsrfToken 
	            },
				data : function (d) {
					d.search = SearchPermission;
					return JSON.stringify(d);
				},error : function (xhr, error, thrown) {
					window.location.replace("login");
	            }
			},
			columns: [
				{ data : null          ,title : "Order" },
				{ data : "perId"       ,title : "Permission ID"},
				{ data : "perCode"     ,title : "Permission Code"},
				{ data : "perName"     ,title : "Permission Name"},
				{ data : "createDate"  ,title : "Create Date"}
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
					targets : 1 ,
					width     : "10%"
				}
			]
		});
	}
	
	var handleButtonSearch = function(){
		$('#BtnSearch').on('click', function(event){
			SearchRole.roleCode = $('#FormSearchRole input[name="roleCode"]').val();
			SearchRole.roleName = $('#FormSearchRole input[name="roleName"]').val();
			TableRole.ajax.reload();
		});
	}
	
	var handleButtonSearchPermission = function(){
		$('#BtnSearchPermission').on('click', function(event){
			SearchPermission.perCode = $('#FormSearchPermission input[name="perCode"]').val();
			SearchPermission.perName = $('#FormSearchPermission input[name="perName"]').val();
			TableSearchPermission.ajax.reload();
		});
	}
	
	var handleButtonClear = function(){
		$('#BtnClear').on('click', function(){
			$('#FormSearchRole')[0].reset();
		});
	}
	
	var handleButtonClearFormPermission = function(){
		$('#BtnClearPermission').on('click', function(){
			$('#FormSearchPermission')[0].reset();
		});
	}
	
	var handleModalEditRole = function(){
		$(document).bind('shown.bs.modal', function (e) {
			TableEditPermission.columns.adjust();
		}).bind('hidden.bs.modal', function (event) {
			$('#FormEditRole').bootstrapValidator('resetForm', true);
		});;
	}
	
	var handleFormEditRole = function(){
		$('#FormEditRole').bootstrapValidator({
			excluded: [':disabled'],
	        fields : {
	        	roleCode : {
	                validators: {
	                    notEmpty: {
	                        message: 'The username is required'
	                    }
	                }
	            },
	            roleName : {
	                validators: {
	                    notEmpty: {
	                        message: 'The username is required'
	                    }
	                }
	            }
        	}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            
            $.confirm({
			    title: 'Confirm!',
			    icon: 'fa fa-warning',
			    typeAnimated: true,
			    animation: 'zoom',
			    type: 'orange',
			    content: 'Confirm!',
			    buttons: {
			        cancel: function () {
			        	$('#FormEditRole button[type="submit"]').attr("disabled", false);
			        },
		            confirm: {
			        	btnClass : 'btn-orange',
			        	action : function() {
			        		updateRole();
			            }
			        }
			    }
			});
		});
	}
	
	var updateRole = function(){
		
		var request = {
			roleId   : SelectedRoleId,
			roleCode : $('#FormEditRole input[name="roleCode"]').val(),
			roleName : $('#FormEditRole input[name="roleName"]').val(),
			perIds   : []
		};
		
		$('input:checked', $('#TBEditPermission').dataTable().fnGetNodes()).each(function() {
			var data = TableEditPermission.row($(this).parents('tr')).data();
			if (data !== undefined) {
				request.perIds.push(data.perId);
			}
		});
		
		AjaxManager.PostData(request ,"api/role/update",
			function(response){
				if (response) {
					$.confirm({
					    title: 'Meaages Alert!',
					    content: 'Remove Session Complete',
					    type: 'green',
					    typeAnimated: true,
					    buttons: {
					        close: function () {
					        	$('#ModalEditRole').modal('hide');
					        }
					    }
					});
				}
			},
			function(jqXHR, textStatus, errorThrown){
				$.confirm({
				    title: 'Meaages Alert!',
				    content: 'Remove Session Complete',
				    type: 'red',
				    typeAnimated: true,
				    buttons: {
				        close: function () {
				        	$('#ModalEditRole').modal('hide');
				        }
				    }
				});
			}
		);
	}
	
	var handleTab = function(){
		$('#tab').on('shown.bs.tab', function(){
			TableSearchPermission.columns.adjust();
		});
	}
	
	return {
		init : function(){
			activeMenu();
			handleTableRole();
			handleTableEditPermission();
			handleTableSearchPermission();
			handleButtonSearch();
			handleButtonSearchPermission();
			handleButtonClear();
			handleButtonClearFormPermission();
			handleModalEditRole();
			handleFormEditRole();
			handleTab();
		}
	};
	
}();