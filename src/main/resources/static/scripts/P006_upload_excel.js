var UploadExcelModule = function(){
	
	var DataTable = {};
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-file').addClass('active');
	}

	var handleDataTable = function(){
		DataTable = $('#TBExcel').DataTable({
			processing   : true,
			responsive   : false,
			searching    : true,
			scrollX      : true,
			select       : true,
			deferRender  : true,
			data         : [],
			columns: [
				{ data : null             ,title : "Action" },
				{ data : null             ,title : "Order" },
				{ data : "name"           ,title : "App Code"},
				{ data : "createDateText" ,title : "App Name"},
				{ data : "createDateText" ,title : "App Version"},
				{ data : "createDateText" ,title : "App Color"},
				{ data : "createDateText" ,title : "App Price"},
				{ data : "createDateText" ,title : "App Size"},
				{ data : "createDateText" ,title : "App Date"},
				{ data : "createDateText" ,title : "App Country"}
			],
			columnDefs: [
				
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
		});
	}
	
	var handleFormUpload = function(){
	    $('#FormUploadExcel').bootstrapValidator({
	        message  : 'This value is not valid',
	        excluded : [':disabled'],
	        feedbackIcons : {
	           valid      : 'glyphicon glyphicon-ok',
	           invalid    : 'glyphicon glyphicon-remove',
	           validating : 'glyphicon glyphicon-refresh'
	        },
	        fields : {
	        	description : {
	                validators: {
	                    notEmpty: {
	                        message: 'กรุณาเลือก IP'
	                    }
	                }
	            },
	            file : {
	                validators: {
	                    notEmpty: {
	                        message: 'กรุณาเลือกไฟล์ excel'
	                    },
	                    file: {
	                    	extension: 'xls,xlsx',
	                        type: 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
	                        message: 'กรุณาเลือกไฟล์ exel เท่านั้น'
	                  }
	                }
	            }
	        }
	    }).on('success.form.bv', function(e) {
	    	e.preventDefault();
	    	
	    	var FormData = new FormData($('#FormUploadExcel')[0]);
        });
	}
	
	return {
		init : function(){
			activeMenu();
			handleDataTable();
			handleFormUpload();
		}
	};
	
}();