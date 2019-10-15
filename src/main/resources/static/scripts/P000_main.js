var MainModule = function(){
	
	var socket = new SockJS('/tirmizee/ws');
	var stompClient = Stomp.over(socket);
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.menu-main').addClass('active');
	}
	
	var handleButton = function(){
		 $('#btnSocket').on('click', function(){
			 stompClient.send("/app/notify", {}, JSON.stringify({'name': 'sssssssssss'}));
		 });
	}
	
	return {
		init : function(){
			
			activeMenu();
			handleButton();
		}
	};
	
}();