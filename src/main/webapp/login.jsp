 <?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="CSS/style.css"/>
<title>Login page</title>
</head>
<body>
	<div class="container">
        
        <form action="login" method="post">
        	<% 
        		
	            String successMessage = (String) request.getAttribute("successMessage");
	            if(successMessage != null){
            %>
            		<p style="color:green;"><%= successMessage %></p>
            <%	} %>
            	
            <h1>Login Now</h1>
            
            <input type="text" name="email" placeholder="Email" class="input-box">
            <% String emailErr = (String) request.getAttribute("emailErr");
            if(emailErr != null){ %>
            	<p style="color:red;"><%= emailErr %></p>
            <% } %>	
            
            <input type="password" name="password" placeholder="Password" class="input-box">
            <% String passwordErr = (String) request.getAttribute("passwordErr");
            if(passwordErr != null){ %>
            	<p style="color:red;"><%= passwordErr %></p>
            <% } %>
            
            <input type="submit" value ="Login" class="btn">
            
            <p id="message">You don't have an account ? </p>
            <a href="signup.jsp">Signup here</a>
            
        </form>
    </div>

</body>
</html>