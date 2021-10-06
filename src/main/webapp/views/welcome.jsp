
<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<title>Insert title here</title>
</head>
<body>
	<% 
		if(session.getAttribute("USERNAME") == null){
			response.sendRedirect("login");
		}
	%>
</body>
</html>