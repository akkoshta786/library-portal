
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
			</c:if>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login"></c:redirect>
		</c:otherwise>
	</c:choose>

	

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
					<c:when test="${sessionScope['ADMIN']}">
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
						<c:when test="${sessionScope['ADMIN']}">
							<c:choose>
								<c:when test="${book.copies > 0}">
									<td><button type="button" class="btn btn-success issue-button" data-toggle='modal' data-target='#issueModal' id="${book.isbn}">Issue</button></td>
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