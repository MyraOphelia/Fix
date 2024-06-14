import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterStudentPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField phoneNumberField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> departmentComboBox;
    private JTextField addressField;
    private JButton registerButton;
    private JButton backButton;

    public RegisterStudentPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 153, 255));
        JLabel headerLabel = new JLabel("Student Registration Form");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255));  // White background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the form panel
        addFormComponent(formPanel, gbc, 0, "ID:", idField = new JTextField(20));
        addFormComponent(formPanel, gbc, 1, "Name:", nameField = new JTextField(20));
        addFormComponent(formPanel, gbc, 2, "Age:", ageField = new JTextField(20));
        addFormComponent(formPanel, gbc, 3, "Phone Number:", phoneNumberField = new JTextField(20));
        addFormComponent(formPanel, gbc, 4, "Gender:", genderComboBox = new JComboBox<>(new String[]{"Male", "Female"}));
        addFormComponent(formPanel, gbc, 5, "Department:", departmentComboBox = new JComboBox<>(new String[]{"Law", "IT", "Engineering", "Business"}));
        addFormComponent(formPanel, gbc, 6, "Address:", addressField = new JTextField(20));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));  // White background
        registerButton = createButton("Register");
        backButton = createButton("Back");

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Add action listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("admin");
            }
        });
    }

    private void addFormComponent(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent component) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(component, gbc);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(51, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private void registerStudent() {
        // Read data from fields
        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String phoneNumber = phoneNumberField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String department = (String) departmentComboBox.getSelectedItem();
        String address = addressField.getText();

        // Print data to terminal
        System.out.println("Student Registration Details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Gender: " + gender);
        System.out.println("Department: " + department);
        System.out.println("Address: " + address);

        // Optionally, clear fields after registration
        clearFields();
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        phoneNumberField.setText("");
        genderComboBox.setSelectedIndex(0);
        departmentComboBox.setSelectedIndex(0);
        addressField.setText("");
    }
}
