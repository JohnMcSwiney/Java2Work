package sait.bms.problemdomain;

public class Periodical extends Book {

	private char frequency;

	public Periodical() {
		super();
	}

	public Periodical(long isbn, String callNum, int numAvail, int numTotal, String title, char frequency) {
		super(isbn, callNum, numAvail, numTotal, title);
		this.frequency = frequency;
	}

	public char getFrequency() {
		return frequency;
	}

	public void setFrequency(char frequency) {
		this.frequency = frequency;
	}

	
	public String print2File() {
		//long isbn, double callNum, int numAvail, int numTotal, String title, int year, char genre
		String output = ("" + getIsbn() + ";" + getCallNum() + ";" + getNumAvail() + ";" + getNumTotal() + ";" + getTitle() + ";" + getFrequency());
		return output;
	}
	
	@Override
	public String toString() {
			
			return "ISBN: " +getIsbn()       + "\n" +
			"Call Number: " + getCallNum()    + "\n" +
			"Available: " + getNumAvail()    + "\n" +
			"Total: " + getNumTotal()    + "\n" +			
			"Title: " + getTitle()        + "\n" +
			"Frequency: " + getFullFrequency()    + "\n";
			}
		
		private String getFullFrequency(){
			String fullFrequency = "";
			switch(frequency){
				case 'D':{
					fullFrequency = "Daily"; 
					return fullFrequency;
					}
				case 'W':{
					fullFrequency = "Weekly" ;
					return fullFrequency;
					}
				case 'M':{
					fullFrequency = "Monthly"; 
					return fullFrequency;
					}
				case 'B':{
					fullFrequency = "Bi Monthly"; 
					return fullFrequency;
					}
				case 'Q':{
					fullFrequency = "Quarterly"; 
					return fullFrequency;
					}
				default: 
					return "Not specified";
			
		}
	}
	
//	@Override
//	public String toString() {
//	String fullFrequency = "";
//	if (frenquency == 'D'){fullFrequency = "Daily";}
//	if (frenquency == 'W'){fullFrequency = "Weekly";}
//	if (frenquency == 'M'){fullFrequency = "Monthly";}
//	if (frenquency == 'B'){fullFrequency = "Bi Monthly";}
//	if (frenquency == 'Q'){fullFrequency = "Quarterly";}
//
//
//	return "ISBN: " + iSBN        + "\n" +
//	"Call Number: " + callNumber    + "\n" +
//	"Available: " + available    + "\n" +
//	"Total: " + total        + "\n" +
//	"Title: " + title        + "\n" +
//	"Frequency: " + fullFrequency + "\n";
//	}
//	}
	

}
