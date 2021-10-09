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
	

<%@include file="common/footer.jspf" %>