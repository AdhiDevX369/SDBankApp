/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositForm extends JFrame {

    private JTextField accountNumberTextField;
    private JTextField depositAmountTextField;
    private JButton depositButton;
    private JButton backButton;

    private BankingSystem bankingSystem;

    public DepositForm(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;

        setTitle("SD Bank - Deposit");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Initialize UI components
        accountNumberTextField = new JTextField();
        depositAmountTextField = new JTextField();
        depositButton = new JButton("Deposit");
        backButton = new JButton("Back");

        // Add components to the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.add(new JLabel("Account Number:"));
        formPanel.add(accountNumberTextField);
        formPanel.add(new JLabel("Deposit Amount:"));
        formPanel.add(depositAmountTextField);
        formPanel.add(depositButton);
        formPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);

        // Add action listener for the Deposit button
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberTextField.getText();
                String amountText = depositAmountTextField.getText();

                if (accountNumber.isEmpty() || amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(DepositForm.this, "Please enter account number and deposit amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount;
                try {
                    amount = Double.parseDouble(amountText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DepositForm.this, "Invalid deposit amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Account account = bankingSystem.getAccountByNumber(accountNumber);
                if (account != null) {
                    account.deposit(amount);
                    JOptionPane.showMessageDialog(DepositForm.this, "Deposit successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(DepositForm.this, "Account not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener for the Back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenuForm mainMenuForm = new MainMenuForm(bankingSystem);
                dispose();
            }
        });

        setVisible(true);
    }

    private void clearForm() {
        accountNumberTextField.setText("");
        depositAmountTextField.setText("");
    }
}

