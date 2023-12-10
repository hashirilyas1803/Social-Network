package GUI;

import DataStructures.BTree;
import Utilities.Huffman;
import Utilities.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class main
{
    private static final String[] MALE_NAMES = {"John", "Michael", "David", "Christopher", "James", "Daniel", "Matthew", "Andrew"};
    private static final String[] FEMALE_NAMES = {"Emily", "Emma", "Olivia", "Sophia", "Ava", "Isabella", "Mia", "Abigail"};
    private static final Random random = new Random();
    public static void main(String[] args) throws IOException
    {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        frame.setBackground(new Color(40, 40, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BTree<User> userBTree = new BTree<>(500);

        File file = new File("Avatar.png");
        BufferedImage image = ImageIO.read(file);
        Huffman tree = new Huffman(image);
        tree.compressImage();


        // Generate and insert 100,000 users
        for (int i = 0; i < 100000; i++) {
            User user = generateRandomUser();
            user.setImage(tree);
            userBTree.insert(user);
        }
//
        // Establish random friendships
        for (int i = 0; i < 1000; i++)
        {
            User user1 = userBTree.search(new User("",generateRandomUsername() , "", 0 , false,null));
            User user2 = userBTree.search(new User("",generateRandomUsername() , "", 0 , false,null));
            if (user1 != null && user2 != null && !user1.equals(user2))
            {
                user1.addFriend(user2);
            }
        }

        Panel panel = new Panel(userBTree);
        frame.add(panel);
        frame.setVisible(true);
        System.out.println(userBTree.numberOfUsers);
        userBTree.traverse();
    }

    private static User generateRandomUser() throws IOException {
        String name = getRandomName();
        String username = generateRandomUsername();
        String password = "password"; // You may want to use a more secure password generation method
        int age = random.nextInt(50) + 18; // Random age between 18 and 67
        boolean gender = random.nextBoolean();
        return new User(name, username, password, age, gender , null);
    }

    private static String getRandomName()
    {
        String[] names = random.nextBoolean() ? MALE_NAMES : FEMALE_NAMES;
        return names[random.nextInt(names.length)];
    }

    private static String  generateRandomUsername()
    {
        String temp = "user" + random.nextInt(1000);
        return temp;
    }
}
