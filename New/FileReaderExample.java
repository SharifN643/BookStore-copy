package New;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderExample {
    public static void main(String[] args) {
        try {
            File file = new File("books.txt");
            Scanner scanner = new Scanner(file);
            LinkedList bookList = new LinkedList();

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                String author = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                String genre = data[4].trim();
                bookList.insertAtBack(new Book(id, title, author, price, genre));
            }

            bookList.display();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}