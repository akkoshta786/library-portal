<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>

<title>MY ISSUES</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	%>
	<c:choose>
		<c:when test="${not empty sessionScope['ADMIN']}">
			<c:if test="${sessionScope['ADMIN']}">
				<c:redirect url="/login"></c:redirect>
			</c:if>	
		</c:when>
		<c:otherwise>
			<c:redirect url="/login"></c:redirect>
		</c:otherwise>
	</c:choose>
	
	<table class="table table-striped" aria-describedby="myIssuesTable">
		<thead class="thead-dark">
			<tr>
		      	<th scope="col">ISBN</th>
		      	<th scope="col">Title</th>
		      	<th scope="col">Issued on</th>
		      	<th scope="col">Return by</th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${myIssues}" var="issue">
				<tr>
					<td>${issue.isbn}</td>
					<td>${issue.title}</td>
					<td><fmt:formatDate pattern = "MMM dd, yyyy" value = "${issue.dateOfIssue}" /></td>
					<td><fmt:formatDate pattern = "MMM dd, yyyy" value = "${issue.returnDate}" /></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

<%@include file="common/footer.jspf" %>