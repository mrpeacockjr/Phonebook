package com.example.utils;

import java.util.ArrayList;

import com.example.phonebook.userendpoint.model.CollectionResponseUser;
import com.example.phonebook.userendpoint.model.User;

import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class Validator {

	public static final String VALID="VALID";
	
	public static User validateLogin(String userName, String passWord, CollectionResponseUser userList){
		User validUser = new User();
		ArrayList<User> userArray = (ArrayList<User>) userList.getItems();
		for(int i = 0 ; i < userArray.size() ; i++){
			User tempUser = userArray.get(i);
			if(tempUser.getUserName().equals(userName) && tempUser.getUserPassword().equals(passWord) ){
				validUser.setUserId(tempUser.getUserId());
				validUser.setUserEmail(tempUser.getUserEmail());
				validUser.setUserName(tempUser.getUserName());
				validUser.setUserFirst(tempUser.getFirstName());
				validUser.setUserLastName(tempUser.getLastName());
				validUser.setKey(tempUser.getKey());
				return validUser;				
			}
		}
		validUser = null;
		return validUser;
	}
	
	public static String validateUserName(String name){
		if(name.isEmpty() || name == null)
			return "User name connot be blank.";
		return VALID;
	}	
	
	public static String validateFirstName(String name){
		if(name.isEmpty() || name == null)
			return "First name cannot be blank.";
	
		for(int i=0; i<name.length();i++){
			if( ! Character.isLetter(name.charAt(i)) )
				return "First name must only contain letters";
		}		
		return VALID;
	}
	
	public static String validateLastName(String name){
		if(name.isEmpty() || name == null)
			return "Last name cannot be blank.";
		for(int i=0; i<name.length();i++){
			if( ! Character.isLetter(name.charAt(i)) )
				return "Last name must only contain letters";
		}	
		//if(name.contains(cs))	
		return VALID;
	}

	// add email addresses to this
	public static String validateEmail(String email){
		if(email.isEmpty() || email == null)
			return "Last name cannot be blank.";
		//if(! email.endsWith("@gmail.com") || ! email.endsWith("@aol.com"))
		//		return "Not a valid email address.";
		return VALID;
	}	
	
	public static String validatePhoneNumber(String number){
		if(number.isEmpty() || number == null)
			return "Phone number cannot be blank.";
		else if(number.length() != 10)
			return "Phone number must contain 10 digits with no dashes or spaces";
		for(int i=0; i<number.length();i++){
			if( ! Character.isDigit(number.charAt(i)) )
				return "Phone number must contain only digits with no dashes or spaces";
		}	
		//if(name.contains(cs))	
		return VALID;
	}
	

	public static String validatePassword(String password){
		boolean hasNumber=false;
		boolean hasLetter=false;
		if(password.isEmpty() || password == null)
			return "Password cannot be blank.";
		else if(password.length() < 6)
			return "Password must be at least 6 digits long";
		for(int i=0; i<password.length();i++){
			if( ! hasNumber)
				if( Character.isDigit(password.charAt(i)) )
					hasNumber=true;
			if( ! hasLetter )
				if( Character.isLetter(password.charAt(i)) )
					hasLetter=true;
				//return "Phone number must contain only digits with no dashes or spaces";
		}	
		
		if( ! hasLetter )
			return "Password must contain a letter";
		if( ! hasNumber )
			return "password must contain a number";
		return VALID;
	}
}
