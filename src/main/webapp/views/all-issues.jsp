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
	
	
	<table class="table table-striped" aria-describedby="allIssuesTable">
		<thead class="thead-dark">
			<tr>
		    	<th scope="col">Member</th>
		      	<th scope="col">ISBN</th>
		      	<th scope="col">Title</th>
		      	<th scope="col">Issued on</th>
		      	<th scope="col">Issued until</th>
		      	<th scope="col"></th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${allIssuesList}" var="issue">
				<tr>
					<td>${issue.email}</td>
					<td>${issue.isbn}</td>
					<td>${issue.title}</td>
					<td><fmt:formatDate pattern = "MMM dd, yyyy" value = "${issue.dateOfIssue}" /></td>
					<td><fmt:formatDate pattern = "MMM dd, yyyy" value = "${issue.returnDate}" /></td>
					<td><button type="button" class="btn btn-success return-button" id="${issue.issueId }">Return</button></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="confirm-modal">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">Are you sure member returned this book?</h4>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-success" id="modal-btn-yes">Yes, book returned</button>
	        <button type="button" class="btn btn-warning" id="modal-btn-no">Not yet</button>
	      </div>
	    </div>
	  </div>
	</div>

<%@include file="common/footer.jspf" %>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/all-issues.js"></script>