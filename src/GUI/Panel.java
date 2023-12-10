package GUI;
import Utilities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;

public class Panel extends JPanel implements ActionListener
{
    private JTextField user_name_login;
    private JPasswordField passwordField_Login;
    private JPasswordField passwordField_signup;
    private JTextField[] SignUp_Fields = new JTextField[3];
    private Boolean Login_status;
    private JButton Login;
    private JButton Logout;
    private JButton signup;
    private JButton finish;
    Panel()
    {
        Login_status = false;
        this.setLayout(null);
        setOpaque(true);
        InitializeComponents();
        this.setBackground(new Color(40, 40, 40));
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
        signup.setBounds(totalwidth - signup.getWidth() - 20, totalheight - 2*signup.getHeight(), signup.getWidth() , signup.getHeight());
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

        int height = (totalheight - 3*totalheight/40  - 4*totalheight/40)/2;

        for( int i = 4 ; i > 1; i--)
        {
            SignUp_Fields [i - 2] = new JTextField();
            SignUp_Fields[i - 2 ].setSize(totalwidth/6 , totalheight/20);
            if(i == 4)
                SignUp_Fields[i - 2].setBounds((totalwidth - SignUp_Fields[i-2].getWidth())/2 , height , SignUp_Fields[i-2].getWidth(), SignUp_Fields[i-2].getHeight());
            else
                SignUp_Fields[i-2].setBounds((totalwidth - SignUp_Fields[i-2].getWidth())/2 , height + (4-i)*totalheight/40 + (4-i)*SignUp_Fields[i-2].getHeight(), SignUp_Fields[i-2].getWidth(), SignUp_Fields[i-2].getHeight());
            this.add(SignUp_Fields[i-2]);
        }

        passwordField_signup = new JPasswordField();
        passwordField_signup.setSize(totalwidth/6 , totalheight/20);
        passwordField_signup.setBounds((totalwidth - passwordField_signup.getWidth())/2 , height + (3)*totalheight/40 + (3)*passwordField_signup.getHeight(), passwordField_signup.getWidth(), passwordField_signup.getHeight());
        this.add(passwordField_signup);

        addlabel("Username" , totalwidth , totalheight , (height) - totalheight/50);
        addlabel("Age" , totalwidth , totalheight , (height)  + totalheight/40 + totalheight/20 - totalheight/50);
        addlabel("Gender" , totalwidth , totalheight , (height)  + 2*totalheight/40 + 2*totalheight/20 - totalheight/50);
        addlabel("Password" , totalwidth , totalheight , (height) + 3*totalheight/40 + 3*totalheight/20 - totalheight/50);

        finish = new JButton("FINISH");
        finish.setSize(totalwidth/16 , totalheight/ 50);
        finish.setBounds((totalwidth - finish.getWidth())/2, (height) + 3*totalheight/40 + 4*totalheight/20 + totalheight/30, finish.getWidth() , finish.getHeight());
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

        //print Users friends

    }

    public void paint(Graphics g)
    {
        super.paint(g);
        if(Login_status)
        {
            Userpage(g, null);
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
            for( int i = 0 ; i < SignUp_Fields.length ; i++)
            {
                System.out.println(SignUp_Fields[i].getText());
                if( SignUp_Fields[i].getText().equals(""))
                    return;
            }
            removeAll();
            InitializeComponents();
            repaint();
        }
        else if (e.getSource().equals(Login))
        {
            //search the database using the username and then check for the correct password
            removeAll();
            Logout = new JButton("LOGOUT");
            Logout.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 16), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 50));
            Logout.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - Logout.getWidth() - 20, (int) 0.20 * (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), Logout.getWidth(), Logout.getHeight());
            add(Logout);
            Logout.addActionListener(this);
            Login_status = true;
            repaint();
        }
        else if (e.getSource().equals(Logout))
        {
            Login_status = false;
            removeAll();
            InitializeComponents();
            repaint();
        }
    }

    private void addlabel(String s , int totalwidth , int totalheight  , int  y)
    {
        JLabel label = new JLabel(s);
        label.setFont(new Font("sansserif", 0, 15));
        label.setSize(totalwidth/6 , totalheight/80);
        label.setForeground(new Color(224, 213, 213));
        label.setBounds((totalwidth - label.getWidth())/2, y,label.getWidth(), label.getHeight());
        this.add(label);
    }
}
