////// LOGIN WINDOW CLASS----------------------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;

    public LoginWindow() {
        setTitle("Coarta Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
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

        JLabel titleLabel = new JLabel("Coarta Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(primaryColor);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, c);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(primaryColor);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, c);

        usernameField = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, c);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(primaryColor);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, c);

        passwordField = new JPasswordField(15);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, c);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Evenly spaced buttons

        loginButton = new JButton("Login");
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(primaryColor, 10, true)); // Rounded corners
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(primaryColor);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBorder(BorderFactory.createLineBorder(primaryColor, 10, true)); // Rounded corners
        signUpButton.addActionListener(this);
        buttonPanel.add(signUpButton);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, c);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (AccountManager.accountExists(username) && AccountManager.getAccount(username).getPassword().equals(password)) {
                dispose();
                new MainWindow(AccountManager.getAccount(username));
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == signUpButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            } else if (AccountManager.accountExists(username)) {
                JOptionPane.showMessageDialog(this, "Account already exists. Make a new one.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                // Add new user with initial balance of 0.0 PHP
                Account account = new Account(username, password, 0.0);
                AccountManager.addAccount(account);
                JOptionPane.showMessageDialog(this, "Account created successfully.", "Sign Up Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
