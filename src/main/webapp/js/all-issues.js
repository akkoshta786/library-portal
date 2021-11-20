$(document).ready(function(){
	if(localStorage.getItem("bookReturned")){
		$.toast({
		    heading: 'Success',
		    text: 'Book returned successfully',
		    showHideTransition: 'slide',
		    icon: 'success'
		})
		localStorage.clear();
	}
	var issueId;
	$(".return-button").click(function(){
		issueId = this.id;	
	});
		
	var modalConfirm = function(callback){
	  
	$(".return-button").on("click", function(){
		$("#confirm-modal").modal('show');
	});
	
	$("#modal-btn-yes").on("click", function(){
		callback(true);
	    $("#confirm-modal").modal('hide');
	  });
	  
	$("#modal-btn-no").on("click", function(){
		callback(false);
	    $("#confirm-modal").modal('hide');
	  });
	};
	
	modalConfirm(function(confirm){
	  if(confirm){
	  	var obj = {'issueId': issueId}
		var json= JSON.stringify(obj);
		
		$.ajax({
			
			type: "POST",
			contentType: "application/json",
			url: "returnBook",
			data: json,
			dataType: 'json',
			success: function(response){
				switch(response){
					case 1:
						localStorage.setItem("bookReturned", true);
						location.reload();
						break;
					default:
						$.toast({
						    heading: 'Failure',
						    text: 'Failed to return book!',
						    showHideTransition: 'slide',
						    icon: 'error'
						})
				}
			},
			error: function(response){
				$.toast({
				    heading: 'Error',
				    text: 'Some unexpected error has occured<br>Please contact administrator',
				    showHideTransition: 'slide',
				    icon: 'error'
				})
			}
		});
	 }
	});
	
})
