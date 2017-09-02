/*
 * Book Class
 * @author: Ashleigh Godkin
 * 
 * contains constructors and methods associated
 * with the book including ratings
 */


package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class Book implements Comparable <Book>{
	public static Long   counter = 0l;

	public Long  bookID;
	public String title;
	public int year;
	public String author; 

	public List<Rating> ratings = new ArrayList<Rating>();//must make reference back to rating for addRating method

	//constructor
	public Book (String title, int year, String author){
		this.bookID        = counter++;
		this.title  = title;
		this.year = year;
		this.author = author;
	}

	//calculates the average rating for a book as a double
	public double calculateAverageRating(){
		if (ratings.isEmpty()){
			return 0;      //if no rating exits return 0
		}
		else {
			double averageRating = 0;
			for (Rating rating : ratings){ 
				averageRating += rating.score;
			}
			return averageRating%ratings.size();
		}
	}

	//book as a string
	@Override
	public String toString()	{
		return toStringHelper(this).addValue(bookID)
				.addValue(title)
				.addValue(year)   
				.addValue(author)
				.toString();
	}

	@Override  
	public int hashCode()  {  
		return Objects.hashCode(this.bookID, this.title, this.year, this.author);  
	} 

	//book object
	@Override
	public boolean equals(final Object obj)	{
		if (obj instanceof Book)
		{
			final Book other = (Book) obj;
			return Objects.equal(bookID, other.bookID) 
					&& Objects.equal(title, other.title)
					&& Objects.equal(year, other.year)
					&& Objects.equal(author, other.author);
		}
		else	{
			return false;
		}
	}

	public int compareTo(Book other){
		return Double.compare(this.calculateAverageRating(), other.calculateAverageRating());
	}
}