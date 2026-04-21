import java.util.ArrayList;
import java.util.Scanner;

class InSufficientFundsException extends Exception {
    public InSufficientFundsException(String message) {
        super(message);
    }
}

class Account {
    private String AccountHoldername;
    private double Balance;
    private ArrayList<String> TransactionHistory = new ArrayList<>();

    public Account(String AccountHoldername) {
        this.AccountHoldername = AccountHoldername;
        this.Balance = 0;
    }

    public String getBalance() {
        return "Available Balance :" + Balance;
    }

    public void processTransaction(double amount) throws InSufficientFundsException {
        if (amount > Balance) {
            throw new InSufficientFundsException("Insufficient Balance");
        } else if (amount == 0) {
            throw new IllegalArgumentException("Amount must be greater than Zero");
        } else if (amount <= -1) {
            throw new IllegalArgumentException("Amount must be Positve");
        } else {
            Balance -= amount;
            System.out.println("Withdrawl Successfull");
            addTransaction(" - " + amount);

        }
    }

    public void depositBalance(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must must be greater than 0");
        } else {
            this.Balance += amount;
            addTransaction(" + " + amount);
            System.out.println("Amount: " + amount + " Deposited Successfully.");
        }
    }

    public void addTransaction(String amount) {
        if (TransactionHistory.size() == 5) {
            TransactionHistory.remove(0);
        }
        TransactionHistory.add(amount);
    }

    public void printMiniStatement() {
        if (TransactionHistory.isEmpty()) {
            System.out.println("No Recent Transactions found.");
        } else {
            System.out.println("Name: " + AccountHoldername);
            for (int i = 0; i < TransactionHistory.size(); i++) {
                System.out.println(TransactionHistory.get(i));
            }
        }
        System.out.println(getBalance());
    }
}

public class TransactionProcessor {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digital Wallet");
        System.out.println("Enter Account Holder NAme");
        String accountHolderName = sc.nextLine();
        boolean ab = true;
        if (accountHolderName.isEmpty()) {
            System.out.println("Account name is Mandatory");
            ab = false;
        } else {
            ab = true;
        }
        Account a = new Account(accountHolderName);
        while (ab) {
            try {
                System.out.println("1.Get Balance");
                System.out.println("2.Deposit");
                System.out.println("3.Withdraw");
                System.out.println("4.View Statement");
                System.out.println("5.Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println(a.getBalance());
                        break;
                    case 2:
                        System.out.println("Enter desired amount");
                        double Depamount = sc.nextDouble();
                        a.depositBalance(Depamount);
                        break;

                    case 3:
                        System.out.println("Enter desired amount");
                        double withamount = sc.nextDouble();
                        a.processTransaction(withamount);
                        break;
                    case 4:
                        a.printMiniStatement();
                        break;
                    case 5:
                        sc.close();
                        return;

                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InSufficientFundsException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error");
            } finally {
            }
            System.out.println("Thanykou");
        }

    }
}
