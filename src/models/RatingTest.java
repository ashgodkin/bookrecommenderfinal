package models;

import static models.DataTest.books;
import static models.DataTest.users;
import static org.junit.Assert.*;
import org.junit.Test;

public class RatingTest
{ 
	Rating test = new Rating (0l,  0l, 2);

	@Test
	public void testCreate()
	{
		assertEquals ("Ashleigh",                		users[0].firstName);
		assertEquals ("Cursed Child",           books[0].title);
		assertEquals (2,              			    test.score);       
	}

	@Test
	public void testToString()
	{
		assertEquals ("Rating{" + test.userID + ", 0, 2}", test.toString());
	}
}