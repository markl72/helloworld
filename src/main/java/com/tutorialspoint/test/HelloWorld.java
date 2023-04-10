package com.tutorialspoint.test;

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
	   message = "updated message: " + input1;
   }
   
   public String showResult() {
	      return "snow result";
   }
}