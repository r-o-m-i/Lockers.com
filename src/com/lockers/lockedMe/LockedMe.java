package com.lockers.lockedMe;

import java.util.Scanner;

public class LockedMe {

	public static void main(String[] args) {

		//		Welcome message
		System.out.println("**********************************************");
		System.out.println("Welcome to project \"LockedMe\" : ");
		System.out.println("Developed by: Lockers Pvt Ltd. \nDeveloper name: Rohit Goparaju");
		System.out.println("**********************************************");

		//		creating a scanner object for user input

		Scanner sc = new Scanner(System.in);

		//		creating a user object

		User u1 = new User(sc);
		if(u1.isValidUser())
		{
	
			//TODO code for when user is valid
			
		}


		//		closing the scanner object before the main method ends
		sc.close();
		System.out.println("!!!!!!!!!!!!!!!!!End of program!!!!!!!!!!!!!!!!!");
	}

}
