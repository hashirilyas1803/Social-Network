package Utilities;


import DataStructures.HashMap;
import DataStructures.PriorityQueue;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Huffman
{
    private BufferedImage image;
    String []  compressionarray;
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

        Final = new String[image.getHeight()][image.getWidth()];

       HashMap<Integer , Integer>  frequencymap = frequencyarray(image);

        DataStructures.PriorityQueue<huff_Node> queue = new PriorityQueue<>(frequencymap.current_index);

        Iterator<Integer> iterator = frequencymap.iterator();

        while (iterator.hasNext())
        {
            Integer i = iterator.next();
            queue.insert(new huff_Node(i, frequencymap.getValue(i)));
        }

        while(queue.getSize() > 1)
        {
            System.out.println("dequeue");
            huff_Node node1 = queue.dequeue();
            int freq1 = node1.frequnency;

            huff_Node node2 = queue.dequeue();
            int freq2 = node2.frequnency;

            huff_Node par = new huff_Node(0 , freq2+freq1);

            par.left = node1;
            par.right = node2;
            root = par;

            queue.insert(par);
            size++;

        }

        compressionarray = new String[size + 1];
        String code ="";
        fill();
        setCompressionarray(root, code , image);
        currentindex = 0;
    }

    private HashMap<Integer , Integer> frequencyarray(BufferedImage bffr_image)
    {
        HashMap<Integer , Integer> frequencymap = new HashMap<>(  (bffr_image.getWidth()* bffr_image.getHeight())/4);

        for( int i = 0 ; i < bffr_image.getHeight(); i++)
        {
            for( int j = 0 ; j < bffr_image.getWidth() ; j++)
                frequencymap.put(bffr_image.getRGB(j,i) , frequencymap.getDefaultvalue(bffr_image.getRGB(j,i) , 0) + 1);
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
    public void setCompressionarray(huff_Node node, String s , BufferedImage Image)
    {
        System.out.println("work");
        if (node.left == null && node.right == null && node.data != 0)
        {
            compressionarray[currentindex] += s;
            for( int i = 0 ; i < image.getHeight() ; i++ )
            {
                for( int j = 0 ; j < image.getWidth() ;j++)
                {
                    if(node.data  == image.getRGB(j,i))
                        Final[i][j] = compressionarray[currentindex];
                }
            }
            currentindex++;
            return;
        }


        setCompressionarray(node.left, s + "0" , Image);
        setCompressionarray(node.right, s + "1" ,Image);
    }


    public BufferedImage decodeImage()
    {
        BufferedImage decodedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

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
