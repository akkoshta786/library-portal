<%@include file="common/header.jspf" %>
<title>SIGNUP</title>
<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/alert.css">
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="wrapper">
	<div class="status_message" id="status_signup">
			<p>${message}</p>
	</div>
	<div id="formContent">
		<h2 class="active"> Sign Up </h2>
		<form:form action="signup" method="post" modelAttribute="user">
			<fieldset class="form-group">
				<form:input path="email" placeholder="email" required="required"/>
			</fieldset>
			<fieldset class="form-group">
				<form:password path="password" placeholder="password" required="required"/>
			</fieldset>
			
			<button type="submit" class="btn btn-primary">Register</button>
		</form:form>
		<br>
		<h5> Already a user? <a href="/">Log In</a> </h5>
	</div>
</div>

<%@include file="common/footer.jspf" %>
