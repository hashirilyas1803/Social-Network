package DataStructures;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HashTable<T extends Comparable<T>> {
    // Toggle CONTROLLER_PIN true for quadratic probing and false for linear probing
    private final boolean CONTROLLER_PIN = true;
    private int[] collisions;
    private class Node<T> {
        T data;
        public Node() {
        }
        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data+"";
        }
    }
    Node<T>[] table;
    Node<T> placeholder = new Node<>();
    HashTable(int s){
        table = new Node[s+(s/3)];
        collisions = new int[size()];
    }
    public int strToInt(T obj) {
        if (obj instanceof String temp) {
            int sum = 0;
            for (int i = 0; i < temp.length(); i++) {
                sum += (temp.charAt(i) * (int)Math.pow(3, i));
            }
            return sum;
        }
        else
            return obj.hashCode();
    }
    public int hash(T obj){
        return strToInt(obj) % table.length;
    }
    public int rehash(T obj) {
        int i = 1;
        int hash = hash(obj);
        if (!CONTROLLER_PIN) {
            while (table[hash] != null) {
                hash = (hash(obj) + i) % table.length;
                i++;
            }
        }
        else {
            while (table[hash] != null) {
                hash = (hash(obj) + (i * i)) % table.length;
                i++;
            }
        }
        collisions[hash] = i;
        return hash;
    }
    public void insert(T obj){
        // Hash the given value to find out the index of insertion
        int hash = hash(obj);

        // Rehash if the calculated index is occupied
        if (table[hash] != null )
            hash = rehash(obj);
        table[hash] = new Node<>(obj);
    }
    public Boolean find(T obj) {
        // Return true if a valid index is returned
        return findIndex(obj) != -1;
    }
    public int findIndex(T obj) {
        int hash = hash(obj);

            // Continue checking the linear probing sequence if a placeholder is encountered
            if (CONTROLLER_PIN) {
                for (int i = 0; i < table.length; i++) {
                    if (table[(hash + (i * i)) % table.length] == placeholder)
                        continue;

                    // Quit the search if a null value is encountered
                    if (table[(hash + (i * i)) % table.length] == null)
                        return -1;

                    // Return the index of the value if found
                    if (table[(hash + (i * i)) % table.length].data.equals(obj))
                        return (hash + (i * i)) % table.length;
                }
            }
            else {
                for (int i = 0; i < table.length; i++) {
                    if (table[(hash + i) % table.length] == placeholder)
                        continue;

                    // Quit the search if a null value is encountered
                    if (table[(hash + i) % table.length] == null)
                        return -1;

                    // Return the index of the value if found
                    if (table[(hash + i) % table.length].data.equals(obj))
                        return (hash + i) % table.length;
                }
            }

        // Return -1 otherwise to signify that the value was not found
        return -1;
    }
    public boolean delete(T obj){
        int hash = hash(obj);

        // Check if the value exists
        if (table[hash] != null) {
            if (table[hash].data != null) {
                // Delete the value by following the linear probing sequence
                if (table[hash].data.equals(obj)) {
                    table[hash] = placeholder;
                    return true;
                }
                else {
                    int index = findIndex(obj);
                    if (index != -1) {
                        table[index] = placeholder;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void recordResults() {
        File file;
        PrintWriter out = null;
        try {
            file = new File("Linear Probing.txt");
            out = new PrintWriter(file);
            for (Node<T> obj : table) {
                if (obj != null)
                    out.println(obj + ": " + collisions[findIndex(obj.data)]);
                else
                    out.println(obj + ": No collisions");
            }
            out.print("Number of Total Collisions in " + (!CONTROLLER_PIN? "Linear":"Quadratic") + " Probing: " + totalCollision());

        } catch (FileNotFoundException e) {
            System.out.println("Please add a file!");;
        }
        finally {
            assert out != null;
            out.close();
        }
    }
    public void displayTable() {
        // Print out the entire hash table
        for (Node<T> obj : table) {
            System.out.print(obj + ": ");
            if (obj != null)
                System.out.println(collisions[findIndex(obj.data)]);
            else
                System.out.println("No collisions");
        }
    }

    public int totalCollision() {
        int sum = 0;
        for (int i = 0; i < collisions.length; i++) {
            sum += collisions[i];
        }
        System.out.println("Number of Total Collisions in " + (!CONTROLLER_PIN? "Linear":"Quadratic") + " Probing: " + sum);
        return sum;
    }

    public int size() {
        return table.length;
    }
}
