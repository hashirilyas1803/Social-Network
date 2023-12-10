package Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws IOException {
        File file = new File("image3.png");
        File file2 = new File("image3.png");
        File file3 = new File("image.png");



        BufferedImage image = ImageIO.read(file);
        Huffman huffman = new Huffman(image);

        huffman.compressImage();

        System.out.println();
        System.out.println("Compressed");
        System.out.println(huffman.size);

//        Image image1 = huffman.decodeImage();
//
//        JFrame frame = new JFrame();
//        frame.setLayout(new BorderLayout());
//        frame.setSize(400,400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JLabel imagecontainer = new JLabel(new ImageIcon(image1));
//        frame.add(imagecontainer);
//        frame.setVisible(true);
    }
}
