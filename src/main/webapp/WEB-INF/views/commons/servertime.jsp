<ol class="breadcrumb">
    <li class="font-kanit-regular">
   		<i class="fa fa-clock-o"></i> 
   		<label id="server_time"></label>
    </li>
</ol>
<script>
$(document).ready(function() {
	
	var socket = new SockJS('/tirmizee/ws');
	var stompClient = Stomp.over(socket);
    
	stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/greetings', function (message) {
        	var respose = JSON.parse(message.body).content;
        	$("#server_time").text(respose);
        });  
        
        stompClient.subscribe('/topic/notify', function (message) {
        	var respose = JSON.parse(message.body).content;
        	$("#btnSocket").text(respose);
        }); 
    });
	
});
</script>