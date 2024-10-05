package New;

import java.io.*;
import java.util.Scanner;

class LinkedListApp {
    private static final String FILE_PATH = "books.txt";
    private static LinkedList bookList = new LinkedList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Input new books");
            System.out.println("2. Update a book");
            System.out.println("3. Read all books from file");
            System.out.println("4. Delete a book by ID");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    inputNewBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    readBooksFromFile();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void inputNewBook() {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        Book book = new Book(id, title, author, price, genre);
        bookList.insertAtBack(book);
        writeBooksToFile();
    }

    private static void updateBook() {
        System.out.print("Enter the book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Node current = bookList.getHead();
        while (current != null) {
            if (current.data.getId() == bookId) {
                System.out.print("Enter new book title: ");
                current.data = new Book(bookId, scanner.nextLine(), current.data.getAuthor(), current.data.getPrice(), current.data.getGenre());
                System.out.print("Enter new author: ");
                current.data = new Book(bookId, current.data.getTitle(), scanner.nextLine(), current.data.getPrice(), current.data.getGenre());
                System.out.print("Enter new price: ");
                current.data = new Book(bookId, current.data.getTitle(), current.data.getAuthor(), scanner.nextDouble(), current.data.getGenre());
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new genre: ");
                current.data = new Book(bookId, current.data.getTitle(), current.data.getAuthor(), current.data.getPrice(), scanner.nextLine());
                writeBooksToFile();
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID not found.");
    }

    private static void readBooksFromFile() {
        bookList.clear(); // Clear the list before reading from the file
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                int id = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                String author = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                String genre = data[4].trim();
                bookList.insertAtBack(new Book(id, title, author, price, genre));
            }
            bookList.display();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void deleteBook() {
        System.out.print("Enter the book ID to delete: ");
        int bookId = scanner.nextInt();
        bookList.remove(bookId);
        writeBooksToFile();
    }

    private static void writeBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Node current = bookList.getHead();
            while (current != null) {
                writer.write(current.data.getId() + ", " + current.data.getTitle() + ", " + current.data.getAuthor() + ", " + current.data.getPrice() + ", " + current.data.getGenre());
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}