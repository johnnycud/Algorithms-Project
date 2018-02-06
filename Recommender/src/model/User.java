package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import com.google.common.base.Objects;
import fileLogger.ToJsonString;

/**
 * User class for like book
 * 
 * @author JCuddihy
 *
 */

public class User {

	private final Integer id;
	private String firstName, lastName, occupation;
	private Integer age;
	private char gender;
	private Map<Integer, Integer> booksRated;
	private TreeSet<Integer> keys;
	private TreeSet<Integer> recommendations;

	/**
	 * Constructor for User
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param occupation
	 * @param age
	 * @param gender
	 */
	public User(Integer id, String firstName, String lastName, Integer age, char gender, String occupation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
		this.occupation = occupation;
		booksRated = new HashMap<Integer, Integer>();
		keys = new TreeSet<Integer>();
		recommendations = new TreeSet<Integer>();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof User) {
			final User that = (User) obj;

			return Objects.equal(this.id, that.id) && Objects.equal(this.firstName, that.firstName)
					&& Objects.equal(this.lastName, that.lastName) && Objects.equal(this.occupation, that.occupation)
					&& Objects.equal(this.age, that.age) && this.gender == that.gender
					&& Objects.equal(this.booksRated, that.booksRated) && Objects.equal(this.keys, that.keys)
					&& Objects.equal(this.recommendations, that.recommendations);
		}

		return false;
	}

	/**
	 * Calculates the similarity between two users by multiplying this users'
	 * rating for a book with the second users' rating for the same book
	 * 
	 * @param user
	 *            (User)
	 * @return (int) how similar the two users are.
	 */
	private int calSimilar(User user) {
		int similar = 0;

		for (Integer id : this.keys)
			if (user.keys.contains(id))
				similar += this.booksRated.get(id) * user.booksRated.get(id);

		return similar;
	}

	/**
	 * Set the recommendations for this user.
	 * 
	 * @param user
	 *            (User) other use to be compared to with this user.
	 * @return (int) how similar the two users are.
	 */
	public int setRecommendations(final User user) {
		int similar = calSimilar(user);

		// if similar id greater than zero
		if (similar > 0) {

			// assign the other user keys to a temporary treeset
			TreeSet<Integer> tempOtherKeys = user.keys;

			// remove any key that is in both this keys and temp other keys
			tempOtherKeys.removeAll(this.keys);

			// add all remaining keys to recommendations.
			recommendations.addAll(tempOtherKeys);
		}

		return similar;
	}

	/**
	 * Getter for recommended book keys.
	 * 
	 * @return (TreeSet<Integer>) movie ID,
	 */
	public TreeSet<Integer> getRecommendations() {
		return recommendations;
	}

	/**
	 * Method to add book id and rating.
	 * 
	 * @param bookID
	 *            (Integer) the book id the user rated.
	 * @param rate
	 *            (Integer) the rating the user gave the book.
	 */
	public void addBook(Integer bookID, Integer rate) {
		keys.add(bookID);
		booksRated.put(bookID, rate);
	}

	/**
	 * Getter for books the user has rated
	 * 
	 * @return (Map<BookID as Integer, Rate as Integer>)
	 */
	public Map<Integer, Integer> getBooks() {
		return booksRated;
	}

	/**
	 * Getter for the user id
	 * 
	 * @return (Integer) user id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Getter for the user first name
	 * 
	 * @return (String) firstname.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the user firstname
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the last name
	 * 
	 * @return (String) users' lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the users' lastname
	 * 
	 * @param lastName
	 *            (String) users' lastname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for occupation
	 * 
	 * @return (String)
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * Setter for occupation
	 * 
	 * @param occupation
	 *            (String)
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * Getter for age
	 * 
	 * @return (Integer)
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Setter for age
	 * 
	 * @param age
	 *            (Integer)
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Getter for Gender
	 * 
	 * @return (char)
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Setter for gender
	 * 
	 * @param gender
	 *            (char)
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}

}