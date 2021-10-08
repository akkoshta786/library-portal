$(document).ready(function(){
	
	$('#exampleModal').on('show.bs.modal', function () {

	  $( "#formdata" ).submit(function( event ) {
		
		var $items = $('#isbn, #title, #author, #publisher, #language, #numberOfPages, #copies');
		var obj = {}
		
		$items.each(function() {
	    obj[this.id] = $(this).val();
		})

		var json= JSON.stringify( obj);
		console.log(json);
		
		
		
		$.ajax({
			
			type: "POST",
			contentType: "application/json",
			url: "postBook",
			data: json,
			dataType: 'json',
			success: function(response){
				alert("Book added successfully");
			},
			error: function(response){
				alert("Book with given ISBN already available");
			}
		});
	
		$(':input','#formdata')
		  .not(':button, :submit, :reset, :hidden')
		  .val('')
		  .removeAttr('checked')
		  .removeAttr('selected');
		
		$('.form-error').empty();	
		$('#isbn').css({ 'border' : ''});

		event.preventDefault();
		
		});	

	});
	
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
	
	
	var regex = /^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$/;

	function validateISBN(subject){
		if (regex.test(subject)) {
	    // Remove non ISBN digits, then split into an array
	    var chars = subject.replace(/[- ]|^ISBN(?:-1[03])?:?/g, "").split("");
	    // Remove the final ISBN digit from `chars`, and assign it to `last`
	    var last = chars.pop();
	    var sum = 0;
	    var check, i;
	
	    if (chars.length == 9) {
	        // Compute the ISBN-10 check digit
	        chars.reverse();
	        for (i = 0; i < chars.length; i++) {
	            sum += (i + 2) * parseInt(chars[i], 10);
	        }
	        check = 11 - (sum % 11);
	        if (check == 10) {
	            check = "X";
	        } else if (check == 11) {
	            check = "0";
	        }
	    } else {
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
	    } else {
	       return 0;
	    }
	} else {
	    return 0;
	}
	}
	

});


