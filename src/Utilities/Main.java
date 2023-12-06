package Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        File file = new File("image3.png");

        BufferedImage image = ImageIO.read(file);
        Huffman huffman = new Huffman(image);

        huffman.compressImage();


        for( int i = 0 ; i < huffman.compressionarray.length ; i++)
            System.out.println(huffman.compressionarray[i]);
        System.out.println();
        System.out.println("decompressing");
        System.out.println(huffman.size);

        Image image1 = huffman.decodeImage();

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel imagecontainer = new JLabel(new ImageIcon(image1));
        frame.add(imagecontainer);
        frame.setVisible(true);
    }
}
