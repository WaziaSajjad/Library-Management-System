package menu;

import login.LoginPage;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;    
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class AdminPage implements ActionListener{


    static ArrayList<User> Users = new ArrayList<>();
    static ArrayList<Book> Books = new ArrayList<>();
    static ArrayList<Book> booksIssued = new ArrayList<>();

    private JMenuItem viewItem, removeItem, addItem;
    private JMenuItem addBookItem, viewBookItem, deleteBookItem, editBookItem;
    private JMenuItem feedbackItem, ratingItem;

    public AdminPage(){

        Books.add(new Book("OOP", "A001", "Wazia", "Programming"));
        Books.add(new Book("Poetry", "L001", "Wazia", "Literature"));
        Books.add(new Book("Data Structures", "A002", "John Doe", "Computer Science"));
        Books.add(new Book("History of Pakistan", "H001", "Ali Khan", "History"));

        Users.add(new User("Ali", "U001", "09876", "ali@gmail.com"));
        Users.add(new User("David", "U002", "0765", "david@gmail.com"));
        Users.add(new User("Alice", "U003", "3412", "alice@gmail.com"));

        JFrame frame = new JFrame("Admin Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,450);
        frame.setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();

        JMenu userMenu = new JMenu("User");
        JMenu bookMenu = new JMenu("Book");
        JMenu responceMenu = new JMenu("Responce");
        
        viewItem = new JMenuItem("View Users");
        removeItem = new JMenuItem("Remove User");
        addItem = new JMenuItem("Add User");

        addBookItem = new JMenuItem("Add Book");
        viewBookItem = new JMenuItem("View Book");
        deleteBookItem = new JMenuItem("Delete Book");
        editBookItem = new JMenuItem("Edit Book Info");

        feedbackItem = new JMenuItem("View Feedback");
        ratingItem = new JMenuItem("View library Rating");

        viewItem.addActionListener(this);
        removeItem.addActionListener(this);
        addItem.addActionListener(this);
        addBookItem.addActionListener(this);
        viewBookItem.addActionListener(this);
        deleteBookItem.addActionListener(this);
        editBookItem.addActionListener(this);
        feedbackItem.addActionListener(this);
        ratingItem.addActionListener(this);

        userMenu.add(viewItem);
        userMenu.add(removeItem);
        userMenu.add(addItem);

        bookMenu.add(addBookItem);
        bookMenu.add(viewBookItem);
        bookMenu.add(deleteBookItem);
        bookMenu.add(editBookItem);

        responceMenu.add(feedbackItem);
        responceMenu.add(ratingItem);

        menuBar.add(userMenu);
        menuBar.add(bookMenu);
        menuBar.add(responceMenu);

        String[] adminButtons = {"Account", "Log Out"};
        for (int i = 0; i < adminButtons.length; i++) {
            JButton button = new JButton(adminButtons[i]);
            button.setFocusable(false);
            button.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
            button.setForeground(new Color(0));
            button.setBackground(new Color(176, 163, 150));
            if (i== 0) {
                button.setBounds(650, 230, 130, 50);
                button.setActionCommand(adminButtons[i]);
                button.addActionListener((ActionEvent e) ->{
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    
                    panel.add(new JLabel("Name"));
                    JTextField name = new JTextField("Wazia Sajjad");
                    name.setEditable(false);
                    panel.add(name);

                    panel.add(new JLabel("Employee ID"));
                    JTextField ID = new JTextField("EP001");
                    panel.add(ID);
                    ID.setEditable(false);

                    panel.add(new JLabel("Post"));
                    JTextField post = new JTextField("Library Manager");
                    post.setEditable(false);
                    panel.add(post);

                    JOptionPane.showMessageDialog(null, panel, "Add User", JOptionPane.PLAIN_MESSAGE);
                    });
                
            } else {
                button.setBounds(650, 300, 130, 50);
                button.setActionCommand(adminButtons[i]);
                button.addActionListener((ActionEvent e) ->{
                    new LoginPage();
                    frame.dispose();
                });
            }
            frame.add(button);
            
        }

        ImageIcon icon = new ImageIcon("Images/admin.png");

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(850, 450, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER); 
        imageLabel.setVerticalAlignment(JLabel.NORTH);   

        frame.setLayout(new BorderLayout());
        frame.add(imageLabel, BorderLayout.NORTH);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

        //functions
    public void ViewUsers(){
        JFrame viewUserFrame = new JFrame();
        viewUserFrame.setSize(300,200);
        viewUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewUserFrame.setLocationRelativeTo(null);

        String[] columnHeadings = {"ID", "Name", "Phone Number", "Email"};

        //TAble
        String data[][] = new String[Users.size()][4];
        for (int i = 0; i < Users.size(); i++) {
            data[i][0] = Users.get(i).id;
            data[i][1] = Users.get(i).name;
            data[i][2] = Users.get(i).phoneNumber;
            data[i][3] = Users.get(i).email;
        }

        JTable userTable = new JTable(data, columnHeadings);
        JScrollPane scrollPane = new JScrollPane(userTable);

        viewUserFrame.add(scrollPane, BorderLayout.CENTER);
        viewUserFrame.setVisible(true);
    }
    public void RemoveUser() {
        JFrame frame = new JFrame();
        if (Users.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No users available to remove.");
        }
        String[] columnNames = {"ID", "Name", "Phone", "Email"};
            String[][] data = new String[AdminPage.Users.size()][4];
            
            for (int i = 0; i < AdminPage.Users.size(); i++) {
                data[i][0] = AdminPage.Users.get(i).id;
                data[i][1] = AdminPage.Users.get(i).name;
                data[i][2] = AdminPage.Users.get(i).phoneNumber;
                data[i][3] = AdminPage.Users.get(i).email;
            }
            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };
            
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
                
            int option = JOptionPane.showConfirmDialog(frame, scrollPane, "Select a User to remove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
            if (option != JOptionPane.OK_OPTION) return; 
                
            int selectedRow = table.getSelectedRow();
                
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "No user selected.");
                return;
            }
            
            String selectedUserID = data[selectedRow][0];
            
            User selectedUser = null;
            for (User user : AdminPage.Users) {
                if (user.id.equals(selectedUserID)) {
                    selectedUser = user;
                    break;
                }
            }
            
            if (selectedUser != null) {
                AdminPage.Users.remove(selectedUser);
                JOptionPane.showMessageDialog(frame, "User Removed successfully: " + selectedUser.name);
            } else {
                JOptionPane.showMessageDialog(frame, "Error: User not found.");
            }
    }
    public void AddUsers(){
        while(true){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.add(new JLabel("Username: "));   
            JTextField nameField = new JTextField();     
            panel.add(nameField);
            
            panel.add(new JLabel("User ID: "));
            JTextField IDField = new JTextField();
            panel.add(IDField);

            panel.add(new JLabel("Phone Number: "));
            JTextField PhoneField = new JTextField();
            panel.add(PhoneField);
            
            panel.add(new JLabel("Email: "));
            JTextField emailField = new JTextField();
            panel.add(emailField);

            int responce = JOptionPane.showConfirmDialog(null, panel, "Add User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (responce == JOptionPane.CANCEL_OPTION || responce == JOptionPane.CLOSED_OPTION) {
                break;
            }

            String name = nameField.getText().trim();
            String ID = IDField.getText().trim();
            String Phone = PhoneField.getText().trim();
            String Email = emailField.getText().trim();

            if (name.isEmpty() || ID.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
            }
            boolean isDuplicate = false;
            for (User user : Users) {
                if (user.id.equalsIgnoreCase(ID) || user.name.equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "User with same ID or Name already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                continue;
            }
            Users.add(new User(name, ID, Phone, Email));
            JOptionPane.showMessageDialog(null, "User successfully added", "Error", JOptionPane.INFORMATION_MESSAGE);
            break;    
        }
    }
    public void AddBook(){
        
        while(true){
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
            
            if (responce == JOptionPane.CANCEL_OPTION || responce == JOptionPane.CLOSED_OPTION) {
                break;
            }

            String name = nameField.getText().trim();
            String ID = IDField.getText().trim();
            String Author = AuthorField.getText().trim();
            String Genre = GenreField.getText().trim();

            if (name.isEmpty() || ID.isEmpty() || Author.isEmpty() || Genre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            boolean isDuplicate = false;
            for (Book book : Books) {
                if (book.id.equalsIgnoreCase(ID) || book.name.equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "User with same ID or Name already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                continue;
            }
            Books.add(new Book(name, ID, Author, Genre));
            JOptionPane.showMessageDialog(null, "Book successfully added", "Error", JOptionPane.INFORMATION_MESSAGE);
            break;    
        }
    }
    public void viewBookItem(){
        JFrame viewBookFrame = new JFrame();
        viewBookFrame.setSize(300,200);
        viewBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewBookFrame.setLocationRelativeTo(null);

        String[] columnHeadings = {"ID", "Book Name", "Author Name", "Genre"};

        //TAble
        String data[][] = new String[Books.size()][4];
        for (int i = 0; i < Books.size(); i++) {
            data[i][0] = Books.get(i).id;
            data[i][1] = Books.get(i).name;
            data[i][2] = Books.get(i).Author;
            data[i][3] = Books.get(i).Genre;
        }

        JTable bookTable = new JTable(data, columnHeadings);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        viewBookFrame.add(scrollPane, BorderLayout.CENTER);
        viewBookFrame.setVisible(true);
    }
    public void deleteBookItem(){
        System.out.println("Issue Book button clicked!");
        JFrame frame = new JFrame();
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
                
            int option = JOptionPane.showConfirmDialog(frame, scrollPane, "Select a Book to delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
            if (option != JOptionPane.OK_OPTION) return; 
                
            int selectedRow = table.getSelectedRow();
                
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "No book selected.");
                return;
            }
            
            String selectedBookID = data[selectedRow][0];
            
            Book selectedBook = null;
            for (Book book : AdminPage.Books) {
                if (book.id.equals(selectedBookID)) {
                    selectedBook = book;
                    break;
                }
            }
            
            if (selectedBook != null) {
                AdminPage.Books.remove(selectedBook);
                JOptionPane.showMessageDialog(frame, "Book deleted successfully: " + selectedBook.name);
            } else {
                JOptionPane.showMessageDialog(frame, "Error: Book not found.");
            }
    }
    public void editBookItem() {
        JFrame viewBookFrame = new JFrame("Edit Books");
        viewBookFrame.setSize(500, 300);
        viewBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewBookFrame.setLocationRelativeTo(null);
    
        String[] columnHeadings = {"ID", "Book Name", "Author Name", "Genre"};
    
        String[][] data = new String[Books.size()][4];
        for (int i = 0; i < Books.size(); i++) {
            data[i][0] = Books.get(i).id;
            data[i][1] = Books.get(i).name;
            data[i][2] = Books.get(i).Author;
            data[i][3] = Books.get(i).Genre;
        }
    
        // Create table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnHeadings) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; 
            }
        };
        
        
        JTable bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Add table model listener to handle updates
        bookTable.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (row >= 0) { // Ensure valid row
                    String updatedValue = (String) bookTable.getValueAt(row, column);
                    switch (column) {
                        case 0:
                            Books.get(row).id = updatedValue;
                            break;
                        case 1:
                            Books.get(row).name = updatedValue;
                            break;
                        case 2:
                            Books.get(row).Author = updatedValue;
                            break;
                        case 3:
                            Books.get(row).Genre = updatedValue;
                            break;
                    }
                }
            }
        });   
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        viewBookFrame.add(panel);
        viewBookFrame.setVisible(true);
    }
    public void viewFeedback(){
        JFrame frame = new JFrame("View Feedback");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        panel.setBackground(new Color(250, 220, 230));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); 
        textArea.setFont(new Font("Serif", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        // textArea.setCaretPosition(textArea.getDocument().getLength()); 

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        loadFeedbackFromFile(textArea);

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }
    public void viewRating(){
        JFrame frame = new JFrame("Admin - View Ratings");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 50, 40));

        JLabel heading = new JLabel("Library Ratings", SwingConstants.CENTER);
        heading.setFont(new Font("Felix Titling", Font.BOLD, 20));
        panel.add(heading, BorderLayout.NORTH);
        
        String[] columnNames = {"Rating"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        
        try (BufferedReader reader = new BufferedReader(new FileReader("ratings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(new Object[]{line});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewItem) {
            ViewUsers();
        }
        else if (e.getSource() == removeItem) {
            RemoveUser();
        }
        else if (e.getSource() == addItem) {
            AddUsers();
        }
        else if (e.getSource() == addBookItem) {
            AddBook();
        }
        else if (e.getSource() == viewBookItem) {
            viewBookItem();
        }
        else if (e.getSource() == deleteBookItem) {
            deleteBookItem();
        }
        else if (e.getSource() == editBookItem) {
            editBookItem();
        }
        else if (e.getSource() == feedbackItem) {
            viewFeedback();
        }
        else if (e.getSource() == ratingItem) {
            viewRating();
        }
    }

    static class User {
        String name;
        String id;
        String phoneNumber;
        String email;
    
        public User(String name, String id, String phoneNumber, String email) {
            this.name = name;
            this.id = id;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }
    }
    static class Book {
        String name;
        String id;
        String Author;
        String Genre;
    
        public Book(String name, String id, String Author, String Genre) {
            this.name = name;
            this.id = id;
            this.Author = Author;
            this.Genre = Genre;
        }
    }
    private static void loadFeedbackFromFile(JTextArea textArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader("feedback.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n"); // Append each line of feedback to the JTextArea
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading feedback file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args){
        new AdminPage();
    }
}