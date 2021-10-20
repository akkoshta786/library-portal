$(document).ready(function(){
	
	$('#delete-book').on('click', function(){
		$(this).find('.fa-check,.fa-trash').toggleClass('fa-check').toggleClass('fa-trash');
		
		var buttons = $('.delete-button');
		buttons.toggleClass('btn-danger').toggleClass('btn-secondary');
		buttons.prop('disabled', function(i, v) { return !v; });
		
		buttons.on('click', function(){
			var deleteBookIsbn = this.id;
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
							alert("Book(s) are currently issued. Re-acquire all books before deleting.")
							break;
						case 1:
							alert("Book deleted");
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
		});
		
		
		
	});
	
});