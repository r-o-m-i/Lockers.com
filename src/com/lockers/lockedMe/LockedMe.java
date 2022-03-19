package com.lockers.lockedMe;

import java.util.Map;
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
		
		User u1 = new User(sc);
		
		System.out.println(u1.isValidUser());
		
//		closing the scanner object before the main method ends
		sc.close();
	}

}
