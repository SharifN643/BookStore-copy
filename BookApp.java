import java.util.Scanner;

public class BookApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList bookLinkedList = new LinkedList();

        // Input books into the linked list
        for (int i = 0; i < 4; i++) {
            System.out.print("Input book title: ");
            String title = in.next();
            System.out.print("Input author: ");
            String author = in.next();
            System.out.print("Input price: ");
            double price = in.nextDouble();
            System.out.print("Input genre: ");
            String genre = in.next();

            // Insert into object
            Book book = new Book(title, author, price, genre);
            // Add into linked list
            bookLinkedList.insertAtFront(book);
        }

        // Display
        System.out.println("=========List of Books==========");
        System.out.println("Title" + "\t\t" + "Author" + "\t\t" + "Price" + "\t\t" + "Genre");
        System.out.println("===============================");

        Node current = bookLinkedList.getFirstNode();
        while (current != null) {
            Book currentBook = current.data;
            System.out.println(currentBook.toString());
            current = current.next;
        }

        // Other parts of the BookApp class...
    }
}