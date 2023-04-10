package com.tutorialspoint.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "helloWorld", eager = true)
public class HelloWorld {
   
	private String message = "hello hello";
	
   public HelloWorld() {
      System.out.println("HelloWorld started!");
   }
	
   public String getMessage() {
      return message;
   }
   
   public void setMessage(String message) {
       this.message = message;
   }
   
   public void updateData(String input1) {
   
	   String dbvalue = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/insecureapp","insecureapp","AM34u9H^k");  // #7 Hardcoded password
			
			String sql = "select * from users where userid = '" + input1 + "'";
			PreparedStatement pstmt = connection.prepareStatement( sql ); 
            ResultSet rs = pstmt.executeQuery(sql);  
            
             
            while(rs.next()) {
            	System.out.println("<p>Name: " + rs.getString(3) + " " + rs.getString(2) + "<br>Address: " + rs.getString(4) + "<br>Phone no: " + rs.getString(5) + "</p>"); // #10 Stored XSS
            	dbvalue = "<p>Name: " + rs.getString(3) + " " + rs.getString(2) + "<br>Address: " + rs.getString(4) + "<br>Phone no: " + rs.getString(5) + "</p>";
            }
    		message = "updated message: " + input1 + " : " + dbvalue + ";";
            connection.close();
        } 
		catch(Exception e){ 
			message = "failed: " + e;
			System.out.println(e);
		}  
		

	   
   }
   
   public String showResult() {
	      return "snow result";
   }
}