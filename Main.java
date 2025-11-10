import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        Account currentAccount = null;

        // --- 1. Login Simulation ---
        System.out.println("\n--- Welcome to the Console ATM ---");
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();
        
        currentAccount = atm.validateUser(accNum, pin);

        if (currentAccount == null) {
            System.out.println("Authentication Failed. Invalid Account Number or PIN.");
            return; // Exit application on failure
        }
        System.out.println("\nAuthentication successful! Welcome, Account " + currentAccount.getAccountNumber() + ".");

        // --- 2. Main Menu Loop ---
        boolean running = true;
        while (running) {
            System.out.println("\n--- Select an Option ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Funds");
            System.out.println("3. Deposit Funds");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            
            try {
                switch (choice) {
                    case "1": // Check Balance
                        System.out.println("Your current balance is: $" + atm.getBalance(currentAccount));
                        break;
                        
                    case "2": // Withdraw Funds
                        System.out.print("Enter amount to withdraw: $");
                        BigDecimal withdrawAmount = scanner.nextBigDecimal();
                        scanner.nextLine(); // Consume newline
                        String withdrawStatus = atm.handleWithdrawal(currentAccount, withdrawAmount);
                        System.out.println("Result: " + withdrawStatus);
                        break;
                        
                    case "3": // Deposit Funds
                        System.out.print("Enter amount to deposit: $");
                        BigDecimal depositAmount = scanner.nextBigDecimal();
                        scanner.nextLine(); // Consume newline
                        String depositStatus = atm.handleDeposit(currentAccount, depositAmount);
                        System.out.println("Result: " + depositStatus);
                        break;

                    case "4": // Exit
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input format. Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        scanner.close();
    }
}
