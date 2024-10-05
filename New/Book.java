package New;

class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private String genre;

    public Book(int id, String title, String author, double price, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return id + ", " + title + ", " + author + ", " + price + ", " + genre;
    }
}