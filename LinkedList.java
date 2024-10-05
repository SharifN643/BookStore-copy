public class LinkedList {
    private Node first;
    private Node last;
    private Node current;

    public LinkedList() {
        first = null;
        last = null;
        current = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertAtFront(Book insertItem) {
        Node newNode = new Node(insertItem);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
    }

    public void insertAtBack(Book insertItem) {
        Node newNode = new Node(insertItem);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    // Other methods...

    public Node getFirstNode() {
        return first;
    }

    public void displayList() {
        // Implement displayList method if not already implemented
    }
}