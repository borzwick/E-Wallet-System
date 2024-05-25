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
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);

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
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } else if (e.getSource() == signUpButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
            } else if (AccountManager.accountExists(username)) {
                JOptionPane.showMessageDialog(this, "Account already exists. Make a new one.");
            } else {
                // Add new user with initial balance of 0.0 PHP
                Account account = new Account(username, password, 0.0);
                AccountManager.addAccount(account);
                JOptionPane.showMessageDialog(this, "Account created successfully.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}