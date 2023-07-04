package bankingsystemapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private BankingSystem bankingSystem;

    public LoginForm(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;

        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Initialize UI components
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Add components to the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameTextField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel()); // Empty label for spacing
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);

        // Add action listener for the Login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                char[] password = passwordField.getPassword();

                if (authenticateUser(username, password)) {
                    MainMenuForm mainMenuForm = new MainMenuForm(bankingSystem);
                    dispose(); // Close the login form
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    clearForm();
                }
            }
        });

        setVisible(true);
    }

    private boolean authenticateUser(String username, char[] password) {
        // Add your authentication logic here
        // For simplicity, let's assume the username is "admin" and the password is "password"
        return username.equals("admin") && String.valueOf(password).equals("1234");
    }

    private void clearForm() {
        usernameTextField.setText("");
        passwordField.setText("");
    }
}
