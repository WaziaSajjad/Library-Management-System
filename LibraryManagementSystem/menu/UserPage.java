package menu;

import login.LoginPage;
import menu.AdminPage.Book;
import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserPage implements ActionListener{

    JFrame frame;
    public String rate;
    ArrayList<Book> booksReadString = new ArrayList<>();

    public UserPage(){
        booksReadString.add(new Book("DSA", "S001", "Lia", "Programming"));
        booksReadString.add(new Book("Entrepreneurship", "E001", "Ethan", "Business"));
        AdminPage.Books.add(new Book("Poetry", "L001", "Wazia", "Literature"));
        AdminPage.Books.add(new Book("OOP", "A001", "Wazia", "Programming"));
        AdminPage.Books.add(new Book("Data Structures", "A002", "John Doe", "Computer Science"));
        AdminPage.Books.add(new Book("History of Pakistan", "H001", "Ali Khan", "History"));
        
        frame = new JFrame("User Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150,650);
        frame.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4, 70, 50));
        buttonPanel.setBackground(new Color(253, 243, 221));      //cream

    {       //LIBRARY button
        JButton library = new JButton();
        library.setText("Wazia's Library");
        library.setFont(new Font("Edwardian Script ITC",Font.BOLD, 25));
        library.addActionListener(this);
        buttonPanel.add(library);
        ImageIcon librImageIcon = new ImageIcon("Images/libr.png");
        Image imglibr = librImageIcon.getImage(); // Get the original image
        Image resizedLibrImg = imglibr.getScaledInstance(150, 100, Image.SCALE_SMOOTH); // Resize to 50x50
        ImageIcon resizedLibrIcon = new ImageIcon(resizedLibrImg);
        library.setIcon(resizedLibrIcon);
        library.setHorizontalTextPosition(JButton.CENTER);
        library.setVerticalTextPosition(JButton.BOTTOM);
        library.setFocusable(false);
        library.setBackground(new Color(250, 220, 230));
        library.setIconTextGap(20);
    }

        String[] buttonTexts = {
            "Books Issued", "Issue Book", "Books Read",
            "Account", "Give Feedback", "Give Rating", "Log Out"
        };
        String[] iconPaths = {
            "Images/issued.png", "Images/issueBook.png", "Images/booksRead.png",
            "Images/account.png", "Images/feedback.png", "Images/rating.png", "Images/logout.png"
        };
        Color[] buttonColors = {
             new Color(230, 220, 250),  // Button 1 color
            new Color(211, 241, 245),  // Button 2 color
            new Color(250, 235, 200),  // Button 3 color (example)
            new Color(255, 200, 200),  // Button 3 color (example)
            new Color(200, 255, 200),  // Button 3 color (example)
            new Color(252, 227, 144),  // Button 3 color (example)
            new Color(154, 184, 230)   // Button 4 color (example)
        };
        Color[] buttonTextColors = {
            new Color(204, 0, 0),  
            new Color(202, 170, 205),  
            new Color(152, 171, 136),  
            new Color(0,0,0),  
            new Color(55, 82, 101), 
            new Color(239, 96, 30),  
            new Color(54, 54, 255)   
        };
        
        for (int i = 0; i < buttonTexts.length; i++) {
            JButton button = new JButton(buttonTexts[i]);
            button.setFont(new Font("Edwardian Script ITC", Font.BOLD, 30));   
            try {
                ImageIcon icon = new ImageIcon(iconPaths[i]);
                Image img = icon.getImage(); 
                Image resizedImg;
                if (i == 3) {
                    resizedImg = img.getScaledInstance(110, 130, Image.SCALE_SMOOTH); 
                    button.setIconTextGap(10);

                } else {
                    resizedImg = img.getScaledInstance(170, 80, Image.SCALE_SMOOTH);
                    button.setIconTextGap(25);
                }
                ImageIcon resizedIcon = new ImageIcon(resizedImg);
                button.setIcon(resizedIcon);
            } catch (Exception e) {
                System.err.println("Icon not found for: " + buttonTexts[i]);
            }           
            button.setActionCommand(buttonTexts[i]);
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(20, 250)); 
            button.setForeground(buttonTextColors[i]);
            button.setBackground(buttonColors[i]);
            button.setFocusable(false);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.BOTTOM);
            buttonPanel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70)); 
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if (command.equals("Wazia's Library")) {
            System.out.println("Library button clicked!");
            JFrame about = new JFrame("About this library");
            about.setLayout(new BorderLayout());
            about.setSize(500, 350);
            about.setLocationRelativeTo(null);
            about.setBackground(new Color(253, 243, 221));
            about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            JPanel centerPanel = new JPanel();
            centerPanel.setBorder(BorderFactory.createLineBorder(new Color(253, 243, 221), 80,true)); 
            centerPanel.setBackground(new Color(250, 220, 230));

            JTextArea paragraph = new JTextArea("This library management system is made using several Java OOP concepts. It offers numerous functions for users, some of them being; Issuing a book, Returning a book, Keeping a record of books they've read, giving a rating to each book, writing feedback for the administrators of this management system viewing and editing their account also to log out from their account.\r\n");
            paragraph.setFont(new Font("Serif", Font.PLAIN, 14));
            paragraph.setLineWrap(true); // Wrap text at word boundaries
            paragraph.setWrapStyleWord(true);
            paragraph.setPreferredSize(new Dimension(300, 150)); // Adjust size as needed
            paragraph.setOpaque(false); // Make the background transparent to match the panel
            paragraph.setEditable(false); // Disable editing
            centerPanel.add(paragraph);


            about.add(centerPanel, BorderLayout.CENTER);
            about.setVisible(true);
        } else if (command.equals("Books Issued")) {
            System.out.println("Books Issued button clicked!");
            JFrame frame = new JFrame("Books Issued");
            frame.setSize(300,300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            String[] columnHeadings = {"ID", "Name", "Author", "Genre"};
            String data[][] = new String[AdminPage.booksIssued.size()][4];
            for (int i = 0; i < AdminPage.booksIssued.size(); i++) {
                data[i][0] = AdminPage.booksIssued.get(i).id;
                data[i][1] = AdminPage.booksIssued.get(i).name;
                data[i][2] = AdminPage.booksIssued.get(i).Author;
                data[i][3] = AdminPage.booksIssued.get(i).Genre;
            }
            JTable table = new JTable(data, columnHeadings);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);
            frame.setVisible(true);
        } else if (command.equals("Issue Book")) {
            System.out.println("Issue Book button clicked!");
            if (AdminPage.Books.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No books available to issue.");
                return;
            }
        
            String[] columnNames = {"ID", "Name", "Author", "Genre"};
            String[][] data = new String[AdminPage.Books.size()][4];
            
            for (int i = 0; i < AdminPage.Books.size(); i++) {
                data[i][0] = AdminPage.Books.get(i).id;
                data[i][1] = AdminPage.Books.get(i).name;
                data[i][2] = AdminPage.Books.get(i).Author;
                data[i][3] = AdminPage.Books.get(i).Genre;
            }
            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };
            
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
                
            int option = JOptionPane.showConfirmDialog(frame, scrollPane, "Select a Book to Issue", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
            if (option != JOptionPane.OK_OPTION) return; 
                
            int selectedRow = table.getSelectedRow();
                
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "No book selected.");
                return;
            }
            
            String selectedBookID = data[selectedRow][0];
            
            // Find the book object by ID
            Book selectedBook = null;
            for (Book book : AdminPage.Books) {
                if (book.id.equals(selectedBookID)) {
                    selectedBook = book;
                    break;
                }
            }
            
            // Issue the book if found
            if (selectedBook != null) {
                AdminPage.Books.remove(selectedBook);
                AdminPage.booksIssued.add(selectedBook);
                JOptionPane.showMessageDialog(frame, "Book issued successfully: " + selectedBook.name);
            } else {
                JOptionPane.showMessageDialog(frame, "Error: Book not found.");
            }
            
        } else if (command.equals("Books Read")) {
            System.out.println("Books Read button clicked!");
            JFrame frame = new JFrame("Books Read");
            frame.setSize(300,300);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            String[] columnHeadings = {"ID", "Name", "Author", "Genre"};
            DefaultTableModel model = new DefaultTableModel(columnHeadings, 0);
            JTable table = new JTable(model);

            JScrollPane scrollPane = new JScrollPane(table);
            for (Book book : booksReadString) {
                model.addRow(new String[]{book.id, book.name, book.Author, book.Genre});
            }
            JButton addButton = new JButton("Add Book");
            addButton.addActionListener((ActionEvent b) -> {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(new JLabel("Book Name: "));   
                JTextField nameField = new JTextField();     
                panel.add(nameField);
                    
                panel.add(new JLabel("Book ID: "));
                JTextField IDField = new JTextField();
                panel.add(IDField);

                panel.add(new JLabel("Author Name: "));
                JTextField AuthorField = new JTextField();
                panel.add(AuthorField);
                
                panel.add(new JLabel("Genre: "));
                JTextField GenreField = new JTextField();
                panel.add(GenreField);
                int responce = JOptionPane.showConfirmDialog(null, panel, "Add Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                if (responce == JOptionPane.OK_OPTION) {
                    String name = nameField.getText().trim();
                    String ID = IDField.getText().trim();
                    String Author = AuthorField.getText().trim();
                    String Genre = GenreField.getText().trim();
                    if (name.isEmpty() || ID.isEmpty() || Author.isEmpty() || Genre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                    Book newBook = new Book(name, ID, Author, Genre);
                    booksReadString.add(newBook);
                    model.addRow(new String[]{ID, name, Author, Genre});
                    }
                }
            });
            frame.add(addButton, BorderLayout.SOUTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        } else if (command.equals("Account")) {
            System.out.println("Account button clicked!");
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Name"));
            JTextField name = new JTextField("Wazia Sajjad");
            name.setEditable(false);
            panel.add(name);

            panel.add(new JLabel("User ID"));
            JTextField ID = new JTextField("A001");
            panel.add(ID);
            ID.setEditable(false);

            panel.add(new JLabel("Post"));
            JTextField post = new JTextField("Student");
            post.setEditable(false);
            panel.add(post);

            JOptionPane.showMessageDialog(null, panel, "Add User", JOptionPane.PLAIN_MESSAGE);
        } else if (command.equals("Give Feedback")) {
            System.out.println("Give Feedback button clicked!");
            JFrame frame = new JFrame("Feedback Form");
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
            panel.setBackground(new Color(250, 220, 230));

            JTextArea textArea = new JTextArea(10, 30);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setFont(new Font("Serif", Font.PLAIN, 16));

            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            
            // Auto-scroll to the bottom when text is added
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                }
            });

            JButton submitButton = new JButton("Submit Feedback");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String feedback = textArea.getText();
                    if (!feedback.trim().isEmpty()) {
                        saveFeedbackToFile(feedback);
                        JOptionPane.showMessageDialog(frame, "Feedback submitted!");
                        textArea.setText(""); 
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter feedback before submitting.");
                    }
                }
            });
            panel.add(submitButton, BorderLayout.SOUTH);
            panel.add(scrollPane, BorderLayout.CENTER);
            frame.add(panel);
            frame.setVisible(true);
        } else if (command.equals("Give Rating")) {
            System.out.println("Give Rating button clicked!");
            JFrame frame = new JFrame("Give Rating");
            frame.setSize(500, 205);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(new Color(253, 243, 221));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20,30,20,20)); 
            mainPanel.setLayout(new GridLayout(3,1,10,10));

            JPanel panel1 = new JPanel();
            panel1.setBackground(new Color(253, 243, 221));
            JLabel panel1Label = new JLabel("Rate the Library");
            panel1Label.setFont(new Font("Felix Titling", Font.PLAIN, 25));
            panel1.add(panel1Label);

            JPanel panel2 = new JPanel();
            panel2.setBackground(new Color(253, 243, 221));
            panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
            JPanel panel3 = new JPanel();
            panel3.setLayout(null);
            panel3.setBackground(new Color(253, 243, 221));

            // Rating buttons
            String[] ratingButtons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            for (String rating : ratingButtons) {
                JButton button = new JButton(rating);
                button.setBackground(new Color(245, 245, 144));
                button.setActionCommand(rating);
                button.addActionListener((ActionEvent event) -> {
                    rate = event.getActionCommand();
                    try (FileWriter writer = new FileWriter("ratings.txt", true)) {
                        writer.write(rate + "\n");
                    } catch (IOException p) {
                        p.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Thank you for your response", "Rating Confirmed", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                });
                panel2.add(button); // Add button directly to panel1 (no BorderLayout.CENTER needed)
            }

            JLabel label1 = new JLabel("Dissatisfied");
            label1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            label1.setBounds(10, 2, 80, 30);
            panel3.add(label1);
            
            JLabel label2 = new JLabel("Neutral");
            label2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            label2.setBounds(190, 2, 80, 30);
            panel3.add(label2); // Second label in the center
            
            JLabel label3 = new JLabel("Satisfied");
            label3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            label3.setBounds(380, 2, 80, 30);
            panel3.add(label3); // Third label on the right

            mainPanel.add(panel1);
            mainPanel.add(panel2);
            mainPanel.add(panel3);
            frame.add(mainPanel);
            frame.setVisible(true);
        } else if (command.equals("Log Out")) {
            System.out.println("Log Out button clicked!");
            int choice = JOptionPane.showConfirmDialog(null,"Back to Home page", "Loging Out", JOptionPane.OK_CANCEL_OPTION);
            if (choice == JOptionPane.OK_OPTION) {
                frame.dispose();
                new LoginPage();
                return;
            }
        }
    }
    private static void saveFeedbackToFile(String feedback) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt", true))) {
            writer.write(feedback + "\n--------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new UserPage();
    }
} 