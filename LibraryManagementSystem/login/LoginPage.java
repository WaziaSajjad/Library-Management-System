package login;

import menu.AdminPage;
import menu.UserPage;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;    

public class LoginPage implements ActionListener{

    private JTextField username;
    private JPasswordField password;
    private JButton submit;
    private JButton cancel;
    private JButton reset;
    private JRadioButton admin;
    private JRadioButton user;
    public LoginPage(){
        JFrame frame = new JFrame("Library Managment System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBounds(550, 0, 300, 450);
        panel1.setBackground(new Color(176, 163, 150));
        panel1.setPreferredSize(new Dimension(300, 450));

    {       // Library Image
        ImageIcon icon = new ImageIcon("Images/login.png"); 
        Image img = icon.getImage(); // Original image
        Image scaledLoginImage = img.getScaledInstance(550, 450, Image.SCALE_SMOOTH); 
        JLabel loginLabel = new JLabel(new ImageIcon(scaledLoginImage));    
        frame.add(loginLabel, BorderLayout.WEST);
    }   
    
    {   //Label and text field
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBackground(new Color(176, 163, 150));
        panel1.add(panel3, BorderLayout.CENTER);

            //username Label
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(60,60, 93, 40);
        panel3.add(usernameLabel);
        
            //username TextField
        username = new JTextField();
        username.setBounds(130, 70, 120, 25);
        panel3.add(username);

            //password Label
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(60, 93, 90, 40);
        panel3.add(passwordLabel);

            //password Text Box
        password = new JPasswordField();
        password.setBounds(130, 100, 120,25);
        panel3.add(password);

            //admin radio button
        admin = new JRadioButton("Admin");
        admin.setBounds(155, 135, 70, 20);
        admin.setBackground(new Color(176, 163, 150));
        panel3.add(admin);
            //user radio button
        user = new JRadioButton("User");
        user.setBounds(90, 135, 60, 20);
        user.setBackground(new Color(176, 163, 150));
        panel3.add(user, BorderLayout.CENTER);
            //grouping radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(admin);
        group.add(user);
            //Submit button
        submit = new JButton("Submit");
        submit.setBounds(160, 165, 85, 28);
        submit.addActionListener(this);
        panel3.add(submit);
            //cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(70, 165, 85, 28);
        cancel.addActionListener(this);
        panel3.add(cancel);
        //     //reset button
        reset = new JButton("Reset");
        reset.setBounds(120, 200, 85, 30);
        reset.addActionListener(this);
        panel3.add(reset);
            
    }

    {   //Shelf Image
        ImageIcon shelfIcon = new ImageIcon("Images/login2.png");
        Image shelfImage = shelfIcon.getImage();
        Image scaledShelfImage = shelfImage.getScaledInstance(250, 80, Image.SCALE_SMOOTH);      
        ImageIcon scaledShelfIcon = new ImageIcon(scaledShelfImage);
        JLabel ShelfLabel = new JLabel((scaledShelfIcon));
        ShelfLabel.setBounds(10, 10, 250, 80);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setPreferredSize(new Dimension(300, 100));
        panel2.setBackground(new Color(176, 163, 150));
        panel2.add(ShelfLabel); 
        panel1.add(panel2, BorderLayout.NORTH);
    }

        frame.add(panel1, BorderLayout.EAST);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit){
            System.out.println("Submitted");
            if(admin.isSelected() && username.getText().equalsIgnoreCase("Admin") && new String(password.getPassword()).equals("123")){
                System.out.println("Admin Login Successful");
                new AdminPage();
            }
            if(user.isSelected() && username.getText().equalsIgnoreCase("User") && new String(password.getPassword()).equals("123")){
                System.out.println("User Login Successful");
                new UserPage();
            }
        }
        else if(e.getSource() == cancel) {
            System.out.println("Cancelled");
            System.exit(0);
        }
        else if(e.getSource() == reset) {
            System.out.println("Reset");
            username.setText("");
            password.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
