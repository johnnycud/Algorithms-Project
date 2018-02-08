package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

/**
 * Command Line Interface for Like Book.<br>
 * Uses asg.cliche-110413.jar<br>
 * instead of switch inside of do while loop.
 * 
 * @author JCuddihy
 *
 */

public class Driver {

	private LikeBook likeBook;

	/**
	 * Constructor for Main.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Driver() throws ClassNotFoundException, FileNotFoundException, IOException {
		LikeBook book = new LikeBook("Data//data.xml");
		book.prime();

	}

	/**
	 * CLI Method to add user.
	 * 
	 * @param firstName
	 *            (String)
	 * @param secondName
	 *            (String)
	 * @param age
	 *            (Integer)
	 * @param gender
	 *            (char)
	 * @param occupation
	 *            (String)
	 */
	@Command(description = "Add a User")
	public void addUser(@Param(name = "First name") String firstName, @Param(name = "Second name") String secondName,
			@Param(name = "Age") Integer age, @Param(name = "Gender") String gender,
			@Param(name = "occupation") String occupation) {
		likeBook.addUser(firstName, secondName, age, gender.charAt(0), occupation);
	}

	/**
	 * CLI Method to remove user.
	 * 
	 * @param userID
	 *            (Integer)
	 */
	@Command(description = "Remove a User")
	public void removeUser(@Param(name = "User ID") Integer userID) {
		likeBook.removeUser(userID);
	}

	/**
	 * CLI Method to add a movie rating.
	 * 
	 * @param userID
	 * @param movieID
	 * @param rate
	 */
	@Command(description = "Add a Rate")
	public void addRate(@Param(name = "User ID") Integer userID, @Param(name = "Book ID") Integer bookID,
			@Param(name = "User rating") Integer rate) {
		likeBook.addRate(userID, bookID, rate);
	}

	/**
	 * CLI Method to add movie.
	 * 
	 * @param title
	 *            (String)
	 * @param year
	 *            (String)
	 * @param url
	 *            (String)
	 */
	@Command(description = "Add a Book")
	public void addBook(@Param(name = "Book Name") String title, @Param(name = "Published Year") String year,
			@Param(name = "Book Website") String url) {
		likeBook.addBook(title, year, url);
	}

	/**
	 * CLI method to get movie information.
	 * 
	 * @param movieID
	 *            (Integer)
	 */
	@Command(description = "Get Book info")
	public void getBook(@Param(name = "Book ID") Integer bookID) {
		System.out.println(likeBook.getBookDetails(bookID));
	}

	/**
	 * CLI Method to get a user ratings.
	 * 
	 * @param userID
	 *            (Integer)
	 */
	@Command(description = "Get User Ratings")
	public void getUserRatings(@Param(name = "User ID") Integer userID) {
		System.out.println(likeBook.getUserRatings(userID));
	}

	/**
	 * CLI Method to get user recommended book.
	 * 
	 * @param userID
	 *            (Integer).
	 */
	@Command(description = "Get User Recommendations")
	public void getUserRecommendations(@Param(name = "User ID") Integer userID) {
		System.out.println(likeBook.getUserRecommendations(userID));
	}

	/**
	 * CLI Method to get user information.
	 * 
	 * @param userID
	 *            (Integer)
	 */
	@Command(description = "Get User information")
	public void getUser(@Param(name = "User ID") Integer userID) {
		System.out.println(likeBook.getUser(userID));
	}

	/**
	 * CLI method to get the top ten movies.
	 */
	@Command(description = "Get Top Ten Book")
	public void getTopTenBooks() {
		System.out.println(likeBook.getTopTenBooks());
	}

	/**
	 * CLI method to load the xml life.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Command(description = "Load file")
	public void loadFile() throws ClassNotFoundException, FileNotFoundException, IOException {
		likeBook.load();
	}

	/**
	 * CLI method write the xml file.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Command(description = "Write file")
	public void writeFile() throws ClassNotFoundException, IOException {
		likeBook.write();
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			// new main object.
			Driver driver = new Driver();

			// create a new shell object.
			Shell shell = ShellFactory.createConsoleShell(">",
					"Welcome to Like Book-console " + "- ?help for instructions", driver);

			// loop the shell menu.
			shell.commandLoop();

			// write the file to xml.
			driver.likeBook.write();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Sorry there was an error writing the xml file.");
		}

	}
}