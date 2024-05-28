import javax.swing.*; // Import Swing classes for GUI components
import java.awt.*; // Import AWT classes for GUI layout and components
import java.awt.event.ActionEvent; // Import ActionEvent for handling button clicks
import java.awt.event.ActionListener; // Import ActionListener for button action events
import java.io.IOException; // Import IOException for file operations
import java.nio.charset.StandardCharsets; // Import StandardCharsets for encoding
import java.nio.file.Files; // Import Files for file operations
import java.nio.file.Paths; // Import Paths for file paths
import java.util.ArrayList; // Import ArrayList for dynamic arrays
import java.util.List; // Import List for collections
import java.nio.file.Path; // Import Path for file paths

// This class represents the main window of the e-wallet system
public class MainWindow extends JFrame implements ActionListener {
    private JLabel balanceLabel; // Label to display current balance
    private JButton cashInButton, cashOutButton, checkHistoryButton, checkBalanceButton, logoutButton; // Buttons for various operations
    private Account account; // Current user account
    private List<String> transactionHistory = new ArrayList<>(); // List to store transaction history

    // Method to save transaction history to a file
    private void saveTransactionHistoryToFile() {
        try {
            Path path = Paths.get(account.getUsername() + ".txt"); // Get path for the user's transaction history file
            Files.write(path, transactionHistory, StandardCharsets.UTF_8); // Write transaction history to the file
        } catch (IOException ex) {
            ex.printStackTrace(); // Print stack trace if an IOException occurs
        }
    }

    // Method to load transaction history from a file
    private void loadTransactionHistoryFromFile() {
        try {
            Path path = Paths.get(account.getUsername() + ".txt"); // Get path for the user's transaction history file
            if (Files.exists(path)) {
                transactionHistory = Files.readAllLines(path, StandardCharsets.UTF_8); // Read transaction history from the file
            } else {
                transactionHistory = new ArrayList<>(); // Create a new list if the file doesn't exist
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Print stack trace if an IOException occurs
        }
    }

    // Constructor for the MainWindow class
    public MainWindow(Account account) {
        this.account = account; // Set the current account

        setTitle("Coarta Main Menu"); // Set the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        setSize(600, 400); // Set window size
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Disable window resizing

        ImageIcon icon = new ImageIcon("C:\\Users\\lopez\\Downloads\\coarta2-removebg-preview.png"); // Specify the path to your icon here
        setIconImage(icon.getImage()); // Set window icon

        // Set up color scheme
        Color primaryColor = new Color(51, 102, 255); // Define primary color
        Color secondaryColor = new Color(230, 230, 230); // Define secondary color

        // Create components
        JPanel panel = new JPanel(new GridBagLayout()); // Create main panel with GridBagLayout
        panel.setBackground(secondaryColor); // Set background color
        GridBagConstraints c = new GridBagConstraints(); // Create constraints
        c.insets = new Insets(10, 10, 10, 10); // Set insets for spacing

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Create panel for title with FlowLayout
        titlePanel.setBackground(secondaryColor); // Set background color

        // Create the title label
        JLabel titleLabel = new JLabel("Coarta"); // Create title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
        titleLabel.setForeground(primaryColor); // Set text color

        // Resize the icon to be a little bigger
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\lopez\\Downloads\\coarta2-removebg-preview.png"); // Change to your icon path
        Image originalImage = originalIcon.getImage(); // Get original image
        int iconSize = titleLabel.getFont().getSize() + 10; // Calculate icon size
        Image resizedImage = originalImage.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH); // Resize image
        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create new ImageIcon with resized image
        JLabel iconLabel = new JLabel(resizedIcon); // Create label with resized icon

        // Add icon and title to the title panel
        titlePanel.add(iconLabel); // Add icon to title panel
        titlePanel.add(titleLabel); // Add title label to title panel

        c.gridx = 0; // Set grid x-coordinate
        c.gridy = 0; // Set grid y-coordinate
        c.gridwidth = 2; // Set grid width
        c.anchor = GridBagConstraints.CENTER; // Set anchor to center
        panel.add(titlePanel, c); // Add title panel to main panel

        balanceLabel = new JLabel("Current balance: Php. " + account.getBalance()); // Create balance label
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size
        balanceLabel.setForeground(primaryColor); // Set text color
        c.gridx = 0; // Set grid x-coordinate
        c.gridy = 1; // Set grid y-coordinate
        c.gridwidth = 2; // Set grid width
        c.anchor = GridBagConstraints.CENTER; // Set anchor to center
        panel.add(balanceLabel, c); // Add balance label to main panel

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Create panel for buttons with GridLayout
        // Grid of buttons

        cashInButton = createButton("Cash In", primaryColor); // Create cash in button
        buttonPanel.add(cashInButton); // Add cash in button to button panel

        cashOutButton = createButton("Cash Out", primaryColor); // Create cash out button
        buttonPanel.add(cashOutButton); // Add cash out button to button panel

        checkHistoryButton = createButton("Check History", primaryColor); // Create check history button
        buttonPanel.add(checkHistoryButton); // Add check history button to button panel

        checkBalanceButton = createButton("Check Balance", primaryColor); // Create check balance button
        buttonPanel.add(checkBalanceButton); // Add check balance button to button panel

        logoutButton = createButton("Logout", new Color(255, 102, 102)); // Create logout button
        c.gridx = 0; // Set grid x-coordinate
        c.gridy = 3; // Set grid y-coordinate
        c.gridwidth = 2; // Set grid width
        c.anchor = GridBagConstraints.CENTER; // Set anchor to center
        panel.add(logoutButton, c); // Add logout button to main panel

        c.gridx = 0; // Set grid x-coordinate
        c.gridy = 2; // Set grid y-coordinate
        c.gridwidth = 2; // Set grid width
        c.anchor = GridBagConstraints.CENTER; // Set anchor to center
        panel.add(buttonPanel, c); // Add button panel to main panel

        add(panel, BorderLayout.CENTER); // Add main panel to the frame's center

        setVisible(true); // Make the window visible
    }

    // Helper method to create buttons with consistent styling
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text); // Create a new button with the specified text
        button.setBackground(color); // Set button background color
        button.setForeground(Color.WHITE); // Set button text color
        button.setBorder(BorderFactory.createLineBorder(color, 10, true)); // Set button border with rounded corners
        button.addActionListener(this); // Add action listener for button clicks
        return button; // Return the created button
    }

    // ActionListener implementation for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) { // If the logout button is clicked
            saveTransactionHistoryToFile(); // Save transaction history before logging out
            dispose(); // Close the main window
            new LoginWindow().setVisible(true); // Open the login window
        } else if (e.getSource() == cashInButton) { // If the cash in button is clicked
            // Placeholder for cash-in operation
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash in:"); // Prompt user for cash in amount
            if (amountStr != null && !amountStr.isEmpty()) { // If input is not empty
                try {
                    double amount = Double.parseDouble(amountStr); // Convert input to double
                    if (amount > 0) { // If amount is positive
                        account.setBalance(account.getBalance() + amount); // Update account balance
                        transactionHistory.add("Cash In: Php. " + amount); // Add transaction to history
                        saveTransactionHistoryToFile(); // Save transaction history after cash-in
                        JOptionPane.showMessageDialog(this, "Cash in successful. New balance: Php. " + account.getBalance()); // Show success message
                        updateBalanceLabel(); // Update balance label
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value."); // Show error message for invalid amount
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number."); // Show error message for invalid input
                }
            }
        } else if (e.getSource() == cashOutButton) { // If the cash out button is clicked
            // Placeholder for cash-out operation
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash out:"); // Prompt user for cash out amount
            if (amountStr != null && !amountStr.isEmpty()) { // If input is not empty
                try {
                    double amount = Double.parseDouble(amountStr); // Convert input to double
                    if (amount > 0 && amount <= account.getBalance()) { // If amount is positive and less than or equal to account balance
                        account.setBalance(account.getBalance() - amount); // Update account balance
                        transactionHistory.add("Cash Out: Php. " + amount); // Add transaction to history
                        saveTransactionHistoryToFile(); // Save transaction history after cash-out
                        JOptionPane.showMessageDialog(this, "Cash out successful. New balance: Php. " + account.getBalance()); // Show success message
                        updateBalanceLabel(); // Update balance label
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance."); // Show error message for invalid amount or insufficient balance
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number."); // Show error message for invalid input
                }
            }
        } else if (e.getSource() == checkHistoryButton) { // If the check history button is clicked
            loadTransactionHistoryFromFile(); // Load transaction history from file
            String historyMsg = "Transaction History:\n"; // Initialize history message
            for (String transaction : transactionHistory) { // Iterate through transaction history
                historyMsg += transaction + "\n"; // Append each transaction to history message
            }
            JOptionPane.showMessageDialog(this, historyMsg); // Show transaction history message
        } else if (e.getSource() == checkBalanceButton) { // If the check balance button is clicked
            // Placeholder for check balance operation
            JOptionPane.showMessageDialog(this, "Current balance: Php. " + account.getBalance()); // Show current balance
        }
    }

    // Method to update the balance label
    private void updateBalanceLabel() {
        balanceLabel.setText("Current balance: Php. " + account.getBalance()); // Update balance label with current balance
    }
}

