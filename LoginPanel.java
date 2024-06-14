import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton toRegisterButton;
    private MainFrame mainFrame;
    private BufferedImage image;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        try {
            image = ImageIO.read(new File("mit.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(51, 215, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("WELCOME USERS");
        //Command - Bold
        //Command - Plain
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("USERNAME:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("PASSWORD:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        loginButton = createButton("Login");
        formPanel.add(loginButton, gbc);

        gbc.gridy++;
        toRegisterButton = createButton("Go to Register");
        formPanel.add(toRegisterButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty");
                } else if (password.equals(mainFrame.getAdminCredentials().get(username))) {
                    JOptionPane.showMessageDialog(null, "Login successful");

                    // Save to terminal
                    System.out.println("User logged in: " + username);

                    mainFrame.showPanel("admin");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }
            }
        });

        toRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("register");
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 25));
        return button;
    }
}
