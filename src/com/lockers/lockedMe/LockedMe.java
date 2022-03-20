package com.lockers.lockedMe;

import java.util.Scanner;

import com.lockers.lockedMe.User.validity;

public class LockedMe {

	public static void main(String[] args) {


		//		creating a scanner object for user input
		Scanner sc = new Scanner(System.in);

		//		creating a user object

		User u1 = new User(sc);


		if(u1.isValidUser() == validity.VALID)
		{
			u1.displayWelcome();
		}


		//		closing the scanner object before the main method ends
		sc.close();
		System.out.println("!!!!!!!!!!!!!!!!!End of program!!!!!!!!!!!!!!!!!");
	}
	
	
	

}
