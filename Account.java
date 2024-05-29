// This class represents a user account for the e-wallet system
public class Account {
    // Instance variables to store account details
    private String username; // Username of the account
    private String password; // Password of the account
    private double balance; // Current balance in the account

    // Constructor to initialize an account with username, password, and balance
    public Account(String username, String password, double balance) {
        this.username = username; // Initialize username
        this.password = password; // Initialize password
        this.balance = balance; // Initialize balance
    }

    // Getter method to retrieve the username of the account
    public String getUsername() {
        return username; // Return the username of the account
    }

    // Getter method to retrieve the password of the account
    public String getPassword() {
        return password; // Return the password of the account
    }

    // Getter method to retrieve the current balance of the account
    public double getBalance() {
        return balance; // Return the current balance of the account
    }

    // Setter method to update the balance of the account
    public void setBalance(double balance) {
        this.balance = balance; // Set the balance of the account to the given value
    }
}
