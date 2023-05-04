package sait.bms.problemdomain;

public class Paperback extends Book {

	private String pAuthors;
	private int year;
	private char genre;
	

	public Paperback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paperback(long isbn, String callNum, int numAvail, int numTotal, String title,String pAuthors, int year, char genre) {
		super(isbn, callNum, numAvail, numTotal, title); 
		this.pAuthors = pAuthors;
		this.year = year;
		this.genre = genre;
	}
	
	public String getpAuthors() {
	return pAuthors;
	}

	public void setpAuthors(String pAuthors) {
		this.pAuthors = pAuthors;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public char getGenre() {
		return genre;
	}

	public void setGenre(char genre) {
		this.genre = genre;
	}

	
	public String print2File() {
		//long isbn, double callNum, int numAvail, int numTotal, String title, int year, char genre
		String output = ("" + getIsbn() + ";" + getCallNum() + ";" + getNumAvail() + ";" + getNumTotal() + ";" + getTitle() + ";" + getpAuthors()+ ";" + getYear() + ";" + getGenre());
		return output;
	}
	
	@Override
	public String toString() {
		
		return "ISBN: " +getIsbn()       + "\n" +
		"Call Number: " + getCallNum()    + "\n" +
		"Available: " + getNumAvail()    + "\n" +
		"Total: " + getNumTotal()    + "\n" +			
		"Title: " + getTitle()        + "\n" +
		"Authors: " + getpAuthors()        + "\n" +
		"Year: " +  getYear()        + "\n" +
		"Genre: " + getFullGenre()    + "\n";
		}
	
	private String getFullGenre(){
		String fullGenre = "";
		switch(genre){
			case 'A':{
				fullGenre = "Adventure"; 
				return fullGenre;
				}
			case 'D':{
				fullGenre = "Drama" ;
				return fullGenre;
				}
			case 'E':{
				fullGenre = "Education"; 
				return fullGenre;
				}
			case 'C':{fullGenre = "Classic"; 
				return fullGenre;
				}
			case 'F':{fullGenre = "Fantasy"; 
				return fullGenre;
				}
			case 'S':{fullGenre = "Science Fiction"; 
				return fullGenre;
				}
			default: 
				return "Not specified";
		
	}
}
}