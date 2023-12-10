package DataStructures;

import java.util.Arrays;

public class BTree <T extends Comparable<T>>{
    // Node class
    public class Node<T extends Comparable<T>> {

        // An array of keys
        T[] keys;
        // Minimum degree (defines the range for number of keys)
        int t;
        // An array of child pointers
        Node<T>[] child;
        // Current number of keys
        int n;
        // Is true when node is leaf. Otherwise false
        boolean leaf;

        // Constructor
        Node(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.keys = (T[]) new Comparable[2 * t - 1];
            this.child = new Node[2 * t];
            this.n = 0;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "keys=" + Arrays.toString(keys) +
                    '}';
        }
    }

    // Pointer to root node
    public Node<T> root;

    // Minimum degree
    public int t;

    // Constructor (Initializes tree as empty)
    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    // function to traverse the tree
    public void traverse() {
        if (this.root != null)
            traverse(root);
        System.out.println();
    }

    public void traverse(Node<T> current) {
        int i = 0;
        for (; i < current.n; i++) {
            if (!current.leaf)
                traverse(current.child[i]);
            System.out.println(current.keys[i]);
        }
        if (!current.leaf)
            traverse(current.child[i]);
    }

    // function to search a key in this tree
    public T search(T k) {
        if (this.root == null)
            return null;
        else
            return search(k, root);
    }

    public T search(T k, Node<T> current) {
        int start = 0, end = current.n - 1, mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (current.keys[mid].compareTo(k) == 0)
                return current.keys[mid];
            if (k.compareTo(current.keys[mid]) > 0)
                start = mid + 1;
            else
                end = mid - 1;
        }
        if (current.leaf)
            return null;
        else
            return search(k, current.child[start]);
    }
    public void insert(T k) {
        if (root == null) {
            root = new Node(t, true);
            // Insert key
            root.keys[0] = k;
            // Update number of keys in root
            root.n = 1;
        }
        else {
            if (root.n == (2 * t - 1)) {
                // Declare new node and make it the new root
                Node<T> newRoot = new Node<>(t, false);
                newRoot.child[0] = root;

                // Split the old root into two
                splitChild(0, newRoot, root);

                // Decide the child to the insert the key into
                int i = 0;
                if (newRoot.keys[0].compareTo(k) < 0)
                    i++;
                insertNotFull(k, newRoot.child[i]);

                // Reassign the root node
                root = newRoot;
            }
            else
                insertNotFull(k, root);

        }
    }
    public void insertNotFull(T k, Node<T> current) {
        // Set i to the index of the last element of the array kays
        int i = current.n - 1;

        // Check if the node is a leaf node
        if (current.leaf) {
            // Iteratively shift the elements in keys to the right to find the correct position for insertion
            while (i >= 0 && current.keys[i].compareTo(k) > 0) {
                current.keys[i + 1] = current.keys[i];
                i--;
            }
            // Insert the new value in the correct position
            current.keys[i + 1] = k;
            current.n++;
        }
        else {
            // Search for the appropriate child
            int start = 0, end = current.n - 1, mid = 0;
            while (start <= end) {
                mid = (start + end) / 2;
                if (current.keys[mid].compareTo(k) == 0)
                    return;
                if (k.compareTo(current.keys[mid]) > 0)
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            if (current.child[start].n >= 2*t - 1) {

                // Split the appropriate child
                splitChild(start, current,current.child[start]);
                if (current.keys[start].compareTo(k) < 0)
                    start++;
            }
            insertNotFull(k, current.child[start]);
        }
    }

    public void splitChild(int i, Node<T> current, Node<T> child) {
        // Create a new child
        Node<T> newChild = new Node<>(child.t, child.leaf);
        newChild.n = t - 1;

        // Copy the last (t-1) keys of child node to newChild
        for (int j = 0; j < t-1; j++)
            newChild.keys[j] = child.keys[j+t];

        // Copy the last t children of the child node to newChild
        if (!child.leaf) {
            for (int j = 0; j < t; j++)
                newChild.child[j] = child.child[j+t];
        }

        // Reduce the number of keys in the child node
        child.n = t - 1;

        // Create space for child
        for (int j = current.n; j >= i+1; j--)
            current.child[j+1] = current.child[j];

        // Insert child
        current.child[i + 1] = newChild;

        // Copy the middle key of the child to the current node and increment n
        current.keys[i] = child.keys[t-1];
        current.n++;
    }

    public int findInNode(T k, Node<T> current) {
        int start = 0, end = current.n - 1, mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (current.keys[mid].compareTo(k) == 0)
                return mid;
            if (k.compareTo(current.keys[mid]) > 0)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return start;
    }

    // Delete a value from a node
    public void delete(T k) {
        // Return if the key doesn't exist in the entire tree
        if (search(k, root) == null)
            return;
        delete(k, root);
    }

    // A function to delete the key k from the sub-tree rooted with this node
    public void delete(T k, Node<T> current) {
        int j = findInNode(k, current);

        // The key to be removed is present in this node
        if (j < current.n && current.keys[j] == k) {
            if (current.leaf) {
                for (int i = j; i < current.n - 1; i++)
                    current.keys[i] = current.keys[i + 1];
                current.n--;
            }
            else {
                if (current.child[j].n >= t) {
                    T pred = getPredecessor(j, current);
                    current.keys[j] = pred;
                    delete(pred, current.child[j]);
                }

                // If the child current.child[j] has less that t keys, examine current.child[j+1].
                // If current.child[j+1] has atleast t keys, find the successor 'succ' of k in
                // the subtree rooted at current.child[j+1]
                // Replace k by succ
                // Recursively delete succ in current.child[j+1]
                else if  (current.child[j+1].n >= t){
                    T succ = getSuccessor(j, current);
                    current.keys[j] = succ;
                    delete(succ, current.child[j+1]);
                }

                // If both current.child[j] and current.child[j+1] has less that t keys,merge k and all of current.child[j+1]
                // into current.child[j]
                // Now current.child[j] contains 2t-1 keys
                // Free current.child[j+1] and recursively delete k from current.child[j]
                else {
                    merge(j, current);
                    delete(k, current.child[j]);
                }
            }
        } else {
            // If this node is a leaf node, then the key is not present in tree
            if (current.leaf) {
                System.out.println("The key " + k + " does not exist in the tree\n");
                return;
            }

            // The key to be removed is present in the sub-tree rooted with this node
            boolean flag = ((j == current.n) ? true : false);

            // If the child where the key is supposed to exist has less than t keys, we fill that child
            if (current.child[j].n < t)
                fill(j, current);

            // If the last child has been merged, it must have merged with the previous child and so we recurse on the (j-1)th child. Else, we recurse on the (j)th child which now has atleast t keys
            if (flag && j > current.n)
                delete(k,current.child[j - 1]);
            else
                delete(k, current.child[j]);
        }
    }

    public void fill(int j, Node<T> current) {
        // If the previous current.child[j-1] has more than t-1 keys, borrow a key
        // from that child
        if (j != 0 && current.child[j-1].n >= t)
            borrowFromPrev(j, current);

            // If the next current.child[j+1] has more than t-1 keys, borrow a key
            // from that child
        else if (j != current.n && current.child[j+1].n >= t)
            borrowFromNext(j, current);

            // Merge current.child[j] with its sibling
            // If current.child[j] is the last child, merge it with its previous sibling
            // Otherwise merge it with its next sibling
        else{
            if (j != current.n)
                merge(j, current);
            else
                merge(j-1, current);
        }
    }

    public void borrowFromPrev(int j, Node<T> current)
    {

        Node<T> child = current.child[j];
        Node<T> sibling = current.child[j-1];

        // The last key from current.child[j-1] goes up to the parent and key[j-1]
        // from parent is inserted as the first key in current.child[j]. Thus, the  loses
        // sibling one key and child gains one key

        // Moving all key in current.child[j] one step ahead
        for (int i = child.n-1; i >= 0; --i)
            child.keys[i+1] = child.keys[i];

        // If current.child[j] is not a leaf, move all its child pointers one step ahead
        if (!child.leaf) {
            for(int i = child.n; i >= 0; --i)
                child.child[i+1] = child.child[i];
        }

        // Setting child's first key equal to keys[j-1] from the current node
        child.keys[0] = current.keys[j-1];

        // Moving sibling's last child as current.child[j]'s first child
        if(!child.leaf)
            child.child[0] = sibling.child[sibling.n];

        // Moving the key from the sibling to the parent
        // This reduces the number of keys in the sibling
        current.keys[j-1] = sibling.keys[sibling.n-1];

        // Update the number of keys in both children
        child.n++;
        sibling.n--;
    }

    public void borrowFromNext(int j, Node<T> current) {

        Node<T> child = current.child[j];
        Node<T> sibling = current.child[j+1];

        // keys[j] is inserted as the last key in current.child[j]
        child.keys[(child.n)] = current.keys[j];

        // Sibling's first child is inserted as the last child
        // into current.child[j]
        if (!(child.leaf))
            child.child[(child.n)+1] = sibling.child[0];

        //The first key from sibling is inserted into keys[j]
        current.keys[j] = sibling.keys[0];

        // Moving all keys in sibling one step behind
        for (int i = 1; i < sibling.n; ++i)
            sibling.keys[i-1] = sibling.keys[i];

        // Moving the child pointers one step behind
        if (!sibling.leaf) {
            for(int i = 1; i <= sibling.n; ++i)
                sibling.child[i-1] = sibling.child[i];
        }

        // Update the number of keys in both children
        child.n++;
        sibling.n--;
    }

    // Returns the predecessor value of the selected key
    public T getPredecessor(int i, Node<T> current) {
        // Keep moving to the right most node until we reach a leaf
        Node<T> temp = current.child[i];
        while (!temp.leaf)
            temp = temp.child[temp.n];

        // Return the last key of the leaf
        return temp.keys[temp.n-1];
    }

    public T getSuccessor(int i, Node<T> current) {
        // Keep moving to the right most node until we reach a leaf
        Node<T> temp = current.child[i + 1];
        while (!temp.leaf)
            temp = temp.child[0];
        // Return the last key of the leaf
        return temp.keys[0];
    }

    public void merge(int j, Node<T> current) {
        Node<T> child = current.child[j], sibling = current.child[j+1];

        // Pulling a key from the current node and inserting it into (t-1)th
        // position of current.child[j]
        child.keys[t-1] = current.keys[j];

        // Copying the keys from current.child[j+1] to current.child[j] at the end
        for (int i=0; i<sibling.n; ++i)
            child.keys[i+t] = sibling.keys[i];

        // Copying the child pointers from current.child[j+1] to current.child[j]
        if (!child.leaf) {
            for(int i=0; i<=sibling.n; ++i)
                child.child[i+t] = sibling.child[i];
        }

        // Moving all keys after j in the current node one step before -
        // to fill the gap created by moving keys[j] to current.child[j]
        for (int i = j + 1; i < current.n; ++i)
            current.keys[i-1] = current.keys[i];

        // Moving the child pointers after (j+1) in the current node one
        // step before
        for (int i = j + 2; i <= current.n; ++i)
            current.child[i-1] = current.child[i];

        // Updating the key count of child and the current node
        child.n += sibling.n+1;
        current.n--;
    }
}