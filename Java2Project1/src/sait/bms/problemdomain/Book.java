package sait.bms.problemdomain;

/**
 * Class Description for the Book Class, this is used for creating Book Objects
 * 
 * @author John McSwiney
 * @author Angad Cheema
 * @author Jack Eyre
 * 
 */
public class Book {

	private long isbn;
	private String callNum;
	private int numAvail;
	private int numTotal;
	private String title = "";
	

	/**
	 * Creates a Book object with inputed values.
	 * 
	 * ISBN, Call Number, Number Available, Total, Title
	 * 
	 * @param isbn     - Number in system
	 * @param callNum  - Number used to call
	 * @param numAvail - Number available to rent
	 * @param numTotal - Total number of this book in system
	 * @param title    - Title of book
	 */
	public Book() {
	}
	
	
	public Book(long isbn, String callNum, int numAvail, int numTotal, String title) {
		super();
		this.isbn = isbn;
		this.callNum = callNum;
		this.numAvail = numAvail;
		this.numTotal = numTotal;
		this.title = title;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getCallNum() {
		return callNum;
	}

	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}

	public int getNumAvail() {
		return numAvail;
	}

	public void setNumAvail(int numAvail) {
		this.numAvail = numAvail;
	}

	public int getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public boolean getAvailable() {
		if(numAvail != 0) {
			//String temp = " is Available";
			return true;
		}
		else {
			//String temp = " is not Available";
			return false;
		}
		
	}


	public void checkoutBook() {
		System.out.println("The book "+ '"' + title + '"' + "has been checked out.\r\n"
				+ "It can be located using a call number: " + callNum + "\r\n");
		numAvail--;

	}
	
	


	public String print2File() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
