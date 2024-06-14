import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private MainFrame mainFrame;
    private JButton registerStudentButton;
    private JButton selectCourseButton;
    private JButton studentActivityButton;
    private JButton hostelButton;
    private JButton financeFeesButton;

    public AdminPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // Create title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Admin Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Create button panel with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize buttons
        registerStudentButton = createButton("Register Student");
        selectCourseButton = createButton("Select Course");
        studentActivityButton = createButton("Student Activity");
        hostelButton = createButton("Hostel");
        financeFeesButton = createButton("Finance Fees");

        // Add buttons to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(registerStudentButton, gbc);

        gbc.gridy++;
        buttonPanel.add(selectCourseButton, gbc);

        gbc.gridy++;
        buttonPanel.add(studentActivityButton, gbc);

        gbc.gridy++;
        buttonPanel.add(hostelButton, gbc);

        gbc.gridy++;
        buttonPanel.add(financeFeesButton, gbc);

        // Add panels to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners
        registerStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("registerStudent");
            }
        });

        selectCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("selectCourse");
            }
        });

        studentActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("studentActivity");
            }
        });

        hostelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("hostel");
            }
        });

        financeFeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("financeFees");
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }
}
