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
<title>Brilliant Idea</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
    <link rel="stylesheet" href="/MainStyles.css">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        
        <h3 class="hero-title">YOUR NEW IDEAS GO HERE </h3>
        
        <form:form class="new-idea-form" action="/postidea" method="post" modelAttribute="idea">
        	<form:hidden path = "user" value = "${user.id}" />
        
     <p class="form-group">
        <form:label path="content">Idea Content: </form:label>
        <form:errors path="content"/>
        <form:input path="content" class="form-input"/>
    </p>
    
     <input type="submit" value="Create" class="submit-button"/>
</form:form> 

<a href="/home" class="home-link"> Home</a>
    </div> <!-- End of Container -->

</body>
</html>
