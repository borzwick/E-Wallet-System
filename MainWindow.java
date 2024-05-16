import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements ActionListener {
    private JButton cashInButton, cashOutButton, checkHistoryButton, checkBalanceButton, logoutButton;
    private double balance = 0.0;
    private List<String> transactionHistory = new ArrayList<>();

    public MainWindow() {
        // Set up the frame
        setTitle("E-Wallet System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create a panel for the buttons
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Create the buttons
        cashInButton = new JButton("Cash In");
        cashOutButton = new JButton("Cash Out");
        checkHistoryButton = new JButton("Check History");
        checkBalanceButton = new JButton("Check Balance");
        logoutButton = new JButton("Logout");

        // Add action listeners
        cashInButton.addActionListener(this);
        cashOutButton.addActionListener(this);
        checkHistoryButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // Add the buttons to the panel
        panel.add(cashInButton);
        panel.add(cashOutButton);
        panel.add(checkHistoryButton);
        panel.add(checkBalanceButton);
        panel.add(logoutButton);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Show the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            dispose(); // Close the main window
            new LoginWindow().setVisible(true); // Open the login window
        } else if (e.getSource() == cashInButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash in:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0) {
                        balance += amount;
                        transactionHistory.add("Cash In: Php. " + amount);
                        JOptionPane.showMessageDialog(this, "Cash in successful. New balance: Php. " + balance);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
                }
            }
        } else if (e.getSource() == cashOutButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash out:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0 && amount <= balance) {
                        balance -= amount;
                        transactionHistory.add("Cash Out: Php. " + amount);
                        JOptionPane.showMessageDialog(this, "Cash out successful. New balance: Php. " + balance);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
                }
            }
        } else if (e.getSource() == checkHistoryButton) {
            String historyMsg = "Transaction History:\n";
            for (String transaction : transactionHistory) {
                historyMsg += transaction + "\n";
            }
            JOptionPane.showMessageDialog(this, historyMsg);
        } else if (e.getSource() == checkBalanceButton) {
            JOptionPane.showMessageDialog(this, "Current balance: Php. " + balance);
        }
    }
}