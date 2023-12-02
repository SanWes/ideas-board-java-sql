<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to The Ideas Board!</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
<link rel="stylesheet" href="/MainStyles.css">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
   	   	<h1 class="hero-title">Welcome to Ideas Catalog</h1>
   	   	<header class="hero">Discover, share, and explore new ideas!</header>
       	
    
    <div class="form-container">
    
    	<h4 class ="section-title"> First Time User? Register!  </h4>
    	
        <form:form action="/registration" method="post" modelAttribute="newUser">
        <div class="hero form-group">
            <label>Name:</label>
            <form:input path="name" class="form-control" />
            <form:errors path="name" class="text-danger" />
        </div>
        <div class="hero form-group">
            <label>Email:</label>
            <form:input path="email" class="form-control" />
            <form:errors path="email" class="text-danger" />
        </div>
        <div class="hero form-group">
            <label>Password:</label>
            <form:password path="password" class="form-control" />
            <form:errors path="password" class="text-danger" />
        </div>
        <div class="hero form-group">
            <label>Confirm Password:</label>
            <form:password path="passwordConfirmation" class="form-control" />
            <form:errors path="passwordConfirmation" class="text-danger" />
        </div>
        <input type="submit" value="Register" class="form-button " />
    </form:form>
    
    <br/>
    <h4 class ="section-title"> Returning User? Login!</h4>
    
    <form:form action="/login" method="post" modelAttribute="newLogin">
        <div class="hero form-group">
            <label>Email:</label>
            <form:input path="email" class="form-control" />
            <form:errors path="email" class="text-danger" />
        </div>
        <div class="hero form-group">
            <label>Password:</label>
            <form:password path="password" class="form-control" />
            <form:errors path="password" class="text-danger" />
        </div>
        <input type="submit" value="Login" class="form-button " />
    </form:form>
    </div>
    
    
    </div> <!-- End of Container -->

</body>
</html>
