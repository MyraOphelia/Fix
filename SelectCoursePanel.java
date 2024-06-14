import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectCoursePanel extends ImagePanel {
    private MainFrame mainFrame;
    private JCheckBox level1CheckBox;
    private JCheckBox level2CheckBox;
    private JCheckBox level3CheckBox;
    private JCheckBox specialCaseCheckBox;
    private JButton calculateButton;
    private JButton clearButton;
    private JButton backButton;
    private JTable priceTable;
    private JLabel messageLabel;

    public SelectCoursePanel(MainFrame mainFrame) {
        super(); // Call the constructor of ImagePanel
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // Load the background image
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("mmu.jpg")); // Replace with your image file path
            setImage(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Make the form panel transparent
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(100, 40, 40, 10)); // Add padding

        // Checkboxes for course levels with bold text
        level1CheckBox = createCheckBox("Level 1 Matriculation (1 Year)");
        level2CheckBox = createCheckBox("Level 2 Undergraduate (3 Years)");
        level3CheckBox = createCheckBox("Level 3 Postgraduate (1.5 Years)");
        specialCaseCheckBox = createCheckBox("Special Case: M + U + P (5 Years)");

        // Price table setup
        String[] columnNames = {"Course Level", "Base Fee (RM)"};
        Object[][] data = {
                {"Level 1 Matriculation", 1000},
                {"Level 2 Undergraduate", 6000},
                {"Level 3 Postgraduate", 3000},
                {"Special Case", 10000}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        priceTable = new JTable(tableModel);
        priceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        priceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        priceTable.setRowHeight(25);
        JScrollPane tableScrollPane = new JScrollPane(priceTable);
        tableScrollPane.setPreferredSize(new Dimension(400, 150));

        // Calculate, Clear, and Back buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Center align buttons with spacing
        calculateButton = createButton("Calculate");
        clearButton = createButton("Clear");
        backButton = createButton("Back");
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        // Message label for feedback
        messageLabel = new JLabel("Select courses to calculate total fee.");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding components to formPanel using BoxLayout
        formPanel.add(level1CheckBox);
        formPanel.add(level2CheckBox);
        formPanel.add(level3CheckBox);
        formPanel.add(specialCaseCheckBox);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(tableScrollPane);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(messageLabel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.CENTER);

        // Action listener for Calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalFee();
            }
        });

        // Action listener for Clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSelections();
            }
        });

        // Action listener for Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("admin");
            }
        });
    }

    // Method to calculate total fee based on selected checkboxes
    private void calculateTotalFee() {
        double totalFee = 0.0; // Variable to store total fee

        // Reset message label
        messageLabel.setForeground(Color.WHITE);

        // Determine the fee calculation based on selected checkboxes
        if (level1CheckBox.isSelected()) {
            totalFee += 1000; // Base fee for Level 1 Matriculation
            showMessage("Selected: Level 1 Matriculation - no discount, 1 year");
            System.out.println("Selected: Level 1 Matriculation - no discount, 1 year");
        }

        if (level2CheckBox.isSelected()) {
            double baseFee = 6000; // Base fee for Level 2 Undergraduate
            double discountedFee = baseFee * 0.9; // 10% discount for each year
            totalFee += discountedFee * 3; // Total fee for 3 years
            showMessage("Selected: Level 2 Undergraduate - discount 10% per year, 3 years");
            System.out.println("Selected: Level 2 Undergraduate - discount 10% per year, 3 years");
        }

        if (level3CheckBox.isSelected()) {
            double baseFee = 3000; // Base fee for Level 3 Postgraduate
            double discountedFee = baseFee * 0.9; // 10% discount for each year
            totalFee += discountedFee * 1.5; // Total fee for 1.5 years
            showMessage("Selected: Level 3 Postgraduate - discount 10% per year, 1.5 years");
            System.out.println("Selected: Level 3 Postgraduate - discount 10% per year, 1.5 years");
        }

        if (specialCaseCheckBox.isSelected()) {
            totalFee = 35000; // Special case fee of RM 35,000
            showMessage("Selected: Special Case - fixed fee of RM 35,000");
            System.out.println("Selected: Special Case - fixed fee of RM 35,000");
        }

        // Display the total fee
        if (totalFee > 0) {
            JOptionPane.showMessageDialog(this, "<html><b>Total Fee:</b> RM " + String.format("%.2f", totalFee) + "</html>");
            System.out.println("Total Fee: RM " + String.format("%.2f", totalFee));
        } else {
            showMessage("No courses selected.");
            System.out.println("No courses selected.");
        }
    }

    // Method to create and customize checkboxes with bold text and padding to the left
    private JCheckBox createCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(new Font("Arial", Font.BOLD, 14)); // Bold text
        checkBox.setForeground(new Color(255, 255, 255)); // White text color
        checkBox.setOpaque(false); // Make the checkbox background transparent
        checkBox.setBorder(new EmptyBorder(0, 20, 0, 0)); // Add left padding to move the checkbox to the right
        return checkBox;
    }

    // Method to create and customize buttons
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(51, 153, 255)); // Blue background
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove focus border
        button.setPreferredSize(new Dimension(150, 40)); // Button size
        return button;
    }

    // Method to show messages in message label with bold text
    private void showMessage(String message) {
        messageLabel.setText("<html><b>" + message + "</b></html>");
    }

    // Method to clear all checkbox selections
    private void clearSelections() {
        level1CheckBox.setSelected(false);
        level2CheckBox.setSelected(false);
        level3CheckBox.setSelected(false);
        specialCaseCheckBox.setSelected(false);
        showMessage("Selections cleared.");
        System.out.println("Selections cleared.");
    }

    // Main method for testing the panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Select Course Panel Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.getContentPane().add(new SelectCoursePanel(null)); // Replace null with your MainFrame instance
                frame.setVisible(true);
            }
        });
    }
}

class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel() {
        super();
        setLayout(new BorderLayout());
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;

// public class SelectCoursePanel extends ImagePanel {
//     private MainFrame mainFrame;
//     private JCheckBox level1CheckBox;
//     private JCheckBox level2CheckBox;
//     private JCheckBox level3CheckBox;
//     private JCheckBox specialCaseCheckBox;
//     private JButton calculateButton;
//     private JButton clearButton;
//     private JButton backButton;
//     private JTable priceTable;
//     private JLabel messageLabel;

//     // Define Course class to hold course details
//     private class Course {
//         private String name;
//         private double priceBeforeDiscount;
//         private double discountAmount;

//         public Course(String name, double priceBeforeDiscount) {
//             this.name = name;
//             this.priceBeforeDiscount = priceBeforeDiscount;
//             this.discountAmount = 0.0;
//         }

//         public String getName() {
//             return name;
//         }

//         public double getPriceBeforeDiscount() {
//             return priceBeforeDiscount;
//         }

//         public double getDiscountAmount() {
//             return discountAmount;
//         }

//         public void setDiscountAmount(double discountAmount) {
//             this.discountAmount = discountAmount;
//         }

//         public double getPriceAfterDiscount() {
//             return priceBeforeDiscount - discountAmount;
//         }
//     }

//     public SelectCoursePanel(MainFrame mainFrame) {
//         super(); // Call the constructor of ImagePanel
//         this.mainFrame = mainFrame;
//         setLayout(new BorderLayout());

//         // Load the background image
//         try {
//             BufferedImage backgroundImage = ImageIO.read(new File("mmu.jpg")); // Replace with your image file path
//             setImage(backgroundImage);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         JPanel formPanel = new JPanel();
//         formPanel.setOpaque(false); // Make the form panel transparent
//         formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
//         formPanel.setBorder(new EmptyBorder(100, 40, 40, 10)); // Add padding

//         // Checkboxes for course levels with bold text
//         level1CheckBox = createCheckBox("Level 1 Matriculation (1 Year)");
//         level2CheckBox = createCheckBox("Level 2 Undergraduate (3 Years)");
//         level3CheckBox = createCheckBox("Level 3 Postgraduate (1.5 Years)");
//         specialCaseCheckBox = createCheckBox("Special Case: M + U + P (5 Years)");

//         // Price table setup
//         String[] columnNames = {"Course Level", "Base Fee (RM)"};
//         Object[][] data = {
//                 {"Level 1 Matriculation", 1000.0},
//                 {"Level 2 Undergraduate", 6000.0},
//                 {"Level 3 Postgraduate", 3000.0},
//                 {"Special Case", 10000.0}
//         };

//         DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
//         priceTable = new JTable(tableModel);
//         priceTable.setFont(new Font("Arial", Font.PLAIN, 14));
//         priceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//         priceTable.setRowHeight(25);
//         JScrollPane tableScrollPane = new JScrollPane(priceTable);
//         tableScrollPane.setPreferredSize(new Dimension(400, 150));

//         // Calculate, Clear, and Back buttons
//         JPanel buttonPanel = new JPanel();
//         buttonPanel.setOpaque(false); // Make the button panel transparent
//         buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Center align buttons with spacing
//         calculateButton = createButton("Calculate");
//         clearButton = createButton("Clear");
//         backButton = createButton("Back");
//         buttonPanel.add(calculateButton);
//         buttonPanel.add(clearButton);
//         buttonPanel.add(backButton);

//         // Message label for feedback
//         messageLabel = new JLabel("Select courses to calculate total fee.");
//         messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         messageLabel.setForeground(Color.WHITE);
//         messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

//         // Adding components to formPanel using BoxLayout
//         formPanel.add(level1CheckBox);
//         formPanel.add(level2CheckBox);
//         formPanel.add(level3CheckBox);
//         formPanel.add(specialCaseCheckBox);
//         formPanel.add(Box.createVerticalStrut(20));
//         formPanel.add(tableScrollPane);
//         formPanel.add(Box.createVerticalStrut(20));
//         formPanel.add(messageLabel);
//         formPanel.add(Box.createVerticalStrut(10));
//         formPanel.add(buttonPanel);

//         add(formPanel, BorderLayout.CENTER);

//         // Action listener for Calculate button
//         calculateButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 generateBill();
//             }
//         });

//         // Action listener for Clear button
//         clearButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 clearSelections();
//             }
//         });

//         // Action listener for Back button
//         backButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 mainFrame.showPanel("admin");
//             }
//         });
//     }

//     // Method to calculate total fee based on selected checkboxes and display bill
//     private void generateBill() {
//         double totalFeeBeforeDiscount = 0.0;
//         double totalDiscountAmount = 0.0;

//         // Reset message label
//         messageLabel.setForeground(Color.WHITE);

//         // Array to hold selected courses
//         Course[] selectedCourses = new Course[4];
//         selectedCourses[0] = new Course("Level 1 Matriculation", 1000.0);
//         selectedCourses[1] = new Course("Level 2 Undergraduate", 18000.0);
//         selectedCourses[2] = new Course("Level 3 Postgraduate", 4500.0);
//         selectedCourses[3] = new Course("Special Case", 50000.0);

//         // Apply discounts based on selected checkboxes
//         if (level1CheckBox.isSelected()) {
//             // No discount for Level 1 Matriculation
//             totalFeeBeforeDiscount += selectedCourses[0].getPriceBeforeDiscount();
//             selectedCourses[0].setDiscountAmount(0.0);
//         }

//         if (level2CheckBox.isSelected()) {
//             // 10% discount per year for Level 2 Undergraduate (3 years)
//             double baseFee = selectedCourses[1].getPriceBeforeDiscount();
//             double discountedFee = baseFee * 0.9 * 3; // 10% discount for each of 3 years
//             totalFeeBeforeDiscount += discountedFee;
//             selectedCourses[1].setDiscountAmount(baseFee - (baseFee * 0.9));
//         }

//         if (level3CheckBox.isSelected()) {
//             // 10% discount per year for Level 3 Postgraduate (1.5 years)
//             double baseFee = selectedCourses[2].getPriceBeforeDiscount();
//             double discountedFee = baseFee * 0.9 * 1.5; // 10% discount for each of 1.5 years
//             totalFeeBeforeDiscount += discountedFee;
//             selectedCourses[2].setDiscountAmount(baseFee - (baseFee * 0.9));
//         }

//         if (specialCaseCheckBox.isSelected()) {
//             // 30% discount per year for Level 3 Postgraduate (5 years)
//             double baseFee = selectedCourses[3].getPriceBeforeDiscount();
//             double discountedFee = baseFee * 0.9 * 1.5; // 10% discount for each of 1.5 years
//             totalFeeBeforeDiscount += discountedFee;
//             selectedCourses[3].setDiscountAmount(baseFee - (baseFee * 0.9));
       
//         }

//         // Calculate total discount amount
//         for (Course course : selectedCourses) {
//             totalDiscountAmount += course.getDiscountAmount();
//         }

//         // Display the bill
//         displayBill(selectedCourses, totalFeeBeforeDiscount, totalDiscountAmount);
//     }

//     // Method to display the bill
//     // Method to display the bill
// private void displayBill(Course[] selectedCourses, double totalFeeBeforeDiscount, double totalDiscountAmount) {
//     // Prepare table data
//     String[] columnNames = {"Course Name", "Price Before Discount (RM)", "Discount Amount (RM)", "Net Payable (RM)"};
//     Object[][] data = new Object[selectedCourses.length][4];

//     for (int i = 0; i < selectedCourses.length; i++) {
//         data[i][0] = selectedCourses[i].getName();
//         data[i][1] = selectedCourses[i].getPriceBeforeDiscount();
//         data[i][2] = selectedCourses[i].getDiscountAmount();
//         data[i][3] = selectedCourses[i].getPriceAfterDiscount();
//     }

//     // Create a new JFrame to display the bill
//     JFrame billFrame = new JFrame("Bill for Ordered Courses");
//     billFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//     // Create a JTable for the bill
//     JTable billTable = new JTable(data, columnNames);
//     billTable.setFont(new Font("Arial", Font.PLAIN, 14));
//     billTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//     billTable.setRowHeight(25);
//     JScrollPane billScrollPane = new JScrollPane(billTable);
//     billScrollPane.setPreferredSize(new Dimension(600, 200));

//     // Add billScrollPane to billFrame
//     billFrame.getContentPane().add(billScrollPane, BorderLayout.CENTER);

//     // Add a label for total fee information
//     JLabel totalLabel = new JLabel("<html><b>Total Fee Before Discount:</b> RM " + String.format("%.2f", totalFeeBeforeDiscount) + "<br>" +
//             "<b>Total Discount Amount:</b> RM " + String.format("%.2f", totalDiscountAmount) + "<br>" +
//             "<b>Net Payable:</b> RM " + String.format("%.2f", (totalFeeBeforeDiscount - totalDiscountAmount)) + "</html>");
//     totalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//     totalLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
//     billFrame.getContentPane().add(totalLabel, BorderLayout.SOUTH);

//     // Set frame properties and make it visible
//     billFrame.pack();
//     billFrame.setLocationRelativeTo(this); // Center the frame relative to the SelectCoursePanel
//     billFrame.setVisible(true);
// }

        
//             // Method to create and customize checkboxes with bold text and padding to the left
//             private JCheckBox createCheckBox(String text) {
//                 JCheckBox checkBox = new JCheckBox(text);
//                 checkBox.setFont(new Font("Arial", Font.BOLD, 14)); // Bold text
//                 checkBox.setForeground(new Color(255, 255, 255)); // White text color
//                 checkBox.setOpaque(false); // Make the checkbox background transparent
//                 checkBox.setBorder(new EmptyBorder(0, 20, 0, 0)); // Add left padding to move the checkbox to the right
//                 return checkBox;
//             }
        
//             // Method to create and customize buttons
//             private JButton createButton(String text) {
//                 JButton button = new JButton(text);
//                 button.setFont(new Font("Arial", Font.BOLD, 16));
//                 button.setBackground(new Color(51, 153, 255)); // Blue background
//                 button.setForeground(Color.WHITE); // White text color
//                 button.setFocusPainted(false); // Remove focus border
//                 button.setPreferredSize(new Dimension(150, 40)); // Button size
//                 return button;
//             }
        
//             // Method to clear all checkbox selections
//             private void clearSelections() {
//                 level1CheckBox.setSelected(false);
//                 level2CheckBox.setSelected(false);
//                 level3CheckBox.setSelected(false);
//                 specialCaseCheckBox.setSelected(false);
//                 showMessage("Selections cleared.");
//                 System.out.println("Selections cleared.");
//             }
        
//             private void showMessage(String string) {
//                 // TODO Auto-generated method stub
//                 throw new UnsupportedOperationException("Unimplemented method 'showMessage'");
//             }

//             // Main method for testing the panel
//             public static void main(String[] args) {
//                 SwingUtilities.invokeLater(new Runnable() {
//                     public void run() {
//                         JFrame frame = new JFrame("Select Course Panel Test");
//                         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                         frame.setSize(600, 400);
//                         frame.getContentPane().add(new SelectCoursePanel(null)); // Replace null with your MainFrame instance
//                         frame.setVisible(true);
//                     }
//                 });
//             }
//         }
        
//         class ImagePanel extends JPanel {
//             private Image image;
        
//             public ImagePanel() {
//                 super();
//                 setLayout(new BorderLayout());
//             }
        
//             public void setImage(Image image) {
//                 this.image = image;
//                 repaint();
//             }
        
//             @Override
//             protected void paintComponent(Graphics g) {
//                 super.paintComponent(g);
//                 if (image != null) {
//                     g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//                 }
//             }
//         }
        
// // import javax.swing.*;
// // import javax.swing.border.EmptyBorder;
// // import javax.swing.table.DefaultTableModel;
// // import java.awt.*;
// // import java.awt.event.ActionEvent;
// // import java.awt.event.ActionListener;
// // import java.awt.image.BufferedImage;
// // import java.io.File;
// // import java.io.IOException;
// // import javax.imageio.ImageIO;

// // public class SelectCoursePanel extends ImagePanel {
// //     private MainFrame mainFrame;
// //     private JCheckBox level1CheckBox;
// //     private JCheckBox level2CheckBox;
// //     private JCheckBox level3CheckBox;
// //     private JCheckBox specialCaseCheckBox;
// //     private JButton calculateButton;
// //     private JButton clearButton;
// //     private JButton backButton;
// //     private JTable priceTable;
// //     private JLabel messageLabel;

// //     public SelectCoursePanel(MainFrame mainFrame) {
// //         super(); // Call the constructor of ImagePanel
// //         this.mainFrame = mainFrame;
// //         setLayout(new BorderLayout());

// //         // Load the background image
// //         try {
// //             BufferedImage backgroundImage = ImageIO.read(new File("mmu.jpg")); // Replace with your image file path
// //             setImage(backgroundImage);
// //         } catch (IOException e) {
// //             e.printStackTrace();
// //         }

// //         JPanel formPanel = new JPanel();
// //         formPanel.setOpaque(false); // Make the form panel transparent
// //         formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
// //         formPanel.setBorder(new EmptyBorder(100, 40, 40, 10)); // Add padding

// //         // Checkboxes for course levels with bold text
// //         level1CheckBox = createCheckBox("Level 1 Matriculation (1 Year)");
// //         level2CheckBox = createCheckBox("Level 2 Undergraduate (3 Years)");
// //         level3CheckBox = createCheckBox("Level 3 Postgraduate (1.5 Years)");
// //         specialCaseCheckBox = createCheckBox("Special Case: M + U + P (5 Years)");

// //         // Price table setup
// //         String[] columnNames = {"Course Level", "Base Fee (RM)"};
// //         Object[][] data = {
// //                 {"Level 1 Matriculation", 1000},
// //                 {"Level 2 Undergraduate", 6000},
// //                 {"Level 3 Postgraduate", 3000},
// //                 {"Special Case", 10000}
// //         };

// //         DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
// //         priceTable = new JTable(tableModel);
// //         priceTable.setFont(new Font("Arial", Font.PLAIN, 14));
// //         priceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
// //         priceTable.setRowHeight(25);
// //         JScrollPane tableScrollPane = new JScrollPane(priceTable);
// //         tableScrollPane.setPreferredSize(new Dimension(400, 150));

// //         // Calculate, Clear, and Back buttons
// //         JPanel buttonPanel = new JPanel();
// //         buttonPanel.setOpaque(false); // Make the button panel transparent
// //         buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Center align buttons with spacing
// //         calculateButton = createButton("Calculate");
// //         clearButton = createButton("Clear");
// //         backButton = createButton("Back");
// //         buttonPanel.add(calculateButton);
// //         buttonPanel.add(clearButton);
// //         buttonPanel.add(backButton);

// //         // Message label for feedback
// //         messageLabel = new JLabel("Select courses to calculate total fee.");
// //         messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
// //         messageLabel.setForeground(Color.WHITE);
// //         messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// //         // Adding components to formPanel using BoxLayout
// //         formPanel.add(level1CheckBox);
// //         formPanel.add(level2CheckBox);
// //         formPanel.add(level3CheckBox);
// //         formPanel.add(specialCaseCheckBox);
// //         formPanel.add(Box.createVerticalStrut(20));
// //         formPanel.add(tableScrollPane);
// //         formPanel.add(Box.createVerticalStrut(20));
// //         formPanel.add(messageLabel);
// //         formPanel.add(Box.createVerticalStrut(10));
// //         formPanel.add(buttonPanel);

// //         add(formPanel, BorderLayout.CENTER);

// //         // Action listener for Calculate button
// //         calculateButton.addActionListener(new ActionListener() {
// //             @Override
// //             public void actionPerformed(ActionEvent e) {
// //                 calculateTotalFee();
// //             }
// //         });

// //         // Action listener for Clear button
// //         clearButton.addActionListener(new ActionListener() {
// //             @Override
// //             public void actionPerformed(ActionEvent e) {
// //                 clearSelections();
// //             }
// //         });

// //         // Action listener for Back button
// //         backButton.addActionListener(new ActionListener() {
// //             @Override
// //             public void actionPerformed(ActionEvent e) {
// //                 mainFrame.showPanel("admin");
// //             }
// //         });
// //     }

// //     // Method to calculate total fee based on selected checkboxes
// //     private void calculateTotalFee() {
// //         double totalFee = 0.0; // Variable to store total fee

// //         // Reset message label
// //         messageLabel.setForeground(Color.WHITE);

// //         // Determine the fee calculation based on selected checkboxes
// //         if (level1CheckBox.isSelected()) {
// //             totalFee += 1000; // Base fee for Level 1 Matriculation
// //             showMessage("Selected: Level 1 Matriculation - no discount, 1 year");
// //         }

// //         if (level2CheckBox.isSelected()) {
// //             double baseFee = 6000; // Base fee for Level 2 Undergraduate
// //             double discountedFee = baseFee * 0.9; // 10% discount for each year
// //             totalFee += discountedFee * 3; // Total fee for 3 years
// //             showMessage("Selected: Level 2 Undergraduate - discount 10% per year, 3 years");
// //         }

// //         if (level3CheckBox.isSelected()) {
// //             double baseFee = 3000; // Base fee for Level 3 Postgraduate
// //             double discountedFee = baseFee * 0.9; // 10% discount for each year
// //             totalFee += discountedFee * 1.5; // Total fee for 1.5 years
// //             showMessage("Selected: Level 3 Postgraduate - discount 10% per year, 1.5 years");
// //         }

// //         if (specialCaseCheckBox.isSelected()) {
// //             totalFee = 35000; // Special case fee of RM 35,000
// //             showMessage("Selected: Special Case - fixed fee of RM 35,000");
// //         }

// //         // Display the total fee
// //         if (totalFee > 0) {
// //             JOptionPane.showMessageDialog(this, "<html><b>Total Fee:</b> RM " + String.format("%.2f", totalFee) + "</html>");
// //         } else {
// //             showMessage("No courses selected.");
// //         }
// //     }

// //     // Method to create and customize checkboxes with bold text and padding to the left
// //     private JCheckBox createCheckBox(String text) {
// //         JCheckBox checkBox = new JCheckBox(text);
// //         checkBox.setFont(new Font("Arial", Font.BOLD, 14)); // Bold text
// //         checkBox.setForeground(Color.WHITE); // White text color
// //         checkBox.setOpaque(false); // Make the checkbox background transparent
// //         checkBox.setBorder(new EmptyBorder(0, 20, 0, 0)); // Add left padding to move the checkbox to the right
// //         return checkBox;
// //     }

// //     // Method to create and customize buttons
// //     private JButton createButton(String text) {
// //         JButton button = new JButton(text);
// //         button.setFont(new Font("Arial", Font.BOLD, 16));
// //         button.setBackground(new Color(51, 153, 255)); // Blue background
// //         button.setForeground(Color.WHITE); // White text color
// //         button.setFocusPainted(false); // Remove focus border
// //         button.setPreferredSize(new Dimension(150, 40)); // Button size
// //         return button;
// //     }

// //     // Method to show messages in message label with bold text
// //     private void showMessage(String message) {
// //         messageLabel.setText("<html><b>" + message + "</b></html>");
// //     }

// //     // Method to clear all checkbox selections
// //     private void clearSelections() {
// //         level1CheckBox.setSelected(false);
// //         level2CheckBox.setSelected(false);
// //         level3CheckBox.setSelected(false);
// //         specialCaseCheckBox.setSelected(false);
// //         showMessage("Selections cleared.");
// //     }

// //     // Main method for testing the panel
// //     public static void main(String[] args) {
// //         SwingUtilities.invokeLater(new Runnable() {
// //             public void run() {
// //                 JFrame frame = new JFrame("Select Course Panel Test");
// //                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// //                 frame.setSize(600, 400);
// //                 frame.getContentPane().add(new SelectCoursePanel(null)); // Replace null with your MainFrame instance
// //                 frame.setVisible(true);
// //             }
// //         });
// //     }
// // }

// // class ImagePanel extends JPanel {
// //     private Image image;

// //     public ImagePanel() {
// //         super();
// //         setLayout(new BorderLayout());
// //     }

// //     public void setImage(Image image) {
// //         this.image = image;
// //         repaint();
// //     }

// //     @Override
// //     protected void paintComponent(Graphics g) {
// //         super.paintComponent(g);
// //         if (image != null) {
// //             g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
// //         }
// //     }
// // }
