package DataStructures;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HashTable<T extends Comparable<T>, U extends Comparable<U>> {
    // Toggle CONTROLLER_PIN true for quadratic probing and false for linear probing
    private final boolean CONTROLLER_PIN = true;
    private int[] collisions;
    private U defaultValue;
    private class Node<T> {
        T key;
        U data;
        public Node() {
        }
        public Node(T key, U data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public String toString() {
            return data+"";
        }
    }
    Node<T>[] table;
    Node<T> placeholder = new Node<>();

    // Constructor
    HashTable(int s, U defaultValue){
        table = new Node[s+(s/3)];
        collisions = new int[size()];
        this.defaultValue = defaultValue;
    }

    // Figure out the integer representation of every key
    public int toInt(T key) {
        if (key instanceof String temp) {
            int sum = 0;
            for (int i = 0; i < temp.length(); i++) {
                sum += (temp.charAt(i) * (int)Math.pow(3, i));
            }
            return sum;
        }
        else if (key instanceof Integer temp) {
            int sum = 0, i = 0;
            while (temp > 0) {
                sum += ((temp % 10) * (int)Math.pow(3, i));
                temp /= 10;
                i++;
            }
            return sum;
        }
        else
            return key.hashCode();
    }

    // Return the default value if key doesn't exist. Return the data otherwise
    public U getDefaultValue(T key) {
        if (!find(key)) {
            return defaultValue;
        }
        return table[findIndex(key)].data;
    }
    public int hash(T key){
        return toInt(key) % table.length;
    }
    public int rehash(T key) {
        int i = 1;
        int hash = hash(key);
        if (!CONTROLLER_PIN) {
            while (table[hash] != null) {
                hash = (hash(key) + i) % table.length;
                i++;
            }
        }
        else {
            while (table[hash] != null) {
                hash = (hash(key) + (i * i)) % table.length;
                i++;
            }
        }
        collisions[hash] = i;
        return hash;
    }
    public void insert(T key, U obj){
        // Hash the given value to find out the index of insertion
        int hash = hash(key);

        // Rehash if the calculated index is occupied
        if (table[hash] != null )
            hash = rehash(key);
        table[hash] = new Node<>(key, obj);
    }
    public Boolean find(T key) {
        // Return true if a valid index is returned
        return findIndex(key) != -1;
    }
    public int findIndex(T key) {
        int hash = hash(key);

            // Continue checking the linear probing sequence if a placeholder is encountered
            if (CONTROLLER_PIN) {
                for (int i = 0; i < table.length; i++) {
                    if (table[(hash + (i * i)) % table.length] == placeholder)
                        continue;

                    // Quit the search if a null value is encountered
                    if (table[(hash + (i * i)) % table.length] == null)
                        return -1;

                    // Return the index of the value if found
                    if (table[(hash + (i * i)) % table.length].key.equals(key))
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
                    if (table[(hash + i) % table.length].key.equals(key))
                        return (hash + i) % table.length;
                }
            }

        // Return -1 otherwise to signify that the value was not found
        return -1;
    }
    public boolean delete(T key){
        int hash = hash(key);

        // Check if the value exists
        if (table[hash] != null) {
            if (table[hash].data != null) {
                // Delete the value by following the linear probing sequence
                if (table[hash].key.equals(key)) {
                    table[hash] = placeholder;
                    return true;
                }
                else {
                    int index = findIndex(key);
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
                    out.println(obj + ": " + collisions[findIndex(obj.key)]);
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
                System.out.println(collisions[findIndex(obj.key)]);
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
