$(document).ready(function(){
	$('.datepicker').datepicker({
	    format: 'mm/dd/yyyy',
	    startDate: '-100y'
	});
	
	$(".datepicker").keydown(function (event) {
    	event.preventDefault();
	});
	
	$('#signupFormData').on('submit', function(event){
		alert("ok");
	});
	
	var messageBox = $('.status_message');
	var message = messageBox.text().trim();
	if(message === "User registered successfully. Sign in to access portal" || message === "Password updated successfully. Sign in to continue"){
		messageBox.css('color', 'green');
	}
	
});