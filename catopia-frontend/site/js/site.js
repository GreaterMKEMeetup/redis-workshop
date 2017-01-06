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

var showBanner = function() {
	console.log("run");
	$.ajax({
	     method: "GET",
	     dataType: "text",
	     url: "http://localhost:8000/backend/banner",
	     
	     success: function(resp){
	       addAlert(resp);
	     }
	});
}

window.onload = function() {
	$(".alert").alert();
	setInterval(showBanner,1000);
}