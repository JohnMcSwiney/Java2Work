package sait.bms.problemdomain;

public class KidsBook extends Book {
	private String authors;
	private char fmat;


	public KidsBook(long isbn, String callNum, int numAvail, int numTotal, String title, String authors, char fmat) {
		super(isbn, callNum, numAvail, numTotal, title);
		this.authors = authors;
		this.fmat = fmat;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public char getFmat() {
		return fmat;
	}

	public void setFmat(char fmat) {
		this.fmat = fmat;
	}

	
	public String print2File() {
//		return "KidsBook [authors=" + authors + ", fmat=" + fmat + ", getAuthors()=" + getAuthors() + ", getFmat()="
//				+ getFmat() + ", getIsbn()=" + getIsbn() + ", getCallNum()=" + getCallNum() + ", getNumAvail()="
//				+ getNumAvail() + ", getNumTotal()=" + getNumTotal() + ", getTitle()=" + getTitle() + ", getClass()="
//				+ getClass() + "]";
		
		String output = ("" + getIsbn() + ";" + getCallNum() + ";" + getNumAvail() + ";" + getNumTotal() + ";" + getTitle() + ";" + getAuthors() + ";" + getFmat());
		return output;
	}
	
	private String getFullFmat() {
		String fullFmat = "";
		switch(fmat){
			case 'P':{
				fullFmat = "Picture Book"; 
				return fullFmat;
				}
			case 'E':{
				fullFmat = "Early Readers" ;
				return fullFmat;
				}
			case 'C':{
				fullFmat = "Chapter Book"; 
				return fullFmat;
				}
			default: 
				return "Not specified";
		}
	}
	//long isbn, double callNum, int numAvail, int numTotal, String title, String authors, char fmat)
	@Override
	public String toString() {

		return "ISBN: " +getIsbn()       + "\n" +
		"Call Number: " + getCallNum()    + "\n" +
		"Available: " + getNumAvail()    + "\n" +
		"Total: " + getNumTotal()    + "\n" +
		"Title: " + getTitle()        + "\n" +
		"Authors: " + getAuthors()    + "\n" +
		"Format: " + getFullFmat()    + "\n";
		}


	
	
	
	

}
