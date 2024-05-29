import javax.swing.*; // Import Swing library for GUI components
import java.awt.*; // Import AWT library for UI layout and components
import java.awt.event.ActionEvent; // Import ActionEvent for handling button clicks
import java.awt.event.ActionListener; // Import ActionListener for handling button events

// This class represents the login window for the e-wallet system
public class LoginWindow extends JFrame implements ActionListener {
    // UI components
    private JTextField usernameField; // Text field for entering username
    private JPasswordField passwordField; // Password field for entering password
    private JButton loginButton, signUpButton; // Buttons for login and sign up

    // Constructor for the LoginWindow class
    public LoginWindow() {
        // Set up the login window
        setTitle("Coarta Login"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(400, 300); // Set window size
        setLocationRelativeTo(null); // Center window on screen
        setResizable(true); // Disable window resizing

        ImageIcon icon = new ImageIcon("C:\\Users\\ACER\\Desktop\\CMPSC 113\\e-Wallet\\coarta2.png"); // Specify the path to your icon here
        setIconImage(icon.getImage());

        // Set up color scheme
        Color primaryColor = new Color(51, 102, 255); // Primary color
        Color secondaryColor = new Color(230, 230, 230); // Secondary color

        // Create components
        JPanel panel = new JPanel(new GridBagLayout()); // Create main panel with GridBagLayout
        panel.setBackground(secondaryColor); // Set background color
        GridBagConstraints c = new GridBagConstraints(); // GridBagLayout constraints
        c.insets = new Insets(10, 10, 10, 10); // Insets for spacing

        // Create and add the title label
        JLabel titleLabel = new JLabel("Coarta Login"); // Create title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
        titleLabel.setForeground(primaryColor); // Set text color
        c.gridx = 0; // Grid position X
        c.gridy = 0; // Grid position Y
        c.gridwidth = 2; // Grid width
        c.anchor = GridBagConstraints.CENTER; // Alignment
        panel.add(titleLabel, c); // Add title label to panel

        // Create and add the username label
        JLabel usernameLabel = new JLabel("Username:"); // Create username label
        usernameLabel.setForeground(primaryColor); // Set text color
        c.gridx = 0; // Grid position X
        c.gridy = 1; // Grid position Y
        c.gridwidth = 1; // Grid width
        c.anchor = GridBagConstraints.LINE_END; // Alignment
        panel.add(usernameLabel, c); // Add username label to panel

        // Create and add the username text field
        usernameField = new JTextField(15); // Create username text field with initial width
        c.gridx = 1; // Grid position X
        c.gridy = 1; // Grid position Y
        c.anchor = GridBagConstraints.LINE_START; // Alignment
        panel.add(usernameField, c); // Add username text field to panel

        // Create and add the password label
        JLabel passwordLabel = new JLabel("Password:"); // Create password label
        passwordLabel.setForeground(primaryColor); // Set text color
        c.gridx = 0; // Grid position X
        c.gridy = 2; // Grid position Y
        c.anchor = GridBagConstraints.LINE_END; // Alignment
        panel.add(passwordLabel, c); // Add password label to panel

        // Create and add the password field
        passwordField = new JPasswordField(15); // Create password field with initial width
        c.gridx = 1; // Grid position X
        c.gridy = 2; // Grid position Y
        c.anchor = GridBagConstraints.LINE_START; // Alignment
        panel.add(passwordField, c); // Add password field to panel

        // Create a panel for the buttons with a grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Create button panel with 1 row, 2 columns, horizontal gap of 10, vertical gap of 0

        // Create and add the login button
        loginButton = new JButton("Login"); // Create login button
        loginButton.setBackground(primaryColor); // Set background color
        loginButton.setForeground(Color.WHITE); // Set text color
        loginButton.setBorder(BorderFactory.createLineBorder(primaryColor, 10, true)); // Set border
        loginButton.addActionListener(this); // Add ActionListener
        buttonPanel.add(loginButton); // Add login button to button panel

        // Create and add the sign-up button
        signUpButton = new JButton("Sign Up"); // Create sign-up button
        signUpButton.setBackground(primaryColor); // Set background color
        signUpButton.setForeground(Color.WHITE); // Set text color
        signUpButton.setBorder(BorderFactory.createLineBorder(primaryColor, 10, true)); // Set border
        signUpButton.addActionListener(this); // Add ActionListener
        buttonPanel.add(signUpButton); // Add sign-up button to button panel

        // Add the button panel to the main panel
        c.gridx = 0; // Grid position X
        c.gridy = 3; // Grid position Y
        c.gridwidth = 2; // Grid width
        c.anchor = GridBagConstraints.CENTER; // Alignment
        panel.add(buttonPanel, c); // Add button panel to main panel

        // Add the main panel to the frame
        add(panel, BorderLayout.CENTER); // Add main panel to frame center

        // Make the window visible
        setVisible(true); // Set window visibility to true
    }

    // ActionListener implementation for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) { // If login button is clicked
            // Handle login button click
            String username = usernameField.getText(); // Get username from text field
            String password = new String(passwordField.getPassword()); // Get password from password field

            // Check if account exists and password is correct
            if (AccountManager.accountExists(username) && AccountManager.getAccount(username).getPassword().equals(password)) { // If account exists and password matches
                dispose(); // Close the login window
                new MainWindow(AccountManager.getAccount(username)); // Open the main window with the corresponding account
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password."); // Show error message
            }
        } else if (e.getSource() == signUpButton) { // If sign-up button is clicked
            // Handle sign-up button click
            String username = usernameField.getText(); // Get username from text field
            String password = new String(passwordField.getPassword()); // Get password from password field

            // Validate input and check if account already exists
            if (username.isEmpty() || password.isEmpty()) { // If username or password is empty
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty."); // Show error message
            } else if (AccountManager.accountExists(username)) { // If account already exist
                JOptionPane.showMessageDialog(this, "Account already exists. Make a new one."); // Show error message
            } else {
                // Add new user with initial balance of 0.0 PHP
                Account account = new Account(username, password, 0.0); // Create new account
                AccountManager.addAccount(account); // Add account to the account manager
                JOptionPane.showMessageDialog(this, "Account created successfully."); // Show success message
            }       
        }
    }      
}