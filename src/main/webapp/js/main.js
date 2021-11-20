$(document).ready(function(){
	var buttons = $('.delete-button');
	var deleteBookButton = $('#delete-book');
	var deleteBookIsbn;
	
	if(localStorage.getItem("bookAdded")){
		$.toast({
		    heading: 'Success',
		    text: 'Book added to library',
		    showHideTransition: 'slide',
		    icon: 'success'
		})
	}
	
	if(localStorage.getItem('maintainState')){
		$.toast({
		    heading: 'Message',
		    text: 'Book deleted successfully',
		    showHideTransition: 'slide',
		    icon: 'success'
		})
		toggleButtonState();
	}
	
	localStorage.clear();
	
	/*
		JAVASCRIPT FOR ADD BOOK(ADMIN)
	*/
	
	$( "#formdata" ).submit(function( event ) {
		
		var $items = $('#isbn, #title, #author, #publisher, #language, #numberOfPages, #copies');
		var obj = {}
		
		$items.each(function() {
	    obj[this.id] = $(this).val();
		})

		var json= JSON.stringify( obj);
		console.log(json);
		
		
		/*
			AJAX call to send POST request to add book
			Request sent to controller
		 */
		$.ajax({
			
			type: "POST",
			contentType: "application/json",
			url: "postBook",
			data: json,
			dataType: 'json',
			success: function(response){
				switch(response){
					case 0:
						$.toast({
						    heading: 'Failure',
						    text: 'Book with given ISBN already exist',
						    showHideTransition: 'slide',
						    icon: 'error'
						})
						break;
					case 1:
						localStorage.setItem("bookAdded", true);
						location.reload();
						break;
					default:
						$.toast({
						    heading: 'Failure',
						    text: 'Failed to add book',
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
	
		// Empty form to make it available to add next book
		$(':input','#formdata')
		  .not(':button, :submit, :reset, :hidden')
		  .val('')
		  .removeAttr('checked')
		  .removeAttr('selected');
		
		$('.form-error').empty();	
		$('#isbn').css({ 'border' : ''});

		event.preventDefault();
		
	});	
	
	
	/*
		JAVASCRIPT FOR DELETE BOOK(ADMIN)
	  
	*/
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
						    text: 'Minimum 1 copy of this book is currently issued<br>Acquire all copies before deleting',
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
	
	
	/*
		JAVASCRIPT FOR ISSUEING THE BOOK 
	*/
	var issueButtonId;
	$('.issue-button').on('click', function(){
		issueButtonId = this.id;
		
	});
	

	$('#issueModal').on('show.bs.modal', function () {
		$('#issue-isbn').val(issueButtonId);
	});
	
	$( "#issueFormData" ).submit(function(event) {
		var email = $('#email').val().toLowerCase();
		var duration = $('#duration').val();
		
		var obj = {'email': email,
					'isbn': issueButtonId,
					'duration': duration
		};
		
		var json = JSON.stringify(obj);
		
		
		// POST REQUEST TO ISSUE BOOK
		$.ajax({
		
			type: "POST",
			url: "issueBook",
			contentType: "application/json",
			data: json,
			dataType: 'json',
			success: function(response){
				switch(response){
					case 1:
						$.toast({
						    heading: 'Success',
						    text: issueButtonId+" issued to "+email,
						    showHideTransition: 'slide',
						    icon: 'success'
						})
						break;
					case 2:
						$.toast({
						    heading: 'Info',
						    text: 'No more copies of this book available, try later',
						    showHideTransition: 'slide',
						    icon: 'info'
						})
						break;
					case 3:
						$.toast({
						    heading: 'Warning',
						    text: 'User has not returned this book yet',
						    showHideTransition: 'slide',
						    icon: 'warning'
						})
						break;
					case 4:
						$.toast({
						    heading: 'Failure',
						    text: email+" is not a member!",
						    showHideTransition: 'slide',
						    icon: 'error'
						})
						break;
					default:
						$.toast({
						    heading: 'Failure',
						    text: 'Unauthorized access!',
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
		$('#email').val('');
		$('#duration').val(7);
		event.preventDefault();
		
	});
	
	$('#issue-model-close-button').on('click', function(){
		location.reload();
	})
	
	
	// Continiously alerts the user about validity of ISBN entered
	// Either when admin enters character or when it leaves the field
	$('#isbn').bind("keyup focusout", function(){
		var isbn = $('#isbn').val();
		if(validateISBN(isbn) != 1){
			$('#isbn-error').html("Invalid ISBN &#10060;").css("color", "red");
			$(this).css("border", "2px solid red");
			$('#submitButton').attr('disabled', 'disabled');
		}else{
			$('#isbn-error').html("Looks good &#9989;").css("color", "green");
			$(this).css("border", "2px solid green");
			$('#submitButton').removeAttr('disabled');
		}
		
	});
	
	
	// Regular expression for valid ISBN
	var regex = /^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$/;

	// This function checks validity to given ISBN
	function validateISBN(subject){
		if (regex.test(subject)) {
		    // Remove non ISBN digits, then split into an array
		    var chars = subject.replace(/[- ]|^ISBN(?:-1[03])?:?/g, "").split("");
		    // Remove the final ISBN digit from `chars`, and assign it to `last`
		    var last = chars.pop();
		    var sum = 0;
		    var check, i;
	
		    if(chars.length == 9) {
		    	// Compute the ISBN-10 check digit
		        chars.reverse();
		        for (i = 0; i < chars.length; i++) {
		            sum += (i + 2) * parseInt(chars[i], 10);
		        }
		        check = 11 - (sum % 11);
		        if (check == 10) {
		            check = "X";
		        }else if (check == 11) {
		            check = "0";
		        }
			}else{
		        // Compute the ISBN-13 check digit
		        for (i = 0; i < chars.length; i++) {
		            sum += (i % 2 * 2 + 1) * parseInt(chars[i], 10);
		        }
		        check = 10 - (sum % 10);
		        if (check == 10) {
		            check = "0";
		        }
		    }
	
		    if (check == last) {
		        return 1;
		    }else{
		       return 0;
		    }
		}else{
	    	return 0;
		}
	}
	
	function toggleButtonState(){
		deleteBookButton.find('.fa-check,.fa-trash').toggleClass('fa-check').toggleClass('fa-trash');
		buttons.toggleClass('btn-danger').toggleClass('btn-secondary');
		buttons.prop('disabled', function(i, v) { return !v; });
	}

});


