package controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.RecommenderAPI;
import models.Book;
import models.Rating;
import models.User;

import static models.DataTest.users;
import static models.DataTest.ratings;
import static models.DataTest.books;

public class RecommenderAPITest
{
  private RecommenderAPI recommender;

  @Before
  public void setup()
  {
	  recommender = new RecommenderAPI();
    for (User user : users)
    {
    	recommender.addUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
    }
  }

  @After
  public void tearDown()
  {
	  recommender = null;
  }

  @Test
  public void testUser()
  {
    assertEquals (users.length, recommender.getUsers().size());
    recommender.addUser("Ashleigh", "Godkin", 21, "female" , "student");
    assertEquals (users.length+1, recommender.getUsers().size());
    assertEquals (users[0], recommender.getUser(users[0].userID));
  }  

  @Test
  public void testUsers()
  {
    assertEquals (users.length, recommender.getUsers().size());
    for (User user: users)
    {
      User eachUser = recommender.getUser(user.userID);
      assertEquals (user, eachUser);
      assertNotSame(user, eachUser);
    }
  }

  @Test
  public void testDeleteUsers()
  {
    assertEquals (users.length, recommender.getUsers().size());
    User user = recommender.getUser(0l);
    recommender.deleteUser(user.userID);
    assertEquals (users.length-1, recommender.getUsers().size());    
  }  
  
  @Test
  public void testAddRating()
  {
    User user = recommender.getUser(0l);
    Rating rating = recommender.addRating(ratings[0].userID, ratings[0].bookID, ratings[0].score);
    Rating returnedRating = recommender.getRating(rating.userID);
    assertEquals(ratings[0],  returnedRating);
    assertNotSame(ratings[0], returnedRating);
  }  

}

