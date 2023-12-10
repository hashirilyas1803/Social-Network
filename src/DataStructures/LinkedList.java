package DataStructures;

public class LinkedList<Item> {
    private Node head;
    public int size;

    public LinkedList() {
        head = null;
    }

    public class Node
    {
        private Item item;
        private Node next;

        public Node(Item data) {
            this.item = data;
        }
    }

    public void insertBefore(Item data, int index) {
        if (head == null) {
            head = new Node(data);
            return;
        }
        if (index > size)
        {
            Node current = head ;
            while ( current . next != null ) {
                current = current . next ;
            }
            current . next = new Node ( data );
            size++;
            return;
        }
        else if (index == 0)
        {
            Node current = new Node(data);
            current.next = head;
            head = current;
            return;
        }
        Node current = head;
        for (int i = 1; i < index; i++)
        {
            current = current.next;
        }
        Node temp = new Node(data);
        temp.next = current.next;
        current.next = temp;
        size++;
    }
    public void insert ( Item data ) {
        if ( head == null ) {
            head = new Node ( data );
            size++;
            return ;
        }
        Node current = head ;
        while ( current . next != null ) {
            current = current . next ;
        }
        current . next = new Node ( data );
        size++;
    }

    public void delete ( Item data ) {
        if ( head == null ) {
            return ;
        }
        if ( head.item == data ) {
            head = head . next ;
            size--;
            System . out . println (" removed ");
            return ;
        }
        Node current = head ;
        while ( current . next != null ) {
            if ( current . next.item == data ) {
                current . next = current . next . next ;
                size--;
                return ;
            }
            current = current . next ;
        }
    }

    public void remove(int index) {
        // Removes the first occurence of an element
        if (index == 0) {
            head = head.next;
            return;
        }
        else if (index > size)
            return;
        else if (index == size)
        {
            Node current = head;
            while(current.next.next != null) {
                current = current.next;
            }
            current.next = null;
            size--;
            return;
        }
        Node current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    public void remove(Item data) {
        // Removes all instances of an element
        if ( head == null ) {
            return ;
        }
        if ( head .item.equals(data) ) {
            head = head . next ;
            size--;
            return;
        }
        Node n = head;
        while (n.next != null) {
            if (n.next.item.equals(data))
            {
                n.next = n.next.next;
                size--;
                return;
            }
            else
                n = n.next;
        }
    }

    public void clear() {
        // Clears the list
        head = null;
    }

    public void printList() {
        // Prints the entire list
        Node n = head;
        while (n != null)
        {
            System.out.println(n.item);
            n = n.next;
        }
    }

    public void removeDuplicates() {
        // Removes all duplicates from the list
        Node current = head;
        Node nextNode;
        while(current != null) {
            nextNode = current;
            while (nextNode.next != null) {
                if (nextNode.next.item == current.item) {
                    nextNode.next = nextNode.next.next;
                    size--;
                }
                else
                    nextNode = nextNode.next;
            }
            current = current.next;
        }
    }

    public void revrse() {
        // Reverses the entire list
        Node prev = null;
        Node current = head;
        Node next  = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public Item get(int i) {
        Node current = head;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current.item;
    }

    public void toArray(Item[] arr) {
        Node current = head;
        int i = 0;
        while (current != null) {
            arr[i] = current.item;
            i++;
            current = current.next;
        }
    }

    public boolean Find(Item data) {
        // Searches the list for a particular element
        Node current = head;
        while (current != null)
        {
            if (current.item.equals(data))
                return true;
            current = current.next;
        }
        return false;
    }

    public String toString() {
        // Returns a comma separated string of all the elements in the list
        if (head == null)
            return null;
        String s = "";
        Node n = head;
        s += n.item;
        n = n.next;
        while (n != null)
        {
            s +=  ", " + n.item;
            n = n.next;
        }
        return s;
    }
}