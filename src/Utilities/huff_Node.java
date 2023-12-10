package Utilities;

public class huff_Node implements Comparable<huff_Node>
{
    int data;
    int frequnency;
    huff_Node left;
    huff_Node right;

    public huff_Node(int data , int frequnency )
    {
        this.data = data;
        this.frequnency = frequnency;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(huff_Node o)
    {
        if( frequnency > o.frequnency)
            return 1;
        else if( frequnency == o.frequnency)
            return 0;
        else
            return -1;
    }
}
