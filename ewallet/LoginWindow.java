////// LOGIN WINDOW CLASS----------------------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private JLabel welcomeLabel;

    public LoginWindow() {
        
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        

        //COLOR
        loginButton.setBackground(new Color(135,206,250));
        signUpButton.setBackground(new Color(135,206,250));
        usernameField.setBorder(new LineBorder(new Color(51, 153, 255)));
        passwordField.setBorder(new LineBorder(new Color(51, 153, 255)));
       

        //FONT
        Font customFont = new Font("Segoe print", Font.BOLD, 15);
        loginButton.setFont(customFont);
        signUpButton.setFont(customFont);
        usernameLabel.setFont(customFont);
        passwordLabel.setFont(customFont);
        usernameField.setFont(customFont);
        passwordField.setFont(customFont);

        welcomeLabel = new JLabel("Welcome to CashA e-Wallet");
        welcomeLabel.setFont(customFont);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);
        
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);
        //panel.setBackground(new Color(192,192,192));

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
