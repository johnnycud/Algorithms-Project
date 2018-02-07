package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.LikeBook;
import model.Book;
import model.Rate;
import model.User;

public class LikeBookTest {
	
	private LikeBook likeBook;
	

	@Before
	public void setup(){
		likeBook = new LikeBook("./Data/test.xml");
	}
	


	/**
	 * Test that a new book was created
	 */
	@Test
	public void testAddAndGetBook() {
		likeBook.addBook("The Room", "www.theroom.com", "2000" );
		assertTrue(likeBook.getBook(1).equals(new Book(1, "The Room", "www.theroom.com", "2000")));
	}

	/**
	 * Test that a new user was added then removed.
	 */
	@Test
	public void testAddAndRemoveUser() {
		likeBook.addUser("Joe", "Bloggs", 18, 'M', "Student");
		assertTrue(likeBook.getUser(1).equals(new User(1, "Joe", "Bloggs", 18, 'M', "Student")));
		likeBook.removeUser(1);
		assertTrue(likeBook.getUser(1) == null);
	}

	/**
	 * Test that a rate cannot be out of bounds
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddRateException() {
		likeBook.addRate(1, 1, -6);
	}

	/**
	 * Test that a valid rate can be added to both a new movie and an existing
	 * movie. Test that the top ten method returns the highest to lowest films
	 */
	@Test
	public void testAddRate() {

		// add dummy data
		for (int x = 1; x <= 10; x++) {
			likeBook.addUser("Joe", "Bloggs", 22, 'M', "Student");
			likeBook.addBook("HardBook " + x, "2002", "www.website.com");
		}

		// rating
		int r = -5;

		// for x = 1 < - > 10 inclusive and a rating of r = -5 < - > 5
		for (int x = 1; x <= 10; x++, r++)
			likeBook.addRate(x, x, r);

		// add a new rating to an existing movie rating
		likeBook.addRate(10, 10, 4);

		// get the top ten movies
		List<Rate> topTen = likeBook.getTopTenBooks();

		// set the rating to -5
		r = -5;

		// for x = 9 < - > 0
		// if x == 0 then the rating should be equal to 8
		// else the rating should be equal to r
		for (int x = 9; x >= 0; x--, r++)
			if (x == 0)
				assertTrue(topTen.get(x).getRate().equals(8));
			else
				assertTrue(topTen.get(x).getRate().equals(r));

	}

	/**
	 * Test the get book details : String
	 */
	@Test
	public void testGetBookDetails() {
		likeBook.addBook("The Room", "2000", "www.theroom.com");
		String notPrimeMovie = likeBook.getBookDetails(1);
		String newMovie = new Book(1, "The Room", "www.theroom.com", "2000").toString();
		assertTrue(notPrimeMovie.equals(newMovie));
	}

	/**
	 * Test the get user ratings.
	 */
	@Test
	public void testGetUserRatings() {

		likeBook.addUser("Joe", "Bloggs", 18, 'M', "Student");
		likeBook.addBook("The Room", "2000", "www.theroom.com");
		likeBook.addRate(1, 1, 5);

		assertTrue(likeBook.getUserRatings(1).size() == 1 && likeBook.getUserRatings(1).get(1).equals(5));
	}

	/**
	 * Test the get user recommendations
	 */
	@Test
	public void testGetUserRecommendations() {
		addValues();

		List<Book> movieRecommendations = likeBook.getUserRecommendations(1);

		assertTrue(movieRecommendations.size() == 2);
		assertTrue(movieRecommendations.get(0).equals(likeBook.getBook(4)));
		assertTrue(movieRecommendations.get(1).equals(likeBook.getBook(6)));
	}

	/**
	 * Test the input and output by creating a second like movie object,<br>
	 * Then writing the objects from the first like movie object to an xml file.
	 * <br>
	 * Then in the second like movie object read in the file previously written.
	 * <br>
	 * Then compare the user, ratings, movies, userIDs, and movieIDs match.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testIO() throws ClassNotFoundException, IOException {
		addValues();
		LikeBook one = new LikeBook("./Data/test.xml");
		likeBook.write();
		one.load();

		assertTrue(one.getBookId().equals(likeBook.getBookId()));
		assertTrue(one.getUserId().equals(likeBook.getUserId()));

		for (Integer i = 1; i < likeBook.getUserId(); i++)
			assertTrue(likeBook.getUser(i).equals(one.getUser(i)));

		for (Integer i = 1; i < likeBook.getBookId(); i++)
			assertTrue(likeBook.getBook(i).equals(one.getBook(i)));

	}

	/**
	 * Assign values to the main like book objects' users, books, and ratings
	 */
	private void addValues() {
		likeBook.addUser("Paddy", "Paddy", 19, 'M', "Student");
		likeBook.addUser("Clodagh", "Clodagh", 20, 'F', "Student");
		likeBook.addUser("Paddy", "Paddy", 21, 'M', "Student");
		likeBook.addUser("Ronan", "Ronan", 22, 'M', "Student");

		for (int x = 0; x < 6; x++)
			likeBook.addBook("Book " + x,"www.website.com",  "200" + x);

		likeBook.addRate(1, 1, 5);
		likeBook.addRate(1, 2, 5);
		likeBook.addRate(1, 3, -5);

		likeBook.addRate(2, 1, 1);
		likeBook.addRate(2, 2, 5);
		likeBook.addRate(2, 3, -3);
		likeBook.addRate(2, 4, 5);

		likeBook.addRate(3, 1, 5);
		likeBook.addRate(3, 2, -3);
		likeBook.addRate(3, 3, 5);
		likeBook.addRate(3, 5, 5);

		likeBook.addRate(4, 1, 1);
		likeBook.addRate(4, 2, 3);
		likeBook.addRate(4, 3, 0);
		likeBook.addRate(4, 6, 5);
	}

	/**
	 * Remove the test file.
	 */
	@After
	public void tearDown() {
		File f = new File("./Data/test.xml");
		if (f.exists())
			f.delete();

	}
}