package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import api.LikeBookAPI;
import api.SerializerAPI;
import edu.princeton.cs.introcs.In;
import filelogger.Serializer;
import model.Book;
import model.Rate;
import model.User;

/**
 * Like book class implements the Like book interface in the api package.
 * 
 * @author JCuddihy
 * @see LikeBookAPI
 */
public class LikeBook implements LikeBookAPI {

	private Map<Integer, User> users;
	private Map<Integer, Book> books;
	private Integer bookID, userID;
	private SerializerAPI serializer;
	private List<Rate> ratings;

	/**
	 * Constructor.
	 */
	public LikeBook(String file) {
		this.bookID = 1;
		this.userID = 1;
		users = new HashMap<Integer, User>();
		books = new HashMap<Integer, Book>();
		serializer = new Serializer(file);
		ratings = new ArrayList<Rate>();
	}

	/**
	 * Loads users, books, and ratings from either one xml file or four dat
	 * files.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Override
	public void prime() throws ClassNotFoundException, FileNotFoundException, IOException {
		if (serializer.isFile()) {
			load();
		} else {
			init("Data//users.dat");
			init("Data//books.dat");
			init("Data//ratings.dat");
			init("Data//occupation.dat");

		}
	}

	@Override
	public void addUser(String firstName, String lastName, Integer age, char gender, String occupation) {
		users.put(userID, new User(userID++, firstName, lastName, age, gender, occupation));
	}

	@Override
	public void addBook(String title, String year, String url) {
		books.put(bookID, new Book(bookID++, title, url, year));
	}

	@Override
	public void addRate(Integer userID, Integer bookID, Integer rate) throws IllegalArgumentException {

		// rate can only be between -5 and 5 inclusive.
		if (rate < -5 || rate > 5)
			throw new IllegalArgumentException("Not a valid range! Rate can only be between -5 and 5 inclusive.");

		// add the book id and rating to the user
		users.get(userID).addBook(bookID, rate);

		// Sort the ratings by book id (Natural order).
		Comparator<Rate> comparator = new Comparator<Rate>() {

			@Override
			public int compare(Rate arg0, Rate arg1) {
				return arg0.getBookID().compareTo(arg1.getBookID());
			}
		};

		// sort the ratings by comparator.
		Collections.sort(ratings, comparator);

		// check if the book has already been rated by one or more users.
		int position = Collections.binarySearch(ratings, new Rate(bookID, rate), comparator);

		// if the movie has been rated by others user, then add the score to
		// its' existing rating.
		if (position >= 0)
			ratings.get(position).setRate(rate);
		else
			// else create a new rate class for the movie.
			ratings.add(new Rate(bookID, rate));

	}

	@Override
	public void removeUser(Integer userID) {
		users.remove(userID);
	}

	@Override
	public String getBookDetails(Integer bookID) {
		return books.get(bookID).toString();
	}

	@Override
	public Map<Integer, Integer> getUserRatings(Integer userID) {
		return getUser(userID).getBooks();

	}

	@Override
	public User getUser(Integer userID) {
		return users.get(userID);
	}

	@Override
	public List<Rate> getTopTenBooks() {

		// sort the ratings by it comparable interface.
		Collections.sort(ratings);

		// reverse the ratings list
		Collections.reverse(ratings);

		// if the ratings list is greater than 10
		// return the first 10 ratings.
		// else
		// return all the ratings.
		return (ratings.size() > 10) ? ratings.subList(0, 10) : ratings;

	}

	@Override
	public List<Book> getUserRecommendations(Integer userID) {

		// recommended books the user has not seen
		List<Book> recommendations = new ArrayList<Book>();

		// movie id's for the recommended books.
		TreeSet<Integer> recommendedBookIDs = new TreeSet<Integer>();

		// user to get the recommended books for.
		User tempUser = getUser(userID);

		// loop through all other users to get books that have not been rated
		// by the tempUser
		for (int x = 1; x < userID; x++)
			if (x == userID)
				continue;
			else
				tempUser.setRecommendations(getUser(x));

		recommendedBookIDs = tempUser.getRecommendations();

		// get the book objects from the recommended book id's.
		for (Integer bookID : recommendedBookIDs)
			recommendations.add(getBook(bookID));

		// return recommended films.
		return recommendations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load() throws ClassNotFoundException, FileNotFoundException, IOException {

		// read the file in
		serializer.read();

		// pop the objects off the stack
		userID = (Integer) serializer.pop();
		bookID = (Integer) serializer.pop();
		ratings = (List<Rate>) serializer.pop();
		users = (Map<Integer, User>) serializer.pop();
		books = (Map<Integer, Book>) serializer.pop();

	}

	@Override
	public void write() throws ClassNotFoundException, IOException {

		// push the objects onto the stack.
		serializer.push(books);
		serializer.push(users);
		serializer.push(ratings);
		serializer.push(bookID);
		serializer.push(userID);

		// write the stack to xml.
		serializer.write();
	}

	public void init(String path) {
		File usersFile = new File(path);

		In inUsers = new In(usersFile);

		// each field is separated(delimited) by a '|'
		String delims = "[|]";

		while (!inUsers.isEmpty()) {

			// get user and rating from data source
			String userDetails = inUsers.readLine();

			// parse user details string
			String[] userTokens = userDetails.split(delims);

			// userTokens length is 4, then it's a rating class,
			// else if its length is 7 it's a user class,
			// else its a book class if its length is 23
			if (userTokens.length == 4)
				addRate(Integer.parseInt(userTokens[0]), Integer.parseInt(userTokens[1]),
						Integer.parseInt(userTokens[2]));
			else if (userTokens.length == 7)
				addUser(userTokens[1], userTokens[2], Integer.parseInt(userTokens[3]), userTokens[4].charAt(0),
						userTokens[5]);
			else if (userTokens.length == 23)
				addBook(userTokens[1], userTokens[2], userTokens[3]);
		}

	}

	@Override
	public Book getBook(Integer bookID) {
		return books.get(bookID);
	}

	@Override
	public Integer getBookId() {
		return bookID;
	}

	@Override
	public Integer getUserId() {
		return userID;
	}

}