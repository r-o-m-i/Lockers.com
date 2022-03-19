package com.lockers.lockedMe;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class User extends PasswordProtection{

	private File userDir;
	private boolean validUser = false;
	
	User(Scanner sc)
	{
		System.out.print("Are you an existing user?\n 'Y' for yes and 'N' for no: ");
		String userInput = sc.nextLine();
		
		try {
			userInput.charAt(0);
		}
		catch(StringIndexOutOfBoundsException e)
		{
			userInput= "x";
		}
		
		userInput = userInput.toLowerCase();
		do {
			if(userInput.charAt(0) != 'y' && userInput.charAt(0) != 'n')
			{
				System.out.println("\n**********************************************\nInvalid input, please try again: \n");
				System.out.print("Are you an existing user?\n 'Y' for yes and 'N' for no: ");
				userInput = sc.nextLine();
				userInput = userInput.toLowerCase();
			
				try {
					userInput.charAt(0);
				}
				catch(StringIndexOutOfBoundsException e)
				{
					userInput= "x";
				}
				
			}
			if(userInput.charAt(0) == 'y')
			{
				validUser = propmptCredentials(sc);
			}
			
			else if(userInput.charAt(0) == 'n')
			{
				System.out.println("******Adding new user******");
				validUser = addUser(sc);
			}
		}while( userInput.charAt(0) != 'y' && userInput.charAt(0) != 'n');
		
	}

//	for new users adds the user and credentials to the server
	public boolean addUser(Scanner sc)
	{
		System.out.print("Enter userName: ");
		String userName = sc.next();
		sc.nextLine();
		if(PasswordProtection.allCredentials.get(userName)!=null)
		{
			System.out.println("!!!!!!!User already exists!!!!!!!");
			return true;
		}
		else
		{
			System.out.print("\n-->Password must not contain any whitespaces.\nSet Password: ");
			String password = sc.next();
			sc.nextLine();
			this.addCredentials(userName, password);
			
			
			this.userDir = new File("USER DIRECTORIES/" + userName );
			
			return this.userDir.mkdirs();
		}
		
	}
	
//	for existing user prompts and validates the entered credentials
	public boolean propmptCredentials(Scanner sc)
	{
		System.out.print("Enter userName: ");
		String userName = sc.next();
		sc.nextLine();
		if(PasswordProtection.allCredentials.get(userName) != null)
		{
			System.out.print("Enter password: ");
			String password= sc.next();
			sc.nextLine();
			return this.validateCredentials(userName, password);
		}
		else
		{
			System.out.println("!!!!!!!!!!!User doesnot exist!!!!!!!!!!!");
			return false;
		}
	}
	
	public boolean isValidUser()
	{
		return this.validUser;
	}
	
}
