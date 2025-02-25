package com.form;
import org.mindrot.jbcrypt.BCrypt;
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


@WebServlet("/signup")
public class SignupConfiguration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("Cpassword");
		
		String nameErr = null;
		String emailErr = null;
		String passwordErr = null;
		String cpasswordErr = null;
		
		// Validate name :
		if(name.trim().isEmpty()) {
			nameErr = "Please enter the name";
		}else if(name.trim().length() > 15){
			nameErr = "The name must not exceed 15 Characters";
		}else if(!name.trim().matches("^[a-zA-Z0-9_]+$")) {
			nameErr = "The name must only contains letters and numbers";
		}
		
		
		// validate email :
		if(email.trim().isEmpty()) {
			emailErr = "Please enter the email";
		}else if (!email.trim().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            emailErr = "Please enter a valid email address";
        }
		
		
		// validate password :
		if(password.trim().isEmpty()) {
			passwordErr = "Please enter the password";
		}else if(!password.trim().matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$")){
		    passwordErr = "Password must contain at least one letter and one number, and be 8-20 characters long";
		}
	    	
		
		// validate confirm password :
		if(cpassword.trim().isEmpty()) {
			cpasswordErr = "please confirm the password";
		}else if(!password.equals(cpassword)){
			cpasswordErr = "password did not match";
		}
		
		
		// send the error messages to index.jsp
		if(nameErr != null || emailErr != null || passwordErr != null || cpasswordErr != null) {		
			request.setAttribute("nameErr", nameErr);
			request.setAttribute("emailErr", emailErr);
			request.setAttribute("passwordErr", passwordErr);
			request.setAttribute("cpasswordErr", cpasswordErr);
			RequestDispatcher dr = request.getRequestDispatcher("signup.jsp");
			dr.forward(request, response);
		}else {
			
			try(Connection conn = connectToDB.getConnection()){
				//check if name already exists
				String nameQuery = "select count(*) from users where name = ?";
				PreparedStatement nameStmt = conn.prepareStatement(nameQuery);
				try(nameStmt){
					nameStmt.setNString(1, name);
					ResultSet rsName = nameStmt.executeQuery();
					rsName.next();
					if(rsName.getInt(1) > 0) {
						nameErr = "name Already used";
						request.setAttribute("nameErr", nameErr);
                        RequestDispatcher dr = request.getRequestDispatcher("signup.jsp");
                        dr.forward(request, response);
                        return;
					}
				}
				//check if email already exists
				String emailQuery = "select count(*) from users where email = ?";
				PreparedStatement emailStmt = conn.prepareStatement(emailQuery);
				try (emailStmt) {
					emailStmt.setString(1, email);
                    ResultSet rsEmail = emailStmt.executeQuery();
                    rsEmail.next();
                    if (rsEmail.getInt(1) > 0) {
                        emailErr = "Email already used";
                        request.setAttribute("emailErr", emailErr);
                        RequestDispatcher dr = request.getRequestDispatcher("signup.jsp");
                        dr.forward(request, response);
                        return;
                    }
                }
				
				// insert new user
				String insertQuery = "insert into users (name, email, hashedPassword) values (?, ?, ?)";
				PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                try (insertStmt) {
                    insertStmt.setString(1, name);
                    insertStmt.setString(2, email);
                    // hash the password :
                    String salt = BCrypt.gensalt(12);
                    String hashedPassword = BCrypt.hashpw(password, salt);
                    insertStmt.setString(3, hashedPassword);
                    
                    int rowsInserted = insertStmt.executeUpdate();
                    if (rowsInserted > 0) {
                        request.setAttribute("successMessage", "Signup successful!");
                        RequestDispatcher dr = request.getRequestDispatcher("login.jsp");
                        dr.forward(request, response);
                    }
                }
				
				
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
                request.setAttribute("errorMessage", "Database error: " + e.getMessage());
                RequestDispatcher dr = request.getRequestDispatcher("signup.jsp");
                dr.forward(request, response);
			}
			
		}
		
	}

}
