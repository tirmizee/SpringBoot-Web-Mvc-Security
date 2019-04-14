var FileModule = function(){
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-file').addClass('active');
	}

	return {
		init : function(){
			activeMenu();
		}
	};
	
}();