package sait.bms.managers;

import java.io.*;
import java.util.*;

import sait.bms.problemdomain.Book;
import sait.bms.problemdomain.CookBook;
import sait.bms.problemdomain.KidsBook;
import sait.bms.problemdomain.Paperback;
import sait.bms.problemdomain.Periodical;

public class Manager {

	private ArrayList<Book> bookList = new ArrayList<>();

	private static final String PATH = "res/books.txt";
	@SuppressWarnings("unused")
	private String line;
	@SuppressWarnings("unused")
	private String[] fields;
	@SuppressWarnings("unused")
	private int lastDigit;
	private int userinput;
	private boolean isRunning;

	public Manager() throws FileNotFoundException {
		loadBooksFromFile();
		menuListener();
	}

	private void loadBooksFromFile() throws FileNotFoundException {
		Scanner in = new Scanner(new File(PATH));
		// callMenu();
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(";");
			long tempL = (Long.parseLong(fields[0]));
			long tempL1 = ((tempL) % 10);
			int lastDigit = Math.toIntExact(tempL1);

			// testing
			// System.out.println(lastDigit); //delete me

			switch (lastDigit) {
			case 0:
			case 1: { // Kids books
				Book b = new KidsBook(Long.parseLong(fields[0]), fields[1], (Integer.parseInt(fields[2])),
						(Integer.parseInt(fields[3])), fields[4], fields[5], (fields[6].charAt(0)));

				bookList.add(b);
				// count++;
				break;

			}

			case 2:
			case 3: { // Cook Books
				Book c = new CookBook(Long.parseLong(fields[0]), fields[1], (Integer.parseInt(fields[2])),
						(Integer.parseInt(fields[3])), fields[4], fields[5], (fields[6].charAt(0)));
				bookList.add(c);
				// count++;
				break;
			}
			case 4:
			case 5:
			case 6:
			case 7: { // PaperBack
				Book d = new Paperback(Long.parseLong(fields[0]), fields[1], (Integer.parseInt(fields[2])),
						(Integer.parseInt(fields[3])), fields[4], fields[5], Integer.parseInt(fields[6]),
						(fields[7].charAt(0)));
				bookList.add(d);
				// count++;
				break;
			}
			case 8:
			case 9: { // Periodicals
				Book e = new Periodical(Long.parseLong(fields[0]), fields[1], (Integer.parseInt(fields[2])),
						(Integer.parseInt(fields[3])), fields[4], (fields[5].charAt(0)));
				bookList.add(e);
				// count++;
				break;
			}
			default: {
				in.close();
			}
			}// switch

			// Scanning books ends
			// Menu is called

		}
		isRunning = true;
		System.out.println("Books loaded! "); // delete me
	}

	private void printMenu() {
		System.out.println("Welcome in ABC Book Company: How May We Assist You?");
		System.out.println("1	Checkout Book");
		System.out.println("2	Find Books by Title");
		System.out.println("3	Display Books by Type");
		System.out.println("4	Produce Random Book List");
		System.out.println("5	Save & Exit");
	}

	// Menu listener
	// Gathers user input then calls methods

	private void menuListener() {

		while (isRunning == true) {
			printMenu();
			Scanner deez = new Scanner(System.in);
			System.out.print("\n" + "Enter option: ");
			userinput = deez.nextInt();

			switch (userinput) {
			case 1: {
				checkoutBook(bookList, deez);
				break;
			}
			case 2: {
				searchTitle(bookList, deez);
				break;
			}
			case 3: {
				displayBooksType(bookList, deez);
				break;
			}
			case 4: {
				randomBooks(bookList, deez);
				break;
			}
			case 5: {

				isRunning = false;
				deez.close();
				try {
					saveChangesToFile(bookList, PATH);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);

			}

			}// switch

		} // while

	}// menuListener

	public static void checkoutBook(ArrayList<Book> bookList, Scanner deez) {

		boolean found = false;
		System.out.print("Enter ISBN of Book: ");
		long input = deez.nextLong();

		for (Book book : bookList) {
			if (book.getIsbn() == input) {
				boolean temp = book.getAvailable();
				if (temp == true) {
					book.checkoutBook();
					found = true;
				} else {
					System.out.println("your book isn't currently available.");
					System.out.println("Returning to main menu...\n");
				}
			}
		}
		if (found == false) {
			System.out.println("\n" + "Sorry your ISBN number isn't in our system");
			System.out.println("Returning to main menu..." + "\n");

		}
	} // checkoutBook

	private void searchTitle(ArrayList<Book> bookList2, Scanner deez) {

		boolean found = false;
		System.out.print("Enter title to search for: ");
		String input = deez.next().toLowerCase();
		ArrayList<Book> foundBooks = new ArrayList<Book>();

		for (Book book : bookList) {
			if (book.getTitle().toLowerCase().contains(input.toLowerCase())) {
				found = true;
				foundBooks.add(book);
			}

		}

		if (found = true) {
			System.out.println("books found");
			for (Book book : foundBooks) {
				System.out.println(book.toString());
			}
		}

		else if (found == false) {
			System.out.println("\n" + "Sorry the book you're looking for isn't currently in our system");
			System.out.println("Returning to main menu..." + "\n");

		}
	} // searchTitle

	public static void displayBooksType(ArrayList<Book> bList, Scanner kbrd) {
		System.out.println("#\tType");
		System.out.println("1\tChildren's Books");
		System.out.println("2\tCookbooks");
		System.out.println("3\tPaperbacks");
		System.out.println("4\tPeriodicals\n");
		System.out.print("Enter type of Book: ");
		int tOfBook = kbrd.nextInt();

		if (tOfBook == 1) {
			System.out.println("Enter a format (P for Picture book, E for Early Readers, or C for Chapter book. )");
			char input = Character.toUpperCase(kbrd.next().charAt(0));
			for (Book book : bList) {
				if (book instanceof KidsBook) {
					if ((((KidsBook) book).getFmat()) == input) {
						System.out.println(book);
					}
				}
			}
		}
		if (tOfBook == 2) {
			System.out.println(
					"Enter a Diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, or N for None )");
			char input = Character.toUpperCase(kbrd.next().charAt(0));
			for (Book book : bList) {
				if (book instanceof CookBook) {
					if ((((CookBook) book).getDiet()) == input) {
						System.out.println(book);
					}
				}
			}
		}
		if (tOfBook == 3) {
			System.out.println(
					"Enter a Genre (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, or S for Science Fiction.)");
			char input = Character.toUpperCase(kbrd.next().charAt(0));
			for (Book book : bList) {
				if (book instanceof Paperback) {
					if ((((Paperback) book).getGenre()) == input) {
						System.out.println(book);
					}
				}
			}
		}
		if (tOfBook == 4) {
			System.out.println(
					"Enter a Frequency (D for Daily, W for Weekly, M for Monthly, B for Bimonthly, and Q for Quarterly)");
			char input = Character.toUpperCase(kbrd.next().charAt(0));
			for (Book book : bList) {
				if (book instanceof Periodical) {
					if ((((Periodical) book).getFrequency()) == input) {
						System.out.println(book);
					}
				}
			}
		}

	} // Book types

	public static void randomBooks(ArrayList<Book> bList, Scanner deez) {
		Random rand = new Random();

		System.out.print("Enter a number of books: ");
		int ranBooks = deez.nextInt();

		System.out.println("\nRandom Books:\n");
		while (ranBooks != 0) {

			System.out.println(bList.get(rand.nextInt(bList.size())));

			ranBooks--;
		}
	} // randomBooks

	public static void searchBooks(ArrayList<Book> bList, Scanner kbrd) {
		System.out.print("Enter title to search for: ");
		String searchArg = kbrd.nextLine().toLowerCase();
		for (Book book : bList) {
			if (book.getTitle().toLowerCase().contains(searchArg.toLowerCase())) {
				System.out.println(book);
			}
		}
	} // searchBooks

	public static void saveChangesToFile(ArrayList<Book> bList, String fileLocation) throws IOException {
		PrintWriter out = new PrintWriter(fileLocation);
		for (Book book : bList) {
			out.println(book.print2File());
		}
		out.close();
	}

}// Class body
