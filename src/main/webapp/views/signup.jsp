<%@include file="common/header.jspf" %>
<title>SIGNUP</title>
<link rel="stylesheet" href="css/main.css">
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
<link href="webjars/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>

<div class="wrapper">
	<div class="status_message" id="status_signup">
			<p>${message}</p>
	</div>
	<div id="formContent">
		<h3 class="active"> Sign Up </h3>
		<form:form action="signup" method="post" modelAttribute="user">
			<fieldset class="form-group">
				<legend></legend>
				<form:input path="email" placeholder="Email" required="required"/>
			</fieldset>
			<fieldset class="form-group">
				<legend></legend>
				<form:input path="dob" class="datepicker" placeholder="Date of Birth" data-date-format="mm/dd/yyyy"/>
			</fieldset>
			<fieldset class="form-group">
				<legend></legend>
				<form:password path="password" placeholder="Password" required="required"/>
			</fieldset>
			<button type="submit" class="btn btn-primary">Register</button>
		</form:form>
		<br>
		<p> Already a user? <a href="login">Log In</a> </p>
	</div>
</div>

<%@include file="common/footer.jspf" %>
<script src="webjars/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="js/common.js"></script>