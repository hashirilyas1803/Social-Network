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

    public void enqueue(T obj) {
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

    public T dequeue(){
        if (size != 0) {
            T temp = Q[F];
            F = ++F % max;
            size--;
            return temp;
        }
        return null;
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
}
