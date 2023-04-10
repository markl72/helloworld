package com.tutorialspoint.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

@ManagedBean(name = "helloWorldFileUpload", eager = true)
public class HelloWorldFileUpload {
   
	private String message = "hello hello";
    private Part file;
    
    public Part getFile() {
        return file;
    }
 
    public void setFile(Part file) {
        this.file = file;
    }
	
   public HelloWorldFileUpload() {
      System.out.println("HelloWorld file uplaod started!");
   }
	
   public String getMessage() {
      return message;
   }
   
   public void setMessage(String message) {
       this.message = message;
   }
   
   public void upload() {
   
	   String text=null;
	   try{
       		Part filepart = getFile();
       		InputStream fileContent = filepart.getInputStream();
       		text = new String(fileContent.readAllBytes(), StandardCharsets.UTF_8);
       } catch(FileNotFoundException e) {
       	message = "filenotfoundexception " + e;        	
       } catch(IOException e) {
    	   message = "ioexception " + e; 
       } catch(Exception e) {
    	   message = "exception " + e; 
       }
	   
	   
	   String dbvalue = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/insecureapp","insecureapp","45EUlZOpL7");  // #7 Hardcoded password
			
			String sql = "select * from users where userid = '" + text + "'";
			PreparedStatement pstmt = connection.prepareStatement( sql ); 
            ResultSet rs = pstmt.executeQuery(sql);  
            
             
            while(rs.next()) {
            	System.out.println("<p>Name: " + rs.getString(3) + " " + rs.getString(2) + "<br>Address: " + rs.getString(4) + "<br>Phone no: " + rs.getString(5) + "</p>"); // #10 Stored XSS
            	dbvalue = "<p>Name: " + rs.getString(3) + " " + rs.getString(2) + "<br>Address: " + rs.getString(4) + "<br>Phone no: " + rs.getString(5) + "</p>";
            }
    		message = "updated message: " + text + " : " + dbvalue + ";";
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