var PdfModule = function(){
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-report').addClass('active');
	}
	
	return {
		init : function(){
			activeMenu();
		}
	};
	
}();