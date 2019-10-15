var NotifyModule = function(){
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.menu-notify').addClass('active');
	}

	return {
		init : function(){
			activeMenu();
		}
	};
	
}();