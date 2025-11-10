import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ATMBankingSimulator extends Application {

    private double balance = 0.0; // initial balance

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ATM Banking Simulator");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(20);

        // Account Number
        Label accLabel = new Label("Account Number:");
        TextField accField = new TextField();
        grid.add(accLabel, 0, 0);
        grid.add(accField, 1, 0);

        // PIN
        Label pinLabel = new Label("PIN:");
        PasswordField pinField = new PasswordField();
        grid.add(pinLabel, 0, 1);
        grid.add(pinField, 1, 1);

        // Display area
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setPrefHeight(150);
        grid.add(display, 0, 6, 2, 1);

        // Check Balance Button
        Button balanceBtn = new Button("Check Balance");
        balanceBtn.setOnAction(e -> display.setText("Current Balance: ₹" + balance));
        grid.add(balanceBtn, 0, 2);

        // Deposit
        Label depositLabel = new Label("Deposit Amount:");
        TextField depositField = new TextField();
        Button depositBtn = new Button("Deposit");
        depositBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(depositField.getText());
                balance += amount;
                display.setText("Deposited ₹" + amount + "\nCurrent Balance: ₹" + balance);
                depositField.clear();
            } catch (NumberFormatException ex) {
                display.setText("Enter a valid amount to deposit.");
            }
        });
        grid.add(depositLabel, 0, 3);
        grid.add(depositField, 1, 3);
        grid.add(depositBtn, 1, 4);

        // Withdraw
        Label withdrawLabel = new Label("Withdraw Amount:");
        TextField withdrawField = new TextField();
        Button withdrawBtn = new Button("Withdraw");
        withdrawBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(withdrawField.getText());
                if (amount > balance) {
                    display.setText("Insufficient balance!");
                } else {
                    balance -= amount;
                    display.setText("Withdrawn ₹" + amount + "\nCurrent Balance: ₹" + balance);
                }
                withdrawField.clear();
            } catch (NumberFormatException ex) {
                display.setText("Enter a valid amount to withdraw.");
            }
        });
        grid.add(withdrawLabel, 0, 5);
        grid.add(withdrawField, 1, 5);
        grid.add(withdrawBtn, 1, 6);

        Scene scene = new Scene(grid, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
