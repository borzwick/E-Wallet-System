////// MAIN WINDOW CLASS----------------------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;

public class MainWindow extends JFrame implements ActionListener {
    private JLabel balanceLabel;
    private JButton cashInButton, cashOutButton, checkHistoryButton, checkBalanceButton, logoutButton;
    private Account account;
    private List<String> transactionHistory = new ArrayList<>();

    // Method to save transaction history to a file
    private void saveTransactionHistoryToFile() {
        try {
            Path path = Paths.get(account.getUsername() + ".txt");
            Files.write(path, transactionHistory, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to load transaction history from a file
    private void loadTransactionHistoryFromFile() {
        try {
            Path path = Paths.get(account.getUsername() + ".txt");
            if (Files.exists(path)) {
                transactionHistory = Files.readAllLines(path, StandardCharsets.UTF_8);
            } else {
                transactionHistory = new ArrayList<>();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Constructor for the MainWindow class
    public MainWindow(Account account) {
        this.account = account;

        setTitle("Coarta Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon("C:\\Users\\Chooey\\Downloads\\E-Wallet-System-main\\ewallet\\coarta2.png"); // Specify the path to your icon here
        setIconImage(icon.getImage());

        // Set up color scheme
        Color primaryColor = new Color(51, 102, 255);
        Color secondaryColor = new Color(230, 230, 230);

        // Create components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(secondaryColor);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Coarta");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(primaryColor);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, c);

        balanceLabel = new JLabel("Current balance: Php. " + account.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(primaryColor);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(balanceLabel, c);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Grid of buttons

        cashInButton = createButton("Cash In", primaryColor);
        buttonPanel.add(cashInButton);

        cashOutButton = createButton("Cash Out", primaryColor);
        buttonPanel.add(cashOutButton);

        checkHistoryButton = createButton("Check History", primaryColor);
        buttonPanel.add(checkHistoryButton);

        checkBalanceButton = createButton("Check Balance", primaryColor);
        buttonPanel.add(checkBalanceButton);

        logoutButton = createButton("Logout", new Color(255, 102, 102));
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(logoutButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, c);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Helper method to create buttons with consistent styling
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(color, 10, true)); // Fix: Use createLineBorder instead of createRoundedBorder
        button.addActionListener(this);
        return button;
    }

    // ActionListener implementation for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            saveTransactionHistoryToFile();  // Save transaction history before logging out
            dispose(); // Close the main window
            new LoginWindow().setVisible(true); // Open the login window
        } else if (e.getSource() == cashInButton) {
            // Placeholder for cash-in operation
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash in:", "Cash In", JOptionPane.QUESTION_MESSAGE);
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0) {
                        account.setBalance(account.getBalance() + amount);
                        transactionHistory.add("Cash In: Php. " + amount);
                        saveTransactionHistoryToFile();  // Save transaction history after cash-in
                        JOptionPane.showMessageDialog(this, "Cash in successful. New balance: Php. " + account.getBalance(), "Cash In", JOptionPane.INFORMATION_MESSAGE);
                        updateBalanceLabel();
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
                        account.setBalance(account.getBalance() - amount);
                        transactionHistory.add("Cash Out: Php. " + amount);
                        saveTransactionHistoryToFile();  // Save transaction history after cash-out
                        JOptionPane.showMessageDialog(this, "Cash out successful. New balance: Php. " + account.getBalance(), "Cash Out", JOptionPane.INFORMATION_MESSAGE);
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance.", "Cash Out", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Cash Out", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == checkHistoryButton) {
            loadTransactionHistoryFromFile();  // Load transaction history from file
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
