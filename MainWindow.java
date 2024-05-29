// Import necessary Swing classes for creating the GUI components
import javax.swing.*;
// Import classes for working with layouts and event handling
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Import classes for file I/O operations
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
// Import classes for working with collections
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;

// The MainWindow class extends JFrame and implements the ActionListener interface
// It follows the OOP principle of inheritance (inheriting from JFrame) and implements an interface (ActionListener)
public class MainWindow extends JFrame implements ActionListener {
    // Instance variables for GUI components and account object
    private JLabel balanceLabel; // Label to display the account balance
    private JButton cashInButton, cashOutButton, checkHistoryButton, checkBalanceButton, logoutButton; // Buttons for various operations
    private Account account; // Reference to the account object
    private List<String> transactionHistory = new ArrayList<>(); // List to store transaction history

    // Method to save transaction history to a file
    private void saveTransactionHistoryToFile() {
        try {
            // Create a Path object with the username as the file name
            Path path = Paths.get(account.getUsername() + ".txt");
            // Write the transaction history list to the file using Files.write
            Files.write(path, transactionHistory, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            // Handle any IOException that may occur during file writing
            ex.printStackTrace();
        }
    }

    // Method to load transaction history from a file
    private void loadTransactionHistoryFromFile() {
        try {
            // Create a Path object with the username as the file name
            Path path = Paths.get(account.getUsername() + ".txt");
            // Check if the file exists
            if (Files.exists(path)) {
                // If the file exists, read all lines from the file and store them in the transactionHistory list
                transactionHistory = Files.readAllLines(path, StandardCharsets.UTF_8);
            } else {
                // If the file doesn't exist, create a new empty list for transactionHistory
                transactionHistory = new ArrayList<>();
            }
        } catch (IOException ex) {
            // Handle any IOException that may occur during file reading
            ex.printStackTrace();
        }
    }

    // Constructor for the MainWindow class
    public MainWindow(Account account) {
        this.account = account; // Initialize the account object

        // Set up the main window
        setTitle("Coarta Main Menu"); // Set the window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        setSize(600, 400); // Set the window size
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false); // Disable window resizing

        // Set the application icon
        ImageIcon icon = new ImageIcon("C:\\Users\\ACER\\Desktop\\CMPSC 113\\e-Wallet\\coarta2.png"); // Specify the path to your icon here
        setIconImage(icon.getImage());

        // Set up color scheme
        Color primaryColor = new Color(51, 102, 255); // Define the primary color
        Color secondaryColor = new Color(230, 230, 230); // Define the secondary color

        // Create components
        JPanel panel = new JPanel(new GridBagLayout()); // Create a panel with GridBagLayout
        panel.setBackground(secondaryColor); // Set the panel background color
        GridBagConstraints c = new GridBagConstraints(); // Create GridBagConstraints for layout management
        c.insets = new Insets(10, 10, 10, 10); // Set the insets (padding) for components

        // Create the title label and icon
        JLabel titleLabel = new JLabel("Coarta");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font and size for the title label
        titleLabel.setForeground(primaryColor); // Set the text color for the title label

        // Load and resize the icon
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ACER\\Desktop\\CMPSC 113\\e-Wallet\\coarta3.png"); // Change to your icon path
        Image originalImage = originalIcon.getImage(); // Get original image
        int iconSize = titleLabel.getFont().getSize() + 10; // Calculate icon size
        Image resizedImage = originalImage.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH); // Resize image
        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create new ImageIcon with resized image
        JLabel iconLabel = new JLabel(resizedIcon); // Create label with resized icon

        // Add icon and title to the title panel
        panel.add(iconLabel); // Add icon to title panel
        panel.add(titleLabel); // Add title label to title panel

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;

        panel.add(titleLabel, c);

        // Create the balance label and add it to the panel
        balanceLabel = new JLabel("Current balance: Php. " + account.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font and size for the balance label
        balanceLabel.setForeground(primaryColor); // Set the text color for the balance label
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(balanceLabel, c);

        // Create a panel for the buttons with a GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Grid of buttons

        // Create buttons and add them to the button panel
        cashInButton = createButton("Cash In", primaryColor);
        buttonPanel.add(cashInButton);

        cashOutButton = createButton("Cash Out", primaryColor);
        buttonPanel.add(cashOutButton);

        checkHistoryButton = createButton("Check History", primaryColor);
        buttonPanel.add(checkHistoryButton);

        checkBalanceButton = createButton("Check Balance", primaryColor);
        buttonPanel.add(checkBalanceButton);

        // Create the logout button and add it to the main panel
        logoutButton = createButton("Logout", new Color(255, 102, 102));
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(logoutButton, c);

        // Add the button panel to the main panel
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, c);

        // Add the main panel to the window
        add(panel, BorderLayout.CENTER);

        setVisible(true); // Show the window
    }

    // Helper method to create buttons with consistent styling
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text); // Create a new JButton with the given text
        button.setBackground(color); // Set the button background color
        button.setForeground(Color.WHITE); // Set the button text color to white
        button.setBorder(BorderFactory.createLineBorder(color, 10, true)); // Set a line border around the button with the specified color and thickness
        button.addActionListener(this); // Add an ActionListener to the button (the current class implements ActionListener)
        return button;
    }

    // ActionListener implementation for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which button was clicked
        if (e.getSource() == logoutButton) {
            saveTransactionHistoryToFile(); // Save transaction history before logging out
            dispose(); // Close the main window
            new LoginWindow().setVisible(true); // Open the login window
        } else if (e.getSource() == cashInButton) {
            // Placeholder for cash-in operation
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash in:", "Cash In", JOptionPane.QUESTION_MESSAGE);
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0) {
                        account.setBalance(account.getBalance() + amount); // Update the account balance
                        transactionHistory.add("Cash In: Php. " + amount); // Add the cash-in transaction to the history
                        saveTransactionHistoryToFile(); // Save transaction history after cash-in
                        JOptionPane.showMessageDialog(this, "Cash in successful. New balance: Php. " + account.getBalance(), "Cash In", JOptionPane.INFORMATION_MESSAGE);
                        updateBalanceLabel(); // Update the balance label
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.", "Cash In", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Cash In", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == cashOutButton) {
            // Placeholder for cash-out operation
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash out:", "Cash Out", JOptionPane.QUESTION_MESSAGE);
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0 && amount <= account.getBalance()) {
                        account.setBalance(account.getBalance() - amount); // Update the account balance
                        transactionHistory.add("Cash Out: Php. " + amount); // Add the cash-out transaction to the history
                        saveTransactionHistoryToFile(); // Save transaction history after cash-out
                        JOptionPane.showMessageDialog(this, "Cash out successful. New balance: Php. " + account.getBalance(), "Cash Out", JOptionPane.INFORMATION_MESSAGE);
                        updateBalanceLabel(); // Update the balance label
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance.", "Cash Out", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Cash Out", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == checkHistoryButton) {
            loadTransactionHistoryFromFile(); // Load transaction history from file
            String historyMsg = "Transaction History:\n";
            for (String transaction : transactionHistory) {
                historyMsg += transaction + "\n";
            }
            JOptionPane.showMessageDialog(this, historyMsg, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == checkBalanceButton) {
            // Placeholder for check balance operation
            JOptionPane.showMessageDialog(this, "Current balance: Php. " + account.getBalance(), "Check Balance", JOptionPane.INFORMATION_MESSAGE);
        }
    }
         
            // Method to update the balance label
    private void updateBalanceLabel() {
        balanceLabel.setText("Current balance: Php. " + account.getBalance());
    }
}

