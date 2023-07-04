/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferForm extends JFrame {

    private JTextField sourceAccountTextField;
    private JTextField destinationAccountTextField;
    private JTextField transferAmountTextField;
    private JButton transferButton;
    private JButton backButton;

    private BankingSystem bankingSystem;

    public TransferForm(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;

        setTitle("SD Bank - Transfer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Initialize UI components
        sourceAccountTextField = new JTextField();
        destinationAccountTextField = new JTextField();
        transferAmountTextField = new JTextField();
        transferButton = new JButton("Transfer");
        backButton = new JButton("Back");

        // Add components to the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        formPanel.add(new JLabel("Source Account Number:"));
        formPanel.add(sourceAccountTextField);
        formPanel.add(new JLabel("Destination Account Number:"));
        formPanel.add(destinationAccountTextField);
        formPanel.add(new JLabel("Transfer Amount:"));
        formPanel.add(transferAmountTextField);
        formPanel.add(transferButton);
        formPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);

        // Add action listener for the Transfer button
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sourceAccountNumber = sourceAccountTextField.getText();
                String destinationAccountNumber = destinationAccountTextField.getText();
                String transferAmountText = transferAmountTextField.getText();

                // Validate input values
                if (sourceAccountNumber.isEmpty() || destinationAccountNumber.isEmpty() || transferAmountText.isEmpty()) {
                    JOptionPane.showMessageDialog(TransferForm.this, "Please enter the source account number, destination account number, and transfer amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Parse the transfer amount
                double amount;
                try {
                    amount = Double.parseDouble(transferAmountText);
                    if (amount <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TransferForm.this, "Invalid transfer amount. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Account sourceAccount = bankingSystem.getAccountByNumber(sourceAccountNumber);
                Account destinationAccount = bankingSystem.getAccountByNumber(destinationAccountNumber);

                if (sourceAccount != null && destinationAccount != null) {
                    if (sourceAccount.getBalance() >= amount) {
                        sourceAccount.transfer(destinationAccount, amount);
                        JOptionPane.showMessageDialog(TransferForm.this, "Transfer successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(TransferForm.this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(TransferForm.this, "Invalid source or destination account", "Error", JOptionPane.ERROR_MESSAGE);
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
        sourceAccountTextField.setText("");
        destinationAccountTextField.setText("");
        transferAmountTextField.setText("");
    }
}
