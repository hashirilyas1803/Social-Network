package GUI;
import DataStructures.BTree;
import DataStructures.HashTable;
import Utilities.Huffman;
import Utilities.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements ActionListener
{
    private BTree<User> users;
    private User LoggedUser = null;
    private JTextField user_name_login;
    private JPasswordField passwordField_Login;
    private JPasswordField passwordField_signup;
    private JTextField[] SignUp_Fields = new JTextField[5];
    private JTextField [] user_pagetext = new JTextField[2];
    private JButton [] user_pageButtons = new JButton[2];
    private Boolean Login_status;
    private JButton Login;
    private JButton Logout;
    private JButton signup;
    private JButton finish;
    private Huffman Default;
    private Huffman [] avatars = new Huffman[4];

    Panel(BTree<User> users) throws IOException
    {
        File [] files = {new File("Avatar2.jpeg") , new File("Avatar3.jpeg") , new File("Avatar4.jpeg") , new File("Avatar5.jpg")} ;
        File file =  new File("Avatar.png");

        BufferedImage image = ImageIO.read(file);
        Default = new Huffman(image);
        Default.compressImage();

        BufferedImage [] arr = new BufferedImage[files.length];


        this.users = users;
        Login_status = false;
        this.setLayout(null);
        setOpaque(true);
        InitializeComponents();
        this.setBackground(new Color(40, 40, 40));

        for( int i = 0 ; i < arr.length ; i++)
        {
            arr[i] = ImageIO.read(files[i]);
            avatars[i] = new Huffman(arr[i]);
            avatars[i].compressImage();
        }
    }

    private void InitializeComponents()
    {
        int totalwidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int totalheight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        JLabel head = new JLabel("Login To Your Account");
        head.setFont(new Font("sansserif", 0, 25));
        head.setSize(totalwidth/5 , totalheight/20);
        head.setForeground(new Color(255, 255, 255));
        head.setBounds((totalwidth - head.getWidth())/2,0,head.getWidth(),head.getHeight());
        this.add(head);

        user_name_login = new JTextField();
        user_name_login.setSize(totalwidth/6 , totalheight/20);
        user_name_login.setBounds((totalwidth - user_name_login.getWidth())/2 , (totalheight - totalheight/40  - 2*user_name_login.getHeight())/2 , user_name_login.getWidth(), user_name_login.getHeight());
        this.add(user_name_login);

        passwordField_Login = new JPasswordField();
        passwordField_Login.setSize(totalwidth/6 , totalheight/20);
        passwordField_Login.setBounds((totalwidth - passwordField_Login.getWidth())/2, user_name_login.getY() + user_name_login.getHeight() + totalheight/40,passwordField_Login.getWidth(),passwordField_Login.getHeight());
        this.add(passwordField_Login);

        addlabel("Username" , totalwidth , totalheight , (totalheight - totalheight/40  - 2*totalheight/20)/2 - totalheight/50);
        addlabel("Password" , totalwidth , totalheight , (totalheight - totalheight/40  - 2*totalheight/20)/2 + totalheight/40 + totalheight/20 - totalheight/50 );

        Login = new JButton("LOGIN");
        Login.setSize(totalwidth/16 , totalheight/ 50);
        Login.setBounds((totalwidth - Login.getWidth())/2 , passwordField_Login.getY() + totalheight/15 , Login.getWidth() , Login.getHeight());
        this.add(Login);

        signup = new JButton("SIGNUP");
        signup.setSize(totalwidth/16 , totalheight/ 50);
        signup.setBounds(totalwidth - signup.getWidth() - 20, totalheight - 8*signup.getHeight(), signup.getWidth() , signup.getHeight());
        this.add(signup);

        signup.addActionListener(this);
        Login.addActionListener(this);
        user_name_login.addActionListener(this);
        passwordField_Login.addActionListener(this);

    }

    public void PaintSecondary()
    {
        int totalwidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int totalheight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        JLabel head = new JLabel("SIGN UP");
        head.setFont(new Font("sansserif", 0, 30));
        head.setSize(totalwidth/12 , totalheight/20);
        head.setForeground(new Color(224, 213, 213));
        head.setBounds((totalwidth - head.getWidth())/2,0,head.getWidth(),head.getHeight());
        this.add(head);

        int height = (totalheight - 5*totalheight/40  - 6*totalheight/20)/2;

        for( int i = 6 ; i > 1; i--)
        {
            SignUp_Fields [i - 2] = new JTextField();
            SignUp_Fields[i - 2 ].setSize(totalwidth/6 , totalheight/20);

            if(i == 6)
                SignUp_Fields[i - 2].setBounds((totalwidth - SignUp_Fields[i-2].getWidth())/2 , height , SignUp_Fields[i-2].getWidth(), SignUp_Fields[i-2].getHeight());
            else
                SignUp_Fields[i-2].setBounds((totalwidth - SignUp_Fields[i-2].getWidth())/2 , height + (6-i)*totalheight/40 + (6-i)*SignUp_Fields[i-2].getHeight(), SignUp_Fields[i-2].getWidth(), SignUp_Fields[i-2].getHeight());

            this.add(SignUp_Fields[i-2]);
            SignUp_Fields[i-2].addActionListener(this);
        }


        passwordField_signup = new JPasswordField();
        passwordField_signup.setSize(totalwidth/6 , totalheight/20);
        passwordField_signup.setBounds((totalwidth - passwordField_signup.getWidth())/2 , height + (5)*totalheight/40 + (5)*passwordField_signup.getHeight(), passwordField_signup.getWidth(), passwordField_signup.getHeight());
        this.add(passwordField_signup);

        addlabel("Profile Picture" , totalwidth , totalheight ,(height) - totalheight/50 );
        addlabel("Name" , totalwidth , totalheight ,(height)  + totalheight/40 + totalheight/20 - totalheight/50 );
        addlabel("Username" , totalwidth , totalheight , (height)  + 2*totalheight/40 + 2*totalheight/20 - totalheight/50);
        addlabel("Age" , totalwidth , totalheight , (height)  + 3*totalheight/40 + 3*totalheight/20 - totalheight/50);
        addlabel("Gender" , totalwidth , totalheight , (height) + 4*totalheight/40 + 4*totalheight/20 - totalheight/50);
        addlabel("Password" , totalwidth , totalheight , (height) + 5*totalheight/40 + 5*totalheight/20 - totalheight/50);

        finish = new JButton("FINISH");
        finish.setSize(totalwidth/16 , totalheight/ 50);
        finish.setBounds((totalwidth - finish.getWidth())/2, (height) + 5*totalheight/40 + 6*totalheight/20 + totalheight/30, finish.getWidth() , finish.getHeight());
        this.add(finish);

        finish.addActionListener(this);
    }


    public void Userpage(Graphics g ,User user)
    {
        int totalwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int totalheight = Toolkit.getDefaultToolkit().getScreenSize().height;

        g.setColor(Color.white);
        g.drawLine(0,(int)(totalheight*0.23) , totalwidth , (int)(totalheight*0.23));
        g.drawRect((int) (totalwidth*0.15), (int)(totalheight*0.20), totalheight/10,totalheight/10);
        g.drawRect((int) (totalwidth*0.15) - 5, (int)(totalheight*0.20) - 5, totalheight/10 + 10,totalheight/10 + 10);
        g.fillRect((int) (totalwidth*0.15) - 5, (int)(totalheight*0.20) - 5, totalheight/10 + 10,totalheight/10 + 10);
        g.setColor(new Color(40, 40, 40));
        g.fillRect((int) (totalwidth*0.15), (int)(totalheight*0.20), totalheight/10,totalheight/10);
        g.setColor(Color.white);
        g.setFont(new Font("sansserif", 0, 25));
        g.drawString(user.getName() ,(int) (totalwidth*0.15) - 5 , (int)(totalheight*0.20) + totalheight/10 + 30 );
        g.setFont(new Font("sansserif", 0, 20));
        g.drawString("@"+user.getUsername() , (int) (totalwidth*0.15) - 5 , (int)(totalheight*0.20) + totalheight/10 + 55);
        g.drawString(user.getGender() ,(int) (totalwidth*0.15) - 5 , (int)(totalheight*0.20) + totalheight/10 + 75);
        g.drawString("," + user.getAge() ,(int) (totalwidth*0.15) + 50 , (int)(totalheight*0.20) + totalheight/10 + 75);

        Image profile = user.getImage().decodeImage().getScaledInstance(totalheight/10,totalheight/10 ,Image.SCALE_DEFAULT);
        g.drawImage(profile ,(int) (totalwidth*0.15), (int)(totalheight*0.20) , null );
        g.setColor(Color.white);
        g.drawRect(((int) (totalwidth*0.15)) + 5 + totalwidth/10 , (int)(totalheight*0.20) + 5 + 2*totalheight/10 , (totalwidth - ((int)(totalheight*0.20) + 5 + totalheight/10))/2 ,(totalheight - ((int)(totalheight*0.20) + 5 + totalheight/10))/2 );

        User [] friends = user.getFriend();
        if(friends.length == 0)
            return;
        int friend_width = (totalwidth - ((int)(totalheight*0.20) + 5 + totalheight/10))/4;
        int friend_height = (totalheight - ((int)(totalheight*0.20) + 5 + totalheight/10))/friends.length;
        int start_X = (int) (totalwidth*0.15) + 5 + totalwidth/10;
        int start_Y = (int)(totalheight*0.20) + 5 + 2*totalheight/10;
        for( int i = 0 ; i < friends.length/2 ;i++)
        {
            for(int j = 0 ; j < 2 ; j++)
            {
                drawfriendbar(start_X + i*friend_width,start_Y + i*friend_height , friend_width , friend_height ,friends[i+j] ,g);
            }
        }

    }

    public void paint(Graphics g)
    {
        super.paint(g);
        if(Login_status)
        {
            Userpage(g, LoggedUser);
            repaint();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {

        Toolkit.getDefaultToolkit().sync();
        if (e.getSource().equals(signup))
        {
            removeAll();
            PaintSecondary();
            repaint();
        }
        else if (e.getSource().equals(finish))
        {
            for( int i = 0 ; i < SignUp_Fields.length - 1 ; i++)
            {
                System.out.println(SignUp_Fields[i].getText());
                if( SignUp_Fields[i].getText().equals(""))
                    return;
            }
            if( users.search(new User("", SignUp_Fields[2].getText() , passwordField_signup.getText(), 0 , false,null)) != null )
                return;
            else
            {
                Boolean gender = false;
                if(SignUp_Fields[0].getText().equalsIgnoreCase("male"))
                    gender = true;

                User creation = new User(SignUp_Fields[3].getText(), SignUp_Fields[2].getText(), passwordField_signup.getText(), Integer.parseInt(SignUp_Fields[1].getText()), gender, null);
                if(SignUp_Fields[4].getText().equals(""))
                    creation.setImage(Default);
                else {
                    char opt = SignUp_Fields[4].getText().charAt(SignUp_Fields[4].getText().length() - 1);
                    switch (opt)
                    {
                        case '2':
                                creation.setImage(avatars[0]);
                                break;
                        case '3':
                                creation.setImage(avatars[1]);
                                break;
                        case '4':
                                creation.setImage(avatars[2]);
                                break;
                        case '5':creation.setImage(avatars[3]);
                                break;
                    }
                }
                users.insert(creation);
            }

            removeAll();
            InitializeComponents();
            repaint();
        }
        else if (e.getSource().equals(Login))
        {

            int totalwidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
            int totalheight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
            System.out.println("logged");
            //search the database using the username and then check for the correct password
            User logger = new User("", user_name_login.getText() , passwordField_Login.getText(), 0 , false,null);
            System.out.println("secondlog");
            User backtrack =  users.search(logger);
            System.out.println(backtrack);
            if( backtrack == null) {
                return;
            }

            if( !(backtrack.getPassword().equals(logger.getPassword())))
                return;

            LoggedUser = backtrack;
            removeAll();

            Logout = new JButton("LOGOUT");
            Logout.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 16), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 50));
            Logout.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - Logout.getWidth() - 20, (int) 0.20 * (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), Logout.getWidth(), Logout.getHeight());
            add(Logout);
            Logout.addActionListener(this);

            Login_status  = true;

            user_pagetext[0] = new JTextField();
            user_pagetext[0].setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/20 ,(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/80 );
            user_pagetext[0].setBounds(totalwidth - (int) (totalwidth*0.15) - 5 , (int)(totalheight*0.20) + totalheight/10 + 55, user_pagetext[0].getWidth() , user_pagetext[0].getHeight());
            add(user_pagetext[0]);

            user_pageButtons[0] = new JButton("ADD FRIEND");
            user_pageButtons[0].setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 16) , (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 50) );
            user_pageButtons[0].setBounds(user_pagetext[0].getX() + (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/50,(int)((int)( Toolkit.getDefaultToolkit().getScreenSize().getHeight())*0.20) - 5, user_pageButtons[0].getWidth() ,user_pageButtons[0].getHeight());
            add(user_pageButtons[0]);

            repaint();
        }
        else if (e.getSource().equals(Logout))
        {
            Login_status = false;
            LoggedUser = null;
            removeAll();
            InitializeComponents();
            repaint();
        }
    }

    private void addlabel(String s , int totalwidth , int totalheight  , int  y)
    {
        JLabel label = new JLabel(s);
        label.setFont(new Font("sansserif", 0, 15));
        label.setSize(totalwidth/6 , totalheight/70);
        label.setForeground(new Color(224, 213, 213));
        label.setBounds((totalwidth - label.getWidth())/2, y,label.getWidth(), label.getHeight());
        this.add(label);
    }

    private void drawfriendbar(int x , int y , int width , int height,  User user, Graphics g)
    {
        g.drawRect(x,y,width,height);
        g.drawString( user.toString(), x + width/10 , y + height/20 );
    }
}
