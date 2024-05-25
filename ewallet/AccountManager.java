//ACCOUNT MANAGER CLASS------------------------------------------------------------------------------------------------------------

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static Map<String, Account> accounts = new HashMap<>();

    public static void addAccount(Account account) {
        accounts.put(account.getUsername(), account);
    }

    public static Account getAccount(String username) {
        return accounts.get(username);
    }

    public static boolean accountExists(String username) {
        return accounts.containsKey(username);
    }

    // Other methods for account management
}