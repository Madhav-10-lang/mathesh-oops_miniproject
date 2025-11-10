import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The core system that handles account management and transaction processing.
 */
public class ATM {
    // Stores accounts using account number as the key
    private final Map<String, Account> accounts;

    public ATM() {
        this.accounts = new HashMap<>();
        // Seed some initial accounts for testing
        Account defaultAccount = new Account("1234", new BigDecimal("500.75"));
        accounts.put(defaultAccount.getAccountNumber(), defaultAccount);
        System.out.println("System Initialized. Test Account: " + defaultAccount.getAccountNumber() + ", PIN: " + defaultAccount.getPin());
    }

    /**
     * Validates the card (account number) and PIN.
     * @param accountNumber The provided account number.
     * @param pin The provided PIN.
     * @return The Account object if valid, otherwise null.
     */
    public Account validateUser(String accountNumber, String pin) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            return account;
        }
        return null;
    }

    /**
     * Handles a withdrawal request.
     * @param account The authenticated account.
     * @param amount The amount to withdraw.
     * @return A status message regarding the transaction.
     */
    public String handleWithdrawal(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Error: Withdrawal amount must be positive.";
        }
        if (account.withdraw(amount)) {
            return "SUCCESS: Withdrew $" + amount + ". New balance: $" + account.getBalance();
        } else {
            return "FAILURE: Insufficient funds. Current balance: $" + account.getBalance();
        }
    }

    /**
     * Handles a deposit request.
     * @param account The authenticated account.
     * @param amount The amount to deposit.
     * @return A status message regarding the transaction.
     */
    public String handleDeposit(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Error: Deposit amount must be positive.";
        }
        account.deposit(amount);
        return "SUCCESS: Deposited $" + amount + ". New balance: $" + account.getBalance();
    }

    /**
     * Handles a balance inquiry request.
     * @param account The authenticated account.
     * @return The current balance.
     */
    public BigDecimal getBalance(Account account) {
        return account.getBalance();
    }
}
