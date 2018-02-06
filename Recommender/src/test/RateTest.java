package test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import model.Rate;

public class RateTest {

	/**
	 * Test the Rate constructor.
	 */
	@Test
	public void testConstructor() {
		Rate rate = null;
		rate = new Rate(1, 5);
		assertTrue(rate != null);
	}

	/**
	 * Test the rate accessor and mutator
	 */
	@Test
	public void testGettersAndSetters() {
		Rate rate = new Rate(1, 5);
		rate.setRate(4);
		assertTrue(rate.getRate() == 9);
		assertTrue(rate.getBookID() == 1);
	}

	/**
	 * Test the comparable method that it ranks the books lowest to highest
	 * (Natural Ordering).
	 */
	@Test
	public void testComparable() {
		List<Rate> books = new ArrayList<Rate>();

		books.add(new Rate(1, 5));
		books.add(new Rate(2, 4));
		books.add(new Rate(4, 2));
		books.add(new Rate(5, 1));
		books.add(new Rate(3, 3));

		Collections.sort(books);

		for (int x = 0; x < books.size(); x++)
			assertTrue(books.get(x).getRate() == (x + 1));
	}

	/**
	 * Test the rate Equals method.
	 */
	@Test
	public void testEquals() {
		Rate rate1 = new Rate(1, 5), rate2 = new Rate(2, 5);
		assertTrue(!rate1.equals(rate2) && rate1.equals(rate1));
	}

}