
<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<title>HOME</title>
</head>
<body>
	<% 
		if(session.getAttribute("USERNAME") == null){
			response.sendRedirect("login");
		}
	%>
	<table class="table table-striped">
	<thead class="thead-dark">
		<tr>
	    	<th scope="col">S No.</th>
	      	<th scope="col">Title</th>
	      	<th scope="col">Author</th>
	      	<th scope="col">Publisher</th>
	      	<th scope="col">Language</th>
	      	<th scope="col">Pages</th>
	      	<th scope="col"></th>
	    	</tr>
	</thead>
	<tbody>
		<c:forEach items="${booksList}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td></td>
				<td>${book.publisher }</td>
				<td>${book.language }</td>
				<td>${book.numberOfPages }</td>
				<c:choose>
					<c:when test="${book.status == 0}">
						<td><a href="#"><button type="button" class="btn btn-success">Issue</button></a></td>
					</c:when>
					<c:otherwise>
						<td><a><button type="button" class="btn btn-secondary" disabled>Issue</button></a></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</tbody>
	</table>
</body>
</html>