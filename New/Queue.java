package New;

class Queue {
    private Node front;
    private Node rear;

    private class Node {
        Book data;
        Node next;

        Node(Book data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Book book) {
        Node newNode = new Node(book);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
    }

    public Book dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Book book = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return book;
    }

    public void clear() {
        front = null;
        rear = null;
    }

    public void display() {
        Node current = front;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}