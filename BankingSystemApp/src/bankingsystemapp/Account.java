/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemapp;

public class Account {

    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;

            } else {

            }
        } else {

        }
    }

    public void transfer(Account destinationAccount, double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                destinationAccount.deposit(amount);

            } else {

            }
        } else {

        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber
                + "\nAccount Holder: " + accountHolderName
                + "\nBalance: Rs " + balance;
    }
}
