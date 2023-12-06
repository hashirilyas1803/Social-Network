package DataStructures;

public class PriorityQueue<T extends Comparable<T>> {
    public T[] Q; int F; int R; int max; int size;
    //Constructor
    public PriorityQueue(){
        max = 10;
        Q=(T[])new Comparable[max];
        F=9; R=9;
    }
    // Constructor
    public PriorityQueue(int size) {
        this.max = size;
        Q = (T[]) new Comparable[size];
        F = size - 1;
        R = size - 1;
    }

    public void enqueue(T obj)
    {
        if (size == 0) {
            R = F = ++F % max;
            Q[R] = obj;
            size++;
            return;
        }
        if (size != max) {
            R = ++R % max;
            size++;
            for (int i = F; i != R || i == F; i = ++i % max) {
                if (obj.compareTo(Q[i]) == -1) {
                    for (int j = R; j != i; j = (j + max - 1) % max) {
                        Q[j] = Q[(j + max - 1) % max];
                    }
                    Q[i] = obj;
                    return;
                }
            }
            Q[R] = obj;
        }
    }

    public T dequeue()
    {
        if (size != 0)
        {
            int index = findSmallest();
            T value = Q[index];

            for( int i = index ; i == F ; i = (i+max - 1)%max)
                Q[(i+max - 1)%max] = Q[i];

            return value;
        }
        return null;
    }

    public int findSmallest()
    {
        if (isEmpty())
        {
            return -1;
        }

        T smallest = Q[F];
        int index = F;
        int current = (F + 1) % Q.length;

        while (current != (R + 1) % Q.length) {
            if (Q[current].compareTo(smallest) < 0) {
                smallest = Q[current];
                index = current;
            }
            current = (current + 1) % Q.length;
        }

        return index;
    }

    public T peek() {
        return Q[F];
    }

    public String toString() {
        String s = "";
        for (int i = F; i != R || i == F; i = ++i % max) {
            s += Q[i] + " ";
        }
        s += Q[R];
        return s;
    }

    public boolean isEmpty(){ return size == 0;}
    public boolean isFull(){ return size == max;}

    public int getSize() {
        return size;
    }
}
