package model;

import com.google.common.base.Objects;
import filelogger.ToJsonString;

/**
 * Rate class for Like book
 * @author JCuddihy
 *
 */

public class Rate implements Comparable<Rate> {

	private Integer bookID, rate;

	/**
	 * Constructor for rate.
	 * @param bookID (Integer)
	 * @param rate (Integer)
	 */
	public Rate(Integer bookID, Integer rate) {
		this.bookID = bookID;
		this.rate = rate;
	}

	/**
	 * Getter for book id.
	 * @return (Integer)
	 */
	public Integer getBookID() {
		return bookID;
	}

	/**
	 * Getter for the rating
	 * @return
	 */
	public Integer getRate() {
		return rate;
	}

	/**
	 * Setter for the rating
	 * @param rate (Integer)
	 */
	public void setRate(Integer rate) {
		if (this.rate == null)
			this.rate = rate;
		else
			this.rate += rate;
	}

	/**
	 * Compare the rate of this class to another classs' rate (Natural Order).
	 * @return (int)
	 */
	@Override
	public int compareTo(Rate arg0) {
		return this.rate.compareTo(arg0.rate);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Rate) {
			final Rate that = (Rate) obj;
			return Objects.equal(this.bookID, that.bookID) && Objects.equal(this.rate, that.rate);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}

}
