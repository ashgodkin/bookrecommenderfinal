package models;

public class DataTest
{
	public static User[] users =
		{
				new User ("Josephine", "Kavanagh", 21, "female", "sudent"),
				new User ("Mary",  "Smith", 40, "female", "teacher"),
				new User ("John",  "Doyle", 30, "male", "programmer"),
				new User ("Bob","Simpson", 19, "female", "other")
		};

	public static Book[] books =
		{
				new Book ("Cursed Child", 2016, "J.K. Rowling"),
				new Book ("Fantastic Beasts",  2017, "J.K. Rowling"),
				new Book ("Harry Potter and the Philosophers Stone",   1999,"J.K. Rowling"),

		};

	public static Rating[] ratings =
		{
				new Rating (0l, 0l, 0),
				new Rating (1l, 1l, -5),
				new Rating (2l, 2l, 2)
		};

}