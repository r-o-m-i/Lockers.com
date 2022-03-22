package com.lockers.lockedMe;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

public abstract class PasswordProtection{

	 private File securityFile;//location of credentials file
	 private FileWriter writer;
	 private FileReader reader; 
	 private Properties credentials;//properties object to handle properties file
	 protected Map<String, String> securityMap = new TreeMap<>();

	public PasswordProtection() {
		this.securityFile = new File("security.properties");
		try
		{
			this.reader = new FileReader(securityFile);			
			this.credentials = new Properties();
			this.credentials.load(reader);
			
			if(!this.securityFile.exists())
			{
//				System.out.println("Security File Created");
				LockedMe.logger.info("Security File Created");
				this.securityFile.createNewFile();
			}
			else
			{
				Iterator<Entry<Object, Object>> itr = this.credentials.entrySet().iterator();
				
				while(itr.hasNext())
				{
					Entry<Object, Object> entry = itr.next();
					this.securityMap.put((String)entry.getKey(), (String)entry.getValue());
				}
			}
			this.writer = new FileWriter(securityFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//	adds new credentials to the credential file

	public void addCredentials(String userName, String password) {

		if(this.securityMap.containsKey(userName))
		{
//			System.out.println("!!!!!!!!!!!User already exists!!!!!!!!!!!");
			LockedMe.logger.warn("!!!!!!!!!!!User already exists!!!!!!!!!!!");
		}
		else
		{
			this.securityMap.put(userName, password);
			updateCredentials();
		}
	}

	//	validates credentials provided by user

	public boolean validateCredentials(String userName, String password)
	{
		if(this.securityMap.get(userName).equals(password))
		{
//			System.out.println("******************Login Successful******************");
			LockedMe.logger.info("Logged in as: " + userName);
			return true;
		}
		else
		{
//			System.out.println("!!!!!!!!Wrong Password!!!!!!!!");
			LockedMe.logger.warn("incorrect password for account: " + userName);
			return false;
		}
	}

	//	removes credentials of a particular user
	public void deleteCredentials(String userName)
	{
//		System.out.println("Deleting User: " + userName);
		LockedMe.logger.info("Deleting User: " + userName);
		this.securityMap.remove(userName);
		updateCredentials();
	}

	public Properties getCredentials()
	{
		return credentials;
	}
	
	public void updateCredentials() {
		
		try {
			this.credentials = new Properties();
			this.credentials.load(reader);
			if(securityMap.isEmpty())
			{
				credentials.clear();
			}
			else
			{
				credentials.putAll(securityMap);
			}
			this.writer = new FileWriter(securityFile);
			credentials.store(writer, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
