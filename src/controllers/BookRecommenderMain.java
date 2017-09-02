/*
 * Book Recommender API
 * 
 * @author: Ashleigh Godkin
 * 
 * uses command line interface for user to input commands
 * typing ?help will return the list of possible commands for the user
 * 
 */



package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

import models.Book;
import models.Rating;
import models.User;



public class BookRecommenderMain
{
	public CSVLoader loader = new CSVLoader();
	public RecommenderAPI recommenderAPI;

	private ArrayList<User> users;

	
	public BookRecommenderMain() throws Exception
	{
		File data = new File("data.xml");
		Serializer serializer = new XMLSerializer(data);

		recommenderAPI = new RecommenderAPI(serializer);
		if (data.isFile())
		{
			recommenderAPI.load();
		}

	}

	public static void main(String[] args) throws Exception
	{
		BookRecommenderMain main = new BookRecommenderMain();

		Shell shell = ShellFactory.createConsoleShell("Welcome to the Book Recommender Console - ?help for instructions", null, main);
		shell.commandLoop();

		main.recommenderAPI.write();
	}

	/*@Command(description="Login")
	public void login(){
		recommenderAPI.login();
	}*/



	@Command(description="Get all users details")
	public void getUsers (){
		Collection<User> users = recommenderAPI.getUsers();
		System.out.println(users);
	}  

	@Command(description="Get a user by their ID")
	public void getUser (@Param(name="userID") Long userID){
		recommenderAPI.getUser(userID);
		System.out.println(userID);
	}

	@Command(description="Add new user")
	public void addUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName,	@Param(name="age") int age, @Param(name="gender") String gender, @Param(name="occupation") String occupation){
		recommenderAPI.addUser(firstName, lastName, age, gender, occupation);
	}


	@Command(description="Delete user")
	public void deleteUser (@Param(name="userID") Long userID){
		recommenderAPI.deleteUser(userID);
	}

	@Command(description = "List all books")
	public void getBooks(){
		Collection<Book> books = recommenderAPI.getBooks();
		System.out.println(books);
	}

	@Command(description="Add a Book")
	public void addBook (@Param(name="Book Title") String title, @Param(name="Book Year") int year, @Param(name="Book URL") String url){
		recommenderAPI.addBook(title, year, url);
	}

	@Command(description="Add a rating")
	public void addRating(@Param(name="Your UserID") Long userID, 
			@Param(name="bookID")Long bookID, @Param(name="score") int score){
		recommenderAPI.addRating(userID, bookID, score);		
	}

	@Command(description="Top 5 books")
	public void getTopBooks (){
		recommenderAPI.getTopBooks();
		System.out.println(recommenderAPI.getTopBooks());
	} 

	@Command(description="Recommended Books")
	public void getUserRecommendations(@Param(name="Your UserID") Long userID){
		recommenderAPI.getUserRecommendations(userID);		
	}


}
