package com.lockers.lockedMe;

import java.io.File;
import java.util.Scanner;

public class User extends PasswordProtection implements UI{

	public enum validity{VALID, INVALID, DOES_NOT_EXIST, ERROR};
	
	private File userDir;
	private validity validUser = validity.INVALID;
	private String userName;

	User(Scanner sc)
	{
		super();

		while(this.validUser != validity.VALID)
		{
			this.validUser = this.propmptCredentials(sc);
			
			if(this.validUser == validity.DOES_NOT_EXIST)
			{
				System.out.println("!!!!!!!!!!!!Creating new user!!!!!!!!!!!!");
				this.validUser = addUser(sc);
			}
			else if(this.validUser == validity.INVALID)
			{
				this.validUser = this.propmptCredentials(sc);
			}
			else
			{
				System.out.println("!!!!!!!!!Error occurred!!!!!!!!!!!!!!");
				break;
			}
				
		}
		
		updateCredentials();
	}

	/////////////////////////////////////implementing UI/////////////////////////////////////////////


	public void displayWelcome()
	{
		//		Welcome message
		System.out.println("**********************************************");
		System.out.println("Welcome to project \"LockedMe\"");
		System.out.println("Developed by ---> Lockers Pvt Ltd. \nDeveloper name ---> Rohit Goparaju");
		System.out.println("**********************************************");
	}


	public void displayMenu()
	{

	}


	////////////////////////////////// User Functionality////////////////////////////////////////////

	//	for new users adds the user and credentials to the server
	private validity addUser(Scanner sc)
	{	
		System.out.println("New userName: " + this.userName);


		System.out.print("Set Password: ");
		String password = sc.next();

		this.userDir = new File("USER DIRECTORIES/" + this.userName );
		boolean addStatus = this.userDir.mkdirs();

		this.addCredentials(this.userName, password);

		if(addStatus)
			return validity.VALID;
		else
		{
			System.out.println("!!!!!!!!!!!!!!!Failed to create user directory!!!!!!!!!!!!!!!");
			return validity.ERROR;
		}
	}

	//	for existing user prompts and validates the entered credentials
	private validity propmptCredentials(Scanner sc)
	{
		System.out.print("userName: ");
		this.userName = sc.next();
		if(securityMap.containsKey(this.userName))
		{
			System.out.print("password: ");
			String password= sc.next();

			this.userDir = new File("USER DIRECTORIES/" + this.userName );

			if( this.validateCredentials(this.userName, password))
				return validity.VALID;
			else
				return validity.INVALID;
		}
		else
		{
			System.out.println("!!!!!!!!!!!User doesnot exist!!!!!!!!!!!");
			return validity.DOES_NOT_EXIST;
		}
	}

	//	returns if user is valid or not

	public validity isValidUser()
	{
		return this.validUser;
	}

	//	returns username

	public String getUserName()
	{
		return this.userName;
	}

	//	returns user directory
	public File getUserDir()
	{
		return this.userDir;
	}

	//	delete user
	public void deleteUser()
	{
		deleteCredentials(this.userName);
		this.userDir.delete();
	}

}
