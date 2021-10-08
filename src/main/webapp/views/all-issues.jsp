<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>

<title>ALL BOOKS ISSUED</title>
</head>
<body>
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
	

<%@include file="common/footer.jspf" %>