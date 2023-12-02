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
<title>Show Idea</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
    <link rel="stylesheet" href="/MainStyles.css">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        	
        	
        	<h1 class="hero-title">Great Idea:</h1>
        	<a href="/home" class="home-link">Home</a>
<br><br>
        	<div class = "idea-card">
        	<h2 class="idea-title">${idea.content}</h2>
	    	<br>
	    	<p class="idea-author">Created By:		${idea.user.name}</p>


            <a href="/ideas/${idea.id}/edit" class="action-link">Edit</a>
            <a href="/delete/idea/${idea.id}" class="action-link">Delete</a>
        	</div>
            
        	<br>
        	<a href="/ideas/new" class="new-ideas-link">New Ideas!</a> <br>
            
        	<br>
        	
        	
        	
        	
        	
			
		   <a href="/logout" class="logout-link">Logout</a>
			
    </div> <!-- End of Container -->

</body>
</html>
