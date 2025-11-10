import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a basic bank account with a balance and PIN.
 */
public class Account {
    private static final AtomicLong nextAccountNumber = new AtomicLong(100000);

    private final String accountNumber;
    private final String pin;
    private BigDecimal balance;

    // Constructor to initialize an account
    public Account(String pin, BigDecimal initialBalance) {
        // Generates a unique, sequential account number
        this.accountNumber = String.valueOf(nextAccountNumber.getAndIncrement());
        this.pin = pin;
        this.balance = initialBalance;
    }

    // --- Getters ---
    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    // --- Business Methods ---

    /**
     * Attempts to deposit money into the account.
     * @param amount The amount to deposit.
     * @return true if deposit was successful.
     */
    public boolean deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(amount);
            return true;
        }
        return false;
    }

    /**
     * Attempts to withdraw money from the account.
     * @param amount The amount to withdraw.
     * @return true if withdrawal was successful (i.e., sufficient funds).
     */
    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && this.balance.compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount);
            return true;
        }
        return false;
    }
}
