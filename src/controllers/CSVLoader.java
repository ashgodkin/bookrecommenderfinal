/*
 * CSV Loader class
 * @author: Ashleigh Godkin
 * 
 * takes in data from file and parses into tokens
 * 
 */



package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.introcs.In;
import models.User;
import models.Book;

public class CSVLoader
{

	private File file = new File("datastore.xml");

	public  List<User> loadUsers(String filename) throws Exception
	{
		In inUsers = new In(file);
		List<User> users = new ArrayList<User>();

		String delims = "[|]";

		while (!inUsers.isEmpty()) 
		{
			String userDetails = inUsers.readLine();//get user and rating from data
			String[] userTokens = userDetails.split(delims);//parse data using the deliminator

			users.add(new User(userTokens[0],userTokens[1],Integer.parseInt(userTokens[2]),userTokens[3],userTokens[4]));			
			// output user data to console.
			if (userTokens.length == 7)
			{
				System.out.println("First Name:"+ userTokens[0]+",Last Name:" + userTokens[1]+",Age:"+
						Integer.parseInt(userTokens[2])+",Gender:"+userTokens[3]+",Occupation:"+
						userTokens[4]);
			}			
			else
			{
				throw new Exception("Invalid member length: " + userTokens.length);
			}
		}
		for(int i = 0;i<users.size();i++)
		{
			System.out.println(users.get(i));
		}
		return users;
	}

	@SuppressWarnings("unused")
	public List<Book> loadbooks(String fileName) throws Exception
	{		
		List <Book> books = new ArrayList<Book>();//Returns an array of model objects created.
		In inUsers = new In(fileName);

		String delims = "[|]";//each field is separated(delimited) by a '|'
		while (!inUsers.isEmpty()) 
		{
			String bookDetails = inUsers.readLine();// get user and rating from data 			
			String[] bookTokens = bookDetails.split(delims);// parse user details string			
			books.add(new Book(bookTokens[1],Integer.parseInt(bookTokens[2]),bookTokens[3]));// output user data to console.

			if (books!=null) 
			{
				System.out.println(books.size());
			}
			else
			{
				throw new Exception("Invalid member length: "+bookTokens.length);
			}

		}
		for(int i = 0;i<books.size();i++)
		{
			System.out.println(books.get(i));
		}
		return books;	
	}

	public File getUsersFile() {
		return file;//return users file
	}

	public void setUsersFile(File usersFile) {
		this.file = file;//sets users file
	}
}