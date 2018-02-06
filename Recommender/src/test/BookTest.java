package test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import model.Book;

public class BookTest{

    /**
     * Test the Constructor.
     */
    @Test
    public void tetConstructor(){
        Book book = null;
        book = new Book(1, "Anna Karenina", "2004","https://en.wikipedia.org/wiki/Anna_Karenina" 
        assertTrue(book!= null);
    }

    /**
	 * Test The Getters and Setters for the Book class.
	 */
	@Test
	public void testGettersAndSetters() {
		Book book = new Book(1, "Anna Karenina", "2004", "https://en.wikipedia.org/wiki/Anna_Karenina");

		book.setTitle("The Lord of the Rings");
		book.setYear("1968");
		book.setUrl("www.theonering.net");

		assertTrue(book.getId() == 1);
		assertTrue(book.getTitle().equals("The Lord of the Rings"));
		assertTrue(book.getYear().equals("1968"));
		assertTrue(book.getUrl().equals("www.theonering.net"));
    }
    
    /**
	 * Test the equals method in the Book Class
	 */
	@Test
	public void testEquals() {
		Book book1 = new Book(1, "Anna Karenina", "2004", "https://en.wikipedia.org/wiki/Anna_Karenina");
		Book book2 = new Book(1, "The Lord of the Rings", "1968", "www.theonering.net");
		assertTrue(book1.equals(book1) && !book1.equals(book2));
	}
}