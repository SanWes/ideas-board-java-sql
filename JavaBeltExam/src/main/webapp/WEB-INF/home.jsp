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
<title>IDEAS THRIVE HERE</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
    <link rel="stylesheet" href="/MainStyles.css">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        	
        	<h1 class="hero-title">Welcome, <c:out value="${user.name}" /> </h1>   
        	<br><br>
        	
        	<p class="hero">"Unlock the boundless power of your creativity. Welcome to the Ideas Board, where every thought has the potential to shape a new reality. Dive into a world of innovation, share your brilliance, and let the collective wisdom of ideas light up your journey. But BEWARE!!!! other users can EDIT YOUR IDEA!"</p>
        	<br>
        	<br>
        	
        	<h3 class="section-title">Great Ideas</h3>
        	
		    	
 	
<table class="ideas-table">
		<thead >
        <tr>
            <th class="ideas-table-header"> Idea </th>
            <th class="ideas-table-header"> Created By </th>
            <th class="ideas-table-header"> Actions </th>
           
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${ideas}" var="n">
        <tr>
            <td class="ideas-content-cell">   <a class="idea-link" href="/ideas/${n.id}"> <c:out value="${n.content}"/></a>    </td>
            <td class="ideas-content-cell"><c:out value="${n.user.name}"/></td>
     
            <td class="ideas-content-cell "><a href="/ideas/${n.id}/edit">Edit</a></td>
            <td class="ideas-content-cell action-link"><a href="/delete/idea/${n.id}">Delete</a></td>
            
        </tr>
        </c:forEach>
    </tbody>
</table>

        
        	<br>
        	<br>
        	
        	<a href="/ideas/new" class="new-ideas-link">New Ideas!</a> <br>
        	
        	<br/>
        	
        	<a href="/logout" class="logout-link">Logout</a>
        	
			
			
    </div> <!-- End of Container -->

</body>
</html>
