<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="CSS/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sign Up page</title>
</head>
<body>
	<div class="container">
            <form action="signup" method="post">
            
            <%
            
            String errorMessage = (String) request.getAttribute("errorMessage");
            if(errorMessage != null){
            %>
            	<p style="color:red;"><%= errorMessage %></p>
            <%} %>	
            
            <h1>Sign Up</h1>
            
            <input type="text" name="name" placeholder="Name" class="input-box"/>
            <% String nameErr = (String) request.getAttribute("nameErr");
            if(nameErr != null){ %>
            	<p style="color:red;"><%= nameErr %></p>
            <% } %>	
            
            <input type="text" name="email" placeholder="Email" class="input-box"/>
            <% String emailErr = (String) request.getAttribute("emailErr");
            if(emailErr != null){ %>
            	<p style="color:red;"><%= emailErr %></p>
            <% } %>	
            
            <input type="password" name="password" placeholder="Password" class="input-box"/>
            <% String passwordErr = (String) request.getAttribute("passwordErr");
            if(passwordErr != null){ %>
            	<p style="color:red;"><%= passwordErr %></p>
            <% } %>	
            
            <input type="password" name="Cpassword" placeholder="Confirm Password" class="input-box"/>
            <% String cpasswordErr = (String) request.getAttribute("cpasswordErr");
            if(cpasswordErr != null){ %>
            	<p style="color:red;"><%= cpasswordErr %></p>
            <% } %>	 
            
            <input type="submit" value ="Sign up" class="btn"/>
            
            <p id="message">Already have an account ? </p>
            <a href="login.jsp">Login here</a>
            
        </form>
    </div>
</body>
</html>