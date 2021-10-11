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
	 		<h2 class="active"> Sign In </h2>
			<form action="login" method="post">
				<fieldset class="form-group">
					<input type="text" name="email" class="form-control-plaintext" placeholder="email" />
				</fieldset>
				<fieldset class="form-group">
					<input type="password" name="password" class="form-control-plaintext" placeholder="password" required="required"/>
				</fieldset>
				<button type="submit" class="btn btn-success">Log In</button>
			</form>
		    <br>
		    <h5>New User? <a href="signup">Sign up</a></h5>
		
		    
		    <div id="formFooter">
		      <a class="underlineHover" href="#">Forgot Password?</a>
		    </div>
		</div>
	</div>
</body>
</html>