package Utilities;


import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman
{
    private class huff_Node implements Comparable<huff_Node>
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
    private BufferedImage image;

    String []   compressionarray;
    String [][] Final;
    private int currentindex = 0;
    int size = 0;
    private huff_Node root;

    public Huffman(BufferedImage image)
    {
        this.image = image;
    }

    public void compressImage()
    {
        int [][] density = ColordensityMatrix(image);
        Final = new String[density.length][density[0].length];

        HashMap<Integer , Integer> frequencymap = frequencyarray(density);

        java.util.PriorityQueue<huff_Node> queue = new PriorityQueue<>();

        for(int e : frequencymap.keySet())
        {
            queue.add(new huff_Node(e , frequencymap.get(e)));
        }

        while(queue.size() > 1)
        {

            huff_Node node1 = queue.poll();
            int freq1 = node1.frequnency;

            huff_Node node2 = queue.poll();
            int freq2 = node2.frequnency;

            huff_Node par = new huff_Node(0 , freq2+freq1);

            par.left = node1;
            par.right = node2;
            root = par;

            queue.add(par);
            size++;

        }

        compressionarray = new String[size + 1];
        String code ="";
        fill();
        setCompressionarray(root,code ,density);
        currentindex = 0;
    }
    private int [][] ColordensityMatrix(BufferedImage bffr_image)
    {

        int [][] density_matrix = new int[bffr_image.getHeight()][bffr_image.getWidth()];

        for( int i = 0 ; i < density_matrix.length; i++)
        {
            for( int j = 0 ; j < density_matrix[0].length ; j++)
                density_matrix[i][j] = bffr_image.getRGB(j,i);
        }

        return density_matrix;
    }

    private HashMap<Integer, Integer> frequencyarray(int [][] arr)
    {
        HashMap<Integer , Integer > frequencymap = new HashMap<>();

        for( int[] e : arr)
        {
            for (int number : e)
            {
                frequencymap.put(number, frequencymap.getOrDefault(number, 0) + 1);
            }
        }
        return frequencymap;
    }

    public void fill()
    {
        for( int i = 0; i < compressionarray.length ; i++)
            compressionarray[i] = "";
    }
    public boolean isleafnode(huff_Node node)
    {
        if(node == null)
            return false;
        if( node.left == null && node.right == null)
            return true;
        else
            return false;
    }
    public void setCompressionarray(huff_Node node, String s , int [][] density)
    {

        if (node.left == null && node.right == null && node.data != 0)
        {
            compressionarray[currentindex] += s;
            for( int i = 0 ; i < density.length ; i++ )
            {
                for( int j = 0 ; j < density[0].length ;j++)
                {
                    if(node.data  == density[i][j])
                        Final[i][j] = compressionarray[currentindex];
                }
            }
            currentindex++;
            return;
        }


        setCompressionarray(node.left, s + "0" , density);
        setCompressionarray(node.right, s + "1" ,density);
    }


    public BufferedImage decodeImage()
    {
        BufferedImage decodedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);

        for(int y = 0 ; y < decodedImage.getHeight() ; y++)
        {
            for (int x = 0; x < decodedImage.getWidth() ; x++)
            {

                huff_Node currentnode = root;
                int ind = 0;

                while ( !isleafnode(currentnode))
                {
                    char bit = Final[y][x].charAt(ind);
                    ind++;

                    if(bit == '0')
                        currentnode = currentnode.left;
                    if(bit == '1')
                        currentnode = currentnode.right;

                }

                decodedImage.setRGB(x,y,currentnode.data);
            }

        }
        return decodedImage;
    }
}
