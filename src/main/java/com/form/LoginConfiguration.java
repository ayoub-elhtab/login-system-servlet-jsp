package com.form;
import com.form.DatabaseConnection.connectToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginConfiguration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String emailErr = null;
		String passwordErr = null;
		
		if(email.trim().isEmpty()) {
			emailErr = "Please enter the email";
		}
		if(password.trim().isEmpty()) {
			passwordErr = "Please enter the password";
		}
		
		// send the error messages to index.jsp
		if(emailErr != null || passwordErr != null) {		
			request.setAttribute("emailErr", emailErr);
			request.setAttribute("passwordErr", passwordErr);
			RequestDispatcher dr = request.getRequestDispatcher("login.jsp");
			dr.forward(request, response);
		}else {
			try(Connection conn = connectToDB.getConnection()){
				
				// validate credentials
				// check if the email exists
				String emailQuery = "select count(*) from users where email = ?";
				PreparedStatement emailStmt = conn.prepareStatement(emailQuery);
				try(emailStmt){
					emailStmt.setString(1, email);
					ResultSet rsEmail = emailStmt.executeQuery();
					try(rsEmail){
						rsEmail.next();
						 if(rsEmail.getInt(1) > 0) {
							 	// email exists , verify password
		                    	String hashedPasswordQuery = "select hashedPassword , name from users where email = ?";
		                    	PreparedStatement hashedPasswordStmt = conn.prepareStatement(hashedPasswordQuery);
		                    	try(hashedPasswordStmt){
		                    		hashedPasswordStmt.setString(1, email);
		                    		ResultSet rsHashedPassword = hashedPasswordStmt.executeQuery();
		                    		try(rsHashedPassword){
		                    			
		                    			if(rsHashedPassword.next()) {
			                    			String hashedPassword = rsHashedPassword.getString("hashedPassword");
			                    			if(!BCrypt.checkpw(password , hashedPassword )) {
					                    		passwordErr = "Wrong password";
					                    		request.setAttribute("passwordErr", passwordErr);
					                    		RequestDispatcher dr = request.getRequestDispatcher("login.jsp");
					                    		dr.forward(request, response);
					                    	}else {
					                    		// Successful login => set name in session and redirect to welcome page
					                    		HttpSession session = request.getSession();
					                    		session.setAttribute("name", rsHashedPassword.getString("name"));
					                    		response.sendRedirect("welcome.jsp");
					                    		
					                    	}
		                    			}
		                    		}
		                    		
		                    		
		                    		
			                    	
		                    	}
		                    	
		                    	
		                    	
						}else {
				            // Email doesn't exist
							emailErr = "Email not found , please signup first";
							request.setAttribute("emailErr", emailErr);
                    		RequestDispatcher dr = request.getRequestDispatcher("login.jsp");
                    		dr.forward(request, response);
				        }
					}
                    
                   
				
				
			}
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            RequestDispatcher dr = request.getRequestDispatcher("login.jsp");
            dr.forward(request, response);
		}
	
	
	
	
	}

}
	}
