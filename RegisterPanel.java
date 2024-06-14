import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton toLoginButton;
    private JComboBox<String> typeComboBox;
    private MainFrame mainFrame;
    private BufferedImage image;

    public RegisterPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        try {
            image = ImageIO.read(new File("mmu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Image Panel
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    int width = getWidth();
                    int height = getHeight();
                    Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    g.drawImage(resizedImage, 0, 0, null);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, getHeight());
            }
        };
        imagePanel.setPreferredSize(new Dimension(400, 0));
        add(imagePanel, BorderLayout.WEST);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(77, 255, 255)); // Sky blue color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title Label
        // JLabel titleLabel = new JLabel("Register");
        // titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        // titleLabel.setForeground(Color.WHITE);
        
        // Components
        typeComboBox = new JComboBox<>(new String[]{"ADMIN"});
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        registerButton = createButton("Register");
        toLoginButton = createButton("Go to Login");

        // Adding components to the panel
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.gridwidth = 2;
        // gbc.anchor = GridBagConstraints.CENTER;
        // // formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Type:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(typeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("USERNAME:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("PASSWORD:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);

        gbc.gridy = 4;
        formPanel.add(registerButton, gbc);

        gbc.gridy = 5;
        formPanel.add(toLoginButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Add action listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty");
                } else if (mainFrame.getAdminCredentials().containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists");
                } else {
                    mainFrame.getAdminCredentials().put(username, password);
                    mainFrame.saveCredentials();
                    JOptionPane.showMessageDialog(null, "Registration successful");

                    // Save to terminal
                    System.out.println("User registered: " + username);

                    mainFrame.showPanel("login");
                }
            }
        });

        toLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("login");
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(255, 255, 255)); // White blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 25));
        return button;
    }
}
