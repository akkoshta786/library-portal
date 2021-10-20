$(document).ready(function(){
	var buttons = $('.delete-button');
	var deleteBookButton = $('#delete-book');
	var deleteBookIsbn;
	
	if(localStorage.getItem('maintainState')){
		$.toast({
		    heading: 'Message',
		    text: 'Book deleted successfully',
		    showHideTransition: 'slide',
		    icon: 'success'
		})
		toggleButtonState();
		localStorage.clear();
	}
	
	deleteBookButton.on('click', function(){
		toggleButtonState();
	});
	
	
	var modalConfirm = function(callback){ 
	buttons.on("click", function(){
		deleteBookIsbn = this.id;
		$("#delete-book-confirm-modal").modal('show');
	});
	
	$("#delete-book-yes").on("click", function(){
		callback(true);
	    $("#delete-book-confirm-modal").modal('hide');
	  });
	  
	$("#delete-book-no").on("click", function(){
		callback(false);
	    $("#delete-book-confirm-modal").modal('hide');
	  });
	};
	
	modalConfirm(function(confirm){
	  if(confirm){
	  	
		var obj = {'deleteBookIsbn': deleteBookIsbn}
		var json = JSON.stringify(obj);
		
		$.ajax({
		
			type: "POST",
			url: "deleteBook",
			contentType: "application/json",
			data: json,
			dataType: 'json',
			success: function(response){
				switch(response){
					case 0:
						$.toast({
						    heading: 'Warning',
						    text: 'Book(s) are currently issued. <br>Re-acquire all books before deleting.',
						    showHideTransition: 'slide',
						    icon: 'warning'
						})
						break;
					case 1:
						localStorage.setItem('maintainState', true);
						location.reload();
						break;
					default:
						pass;
				}
			},
			error: function(error){
				alert(error);
			}
		});
	 }
	});
		
	function toggleButtonState(){
		deleteBookButton.find('.fa-check,.fa-trash').toggleClass('fa-check').toggleClass('fa-trash');
		buttons.toggleClass('btn-danger').toggleClass('btn-secondary');
		buttons.prop('disabled', function(i, v) { return !v; });
	}
	
});