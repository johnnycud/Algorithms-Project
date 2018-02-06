package model;

import com.google.common.base.Objects;
import fileLogger.ToJsonString;

/**
 * Book Class for LikeBook.
 * @author JCUDDIHY
 *
 */

public class Book {
	
	private final Integer id;
	private String title;
	private String url;
	private String year;

	/**
	 * Constructor for Book class
	 * @param id (Integer)
	 * @param title (String)
	 * @param url (String)
	 * @param year (String)
	 */
	public Book(Integer id, String title, String url, String year) {
		this.id = id;
		this.url = url;
		this.title = title;
		this.year = year;
	}

	/**
	 * Getter for url
	 * @return (String)
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for url
	 * @param url (String)
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter for title of book
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title of book
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter for year published.
	 * @return (String)
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Setter for year published.
	 * @param year (String)
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Getter for book id.
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Book) {
			final Book that = (Book) obj;
			
			return Objects.equal(this.id, that.id) 
					&& Objects.equal(this.title, that.title)
					&& Objects.equal(this.url, that.url)
					&& Objects.equal(this.year, that.year);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}

	
}