// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.util.List;


// public class FinanceFeesPanel extends JPanel {
//     private MainFrame mainFrame;
//     private JTable feesTable;
//     private DefaultTableModel tableModel;

//     public FinanceFeesPanel(MainFrame mainFrame) {
//         this.mainFrame = mainFrame;
//         this.setLayout(new BorderLayout());

//         // Initialize the table model with columns
//         String[] columnNames = {"Student ID", "Name", "Course", "Price (RM)", "Discount (RM)", "Net Payable (RM)"};
//         tableModel = new DefaultTableModel(columnNames, 0);

//         // Create JTable with the initialized table model
//         feesTable = new JTable(tableModel);
//         feesTable.setFont(new Font("Arial", Font.PLAIN, 14));
//         feesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//         feesTable.setRowHeight(25);
//         JScrollPane scrollPane = new JScrollPane(feesTable);

//         // Add components to the panel
//         this.add(new JLabel("Finance Fees Panel", SwingConstants.CENTER), BorderLayout.NORTH);
//         this.add(scrollPane, BorderLayout.CENTER);
//     }

//     // Method to update the fee table with student data
//     public void updateFeeTable(List<Student> studentList) {
//         // Clear existing rows
//         tableModel.setRowCount(0);

//         // Populate the table with student data
//         for (Student student : studentList) {
//             Object[] rowData = {
//                     student.getId(),
//                     student.getName(),
//                     student.getSelectedCourse(),
//                     student.getPriceBeforeDiscount(),
//                     student.getDiscountAmount(),
//                     student.getNetPayable()
//             };
//             tableModel.addRow(rowData);
//         }
//     }

//     // Method to clear the fee table
//     public void clearFeeTable() {
//         tableModel.setRowCount(0);
//     }

//     // Method to show a message in the panel
//     public void showMessage(String message) {
//         JOptionPane.showMessageDialog(this, message);
//     }

//     // Method to get selected student ID from the table
//     public String getSelectedStudentId() {
//         int rowIndex = feesTable.getSelectedRow();
//         if (rowIndex != -1) {
//             return (String) tableModel.getValueAt(rowIndex, 0); // Assuming student ID is in the first column
//         }
//         return null;
//     }
// }
