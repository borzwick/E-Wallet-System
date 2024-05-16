import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginWindow extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private Map<String, String> userAccounts;

    public LoginWindow() {
        // Set up the frame
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create a panel for the components
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Create the components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        // Add action listeners
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);

        // Add the components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Initialize the user accounts map
        userAccounts = new HashMap<>();

        // Show the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (userAccounts.containsKey(username) && userAccounts.get(username).equals(password)) {
                // Login successful
                dispose(); // Close the login window
                new MainWindow(); // Open the main window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } else if (e.getSource() == signUpButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
            } else if (userAccounts.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
            } else {
                // Create a new account
                userAccounts.put(username, password);
                JOptionPane.showMessageDialog(this, "Account created successfully.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow();
            }
        });
    }
}