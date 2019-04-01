var ManageSessionModule = function(){
	
	var Search = {};
	var DataTable = {};
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-setting').addClass('active');
	}
	
	var handleDataTable = function() {
		
		var btnDelete = '<a href="#" data-btn-name="btnDelete" data-toggle="tooltip" title="Delete Session !"><span class="fa fa-lg fa-remove text-danger" aria-hidden="true"></span></a>';
		
		DataTable = $('#TBSession').DataTable({
			processing   : true,
			responsive   : false,
			scrollX      : true,
			select       : true,
			deferRender  : true,
			data         : [],
			columns: [
				{ data : null          ,title : "Action" },
				{ data : "username"    ,title : "Useruame"},
				{ data : "firstName"   ,title : "Fullname"},
				{ data : "roleName"    ,title : "Role"},
				{ data : "createDate"  ,title : "Last Request"},
				{ data : "accessIp"    ,title : "Access IP"},
				{ data : "maxSession"  ,title : "Max Session"},
				{ data : "expired"     ,title : "Status"},
				{ data : "sessionId"   ,title : "SessionId"}
			],
			columnDefs: [
				{
					targets   : 0,
					orderable : false,
					className : "text-center",
					render    : function (data, type, row, meta) {
						return (sessionid == row.sessionId ? '' : btnDelete);
					}
				},
				{
					targets   : 2,
					render    : function (data, type, row, meta) {
						return row.firstName + ' ' + row.lastName;
					}
				},
				{
					targets   : 4,
					className : "text-center"
				},
				{
					targets   : 7,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var actice = '<span class="label label-success">Session Active</span>';
						var inActive = '<span class="label label-danger">Session Expired</span>';
						return !data ? actice : inActive;
					}
				},
				{
					targets : 8,
					visible : false
				}
			],
			select: {
		   		style: 'single'
		    },
			colReorder : {
		        fixedColumnsLeft: 1
		    }
		}).on('click', 'a[data-btn-name="btnDelete"]', function (event) {
			
			var data = DataTable.row($(this).parents('tr')).data();
			var url = "api/session/removesession/" + data.username + '/' + data.sessionId;
				
			$.confirm({
			    title: 'Confirm!',
			    type: 'blue',
			    content: 'Simple confirm!',
			    buttons: {
			        confirm: {
			        	btnClass: 'btn-blue',
			        	action: function(){
			        		updateSession(url);
			            }
			        },
			        cancel: function () {}
			    }
			});
			
		});
	}
	
	var updateSession = function(url){
		AjaxManager.GetData(null, url,
			function(response){
				loadData();
				$.confirm({
				    title: 'Meaages Alert!',
				    content: 'Remove Session Complete',
				    type: 'green',
				    typeAnimated: true,
				    buttons: {
				        close: function () {
				        	
				        }
				    }
				});
			},
			function(jqXHR, textStatus, errorThrown){
				$.alert('Error!');
			}
		);
	}
	
	var loadData = function() {
		
		// LOADING
		$('#TBSession_wrapper,#BoxAllUsers,#BoxUsersLogged,#BoxUsersActive,#BoxSessionExpried').waitMe({ 
			effect : 'bouncePulse' ,
			textPos : 'vertical'
		});
		
		// CALL SERVICE 
		AjaxManager.PostData(null ,"api/session/alluserlogged",
			function(response){
				if (response) {
					setTimeout(function(){ 
						
						var usersLogged = response.usersLogged;
						var countUserLogged = response.usersLogged.length;
						var countSessionActive = response.countSessionActive;
						var countSessionExpired = response.countSessionExpired;
						
						DataTable.clear();
						DataTable.rows.add(usersLogged);
						DataTable.draw();
						
						$('#SPCountUserLogged').text(countUserLogged);
						$('#SPCountSessionExpired').text(countSessionExpired);
						$('#SPCountUserActive').text(countSessionActive);
						$('#TBSession_wrapper,#BoxUsersLogged,#BoxUsersActive,#BoxSessionExpried').waitMe("hide");
				
					}, 300);
				}
			},
			function(jqXHR, textStatus, errorThrown){
				$('#TBSession_wrapper,#BoxUsersLogged,#BoxUsersActive,#BoxSessionExpried').waitMe("hide");
			}
		);
		
		// CALL SERVICE 
		AjaxManager.GetData(null ,"api/user/count",
			function(response){
				setTimeout(function(){ 
					$('#BoxAllUsers').waitMe("hide");
					$('#SPCountUsers').text(response);
				}, 300);
			},
			function(jqXHR, textStatus, errorThrown){
				$('#BoxAllUsers').waitMe("hide");
			}
		);
	}
	
	var handleButtonRefresh = function(){
		$('.refresh').on('click', function(event){
			loadData();
		});
	}
	
	return {
		init : function(){
			activeMenu();
			handleDataTable();
			loadData();
			handleButtonRefresh();
		}
	};
	
}();