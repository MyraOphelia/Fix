import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentActivityPanel extends JPanel {
    private MainFrame mainFrame;
    private JCheckBox accommodationCheckBox;
    private JCheckBox networkFeeCheckBox;
    private JCheckBox libraryFeeCheckBox;
    private JCheckBox clubSocietyFeeCheckBox;
    private JButton calculateButton;
    private JButton backButton;
    private JLabel resultLabel;

    public StudentActivityPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // Create title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Student Activity and Fees");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Create form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Checkbox for accommodation
        accommodationCheckBox = new JCheckBox("Require Accommodation?");
        accommodationCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Checkbox for network fee (mandatory)
        networkFeeCheckBox = new JCheckBox("Network Fee (Mandatory)");
        networkFeeCheckBox.setSelected(true); // Default to selected and disabled
        networkFeeCheckBox.setEnabled(false);

        // Checkbox for library fee (mandatory)
        libraryFeeCheckBox = new JCheckBox("Library Fee (Mandatory)");
        libraryFeeCheckBox.setSelected(true); // Default to selected and disabled
        libraryFeeCheckBox.setEnabled(false);

        // Checkbox for club and society fee (mandatory)
        clubSocietyFeeCheckBox = new JCheckBox("Club and Society Fee (Mandatory)");
        clubSocietyFeeCheckBox.setSelected(true); // Default to selected and disabled
        clubSocietyFeeCheckBox.setEnabled(false);

        // Button to calculate total fee
        calculateButton = new JButton("Calculate Total Fee");

        // Button to navigate back to admin panel
        backButton = new JButton("Back to Admin Panel");

        // Label to display total fee
        resultLabel = new JLabel("Total Fee: RM0");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(Color.BLUE);

        // Add components to form panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(accommodationCheckBox, gbc);

        gbc.gridy++;
        formPanel.add(networkFeeCheckBox, gbc);

        gbc.gridy++;
        formPanel.add(libraryFeeCheckBox, gbc);

        gbc.gridy++;
        formPanel.add(clubSocietyFeeCheckBox, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(calculateButton, gbc);

        gbc.gridy++;
        formPanel.add(resultLabel, gbc);

        gbc.gridy++;
        formPanel.add(backButton, gbc);

        // Add panels to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        // Add action listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalFee();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("admin");
            }
        });
    }

    private void calculateTotalFee() {
        int networkFee = 100; // Example network fee
        int libraryFee = 50; // Example library fee
        int clubSocietyFee = 75; // Example club and society fee
        int accommodationFee = 200; // Example accommodation fee

        int totalFee = 0;

        // Add mandatory fees
        totalFee += networkFee + libraryFee + clubSocietyFee;

        // Add optional accommodation fee
        if (accommodationCheckBox.isSelected()) {
            totalFee += accommodationFee;
        }

        // Print selected options to terminal
        System.out.println("Accommodation Required: " + (accommodationCheckBox.isSelected() ? "Yes" : "No"));
        System.out.println("Accommodation Fee: RM"+accommodationFee);
        System.out.println("Network Fee: RM" + networkFee);
        System.out.println("Library Fee: RM" + libraryFee);
        System.out.println("Club and Society Fee: RM" + clubSocietyFee);
        
        // Print total fee to terminal
        System.out.println("Total Fee: RM" + totalFee);

        // Update result label
        resultLabel.setText("Total Fee: RM" + totalFee);
    }

    // Main method for testing the panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Student Activity Panel Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.getContentPane().add(new StudentActivityPanel(null)); // Replace null with your MainFrame instance
                frame.setVisible(true);
            }
        });
    }
}
