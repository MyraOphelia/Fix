import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HostelPanel extends JScrollPane {
    private MainFrame mainFrame;
    private JPanel contentPanel; // Panel inside the scroll pane

    public HostelPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Create the content panel for the hostel images
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 20, 20)); // 2 rows, 2 columns, with gaps
        contentPanel.setBackground(Color.WHITE); // Set background color for better contrast

        // Create image icons with adjusted size (larger)
        ImageIcon singleRoomACIcon = createImageIcon("02.png", "Single Room w A/c");
        ImageIcon singleRoomNonACIcon = createImageIcon("03.png", "Single Room w/n A/c");
        ImageIcon doubleRoomACIcon = createImageIcon("04.png", "Double Room w A/c");
        ImageIcon doubleRoomNonACIcon = createImageIcon("05.png", "Double Room w/n A/c");

        JButton singleRoomACButton = createImageButton(singleRoomACIcon, "Single Room w A/c - ABC");
        JButton singleRoomNonACButton = createImageButton(singleRoomNonACIcon, "Single Room w/n A/c - ABC");
        JButton doubleRoomACButton = createImageButton(doubleRoomACIcon, "Double Room w A/c - ABC");
        JButton doubleRoomNonACButton = createImageButton(doubleRoomNonACIcon, "Double Room w/n A/c - ABC");

        // Add buttons to the content panel
        contentPanel.add(singleRoomACButton);
        contentPanel.add(singleRoomNonACButton);
        contentPanel.add(doubleRoomACButton);
        contentPanel.add(doubleRoomNonACButton);

        // Set the content panel to the scroll pane
        setViewportView(contentPanel);

        // Make the scroll pane always show vertical scrollbar
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add back button to return to admin panel
        JButton backButton = new JButton("Back to Admin Panel");
        backButton.addActionListener(e -> mainFrame.showPanel("admin"));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);

        // Add button panel to the bottom of the scroll pane
        setColumnHeaderView(buttonPanel);
    }

    // Method to create an image icon from file with scaled size
    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL, description);
            // Scale the image icon to a larger size
            Image scaledImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // Method to create an image button with a label
    private JButton createImageButton(ImageIcon icon, String labelText) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setLayout(new BorderLayout()); // Set layout to BorderLayout

        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER); // Center align label text

        button.add(label, BorderLayout.SOUTH); // Add label below the button

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show full-size image on click
                showImageDialog(icon, labelText);
            }
        });
        return button;
    }

    // Method to show image dialog
    private void showImageDialog(ImageIcon icon, String labelText) {
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(new JLabel(labelText, JLabel.CENTER), BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(HostelPanel.this, panel, "Room Image", JOptionPane.PLAIN_MESSAGE);
    }

    // Main method for testing the panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Hostel Panel Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.getContentPane().add(new HostelPanel(null)); // Replace null with your MainFrame instance
                frame.setVisible(true);
            }
        });
    }
}
