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
				{ data : null             ,title : "Order" },
				{ data : "isValid"        ,title : "Validate" },
				{ data : "appCode"        ,title : "App Code"},
				{ data : "appName"        ,title : "App Name"},
				{ data : "appVersion"     ,title : "App Version"},
				{ data : "price"          ,title : "App Price"},
				{ data : "size"           ,title : "App Size"},
				{ data : "date"           ,title : "App Date"},
				{ data : "country"        ,title : "App Country"}
			],
			columnDefs: [
				{
					targets : 0,
					checkboxes : {
					   'selectRow': true
					}
		        },
				{
					targets   : 1,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var valid   = '<span class="label label-success">Valid</span>';
						var inValid = '<span class="label label-danger">Invalid</span>';
						return row.price > 350000 ? valid : inValid;
					}
				},
				{
					targets   : 4,
					className : "text-center",
					render    : function (data, type, row, meta) {
						return '<b>' + data + '</b>';
					}
				},
				{
					targets   : 5,
					className : "text-center",
					render    : function (data, type, row, meta) {
						var green = '<b class="text-success">' + data + '</b>';
						var red   = '<b style="color:#b30303">' + data + '</b>';
						return data > 350000 ? green : red;
					}
				},
				{
					targets   : 6,
					className : "text-center",
					render    : function (data, type, row, meta) {
						return '<b>' + data + '</b>';
					}
				}
				
			],
			fnRowCallback : function( Row, Data) {
				if(!(Data.price > 500000)){
					$('td', Row)
					 	.css('background-color', '#ff6868')
					 	.find('input[type=checkbox]')
					 	.attr("disabled", true);
				}
            },
			select: {
		   		style: 'multi'
		    },
	        lengthMenu : [[5,10, 25, 50, 100, -1], [5,10, 25, 50, 100, "All"]]
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
	    	
	    	var formData = new FormData( $('#FormUploadExcel')[0]);
	    	
	    	AjaxManager.UploadData(formData, 'api/file/excel/pre', 
	        	function(response){
	    			
	    			var sumPrice = 0;
	    			var sumValid = 0
	    			var sumInvalid = 0;
	    			
	    			response.forEach(function (object) {
	    				sumPrice += object.price;
	    				sumValid += (object.price > 350000 ? 1 : 0);
	    				sumInvalid += (object.price > 350000 ? 0 : 1);
	    			});
	    			
	    			var sumValidPercen = (sumValid/response.length) * 100;
	    			var sumInvalidPercen = (sumInvalid/response.length) * 100;
	    			
	    			var numberStep = $.animateNumber.numberStepFactories.separator(',');
	    			$('#TotalRecord').animateNumber({ number: response.length, numberStep: numberStep});
	    			$('#TotalAmount').animateNumber({ number: sumPrice, numberStep: numberStep});
	    			$('#RecordValid').animateNumber({ number: sumValid, numberStep: numberStep});
	    			$('#RecordInvalid').animateNumber({ number: sumInvalid, numberStep: numberStep});
	    			$('#PercenValid').animateNumber({ number: sumValidPercen, numberStep: numberStep});
	    			$('#PercenInvalid').animateNumber({ number: sumInvalidPercen, numberStep: numberStep});
	    			
	    			DataTable.clear();
					DataTable.rows.add(response);
					DataTable.draw();
					
					
	    		},
				function (jqXHR, textStatus, errorThrown) {
				
	    		}
			); 
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