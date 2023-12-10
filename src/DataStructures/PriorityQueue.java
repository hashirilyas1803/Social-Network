package DataStructures;

public class PriorityQueue <T extends Comparable<T>>
{
    private int size;
    private T [] heap;

    public PriorityQueue(int capacity)
    {
        this.size = 0;
        this.heap = (T[]) new Comparable[capacity];
    }

    public void insert(T data)
    {
        if(size == 0)
        {
            heap[size] = data;
            size++;
            return;
        }

        Makespace();
        heap[size] = data;
        size++;
        heapifyup();
    }

    public T dequeue()
    {
        if(size == 0)
        {
            return heap[0];
        }

        T temp = heap[0];
        heap[0] = heap[size - 1];
        heapifyDown();
        size--;
        return temp;
    }
    public void Makespace()
    {
        if(size == heap.length)
        {
            copy();
        }
    }

    public void copy()
    {
        T [] temp = (T[]) new Comparable[2* heap.length];

        for( int i = 0 ; i < heap.length; i++)
        {
            temp[i] = heap[i];
        }

        heap = temp;
    }

    public void heapifyup()
    {
        int index = size - 1;
        while( heap[(index - 1)/2] != null && index > 0)
        {
            System.out.println("asdad");
            if (heap[index].compareTo(heap[(index - 1) / 2]) < 0)
            {
                T temp = heap[(index - 1) / 2];
                heap[(index - 1) / 2] = heap[index];
                heap[index] = temp;
            }
            index = (index - 1)/2;
        }
    }

    private void heapifyDown()
    {
        int index = 0;
        while ( 2 * index + 1 < size)
        {
            int smallerChildIndex = 2 * index + 1;

            if ( 2 * index + 2 < size && heap[2 * index + 2].compareTo(heap[2 * index + 1]) < 0)
            {
                smallerChildIndex = 2 * index + 2;
            }

            if (heap[index].compareTo(heap[smallerChildIndex]) < 0)
            {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    private void swap(int index1, int index2)
    {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public T min(T data1 , T data2)
    {
        if(data1.compareTo(data2) < 0 )
            return data1;
        else
            return data2;
    }

    public int getSize()
    {
        return size;
    }
}
