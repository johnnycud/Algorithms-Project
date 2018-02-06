package test;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import model.User;

public class UserTest {

	private Integer id;
	private Map<Integer, User> users;

	/**
	 * Set up the users map.
	 */
	@Before
	public void setup() {
		id = 1;
		users = new HashMap<Integer, User>();
		users.put(id, new User(id++, "Paddy", "Paddy", 19, 'M', "Student"));
		users.put(id, new User(id++, "Clodagh", "Clodagh", 20, 'F', "Student"));
		users.put(id, new User(id++, "Paddy", "Paddy", 21, 'M', "Student"));
		users.put(id, new User(id++, "Ronan", "Ronan", 21, 'M', "Student"));

		users.get(1).addBook(1, 5);
		users.get(1).addBook(2, 5);
		users.get(1).addBook(3, -5);

		users.get(2).addBook(1, 1);
		users.get(2).addBook(2, 5);
		users.get(2).addBook(3, -3);
		users.get(2).addBook(4, 5);

		users.get(3).addBook(1, 5);
		users.get(3).addBook(2, -3);
		users.get(3).addBook(3, 5);
		users.get(3).addBook(5, 5);

		users.get(4).addBook(1, 1);
		users.get(4).addBook(2, 3);
		users.get(4).addBook(3, 0);
		users.get(4).addBook(6, 5);

	}

	/**
	 * Test the constructor for the User class.
	 */
	@Test
	public void testConstructor() {
		User u = null;
		u = new User(1, "Joe", "Bloggs", 18, 'M', "Student");
		assertTrue(u != null);
	}

	/**
	 * Test the basic accessor and mutators for the User class.
	 */
	@Test
	public void testSettersAndGetters() {
		User u = null;
		u = new User(1, "Joe", "Bloggs", 18, 'M', "Student");
		u.setAge(19);
		u.setFirstName("Jane");
		u.setLastName("Blogg");
		u.setGender('F');
		u.setOccupation("Unemployed");

		assertTrue(u.getAge() == 19);
		assertTrue(u.getOccupation().equals("Unemployed"));
		assertTrue(u.getGender() == 'F');
		assertTrue(u.getLastName().equals("Blogg"));
		assertTrue(u.getFirstName().equals("Jane"));
		assertTrue(u.getId() == 1);
	}

	/**
	 * Test the accessor for rated movie
	 */
	@Test
	public void testGetMovies() {

		for (int x = 1; x <= users.size(); x++)
			if (x > 1)
				assertTrue(users.get(x).getBooks().size() == 4);
			else
				assertTrue(users.get(x).getBooks().size() == 3);
	}

	/**
	 * Test the equals method in the User class.<br>
	 * That for the same user object they are equal <br>
	 * and if not the same object they are not equal.
	 */
	@Test
	public void testEquals() {
		// for each user in users test that they equal themselves and are not
		// equal to a different user
		for (int x = 1; x < id - 1; x++)
			if (x == (id - 1))
				assertTrue(users.get(x).equals(users.get(x)) && (!users.get(x).equals(users.get(1))));
			else
				assertTrue(users.get(x).equals(users.get(x)) && (!users.get(x).equals(users.get(x + 1))));

	}

	/**
	 * 
	 */
	@Test
	public void testRecommender() {

		for (int x = 2; x <= users.size(); x++) {

			users.get(1).setRecommendations(users.get(x));
			TreeSet<Integer> temp = users.get(1).getRecommendations();

			if (x == 4)
				assertTrue(temp.size() == 2 && temp.last() == 6);
			else
				assertTrue(temp.size() == 1 && temp.first() == 4);
		}
	}

}