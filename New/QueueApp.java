package New;

import java.io.*;
import java.util.Scanner;

class QueueApp {
    private static final String FILE_PATH = "books.txt";
    private static Queue bookQueue = new Queue();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Read existing books from the file at the start
        readBooksFromFile();

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
        bookQueue.enqueue(book);
        writeBooksToFile();
    }

    private static void updateBook() {
        System.out.print("Enter the book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Queue tempQueue = new Queue();
        boolean found = false;

        while (!bookQueue.isEmpty()) {
            Book currentBook = bookQueue.dequeue();
            if (currentBook.getId() == bookId) {
                System.out.print("Enter new book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter new author: ");
                String author = scanner.nextLine();
                System.out.print("Enter new price: ");
                double price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new genre: ");
                String genre = scanner.nextLine();
                currentBook = new Book(bookId, title, author, price, genre);
                found = true;
            }
            tempQueue.enqueue(currentBook);
        }

        bookQueue = tempQueue;
        if (found) {
            writeBooksToFile();
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book ID not found.");
        }
    }

    private static void readBooksFromFile() {
        bookQueue.clear(); // Clear the queue before reading from the file
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                int id = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                String author = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                String genre = data[4].trim();
                bookQueue.enqueue(new Book(id, title, author, price, genre));
            }
            bookQueue.display();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void deleteBook() {
        System.out.print("Enter the book ID to delete: ");
        int bookId = scanner.nextInt();
        Queue tempQueue = new Queue();
        boolean found = false;

        while (!bookQueue.isEmpty()) {
            Book currentBook = bookQueue.dequeue();
            if (currentBook.getId() != bookId) {
                tempQueue.enqueue(currentBook);
            } else {
                found = true;
            }
        }

        bookQueue = tempQueue;
        if (found) {
            writeBooksToFile();
        } else {
            System.out.println("Book ID not found.");
        }
    }

    private static void writeBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Queue tempQueue = new Queue();
            while (!bookQueue.isEmpty()) {
                Book book = bookQueue.dequeue();
                writer.write(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getPrice() + ", " + book.getGenre());
                writer.newLine();
                tempQueue.enqueue(book);
            }
            bookQueue = tempQueue;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}