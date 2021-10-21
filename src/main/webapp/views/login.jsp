<%@include file="common/header.jspf" %>
<title>LOGIN</title>
<link rel="stylesheet" href="css/main.css">
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<c:if test = "${not empty sessionScope['USERNAME']}">
		<c:redirect url="/welcome"></c:redirect>
	</c:if>
		
	<div class="wrapper container">
		<div class="status_message" id="status_login">
			<p>${message}</p>
		</div>
		<div id="formContent">
	 		<h3 class="active"> Sign In </h3>
			<form action="login" method="post">
				<fieldset class="form-group">
					<legend></legend>
					<input type="text" name="email" class="form-control-plaintext" placeholder="email" />
				</fieldset>
				<fieldset class="form-group">
					<legend></legend>
					<input type="password" name="password" class="form-control-plaintext" placeholder="password" required="required"/>
				</fieldset>
				<button type="submit" class="btn btn-success">Log In</button>
			</form>
		    <br>
		    <p>New User? <a href="signup">Sign up</a></p>
		
		    
		    <div id="formFooter">
		      <a class="underlineHover" href="forgot-password">Forgot Password?</a>
		    </div>
		</div>
	</div>
<%@include file="common/footer.jspf" %>
<script src="webjars/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="js/common.js"></script>