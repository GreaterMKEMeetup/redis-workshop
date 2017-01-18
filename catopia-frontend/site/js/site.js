window.onload = function() {
	var loggedInUser = 'aglassman';

	$(".alert").alert();
	
	$(".notifications").click(function(){
		getNotifications(loggedInUser,function(notifications){
			setNotificationsList(notifications);
			$('#notifications-modal').modal('show'); 
		});
	});

	//Initial Run
	getNotificationCount(loggedInUser,setNotificationCounts);
	getBanner(addAlert);

	//Run every 5 seconds
	setInterval(getBanner.bind(null,addAlert),5000);
	setInterval(getNotificationCount.bind(null,loggedInUser,setNotificationCounts),5000);
}

/**
 * UI Modification functions
**/
var addAlert = function(bannerText) {

	if(!bannerText || bannerText.length == 0) {
		$('#banner').empty();
	} else if($("#banner-msg").length == 0) {
	    $('#banner').append(
	        '<div id="banner-msg" class="alert alert-warning alert-dismissible show" role="alert">'+
			  '<button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
			    '<span aria-hidden="true">&times;</span>'+
			  '</button><span class="banner-text">' +
			  bannerText +
			'</span></div>');
	} else {
		$("#banner-msg , .banner-text").text(bannerText);
	}
};

var setNotificationCounts = function(numberOfNotifications) {
	if(numberOfNotifications < 1) {
		$(".notification-count").text();
	} else {
		$(".notification-count").text(numberOfNotifications);
	}
};

var setNotificationsList = function(notifications) {
	$(".notifications-list").empty();
	notifications.forEach(function(notification){
		$(".notifications-list").append("<li>").append(notification);
	});
};

/**
 * Banner endpoint functions
**/
var getBanner = function(bannerCallback) {
	console.log("getting banner");
	$.get('/backend/banner', 
	    function(bannerText){
	         bannerCallback(bannerText);
	}).fail(function(){
	      console.log("could not retrieve banner");
	});

}

var putBanner = function(bannerText) {
	console.log("putting banner");
	$.ajax({
	    type: "PUT",
	    url: "/backend/banner",
	    // The key needs to match your method's input parameter (case-sensitive).
	    data: bannerText,
	    contentType: "text/plain; charset=utf-8",
	    dataType: "text",
	    success: function(data){
	    	console.log(data);
	    },
	    failure: function(errMsg) {
	        console.log(errMsg);
	    }
	});
}

/**
 * notification endpoint functions
**/
var getNotificationCount = function(userId,notificationCountCallback) {
	console.log("getting notification count for user: " + userId);
	
	$.get('/backend/notifications', { countOnly: true, userId : userId}, 
	    function(notificationCount){
	         notificationCountCallback(notificationCount);
	}).fail(function(){
	      console.log("could not retrieve notifications for: " + userId);
	});
}

var getNotifications = function(userId, notificationsCallback) {
	console.log("getting notifications for user: " + userId);

	$.get('/backend/notifications', { userId: userId}, 
	    function(notifications){
	         notificationsCallback(notifications);
	}).fail(function(){
	      console.log("error");
	});
}

var postNotification = function(userId, text) {
	console.log("posting notification to user: " + userId + " message: " + text);

	$.post('/backend/notifications', { userId: userId, text : text}, 
	    function(returnedData){
	         console.log(returnedData);
	}).fail(function(){
	      console.log("error");
	});
}

var deleteNotification = function(userId,index) {
	console.log("deleting message: " + index + " for user: " + userId);
	$.ajax({
	    type: "DELETE",
	    url: "/backend/notifications" + '?' + $.param({"userId": userId, "index" : index}),
	    success: function(data){
	    	console.log(data);
	    },
	    failure: function(errMsg) {
	        console.log(errMsg);
	    }
	});
}



