package DataStructures;

public class Stack<Item> {
    private class Node {
        private Item item;
        private Node next;
        // Constructors

        public Node() {
        }

        public Node(Item item) {
            this.item = item;
        }
    }
    private Node first = null;
    private int N = 0;
    public boolean isEmpty() { return first == null; }
    public void push(Item item) {
        Node second = first; 
        first = new Node(item);
        first.next = second; 
        N++; 
    }
    public Item peek() {
        return first.item;
    }

    public Item pop() {
        Item item = first.item; 
        first = first.next; 
        N--; 
        return item; 
    } 
    public int size() { return N; }
}