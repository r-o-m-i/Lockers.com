package com.lockers.lockedMe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

public abstract class PasswordProtection{

	protected static Map<String, String> allCredentials = new Hashtable<>();
	private static File credentialFile = new File("credentials.properties");
	private static FileOutputStream fOutStream;
	private static FileInputStream fInStream; 
	private static Properties config = new Properties();

	static{
		//	creating the file to store credentials if it doesnt exist
		if (!PasswordProtection.credentialFile.exists())
		{
			try {
				boolean created = PasswordProtection.credentialFile.createNewFile();
				//				uncomment below code to get notified if new credentials file is created
				//				System.out.println("\n**********************************************");
				//				System.out.println("New Credentials File Created: " + created);
				//				System.out.println("**********************************************\n");	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{			
			//			initializing input file stream
			try {
				fInStream = new FileInputStream(credentialFile);
				config.load(fInStream);

			} catch (Exception e) {
				e.printStackTrace();
			}

			Iterator<Entry<Object, Object>> i = config.entrySet().iterator();

			while(i.hasNext())
			{
				Entry<Object, Object> entry = i.next();
				allCredentials.put((String)(entry.getKey()), (String)(entry.getValue()));
			}

			//			uncomment below line to see that allCredentials are initialized
			//			System.out.println(allCredentials);


		}

		//	initializing file output stream object with the credential file
		try {
			fOutStream = new FileOutputStream(credentialFile,true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}



	}


	//	adds new credentials to the credential file

	public void addCredentials(String userName, String password) {

		if(allCredentials.get(userName) == null)
		{
			allCredentials.put(userName, password);

			config.put(userName, password);
			try {
				config.store(fOutStream, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else
		{
			System.out.println("!!!!!!!!!!!User already exists!!!!!!!!!!!");
		}

	}

	//	validates credentials provided by user

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
