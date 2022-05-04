package part2;
import java.util.ArrayList;

public class Library {
    String libary;
    ArrayList<part2.Book> collection;

    //constructor
    public Library(String name){
        this.libary = name;
        this.collection = new ArrayList<part2.Book>();
    }
    //adds book to library collection
    public void addBook(part2.Book book){
        collection.add(book);
    }

    //prints the opening hours of the libraries
    public static void printOpeningHours(){
        System.out.println("Libraries are open daily from 9:00 to 17:00.");
    }

    //prints the address of the library
    public void printAddress(){
        System.out.println(this.libary);
    }
    /**
     * This method check if the given book is in the collection, then if it can be borrowed
     * and if the book is borrowable marks it as borrowed.
     * @param name
     */
    public void borrowBook(String name) {
        Boolean bool = false;
        for (part2.Book rec : collection) {
            if (rec.title.equals(name)) {
                bool = true;
                if (rec.isBorrowed())
                    System.out.println("Sorry, this book is already borrowed.");

                else {
                    System.out.println("You successfully borrowed Harry Potter");
                    rec.borrowed();
                }

            }
        }
        if (!bool)
            System.out.println("Sorry, this book is not in our catalog.");
    }
    /**
     * This method prints available books of collection
     */
    public void printAvailableBooks(){
        Boolean bool = true;
        for (part2.Book rec : collection) {
            if (!rec.isBorrowed()) {
                bool = false;
                System.out.println(rec.title);
            }

        }
        if (bool)
            System.out.println("No books in catalog.");
    }

    /**
     * This method returns a book the user gave.
     * @param name
     */
    public void returnBook(String name){
        for (part2.Book rec : collection) {
            if(rec.title.equals(name)&&rec.isBorrowed()) {
                rec.returned();
                System.out.printf("You successfully returned %s", name).println();
            }
        }
    }
    public static void main(String[] args) {
        //Create two libraries
        Library firstLibrary = new Library("CS Faculty");
        Library secondLibrary = new Library("IE&M Faculty");

        //Add four books to the first library
        firstLibrary.addBook (new part2.Book("Harry Potter"));
        firstLibrary.addBook (new part2.Book("To Kill a Mockingbird"));
        firstLibrary.addBook (new part2.Book("JAVA"));
        firstLibrary.addBook (new part2.Book("Calculus For Dummies"));

        //Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        //Try to borrow Harry Potter from both libraries
        System.out.println("Borrowing Harry Potter:");
        firstLibrary.borrowBook("Harry Potter");
        firstLibrary.borrowBook("Harry Potter");
        secondLibrary.borrowBook("Harry Potter");
        System.out.println();

        //Print the title of all available books from both libraries
        System.out.println("Books available in the first library");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library");
        secondLibrary.printAvailableBooks();
        System.out.println();

        //Return Harry Potter
        System.out.println("Returning Harry Potter");
        firstLibrary.returnBook("Harry Potter");
        System.out.println();

        // Print the titles of available books from the first library
        System.out.println("Books available in the first library");
        firstLibrary.printAvailableBooks();
    }
}
