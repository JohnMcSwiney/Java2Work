package sait.bms.problemdomain;

public class CookBook extends Book {
	
	private String publisher;
	private char diet;

	public CookBook(long isbn, String callNum, int numAvail, int numTotal, String title, String publisher, char diet) {
		super(isbn, callNum, numAvail, numTotal, title);
		this.publisher = publisher;
		this.diet = diet;
	}

	//public CookBook(double isbn, double callNum, int numAvail, int numTotal, String title, String publisher, char diet) {

		

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public char getDiet() {
		return diet;
	}

	public void setDiet(char diet) {
		this.diet = diet;
	}


	public String print2File() {
		//long isbn, double callNum, int numAvail, int numTotal, String title, String publisher, char diet
		String output = ("" + getIsbn() + ";" + getCallNum() + ";" + getNumAvail() + ";" + getNumTotal() + ";" + getTitle() + ";" + getPublisher() + ";" + getDiet());
		return output;
	}
	
	@Override
	public String toString() {

	return "ISBN: " +getIsbn()       + "\n" +
	"Call Number: " + getCallNum()    + "\n" +
	"Available: " + getNumAvail()    + "\n" +
	"Total: " + getNumTotal()    + "\n" +
	"Title: " + getTitle()        + "\n" +
	"Publisher: " + getPublisher()    + "\n" +
	"Diet: " + getFullDiet()    + "\n";
	}

	private String getFullDiet(){
		String fullDiet = "";
		switch(diet){
			case 'D':{
				fullDiet = "Diabetic"; 
				return fullDiet;
				}
			case 'V':{
				fullDiet = "Vegetarian" ;
				return fullDiet;
				}
			case 'G':{
				fullDiet = "Gluten - Free"; 
				return fullDiet;
				}
			case 'I':{fullDiet = "International"; 
				return fullDiet;
				}
			case 'N':{fullDiet = "None"; 
				return fullDiet;
				}
			default: 
				return "Not specified";
			
		}
	}
	
} // end
