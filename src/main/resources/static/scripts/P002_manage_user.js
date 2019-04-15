var ManageUserModule = function(){
	
	var Search = {};
	var DataTable = {};
	
	var selectedProvinceId = null;
	var selectedDistrictId = null;
	
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
				{ data : "profileImage"           ,title : "Profile" },
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
						return '<img src="' + data + '" class="img-circle" alt="Cinque Terre" width="40" height="40"> ';
					}
				},
				{
					targets   : 2,
					width     : "10%",
					orderable : false,
					render    : function (data, type, row, meta) {
						return meta.settings._iDisplayStart + meta.row + 1;
					}
				},
				{
					targets   : 5,
					render    : function (data, type, row, meta) {
						return row.firstName + ' ' + row.lastName;
					}
				},
				{
					targets   : 6,
					visible   : false
				},
				{
					targets   : 9,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var enable = '<a data-btn-name="enable" href=""><span class="label label-success raduis">enable</span></a>';
						var disable = '<a data-btn-name="enable" href=""><span class="label label-danger raduis">disable</span></a>';
						return data ? enable : disable;
					}
				},
				{
					targets   : 10,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="accountExpired" href=""><span class="label label-success raduis">normal</span></a>';
						var expired = '<a data-btn-name="accountExpired" href=""><span class="label label-danger raduis">expired</span></a>';
						return data ? normal : expired;
					}
				},
				{
					targets   : 11,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="locked" href=""><span class="label label-success raduis">normal</span></a>';
						var locked = '<a data-btn-name="locked" href=""><span class="label label-danger raduis">locked</span></a>';
						return data ? normal : locked;
					}
				},
				{
					targets   : 12,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var normal = '<a data-btn-name="passwordExpired" href=""><span class="label label-success raduis">normal</span></a>';
						var expired = '<a data-btn-name="passwordExpired" href=""><span class="label label-danger raduis">expired</span></a>';
						return data ? normal : expired;
					}
				},
				{
					targets   : 13,
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
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			confirmUpdateStatusEnable(data);
			
		}).on('click', 'a[data-btn-name="locked"]', function (event) {
			event.preventDefault();
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			confirmUpdateStatusLocked(data);
			
		}).on('click', 'a[data-btn-name="passwordExpired"]', function (event) {
			event.preventDefault();
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			confirmUpdateStatusPasswordExpired(data);
			
		}).on('click', 'a[data-btn-name="firstlogin"]', function (event) {
			event.preventDefault();
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			confirmUpdateStatusLogin(data);
		
		}).on('click', 'a[data-btn-name="accountExpired"]', function (event) {
			event.preventDefault();
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			confirmUpdateStatusAccount(data);
		
		}).on('click', 'button[data-btn-name="btnEdit"]', function (event) {
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			showModalEdit(data);
			
		}).on('click', 'button[data-btn-name="btnView"]', function (event) {
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			alert(JSON.stringify(data,null,5));
			
		}).on('click', 'button[data-btn-name="btnDelete"]', function (event) {
			
			var node = $(this).parents('tr');
			var data = DataTable.row(node).data();
			alert(JSON.stringify(data,null,5));
			
		});
		
	}
	
	var showModalEdit = function(data) {
		$('#ModalEditUser').modal({
		    backdrop: 'static'
		});
		AjaxManager.GetData(null, 'api/user/get/' + data.userId,
			function(response){
				
				selectedProvinceId = response.provinceId;
				selectedDistrictId = response.districtId;
				
				var selectedProv = { value : response.provinceId    ,text : response.provinceNameTh};
				var selectedDist = { value : response.districtId    ,text : response.districtNameTh};
				var selectedSubd = { value : response.subdistrictId ,text : response.subdistrictNameTh};
				var selectedRole = { value : response.roleId        ,text : response.roleName};
				
				var passwordExpiredDate = new Date(response.credentialsexpiredDate);
				var accountExpiredDate = new Date(response.accountExpiredDate);
				
				var elementAccountExpired = $('.toggle-account-expired')[0];
				var elementAccountEnabled = $('.toggle-account-enabled')[0];
				var elementAccountLocked = $('.toggle-account-locked')[0];
				var elementPasswordExpired = $('.toggle-password-expired')[0];
				
				$('#SLProvince, #SLDistrict, #SLSubDistrict').empty();
				$('#FormEditUser input[name="userId"]').val(response.userId);
				$('#FormEditUser input[name="username"]').val(response.username);
				$('#FormEditUser input[name="firstName"]').val(response.firstName);
				$('#FormEditUser input[name="lastName"]').val(response.lastName);
				$('#FormEditUser input[name="citizenId"]').val(response.citizenId);
				$('#FormEditUser input[name="tel"]').val(response.tel);
				$('#FormEditUser input[name="email"]').val(response.email);
				$('#FormEditUser input[name="postCode"]').val(response.zipcode);
				$('#FormEditUser input[name="maxSession"]').val(response.maxSession);
				$('#FormEditUser input[name="accountExpiredDate"]').datepicker('setDate', accountExpiredDate);
				$('#FormEditUser input[name="passwordExpiredDate"]').datepicker('setDate', passwordExpiredDate);
				
				$('#SLProvince').append($('<option>', selectedProv)).prop('selected', true);
				$('#SLDistrict').append($('<option>', selectedDist)).prop('selected', true);
				$('#SLSubDistrict').append($('<option>', selectedSubd)).prop('selected', true);
				$('#FormEditUser select[name="roleId"]').append($('<option>', selectedRole)).prop('selected', true);
				
				defaultToggleElement(elementAccountExpired, response.accountnonexpired);
				defaultToggleElement(elementAccountEnabled, response.enabled);
				defaultToggleElement(elementAccountLocked, response.accountnonlocked);
				defaultToggleElement(elementPasswordExpired, response.credentialsnonexpired);
			},
			function(jqXHR, textStatus, errorThrown){
				
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
			status   : !data.enabled
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
				DataTable.ajax.reload(null, false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}
	
	var updateStatusLocked = function(data){
		var request = {
			username : data.username,
			status   : !data.accountnonlocked
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
				DataTable.ajax.reload(null, false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}

	var updateStatusPasswordExpired = function(data){
		var request = {
			username : data.username,
			status   : !data.credentialsnonexpired
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
				DataTable.ajax.reload(null, false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}

	var updateStatusLogin = function(data){
		var request = {
			username : data.username,
			status   : !data.firstLogin
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
				DataTable.ajax.reload(null, false);
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}
	
	var updateStatusAccount = function(data){
		var request = {
			username : data.username,
			status   : !data.accountnonexpired
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
				DataTable.ajax.reload(null, false);
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
	
	var handleSelectRole = function(){
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
	
	var handleSelectEditRole = function(){
		$('#FormEditUser select[name="roleId"]').select2({
			dropdownParent: $('#ModalEditUser .modal-content'),
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
	
	var handleSelectProvince = function(){
		$('#SLProvince').select2({
			dropdownParent: $('#ModalEditUser .modal-content'),
			ajax: {
			    url : 'api/province/findByTerm',
			    delay : 250,
			    type : 'POST',
			    contentType : 'application/json',
			    headers : {
			    	[AjaxManager.CsrfHeader] : AjaxManager.CsrfToken 
	            },
	            data : function (params) {
	            	params.page = params.page || 0;
	            	params.size = 10;
	                return JSON.stringify(params);
	            },
			    processResults : function (data , params) {
			    	return {
		                results : $.map(data.content, function (item) { 
		                    return {
		                    	id     : item.provinceId,
		                    	code   : item.provincecCode,
		                    	text   : item.provinceNameTh,
		                    	textEn   : item.provinceNameEn
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
				var $body_line2 = $('<small><b>' + data.code + ' : ' + data.textEn + '</b></small>');  
				
				$template.append($body_line1);
				$template.append('<br>');
				$template.append($body_line2);
				  
				return $template;
			} 
		}).on('select2:select', function (e) {
		    selectedProvinceId = e.params.data.id;
		    $('#SLDistrict').val([]).trigger('change');
		    $('#SLSubDistrict').val([]).trigger('change');
		    $('#FormEditUser input[name="postCode"]').val('');
		});
	}
	
	var handleSelectDistrict = function(){
		$('#SLDistrict').select2({
			dropdownParent: $('#ModalEditUser .modal-content'),
			placeholder: '',
			ajax: {
			    url : 'api/district/findByTerm',
			    delay : 250,
			    type : 'POST',
			    contentType : 'application/json',
			    headers : {
			    	[AjaxManager.CsrfHeader] : AjaxManager.CsrfToken  
	            },
	            data : function (params) {
	            	params.page = params.page || 0;
	            	params.size = 10;
	            	params.provinceId = selectedProvinceId;
	                return JSON.stringify(params);
	            },
			    processResults : function (data , params) {
			    	return {
		                results : $.map(data.content, function (item) {
		                    return {
		                    	id     : item.districtId,
		                    	code   : item.districtCode,
		                    	text   : item.districtNameTh,
		                        textEn : item.districtNameEn
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
				  var $body_line2 = $('<small><b>' + data.code + ' : ' + data.textEn + ' </b></small>');  
				  
				  $template.append($body_line1);
				  $template.append('<br>');
				  $template.append($body_line2);
				  
                  return $template;
			  } 
		}).on('select2:select', function (e) {
			selectedDistrictId = e.params.data.id;
			$('#SLSubDistrict').val([]).trigger('change');
			$('#FormEditUser input[name="postCode"]').val('');
		});
	}
	
	var handleSelectSubDistrict = function(){
		$('#SLSubDistrict').select2({
			dropdownParent: $('#ModalEditUser .modal-content'),
			placeholder: '',
			ajax: {
			    url : 'api/subdistrict/findByTerm',
			    delay : 250,
			    type : 'POST',
			    contentType : 'application/json',
			    headers : {
			    	[AjaxManager.CsrfHeader] : AjaxManager.CsrfToken  
	            },
	            data : function (params) {
	            	params.page = params.page || 0;
	            	params.size = 10;
	            	params.districtId = selectedDistrictId;
	                return JSON.stringify(params);
	            },
			    processResults : function (data , params) {
			    	return {
		                results : $.map(data.content, function (item) {
		                    return {
		                    	id     : item.subdistrictId,
		                    	code   : item.subdistrictCode,
		                    	text   : item.subdistrictNameTh,
		                    	postcode   : item.zipcode
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
				  var $body_line2 = $('<small><b>' + data.code + '</b></small>');  
				  
				  $template.append($body_line1);
				  $template.append('<br>');
				  $template.append($body_line2);
				  
                  return $template;
			  } 
		}).on('select2:select', function (e) {
			$('#FormEditUser input[name="postCode"]').val(e.params.data.postcode);
		});
	}
	
	var handleInputCitizenId = function(){
		$('#FormEditUser input[name="citizenId"]').inputmask({
			mask : "9-9999-99999-99-9",
			autoUnmask : true
		});
	}
	
	var handleInputTel = function(){
		$('#FormEditUser input[name="tel"]').inputmask({
			mask : "(999) 999-9999",
			autoUnmask : true
		});
	}
	
	var handleInputMaxSession = function(){
		var quantitiy = 0;
		var element = $('#FormEditUser input[name="maxSession"]');
		$('.number-plus').click(function(e){
			e.preventDefault();
			var quantity = parseInt($(element).val());
			$(element).val(quantity + 1);
		});
		$('.number-minus').click(function(e){
			e.preventDefault();
			var quantity = parseInt($(element).val());
			if(quantity > 0){
				$(element).val(quantity - 1);
	        }
		});
		$(element).inputmask('Regex', {regex: "^[0-9]+$"});
		
	}
	
	var handleDateAccountExpired = function(){
		$('#FormEditUser input[name="accountExpiredDate"]').datepicker({
			autoclose: true,
			format: 'dd/mm/yyyy',
	        startDate: '+0d'
		});
	}
	
	var handlePasswordExpiredDate = function(){
		$('#FormEditUser input[name="passwordExpiredDate"]').datepicker({
			autoclose: true,
			format: 'dd/mm/yyyy',
	        startDate: '+0d'
		});
	}
	
	var defaultToggleElement = function(element, flag){
		$(element).find('.active').removeClass('active');
		if(flag){
			$(element).find('[data-name="Y"]').addClass('active');
			$(element).find('[data-name="Y"]').removeClass('btn-default');
			$(element).find('[data-name="Y"]').removeClass('btn-success');
			$(element).find('[data-name="Y"]').addClass('btn-success');
			$(element).find('[data-name="N"]').removeClass('btn-danger');
			$(element).find('[data-name="N"]').removeClass('btn-default');
			$(element).find('[data-name="N"]').addClass('btn-default');
		}else {
			$(element).find('[data-name="N"]').addClass('active');
			$(element).find('[data-name="N"]').removeClass('btn-default');
			$(element).find('[data-name="N"]').removeClass('btn-danger');
			$(element).find('[data-name="N"]').addClass('btn-danger');
			$(element).find('[data-name="Y"]').removeClass('btn-success');
			$(element).find('[data-name="Y"]').removeClass('btn-default');
			$(element).find('[data-name="Y"]').addClass('btn-default');
		}
	}
	
	var toggleElement = function(element){
		$(element).find('.btn').toggleClass('active');
		if($(element).find('.active').data('name') == 'Y'){
			$(element).find('.active').removeClass('btn-default');
			$(element).find('.active').removeClass('btn-success');
			$(element).find('.active').addClass('btn-success');
			$(element).find(':not(.active)').removeClass('btn-danger');
			$(element).find(':not(.active)').removeClass('btn-default');
			$(element).find(':not(.active)').addClass('btn-default');
		}else {
			$(element).find('.active').removeClass('btn-default');
			$(element).find('.active').removeClass('btn-danger');
			$(element).find('.active').addClass('btn-danger');
			$(element).find(':not(.active)').removeClass('btn-success');
			$(element).find(':not(.active)').removeClass('btn-default');
			$(element).find(':not(.active)').addClass('btn-default');
		}
	}
	
	var handleToggleAccountExpired = function(){
		$('.toggle-account-expired').click(function(event) {
			toggleElement(this);
		});
	}
	
	var handleToggleAccountEnabled = function(){
		$('.toggle-account-enabled').click(function(event) {
			toggleElement(this);
		});
	}
	
	var handleToggleAccountLocked = function(){
		$('.toggle-account-locked').click(function(event) {
			toggleElement(this);
		});
	}
	
	var handleTogglePasswordExpired = function(){
		$('.toggle-password-expired').click(function(event) {
			toggleElement(this);
		});
	}
	
	return {
		init : function(){
			activeMenu();
			handleDataTable();
			handleButtonSearch();
			handleSelectRole();
			handleSelectEditRole();
			handleSelectProvince();
			handleSelectDistrict();
			handleSelectSubDistrict();
			handleInputTel();
			handleInputCitizenId();
			handleInputMaxSession();
			handleDateAccountExpired();
			handlePasswordExpiredDate();
			handleToggleAccountExpired();
			handleToggleAccountEnabled();
			handleToggleAccountLocked();
			handleTogglePasswordExpired();
		}
	};
	
}();