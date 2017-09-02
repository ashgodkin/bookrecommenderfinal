/* 
 * RecommenderAPI
 * @author: Ashleigh Godkin
 * 
 * contains all methods getters and setters used
 * for the main class and command line
 */


package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import models.Book;
import models.Rating;
import models.User;
import utils.Serializer;



public class RecommenderAPI{
	private Serializer serializer;
	private Map<String, Rating>   ratingIndex       = new HashMap<>();
	private static Map<Long, User>   userIndex       = new HashMap<>();//mapping the LOng userID onto User object
	private Map<Long,Book>   bookIndex      = new HashMap<>();// mapping the long bookID onto the book object
	private Map<String,Long> userName = new HashMap<>();

	public RecommenderAPI()
	{}

	public RecommenderAPI(Serializer serializer){
		this.serializer = serializer;
	}

	@SuppressWarnings("unchecked")
	public void load() throws Exception	{
		serializer.read();
		Book.counter = (Long) serializer.pop();
		bookIndex = (Map<Long, Book>) serializer.pop();
		userName = (Map<String,Long>)  serializer.pop();
		User.counter = (Long) serializer.pop();
		userIndex = (Map<Long, User>)	serializer.pop();
	}

	void write() throws Exception
	{
		serializer.push(userIndex);//must be in exact reverse oder of pop, as it enters on the stack
		serializer.push(User.counter);
		serializer.push(userName);
		serializer.push(bookIndex);
		serializer.push(Book.counter);
		serializer.write(); 
	}


	//attempted member login
	//if member wants to log in
	/*private void login()
	{
	
		
		//asks user to input email address
		System.out.println("Hello user, please login.");
		String loginEmail = validNextString("Enter your email: ");
		
		//https://www.tutorialspoint.com/javaexamples/regular_email.htm
		if(!loginEmail.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) 
		{
			System.out.println("Invalid E-mail entered: " + loginEmail);
		}
		
		else
		{
			//if email already exists, access denied
			System.out.println("ACCESS DENIED: " + loginEmail +
					           "\nReturning to menu.");
		}
	}*/
	
	/*//valids from stack overflow
	public static String validNextString(String prompt)
	{
		Scanner input = new Scanner(System.in);
		do
		{
			System.out.print(prompt);
			return input.nextLine();
		}
		while(true);
	}*/

	
	public Collection<User> getUsers (){
		return userIndex.values();
	}  

	public User getUser(Long userID) {
		System.out.println(userIndex.get(userID));
		return userIndex.get(userID);
	}

	public  void deleteUsers() {
		userIndex.clear();
	}

	public void deleteUser(Long userID){
		System.out.println("User id: " + userIndex.get(userID).userID + " First Name: "+ userIndex.get(userID).firstName + " has been deleted");
		userIndex.remove(userID);
	}

	public User addUser(User user){
		userIndex.put(user.userID, user);
		userName.put(user.firstName +" " + user.lastName, user.userID);
		return user;
	}

	public User addUser(String firstName, String lastName, int age, String gender, String occupation) 	{
		User user = new User (firstName, lastName, age, gender, occupation);
		userIndex.put(user.userID, user);//mapping a user id onto the user object within the userindex map
		userName.put(user.firstName +" " + user.lastName, user.userID);
		return user;
	}

	public Collection<Book> getBooks (){
		return bookIndex.values();
	}

	public Book getBook(Long bookID){
		return bookIndex.get(bookID);
	}

	public Book addBook(String title, int year, String url){
		Book book = new Book (title, year, url);
		bookIndex.put(book.bookID, book);//mapping a book id onto the book object within the bookindex map
		return book;
	}

	public Book addBook(Book book){
		bookIndex.put(book.bookID, book);
		return book; 
	}

	public void deleteBooks(){
		bookIndex.clear();
	}

	public void deleteBook(Long bookID) {
		System.out.println("Book id: " + bookIndex.get(bookID).bookID + " Book Name: "+ bookIndex.get(bookID).title + " has been deleted");
		bookIndex.remove(bookID);
	}	

	public Collection<Rating> getRatings ()	{
		return ratingIndex.values();
	}

	public Rating getRating(Long bookID){
		return ratingIndex.get(bookID);
	}

	//adds rating
	public Rating addRating(Long userID, Long bookID, int score){
		Rating rating = new Rating(userID, bookID, score) ;
		ratingIndex.put(userID + "," + bookID, rating);//.put only working for hashmaps
		Book book = getBook(bookID);// gives book object, assigning as local variable to use
		book.ratings.add(rating); //adding reference to rating object from book object

		return rating;
	}

	public Rating addRating(Rating rating){
		ratingIndex.put(rating.userID + "," + rating.bookID, rating);

		User user = userIndex.get(rating);
		user.ratings.add(rating);
		Book book = getBook(rating.bookID);
		book.ratings.add(rating);

		return rating; 
	}

	public Collection<Rating> getUserRatings(Long userID){
		User user = userIndex.get(userID);
		return user.ratings;
	}  

	//lists and sorts top books
	public List <Book> getTopBooks(){

		List <Book> allBooks = new ArrayList <Book> (bookIndex.values());	
		Collections.sort (allBooks);

		Collections.reverse(allBooks);
		return allBooks.subList(0,Math.min (allBooks.size(),10));
	}


	//returns recommendations
	public List <Book> getUserRecommendations(Long userID)
	{
		List <Book> recommendedbooks = new ArrayList <Book> (bookIndex.values());
		Collections.sort(recommendedbooks); 
		Collections.reverse(recommendedbooks);//same as above method

		List <Book> unratedBooks = new ArrayList <Book>(); //empty array at beginning
		for (Book book : recommendedbooks)
		{
			boolean ratedByUser = false; //default not rated
			for (Rating rating : book.ratings) {
				//checking all the ratings of that book
				if (rating.userID == userID) {
					ratedByUser = true;
					break; //stop checking the list
				}
			}
			if (!ratedByUser){ //if the book wasn't rated by the user
			
				unratedBooks.add(book); //adds the users unrated books to the new arraylist
				if (unratedBooks.size() == 5){
					break;	
				}
			}
		}
		return unratedBooks;
	}

	public void store() throws Exception{
		serializer.push(userIndex);
		serializer.push(User.counter);
		//serializer.push(userName);
		serializer.push(bookIndex);
		serializer.push(Book.counter);
		serializer.write(); 
	}
}