<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
	<div class="wrapper">
	  <div id="formContent">
	    <!-- Tabs Titles -->
	    <h2 class="active"> Sign In </h2>
	

	    <form method="post">
	      <input type="text" id="username" placeholder="username">
	      <input type="password" id="password" placeholder="password">
	      <input type="submit" value="Log In">
	    </form>
	    
	    <h5>New User? <a href="signup">Sign up</a></h5>
	
	    <!-- Remind Passowrd -->
	    <div id="formFooter">
	      <a class="underlineHover" href="#">Forgot Password?</a>
	    </div>
	
	  </div>
	</div>
</body>
</html>