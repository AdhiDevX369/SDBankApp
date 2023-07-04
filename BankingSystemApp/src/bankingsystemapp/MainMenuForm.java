/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainMenuForm extends JFrame {
    private JButton createAccountButton;
    private JButton viewAccountButton;
    private JButton depositButton;
    private JButton withdrawalButton;
    private JButton transferButton;
    private JButton logoutButton;

    private BankingSystem bankingSystem;

    public MainMenuForm(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;

        setTitle("SD Bank - Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Initialize UI components
        createAccountButton = new JButton("Create Account");
        viewAccountButton = new JButton("View Account");
        depositButton = new JButton("Deposit");
        withdrawalButton = new JButton("Withdrawal");
        transferButton = new JButton("Transfer");
        logoutButton = new JButton("Exit");

        // Add components to the form
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));
        buttonPanel.add(createAccountButton);
        buttonPanel.add(viewAccountButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawalButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners for the buttons
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Account Creation form
                AccountCreationForm accountCreationForm = new AccountCreationForm(bankingSystem);
                dispose(); // Close the current form
            }
        });

        viewAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = JOptionPane.showInputDialog(MainMenuForm.this, "Enter Account Number:");
                Account account = bankingSystem.getAccountByNumber(accountNumber);
                if (account != null) {
                    AccountDetailsForm accountDetailsForm = new AccountDetailsForm(account);
                    dispose(); // Close the current form
                } else {
                    JOptionPane.showMessageDialog(MainMenuForm.this, "Account not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Deposit form
                DepositForm deposit = new DepositForm(bankingSystem);
                dispose();
            }
        });

        withdrawalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Withdrawal form
                WithdrawalForm withdrawalForm = new WithdrawalForm(bankingSystem);
                dispose(); // Close the current form
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Transfer form
                TransferForm transferForm = new TransferForm(bankingSystem);
                dispose(); // Close the current form
            }
        });

 logoutButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(MainMenuForm.this, "Are you sure you want to Exit?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Go back to the Login form            
            JOptionPane.showMessageDialog(MainMenuForm.this, "Happy Banking Good Bye!!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the current form
        }
    }
});


        setVisible(true);
    }
}
    