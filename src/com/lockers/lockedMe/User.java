package com.lockers.lockedMe;

import java.io.File;

public class User extends PasswordProtection implements UI{

	public enum validity{VALID, INVALID, DOES_NOT_EXIST, ERROR, EXIT};

	private File userDir;
	private validity validUser = validity.INVALID;
	private String userName;


	User()
	{
		super();

		while(this.validUser != validity.VALID && this.validUser != validity.EXIT)
		{
			this.validUser = this.propmptCredentials();

			if(this.validUser == validity.DOES_NOT_EXIST)
			{		
				System.out.println("Do you want to create a new user?\n1. Yes\n2. No");
				int userChoice;
				boolean loopVar = true;
				while(loopVar)
				{
					System.out.print("Enter your choice: ");
					userChoice = LockedMe.sc.nextInt();

					if(userChoice == 1)
					{
						this.validUser = addUser();
						loopVar = false;
					}
					else if(userChoice == 2)
					{
						loopVar = false;
						this.validUser = validity.EXIT;
					}	
					else
					{
						System.out.println("!!!!!!!!!!!!!!!!!Please enter a valid input!!!!!!!!!!!!!!!!!");
					}
				}
			}
			else if(this.validUser == validity.INVALID)
			{
				this.validUser = this.propmptCredentials();
			}
			else if(this.validUser == validity.ERROR)
			{
				System.out.println("!!!!!!!!!Error occurred!!!!!!!!!!!!!!");
				break;
			}

		}


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
		//		menu of operations
		System.out.println("1. Delete\n2. User Status");

		int userInput;

		boolean inputLoopVar = true;

		while(inputLoopVar)
		{
			System.out.print("Enter your choice: ");
			userInput =  LockedMe.sc.nextInt();

			switch(userInput)
			{
			case 1: 
				this.deleteUser();
				inputLoopVar = false;
				break;
			case 2:
				System.out.println(this.userName + " Directory exists at " + this.userDir.getAbsolutePath());
				inputLoopVar = false;
				break;
			default: 
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!enter valid input!!!!!!!!!!!!!!!!!!!!!!");
				break;

			}

		}

	}


	public void exitMessage()
	{
		System.out.println("!!!!!!!!!!!!!!!!!End of program!!!!!!!!!!!!!!!!!");
	}


	////////////////////////////////// User Functionality////////////////////////////////////////////

	//	for new users adds the user and credentials to the server
	private validity addUser()
	{	
		System.out.println("New userName: " + this.userName);


		System.out.print("Set Password: ");
		String password = LockedMe.sc.next();

		this.userDir = new File("USER DIRECTORIES/" + this.userName );
		boolean addStatus = this.userDir.mkdirs();

		this.addCredentials(this.userName, password);

		if(addStatus)
		{
			//			System.out.println("!!!!!!!!!!!!Creating new user!!!!!!!!!!!!");
			LockedMe.logger.info("New User Created: " + this.userName);
			return validity.VALID;
		}
		else if(this.userDir.exists())
		{
			LockedMe.logger.warn("User Directory already exists: " + this.userDir.getAbsolutePath());
			return validity.VALID;
		}
		else
		{
			LockedMe.logger.fatal("Unable to create user directory for user: " + this.userName);
			return validity.ERROR;
		}
	}

	//	for existing user prompts and validates the entered credentials
	private validity propmptCredentials()
	{
		System.out.print("userName: ");
		this.userName = LockedMe.sc.next();
		if(securityMap.containsKey(this.userName))
		{
			System.out.print("password: ");
			String password= LockedMe.sc.next();

			this.userDir = new File("USER DIRECTORIES/" + this.userName );

			if( this.validateCredentials(this.userName, password))
				return validity.VALID;
			else
				return validity.INVALID;
		}
		else
		{
//			System.out.println("!!!!!!!!!!!User doesnot exist!!!!!!!!!!!");
			LockedMe.logger.warn("User does not exist: " + this.userName);
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
