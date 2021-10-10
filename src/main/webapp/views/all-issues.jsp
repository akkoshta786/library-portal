<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>

<title>ALL BOOKS ISSUED</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	%>
	<c:choose>
		<c:when test="${not empty sessionScope['ADMIN']}">
			<c:if test="${not sessionScope['ADMIN']}">
				<c:redirect url="/login"></c:redirect>
			</c:if>	
		</c:when>
		<c:otherwise>
			<c:redirect url="/login"></c:redirect>
		</c:otherwise>
	</c:choose>
	
	
	<table class="table table-striped">
		<thead class="thead-dark">
			<tr>
		    	<th scope="col">Member</th>
		      	<th scope="col">ISBN</th>
		      	<th scope="col">Title</th>
		      	<th scope="col">Issued on</th>
		      	<th scope="col">Issued for</th>
		      	<th scope="col"></th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${allIssuesList}" var="issue">
				<tr>
					<td>${issue.email}</td>
					<td>${issue.isbn}</td>
					<td>${issue.title}</td>
					<td>${issue.dateOfIssue}</td>
					<td>${issue.duration}D</td>
					<td><a><button type="button" class="btn btn-success">Return</button></a></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

<%@include file="common/footer.jspf" %>