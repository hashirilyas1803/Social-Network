package DataStructures;
import java.util.Iterator;


public class HashMap <K extends Comparable<K> , V extends Comparable<V>> implements Iterable<K>
{
    private class Node<K extends Comparable<K> , V extends Comparable<V>> implements Comparable<Node<K, V>>
    {
        K key;
        V value;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node<K, V> o)
        {
            if( key.compareTo((K) o.key) > 0 )
                return 1;
            else if(key.compareTo((K) o.key)  == 0)
                return 0;
            else
                return -1;

        }
    }

    private LinkedList<Node<K,V>> [] map;
    public K set [];
    public int current_index;
    int iterator;

    public HashMap(int size)
    {
        map = new LinkedList[size + (size/3)];
        set = (K[]) new Comparable[map.length];
        current_index = -1;
        iterator = -1;
        for( int i = 0 ; i < map.length ; i++)
            map[i] = new LinkedList<>();
    }

    private int Hash(K key)
    {
        return Math.abs(key.hashCode()) % map.length;
    }

    public void put(K key , V value)
    {
        int index = Hash(key);
        LinkedList<Node<K,V>> list = map[index];

        Node<K,V> temp = list.Find( new Node(key,value));

        if(temp == null)
        {
            list.insert(new Node(key, value));
            set[++current_index] = key;
        }
        else
            temp.value = value;

    }

    public V getDefaultvalue(K key , V value)
    {
        int index = Hash(key);

        LinkedList<Node<K,V>> list = map[index];

        Node<K,V> temp = list.Find( new Node(key,value));

        if(temp == null)
            return value;
        else
            return temp.value;
    }

    public V getValue(K key)
    {
        return getDefaultvalue(key,null);
    }

    @Override
    public Iterator<K> iterator()
    {
        Iterator<K> itr = new Iterator<K>()
        {
            @Override
            public boolean hasNext()
            {
                if( set[iterator + 1]!= null)
                    return true;
                else
                    return false;
            }
            @Override
            public K next()
            {
                return set[++iterator];
            }
        }
        ;
        return itr;
    }
}
