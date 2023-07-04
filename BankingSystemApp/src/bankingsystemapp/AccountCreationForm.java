/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountCreationForm extends JFrame {

    private JTextField accountNumberTextField;
    private JTextField accountHolderTextField;
    private JTextField initialBalanceTextField;
    private JButton createAccountButton;

    private BankingSystem bankingSystem;

    public AccountCreationForm(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;

        setTitle("SD Bank - Account Creation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Initialize UI components
        accountNumberTextField = new JTextField();
        accountHolderTextField = new JTextField();
        initialBalanceTextField = new JTextField();
        createAccountButton = new JButton("Create Account");

        // Add components to the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        formPanel.add(new JLabel("Account Number:"));
        formPanel.add(accountNumberTextField);
        formPanel.add(new JLabel("Account Holder:"));
        formPanel.add(accountHolderTextField);
        formPanel.add(new JLabel("Initial Balance:"));
        formPanel.add(initialBalanceTextField);
        formPanel.add(new JLabel()); // Empty label for spacing
        formPanel.add(createAccountButton);

        add(formPanel, BorderLayout.CENTER);

        // Add action listener for the Create Account button
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberTextField.getText();
                String accountHolder = accountHolderTextField.getText();
                String initialBalanceText = initialBalanceTextField.getText();

                // Validate input values
                if (accountNumber.isEmpty() || accountHolder.isEmpty() || initialBalanceText.isEmpty()) {
                    JOptionPane.showMessageDialog(AccountCreationForm.this, "Please enter the account number, account holder, and initial balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Parse the initial balance
                double initialBalance;
                try {
                    initialBalance = Double.parseDouble(initialBalanceText);
                    if (initialBalance < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AccountCreationForm.this, "Invalid initial balance. Please enter a non-negative number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                bankingSystem.createAccount(accountNumber, accountHolder, initialBalance);
                JOptionPane.showMessageDialog(AccountCreationForm.this, "Account created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                dispose();
                MainMenuForm mainMenu = new MainMenuForm(bankingSystem);
            }
        });

        setVisible(true);
    }

    private void clearForm() {
        accountNumberTextField.setText("");
        accountHolderTextField.setText("");
        initialBalanceTextField.setText("");
    }
}
