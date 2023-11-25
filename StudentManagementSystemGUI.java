import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}

public class StudentManagementSystemGUI extends JFrame {
    private StudentManagementSystem studentManagementSystem;

    public StudentManagementSystemGUI() {
        studentManagementSystem = new StudentManagementSystem();
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Student Management System");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230));  background

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(240, 255, 240));  background
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");

        addButton.setBackground(new Color(144, 238, 144)); 
        removeButton.setBackground(new Color(144, 238, 144));
        searchButton.setBackground(new Color(144, 238, 144));
        displayButton.setBackground(new Color(144, 238, 144));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        add(mainPanel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents(outputArea);
            }
        });
    }

    private void addStudent() {
        String name = JOptionPane.showInputDialog("Enter student name:");
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rollNumberStr = JOptionPane.showInputDialog("Enter student roll number:");
        if (rollNumberStr == null || rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Roll number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber;
        try {
            rollNumber = Integer.parseInt(rollNumberStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid roll number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String grade = JOptionPane.showInputDialog("Enter student grade:");
        if (grade == null || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Grade cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student(name, rollNumber, grade);
        studentManagementSystem.addStudent(student);
        JOptionPane.showMessageDialog(this, "Student added successfully.");
    }

    private void removeStudent() {
        String rollNumberStr = JOptionPane.showInputDialog("Enter roll number of student to remove:");
        if (rollNumberStr == null || rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Roll number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber;
        try {
            rollNumber = Integer.parseInt(rollNumberStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid roll number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        studentManagementSystem.removeStudent(rollNumber);
        JOptionPane.showMessageDialog(this, "Student removed successfully.");
    }

    private void searchStudent() {
        String rollNumberStr = JOptionPane.showInputDialog("Enter roll number of student to search:");
        if (rollNumberStr == null || rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Roll number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber;
        try {
            rollNumber = Integer.parseInt(rollNumberStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid roll number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = studentManagementSystem.searchStudent(rollNumber);
        if (student != null) {
            JOptionPane.showMessageDialog(this, "Student found:\n" + student.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAllStudents(JTextArea outputArea) {
        ArrayList<Student> students = studentManagementSystem.getAllStudents();
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students to display.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            outputArea.setText("");
            for (Student student : students) {
                outputArea.append(student.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystemGUI().setVisible(true));
    }
}
