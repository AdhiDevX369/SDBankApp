package bankingsystemapp;

public class BankingSystemApp {
    private BankingSystem bankingSystem;

    public BankingSystemApp(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        MainMenuForm strt = new MainMenuForm(bankingSystem);
    }
}

