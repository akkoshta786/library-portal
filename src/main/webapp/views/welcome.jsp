
<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<title>HOME</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	%>
	<c:choose>
		<c:when test = "${not empty sessionScope['ADMIN']}">
			<c:if test="${sessionScope['ADMIN']}">
				<c:import url="addBook.jspf" />
			</c:if>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login"></c:redirect>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test = "${not empty sessionScope['ADMIN']}">
			<c:if test="${sessionScope['ADMIN']}">
				<c:import url="issue-book.jspf" />
				<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteBookConfirmation" aria-hidden="true" id="delete-book-confirm-modal">
				  <div class="modal-dialog modal-sm">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h6 class="modal-title">Remove this book permanently from library?</h6>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-success" id="delete-book-yes">Yes</button>
				        <button type="button" class="btn btn-warning" id="delete-book-no">No</button>
				      </div>
				    </div>
				  </div>
				</div>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login"></c:redirect>
		</c:otherwise>
	</c:choose>

	

	<table class="table table-striped" aria-describedby="allBooksTable">
		<thead class="thead-dark">
			<tr>
		    	<th scope="col">ISBN</th>
		      	<th scope="col">Title</th>
		      	<th scope="col">Author</th>
		      	<th scope="col">Publisher</th>
		      	<th scope="col">Language</th>
		      	<th scope="col">Pages</th>
		      	<th scope="col">Copies</th>
		      	<c:if test="${sessionScope['ADMIN']}">
						<th scope="col"></th>
				</c:if>
				<th scope="col"></th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${booksList}" var="book">
				<tr>
					<td>${book.isbn}</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.publisher }</td>
					<td>${book.language }</td>
					<td>${book.numberOfPages }</td>
					<td>${book.copies }</td>
					
					<c:if test="${sessionScope['ADMIN']}">
						<c:choose>
							<c:when test="${book.copies > 0}">
								<td><button type="button" class="btn btn-success issue-button" data-toggle='modal' data-target='#issueModal' id="${book.isbn}">Issue</button></td>
								
							</c:when>
							<c:otherwise>
								<td><a><button type="button" class="btn btn-secondary issue-button-disabled" disabled>Issue</button></a></td>
							</c:otherwise>
						</c:choose>
						<td><button type="button" class="btn btn-secondary delete-button" id="${book.isbn}" disabled><i class="fa fa-times"></i></button></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
<%@include file="common/footer.jspf" %>
<script src="js/main.js"></script>
<script src="js/admin.js"></script>