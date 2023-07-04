package bankingsystemapp;

import javax.swing.*;
import java.awt.*;

public class AccountDetailsForm extends JFrame {

    private JTextArea accountDetailsTextArea;
    private JButton backButton;

    private Account currentAccount;
    private BankingSystem bankingSystem;

    public AccountDetailsForm(Account account) {
        this.currentAccount = account;
        this.bankingSystem = bankingSystem;

        setTitle("SD Bank - Account Details");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Initialize UI components
        accountDetailsTextArea = new JTextArea();
        accountDetailsTextArea.setEditable(false);
        backButton = new JButton("Back");

        // Add components to the form
        add(new JScrollPane(accountDetailsTextArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        // Display account details
        accountDetailsTextArea.setText(account.toString());

        // Add action listener for the Back button
        backButton.addActionListener(e -> {
            MainMenuForm mainMenu = new MainMenuForm(bankingSystem);
            dispose();
        });

        setVisible(true);
    }
}
