import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class LibraryContactManagementSystem extends JFrame {
    private JTextField nameField, rollField, titleField, authorField;
    private JComboBox<Student> studentComboBox;
    private JComboBox<Book> bookComboBox;
    private JTextArea displayArea;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();

    public LibraryContactManagementSystem() {
        setTitle("Library Management System");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel studentPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        studentPanel.setBorder(BorderFactory.createTitledBorder("Student Info"));
        studentPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        studentPanel.add(nameField);
        studentPanel.add(new JLabel("Roll No:"));
        rollField = new JTextField();
        studentPanel.add(rollField);
        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> addStudent());
        studentPanel.add(addStudentButton);

       
        JPanel bookPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        bookPanel.setBorder(BorderFactory.createTitledBorder("Book Info"));
        bookPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        bookPanel.add(titleField);
        bookPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        bookPanel.add(authorField);
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> addBook());
        bookPanel.add(addBookButton);

        JPanel actionPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        actionPanel.add(new JLabel("Select Student:"));
        studentComboBox = new JComboBox<>();
        actionPanel.add(studentComboBox);
        actionPanel.add(new JLabel("Select Book:"));
        bookComboBox = new JComboBox<>();
        actionPanel.add(bookComboBox);
        JButton issueButton = new JButton("Issue Book");
        issueButton.addActionListener(e -> issueBook());
        JButton returnButton = new JButton("Return Book");
        returnButton.addActionListener(e -> returnBook());
        actionPanel.add(issueButton);
        actionPanel.add(returnButton);

        displayArea = new JTextArea(8, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log"));

        mainPanel.add(studentPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(bookPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(actionPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(scrollPane);

        add(mainPanel);
        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        if (!name.isEmpty() && !roll.isEmpty()) {
            Student student = new Student(name, roll);
            students.add(student);
            studentComboBox.addItem(student);
            nameField.setText("");
            rollField.setText("");
        }
    }

    private void addBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        if (!title.isEmpty() && !author.isEmpty()) {
            Book book = new Book(title, author);
            books.add(book);
            bookComboBox.addItem(book);
            titleField.setText("");
            authorField.setText("");
        }
    }

    private void issueBook() {
        Student student = (Student) studentComboBox.getSelectedItem();
        Book book = (Book) bookComboBox.getSelectedItem();
        if (student != null && book != null) {
            student.issueBook(book);
            displayArea.append(student.getName() + " borrowed \"" + book.getTitle() + "\"\n");
        }
    }

    private void returnBook() {
        Student student = (Student) studentComboBox.getSelectedItem();
        Book book = (Book) bookComboBox.getSelectedItem();
        if (student != null && book != null) {
            student.returnBook(book);
            displayArea.append(student.getName() + " returned \"" + book.getTitle() + "\"\n");
        }
    }

    class Student {
        private String name, rollNo;
        private ArrayList<Book> borrowedBooks = new ArrayList<>();

        public Student(String name, String rollNo) {
            this.name = name;
            this.rollNo = rollNo;
        }

        public String getName() {
            return name;
        }

        public void issueBook(Book book) {
            borrowedBooks.add(book);
        }

        public void returnBook(Book book) {
            borrowedBooks.remove(book);
        }

        @Override
        public String toString() {
            return name + " (" + rollNo + ")";
        }
    }

    class Book {
        private String title, author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return title + " by " + author;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryContactManagementSystem::new);
    }
}