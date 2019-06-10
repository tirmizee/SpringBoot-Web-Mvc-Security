var ViewLogModule = function(){
	
	var DataTable = {};
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.menu-log').addClass('active');
	}
	
	var handleDataTable = function() {
		
		var btnView = '<button data-btn-name="btnView" type="button" class="btn btn-primary" data-toggle="tooltip" title="View Log!"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></button>';
		var btnLoad = '<button data-btn-name="btnLoad" type="button" class="btn btn-warning" data-toggle="tooltip" title="Download Log!"><span class="fa fa-download" aria-hidden="true"></span></button>';
		
		DataTable = $('#TBLog').DataTable({
			processing   : true,
			responsive   : false,
			searching    : true,
			scrollX      : true,
			select       : true,
			deferRender  : true,
			ajax: 'api/log/all',
			columns: [
				{ data: null              ,title : "Action" },
				{ data : null             ,title : "Order" },
				{ data : "name"           ,title : "File Name"},
				{ data : "createDateText" ,title : "Create"}
			],
			columnDefs: [
				{
					targets   : 0,
					width     : "10%",
					orderable : false,
					className : "text-center",
					render    : function (data, type, row, meta) {
						return btnView + ' ' + btnLoad;
					}
				},
				{
					targets   : 1,
					width     : "10%",
					orderable : false,
					render    : function (data, type, row, meta) {
						return meta.settings._iDisplayStart + meta.row + 1;
					}
				}
			],
			select: {
		   		style: 'single'
		    }
		}).on('click', 'button[data-btn-name="btnView"]', function (event) {
			
			var data = DataTable.row($(this).parents('tr')).data();
			$.confirm({
				columnClass: 'col-md-12',
				typeAnimated: true,
			    title: 'Meaages Alert!',
			    content: 'url:api/log/' + data.createDateText,
			    contentLoaded: function(data, status, xhr){
			        this.setContentAppend('<br>Status: ' + status);
			    },
			    buttons: {
			        close: function () {}
			    }
			});
		});
	}
	
	return {
		init : function(){
			activeMenu();
			handleDataTable();
		}
	};
	
}();