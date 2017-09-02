/*
 * User Class
 * @author: Ashleigh Godkin
 * 
 * contains constructors and methods associated with user class
 */



package models;

import com.google.common.base.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

public class User {
	public String firstName;
	public String lastName;
	public int age;
	public String gender; 
	public String occupation;
	public Long userID; 
	public static Long   counter = 0l;

	public List<Rating> ratings = new ArrayList<>();

	//user constructor
	public User(String firstName, String lastName, int age, String gender, String occupation){
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.userID        = counter++;
	}


	//returns user as string
	@Override
	public String toString(){
		return toStringHelper(this).addValue(userID)
				.addValue(firstName)
				.addValue(lastName)
				.addValue(age)
				.addValue(gender)
				.addValue(occupation) 
				.toString();
	}

	@Override  
	public int hashCode()  {  
		return Objects.hashCode(this.userID, this.lastName, this.firstName, this.age, this.gender, this.occupation);  
	}  

	//user object
	@Override
	public boolean equals(final Object obj){
		if (obj instanceof User)
		{
			final User other = (User) obj;
			return Objects.equal(firstName, other.firstName) 
					&& Objects.equal(lastName,  other.lastName)
					&& Objects.equal(age,  other.age)
					&&  Objects.equal(gender,  other.gender)
					&& Objects.equal(occupation,     other.occupation);
		}
		else{
			return false;
		}
	}
}
