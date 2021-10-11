$(document).ready(function(){
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
						alert("Book returned successfully.");
						location.reload();
						break;
					default:
						alert("Unsuccessful attempt to return book!");
				}
			},
			error: function(response){
				alert("Opps! Some error occured");
			}
		});
	 }
	});
	
})
