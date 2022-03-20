package com.lockers.lockedMe;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class User extends PasswordProtection{

	private File userDir;
	private boolean validUser = false;
	private String userName;

	User(Scanner sc)
	{
		super();
		
		System.out.print("Are you an existing user?\n1. yes\n2. no\nEnter your input: ");
		int userInput;
		
		while(true)
		{
			userInput = sc.nextInt();
			if(userInput == 1)
			{
				this.validUser = this.propmptCredentials(sc);
				break;
			}
			else if(userInput == 2)
			{
				this.validUser = addUser(sc);
				break;
			}
			else
			{
				System.out.print("please enter a valid input: ");
			}
		}
		
		updateCredentials();
	}

	
	//	for new users adds the user and credentials to the server
	private boolean addUser(Scanner sc)
	{
		System.out.print("Enter userName: ");
		this.userName = sc.next();
		
		System.out.print("Set Password: ");
		String password = sc.next();
			
		this.addCredentials(this.userName, password);
		
		this.userDir = new File("USER DIRECTORIES/" + this.userName );
		
		return this.userDir.mkdirs();
	}

	//	for existing user prompts and validates the entered credentials
	private boolean propmptCredentials(Scanner sc)
	{
		System.out.print("Enter userName: ");
		String enteredUserName = sc.next();
		if(securityMap.containsKey(enteredUserName))
		{
			this.userName = enteredUserName;
			System.out.print("Enter password: ");
			String password= sc.next();

			this.userDir = new File("USER DIRECTORIES/" + this.userName );

			return this.validateCredentials(this.userName, password);
		}
		else
		{
			System.out.println("!!!!!!!!!!!User doesnot exist!!!!!!!!!!!");
			return false;
		}
	}

	//	returns if user is valid or not

	public boolean isValidUser()
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
