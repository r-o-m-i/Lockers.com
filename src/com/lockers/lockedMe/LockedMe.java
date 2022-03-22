package com.lockers.lockedMe;

import java.util.Scanner;
import com.lockers.lockedMe.User.validity;
import org.apache.logging.log4j.*;

public class LockedMe {

	//		creating a scanner object for user input
	public static final Scanner sc = new Scanner(System.in);

	//	creating logger for logging onto console and files
	public static final Logger logger = LogManager.getLogger(LockedMe.class.getName());


	public static void main(String[] args) {

		//		creating a user object

		User u1 = new User();


		if(u1.isValidUser() == validity.VALID)
		{
			u1.displayWelcome();

			u1.displayMenu();
		}


		//		closing the scanner object before the main method ends
		u1.exitMessage();
		sc.close();
	}




}
