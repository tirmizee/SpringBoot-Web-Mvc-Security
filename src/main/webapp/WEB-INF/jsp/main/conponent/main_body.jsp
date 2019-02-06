<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
var subscribe = function() {  
  var eventSource = new EventSource('api/rest/new_notification');

  eventSource.onmessage = function(e) {    
    var notification = JSON.parse(e.data);               
    document.getElementById("notificationDiv").innerHTML += notification.text + " at " + new Date(notification.time) + "<br/>";
  };
}
window.onload = subscribe;
window.onbeforeunload = function() {
  eventSource.close();
 }
 </script>
 <div id="notificationDiv"></div>