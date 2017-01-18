var addAlert = function(message) {
	if($("#banner-msg").length == 0) {
	    $('#banner').append(
	        '<div id="banner-msg" class="alert alert-warning alert-dismissible show" role="alert">'+
			  '<button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
			    '<span aria-hidden="true">&times;</span>'+
			  '</button><span class="banner-text">' +
			  message +
			'</span></div>');
	} else {
		$("#banner-msg , .banner-text").text(message);
	}
};

var setNotifications = function(numberOfNotifications) {
	$(".notification-count").text(numberOfNotifications);
};



var showBanner = function() {
	console.log("checking banner");
	$.ajax({
	     method: "GET",
	     dataType: "text",
	     url: "/backend/banner",
	     
	     success: function(resp){
	       addAlert(resp);
	     },
	     error: function(XMLHttpRequest, textStatus, errorThrown) { 
	        console.log(textStatus);
	        console.log(errorThrown); 
	    }
	});
}

var checkNotifications = function() {
	console.log("checking notifications");
	$.ajax({
	     method: "GET",
	     url: "/backend/notification-count?user=aglassman",
	     
	     success: function(resp){
	       if(resp) {
	       	setNotifications(resp.count);
	       } else {
	       	setNotifications(null);
	       }
	     },
	     error: function(XMLHttpRequest, textStatus, errorThrown) { 
	        console.log(textStatus);
	        console.log(errorThrown); 
	    }

	});
}

var putBanner = function(bannerText) {
	console.log("checking notifications");
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

var postNotification = function(username,text) {
	console.log("checking notifications");
	$.ajax({
	    type: "POST",
	    url: "/backend/notification-count",
	    // The key needs to match your method's input parameter (case-sensitive).
	    data: JSON.stringify({ 
	    	username: username,
	    	text: text}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(data){
	    	console.log(data);
	    },
	    failure: function(errMsg) {
	        console.log(errMsg);
	    }
	});
}



window.onload = function() {
	$(".alert").alert();
	setInterval(showBanner,5000);
	//setInterval(checkNotifications,5000);
}