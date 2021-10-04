<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SIGNUP</title>
<link rel="stylesheet" href="../css/main.css">
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form:form action="signup" method="post" modelAttribute="user">
			<fieldset class="form-group">
				<form:input path="email" />
			</fieldset>
			<fieldset class="form-group">
				<form:password path="password"/>
			</fieldset>
			
			<button type="submit" class="btn btn-success">Register</button>
		</form:form>

	</div>
	
		<h5> Already a user? <a href="login">Log In</a> </h5>
	
	<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
</body>
</html> 
