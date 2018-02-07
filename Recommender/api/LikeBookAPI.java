package api;

/**
 * Like Book API.
 * @author JCuddihy
 * @see LikeBook
 * 
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import model.Book;
import model.Rate;
import model.User;

public interface LikeBookAPI {

	/**
	 * Add a new user.
	 * 
	 * @param firstName
	 *            (String) new users' first name.
	 * @param lastName
	 *            (String) new users' last name.
	 * @param age
	 *            (Integer) new users' age.
	 * @param gender
	 *            (char) new users' gender.
	 * @param occupation
	 *            (String) new users' occupation.
	 */
	public void addUser(String firstName, String lastName, Integer age, char gender, String occupation);

	/**
	 * Remove a user.
	 * 
	 * @param userID
	 *            (Integer) users' id.
	 */
	public void removeUser(Integer userID);

	/**
	 * Add a book rating.
	 * 
	 * @param userID
	 *            (Integer) user id who added the rating.
	 * @param bookID
	 *            (Integer) the book id being rating.
	 * @param rating
	 *            (Integer) the rating.
	 */
	public void addRate(Integer userID, Integer bookID, Integer rating);

	/**
	 * Add a Book
	 * 
	 * @param title
	 *            (String) title of the book.
	 * @param year
	 *            (String) year published.
	 * @param url
	 *            (String) the url to the website.
	 */
	public void addBook(String title, String year, String url);

	/**
	 * Get book details (toString).
	 * 
	 * @param bookID
	 *            (Integer) book id.
	 * @return (String) Book objects' toString (in JSON format).
	 */
	public String getBookDetails(Integer bookID);

	/**
	 * Get the book of a given Integer
	 * 
	 * @param bookID
	 *            (Integer) id of book being searched.
	 * @return (Book) Book object with matching id.
	 */
	public Book getBook(Integer bookID);

	/**
	 * Get a given id for a user, get their ratings for books.
	 * 
	 * @param userID
	 *            (Integer) user id.
	 * @return (Map<Integer BOOKID, Integer Rate>)
	 */
	public Map<Integer, Integer> getUserRatings(Integer userID);

	/**
	 * For a given user id, get their recommendations.
	 * 
	 * @param userID
	 *            (Integer) user id.
	 * @return (List<Book>) books the given user has not rated but have been
	 *         rated by other users with similar interest.
	 */
	public List<Book> getUserRecommendations(Integer userID);

	/**
	 * Returns the top ten rated books. Highest to lowest.
	 * 
	 * @return (List<Rate>) list of book ids' and there total ratings in JSON
	 *         formatting.
	 */
	public List<Rate> getTopTenBooks();

	/**
	 * Read in the xml file, and store the users, books, ratings in memory.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @see SerializerAPI
	 */
	public void load() throws ClassNotFoundException, FileNotFoundException, IOException;

	/**
	 * Write the users, books, and ratings to xml file.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @see SerializerAPI
	 */
	public void write() throws ClassNotFoundException, IOException;

	/**
	 * Get a user object with a given index.
	 * 
	 * @param userID
	 *            (Integer) user index.
	 * @return (User)
	 */
	public User getUser(Integer userID);

	/**
	 * If the xml file dose not exist, read in the data from the dat files and
	 * parse it into memory.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void prime() throws ClassNotFoundException, FileNotFoundException, IOException;

	/**
	 * Returns the next index of the book count.
	 * 
	 * @return (Integer)
	 */
	public Integer getBookId();

	/**
	 * Returns the next index of the user count.
	 * 
	 * @return (Integer)
	 */
	public Integer getUserId();

}