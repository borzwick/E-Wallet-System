//////MAIN WINDOW CLASS------------------------------------------------------------------------------------------------------------------------

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
import java.nio.file.*;

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

        setTitle("E-Wallet System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        cashInButton = new JButton("Cash In");
        cashOutButton = new JButton("Cash Out");
        checkHistoryButton = new JButton("Check History");
        checkBalanceButton = new JButton("Check Balance");
        logoutButton = new JButton("Logout");

        cashInButton.addActionListener(this);
        cashOutButton.addActionListener(this);
        checkHistoryButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);
        logoutButton.addActionListener(this);

        panel.add(cashInButton);
        panel.add(cashOutButton);
        panel.add(checkHistoryButton);
        panel.add(checkBalanceButton);
        panel.add(logoutButton);

        balanceLabel = new JLabel("Current balance: Php. " + account.getBalance());
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel, BorderLayout.NORTH);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
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
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash in:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0) {
                        account.setBalance(account.getBalance() + amount);
                        transactionHistory.add("Cash In: Php. " + amount);
                        saveTransactionHistoryToFile();  // Save transaction history after cash-in
                        JOptionPane.showMessageDialog(this, "Cash in successful. New balance: Php. " + account.getBalance());
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
                }
            }
        } else if (e.getSource() == cashOutButton) {
            // Placeholder for cash-out operation
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash out:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0 && amount <= account.getBalance()) {
                        account.setBalance(account.getBalance() - amount);
                        transactionHistory.add("Cash Out: Php. " + amount);
                        saveTransactionHistoryToFile();  // Save transaction history after cash-out
                        JOptionPane.showMessageDialog(this, "Cash out successful. New balance: Php. " + account.getBalance());
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
                }
            }
        } else if (e.getSource() == checkHistoryButton) {
            loadTransactionHistoryFromFile();  // Load transaction history from file
            String historyMsg = "Transaction History:\n";
            for (String transaction : transactionHistory) {
                historyMsg += transaction + "\n";
            }
            JOptionPane.showMessageDialog(this, historyMsg);
        } else if (e.getSource() == checkBalanceButton) {
            // Placeholder for check balance operation
            JOptionPane.showMessageDialog(this, "Current balance: Php. " + account.getBalance());
        }
    }

    // Method to update the balance label
    private void updateBalanceLabel() {
        balanceLabel.setText("Current balance: Php. " + account.getBalance());
    }
}
