
<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<link href="../css/welcome.css" rel="stylesheet">
<title>HOME</title>
</head>
<body>
	
	<% 
		if(session.getAttribute("USERNAME") == null || session.getAttribute("ADMIN") == null){
			response.sendRedirect("/login");
		}
	
	%>
	<% if(session.getAttribute("ADMIN") != null){
			if((boolean) session.getAttribute("ADMIN"))%>
			<%@include file="addBook.jspf" %>
	<%} %>
		

	
	<table class="table table-striped">
	<thead class="thead-dark">
		<tr>
	    	<th scope="col">ISBN</th>
	      	<th scope="col">Title</th>
	      	<th scope="col">Author</th>
	      	<th scope="col">Publisher</th>
	      	<th scope="col">Language</th>
	      	<th scope="col">Pages</th>
	      	<c:choose>
				<c:when test="${sessionScope['ADMIN'] == false }">
					<th scope="col"></th>
				</c:when>
				<c:otherwise>
					<th scope="col">Copies</th>
				</c:otherwise>
				</c:choose>
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
				<c:choose>
					<c:when test="${sessionScope['ADMIN'] == false }">
						<c:choose>
							<c:when test="${book.copies > 0}">
								<td><a href="#"><button type="button" class="btn btn-success">Issue</button></a></td>
							</c:when>
							<c:otherwise>
								<td><a><button type="button" class="btn btn-secondary" disabled>Issue</button></a></td>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<td>${book.copies }</td>
					</c:otherwise>
				</c:choose>
				
			</tr>
		</c:forEach>
		
	</tbody>
	</table>
<%@include file="common/footer.jspf" %>