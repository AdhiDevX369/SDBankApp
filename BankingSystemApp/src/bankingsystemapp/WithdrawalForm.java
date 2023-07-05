/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawalForm extends JFrame {

    private JTextField accountNumberTextField;
    private JTextField withdrawalAmountTextField;
    private JButton withdrawalButton;
    private JButton backButton;

    private BankingSystem bankingSystem;

    public WithdrawalForm(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;

        setTitle("SD Bank - Withdrawal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Initialize UI components
        accountNumberTextField = new JTextField();
        withdrawalAmountTextField = new JTextField();
        withdrawalButton = new JButton("Withdraw");
        backButton = new JButton("Back");

        // Add components to the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.add(new JLabel("Account Number:"));
        formPanel.add(accountNumberTextField);
        formPanel.add(new JLabel("Withdrawal Amount:"));
        formPanel.add(withdrawalAmountTextField);
        formPanel.add(withdrawalButton);
        formPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);

        // Add action listener for the Withdraw button
        // ...
        withdrawalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberTextField.getText();
                String withdrawalAmountText = withdrawalAmountTextField.getText();

                // Validate input values
                if (accountNumber.isEmpty() || withdrawalAmountText.isEmpty()) {
                    JOptionPane.showMessageDialog(WithdrawalForm.this, "Please enter both the account number and withdrawal amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Parse the withdrawal amount
                double amount;
                try {
                    amount = Double.parseDouble(withdrawalAmountText);
                    if (amount <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(WithdrawalForm.this, "Invalid withdrawal amount. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Account account = bankingSystem.getAccountByNumber(accountNumber);
                if (account != null) {
                    if (account.getBalance() >= amount) {
                        account.withdraw(amount);
                        JOptionPane.showMessageDialog(WithdrawalForm.this, "Withdrawal successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(WithdrawalForm.this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(WithdrawalForm.this, "Account not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener for the Back button
        backButton.addActionListener(e
                -> {
            MainMenuForm mainMenuForm = new MainMenuForm(bankingSystem);
            dispose();
        }
        );

        setVisible(
                true);
    }

    private void clearForm() {
        accountNumberTextField.setText("");
        withdrawalAmountTextField.setText("");
    }
}
