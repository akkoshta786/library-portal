<%@include file="common/header.jspf" %>
<title>FORGOT PASSWORD</title>
<link rel="stylesheet" href="css/main.css">
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
<link href="webjars/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>

<div class="wrapper">
	<div class="status_message" id="status_forgot_password">
			<p>${message}</p>
	</div>
	<div id="formContent">
		<h3 class="active"> Forgot Password </h3>
		<form:form action="forgot-password" method="post" modelAttribute="user">
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
				<form:password path="password" placeholder="New Password" required="required"/>
			</fieldset>
			<button type="submit" class="btn btn-primary">Update Password</button>
		</form:form>
		<br>
		<p> Back to <a href="login">Log In</a> or <a href="signup">Sign Up</a></p>
	</div>
</div>

<%@include file="common/footer.jspf" %>
<script src="webjars/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="js/common.js"></script>