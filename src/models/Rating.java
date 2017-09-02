/*
 * Rating Class
 * @author: Ashleigh Godkin
 * 
 * contains constructors and methods associated with ratings
 */




package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class Rating { 
	public Long bookID;
	public Long   userID;
	public int score; 

	public Rating()
	{
	}

	//rating constructor
	public Rating(Long userID, Long movieID, int score){
		this.userID 		= userID; 
		this.bookID        = movieID;
		this.score			= score;
	}

	//returns rating as string
	@Override
	public String toString(){
		return toStringHelper(this).addValue(userID)
				.addValue(bookID) 
				.addValue(score)
				.toString();
	}

	@Override  
	public int hashCode()  	{  
		return Objects.hashCode(this.userID, this.bookID, this.score);  
	}

	//rating object
	@Override
	public boolean equals(final Object obj){
		if (obj instanceof Rating)	{
			final Rating other = (Rating) obj;
			return Objects.equal(userID, other.userID) 
					&& Objects.equal(bookID, other.bookID) 
					&& Objects.equal(score, other.score);
		}
		else{
			return false;
		}
	}
}