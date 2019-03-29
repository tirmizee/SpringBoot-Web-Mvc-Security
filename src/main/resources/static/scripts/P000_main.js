var MainModule = function(){
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.menu-main').addClass('active');
	}
	
	return {
		init : function(){
			activeMenu();
		}
	};
	
}();