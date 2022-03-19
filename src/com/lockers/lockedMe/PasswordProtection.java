package com.lockers.lockedMe;

import java.util.Map;
import java.util.TreeMap;

public abstract class PasswordProtection {
	
	protected static Map<String, String> allCredentials = new TreeMap<>();
	
	public void addCredentials(String userName, String password) {

		if(allCredentials.get(userName) == null)
		{
			allCredentials.put(userName, password);
		}
		else
		{
			System.out.println("!!!!!!!!!!!User already exists!!!!!!!!!!!");
		}
		
	}
	
	public boolean validateCredentials(String userName, String password)
	{
		if(allCredentials.get(userName).equals(password))
		{
			return true;
		}
		else
		{
			System.out.println("!!!!!!!!Wrong Password!!!!!!!!");
			return false;
		}
	}
	
	
		
}
