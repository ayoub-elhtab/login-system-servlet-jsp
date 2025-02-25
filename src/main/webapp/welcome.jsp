<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-size: 16px;
            font-family: "Lato", sans-serif;
        }

        body{
            display: flex;
            justify-content: center;
            align-items: center;
            padding-top: 100px;
            flex-direction: column;
        }
        h1, span{
            margin-bottom: 40px;
            font-size: 30px;
        }
        span{
            color: #02b3ee;
        }
        .signout-btn{
            background-color: #d0cfcf;
            color:#1d1d1d;
            border: none;
            width: 90px;
            height: 40px;
            padding: 10px;
            border-radius: 6px;
            font-weight: 700;
            text-align: center;
            text-decoration: none;
    
        }

        .signout-btn:hover{
            cursor: pointer;
            background-color:#989797;
            transition: background-color .13s;
        }
	</style>
<title>Welcome page</title>
</head>
<body>
	<% 
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	String name = (String) session.getAttribute("name");
	if ( name == null){
		response.sendRedirect("login.jsp");
	}
	%>
	<h1>Hi <span><%=name %></span> ! Welcome to our page.</h1>
    <a href="signout" class="signout-btn">Sign Out</a>
</body>
</html>