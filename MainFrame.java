import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<String, String> adminCredentials;
    private static final String FILE_PATH = "admin_credentials.txt";

    public MainFrame() {
        setTitle("Admin Register and Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        adminCredentials = loadCredentials();

        RegisterPanel registerPanel = new RegisterPanel(this);
        LoginPanel loginPanel = new LoginPanel(this);
        AdminPanel adminPanel = new AdminPanel(this);  // Placeholder for actual AdminPanel implementation
        RegisterStudentPanel registerStudentPanel = new RegisterStudentPanel(this);  // Placeholder
        SelectCoursePanel selectCoursePanel = new SelectCoursePanel(this);  // Placeholder
        StudentActivityPanel studentActivityPanel = new StudentActivityPanel(this);  // Updated to include fee calculation functionality
        HostelPanel hostelPanel = new HostelPanel(this);  // Placeholder
        // FinanceFeesPanel financeFeesPanel = new FinanceFeesPanel(this);  // Placeholder

        mainPanel.add(registerPanel, "register");
        mainPanel.add(loginPanel, "login");
        mainPanel.add(adminPanel, "admin");
        mainPanel.add(registerStudentPanel, "registerStudent");
        mainPanel.add(selectCoursePanel, "selectCourse");
        mainPanel.add(studentActivityPanel, "studentActivity");
        mainPanel.add(hostelPanel, "hostel");
        // mainPanel.add(financeFeesPanel, "financeFees");

        add(mainPanel);

        cardLayout.show(mainPanel, "register");

        setSize(800, 600); // Set window size to a standard size (width, height)
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    public Map<String, String> getAdminCredentials() {
        return adminCredentials;
    }

    public void saveCredentials() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, String> entry : adminCredentials.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> loadCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    credentials.put(parts[0], parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, return an empty map
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
