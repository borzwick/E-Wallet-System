import java.util.HashMap; // Import HashMap class for storing key-value pairs
import java.util.Map; // Import Map interface for mapping keys to values

// This class manages user accounts for the e-wallet system
public class AccountManager {
    // Map to store user accounts with usernames as keys
    private static Map<String, Account> accounts = new HashMap<>(); // Create a HashMap to store accounts

    // Method to add an account to the map
    public static void addAccount(Account account) {
        accounts.put(account.getUsername(), account); // Add the account to the map with its username as key
    }

    // Method to retrieve an account by username
    public static Account getAccount(String username) {
        return accounts.get(username); // Retrieve the account associated with the given username
    }

    // Method to check if an account exists based on username
    public static boolean accountExists(String username) {
        return accounts.containsKey(username); // Check if the map contains the given username as a key
    }
}
